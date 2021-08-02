package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mascotas.Mascotas;
import usuarios.Usuario;
import usuarios.Usuarios;
import util.Validaciones;

/**
 * Servlet implementation class MascotasUsuario
 */
@WebServlet("/UsuariosAdmin")
public class UsuariosAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "panel.jsp?verUsuariosAdmin=1";
		String success = "";
		String errores = "";
		int idUsuario;
		
		if (session.getAttribute("logeado") == null) {
			url = "index.jsp";
			response.sendRedirect(url);
			return;
		}
		
		if(request.getParameter("agregarAdmin") != null) {
			idUsuario = Integer.parseInt(request.getParameter("agregarAdmin"));
			Usuarios.getInstance().setAdmin(idUsuario);
			success = "Admin agregado exitosamente";
		}
		
		if(request.getParameter("quitarAdmin") != null) {
			idUsuario = Integer.parseInt(request.getParameter("quitarAdmin"));
			if(idUsuario == (int)session.getAttribute("id")) {
				errores = "Error al quitar admin.";
			}else {
				Usuarios.getInstance().removeAdmin(idUsuario);
				success = "Admin quitado exitosamente";				
			}
		}
		
		request.setAttribute("errores", errores);
		request.setAttribute("success", success);
		request.setAttribute("listaUsuarios", Usuarios.getInstance().getUserList());
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario;
		String url = "panel.jsp?verUsuariosAdmin=1";
		ArrayList<Usuario> listaUsuarios = Usuarios.getInstance().getUserList();
		String success = "";
		String errores = "";
		
		if(request.getParameter("nombreUsuario") != null) {
			nombreUsuario = request.getParameter("nombreUsuario").trim();
			if(Validaciones.validaCampo(nombreUsuario)) {
				listaUsuarios = Usuarios.getInstance().buscarUsuariosPorNombre(nombreUsuario);
				if(listaUsuarios.isEmpty()) {
					errores = "No se han encontraro coincidencias.";
				}
			}else errores = "Campo inválido.";
		}
		
		request.setAttribute("errores", errores);
		request.setAttribute("listaUsuarios", listaUsuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
