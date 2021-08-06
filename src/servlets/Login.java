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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int menuLogin = 0;
		if(request.getParameter("menuLogin") != null) {
			menuLogin = Integer.parseInt(request.getParameter("menuLogin"));
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp?menuLogin="+menuLogin);
		dispatcher.forward(request, response);
	
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email").trim();
		String pass = request.getParameter("pass").trim();
		String errores = "";
		String success = "";
		String url = "login.jsp?menuLogin=1"; // vista de login.
		email = email.isEmpty()?"":Validaciones.capitalize(email);
		
		if(Validaciones.validaCampo(email)) {
			if(Validaciones.validaCampo(pass)) {
				if(Usuarios.getInstance().existeUsuario(email)) {
					if(Usuarios.getInstance().validaUser(email, pass)){
						HttpSession session = request.getSession(true);
						Usuarios.getInstance().cargarSession(email, pass, session);
						success = "Bienvenido "+ session.getAttribute("nombreApellido") +".";
						url = "index.jsp";	
					}else errores = "Email o Contraseña incorrectos.";
				}else errores = "El usuario o email ingresado no existe.";				
			}else errores = "Contraseña invalida.";
		}else errores = "Usuario invadilo.";
		
		request.setAttribute("success", success);
		request.setAttribute("errores", errores);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}

}
