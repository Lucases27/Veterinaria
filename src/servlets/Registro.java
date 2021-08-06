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
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		// Si esta logeado te manda al index, sino a registro.jsp
		if(session.getAttribute("logeado") == null) {
			response.sendRedirect("registro.jsp");
		}
		else response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombreApellido = request.getParameter("nombre").trim();
		String pass = request.getParameter("pass").trim();
		String email = request.getParameter("email").trim();
		boolean esVeterinario = Boolean.parseBoolean(request.getParameter("veterinario"));
		String errores = "";
		String success = "";
		String telefono = "";
		String url = "login.jsp?menuLogin=2"; // vista del registro
		email = email.isEmpty()?"":Validaciones.capitalize(email);
		
		if(Validaciones.validaNumLong(request.getParameter("telefono").trim())) {
			telefono = request.getParameter("telefono").trim();
			if(Validaciones.validaCampo(nombreApellido)) {
				if(Validaciones.validaCampo(pass)) {
					if(Validaciones.validaEmail(email)) {
						if(Usuarios.getInstance().addUser(email, pass, nombreApellido, email, telefono)) {
							success = "Registro exitoso";
							url = "login.jsp?menuLogin=1";  // vista del login
						}else errores = "El email ingresado ya se encuentra registrado.";
					}else errores = "Email incorrecto.";
				}else errores = "Contraseña incorrecta.";
			}else errores = "Nombre y apellido incorrectos.";
		}else errores = "Telefono incorrecto.";
		
		System.out.println("url:");
		request.setAttribute("errores", errores);
		request.setAttribute("success", success);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
