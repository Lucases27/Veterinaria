package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import usuarios.Usuarios;
import util.Validaciones;

/**
 * Servlet implementation class ModificarDatos
 */
@WebServlet("/ModificarDatos")
public class ModificarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarDatos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String url = "panel.jsp?modificarDatos=1";
		
		if (session.getAttribute("logeado") == null) {
			url = "index.jsp";
		}
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String nombreApellido = (String) session.getAttribute("nombreApellido");
		int idUsuario = (int) session.getAttribute("id");
		String pass = request.getParameter("pass").trim();
		String pass1 = request.getParameter("pass1").trim();
		String pass2 = request.getParameter("pass2").trim();
		String telefono = request.getParameter("telefono").trim();
		String email = (String) session.getAttribute("email");
		String emailNuevo = Validaciones.capitalize(request.getParameter("email").trim());
		String errores = "";
		String success = "";
		String url = "panel.jsp?modificarDatos=1";
		
		if(Validaciones.validaCampo(pass) && Validaciones.validaCampo(pass1) && Validaciones.validaCampo(pass2)) {
			if(Validaciones.validaCampo(emailNuevo) && Validaciones.validaEmail(emailNuevo)) {
				if(!Usuarios.getInstance().updateEmail(emailNuevo,idUsuario)) {
					if(Usuarios.getInstance().validaUser(email, pass)) {
						if(pass1.equals(pass2)) {
							Usuarios.getInstance().updateUser(emailNuevo, nombreApellido, pass1, email, telefono);
							session.setAttribute("pass", pass1);
							session.setAttribute("email", emailNuevo);
							session.setAttribute("telefono", telefono);
							success = "Modificación exitosa.";													
						}else errores = "Las contraseñas no coinciden.";
					}else errores = "Contraseña incorrecta.";
				}else errores = "El email ingresado ya se encuentra en uso.";
			}else errores = "El email ingresado es inválido.";
		}else errores = "La contraseña ingresada es inválida.";
		
		request.setAttribute("success", success);
		request.setAttribute("errores", errores);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
