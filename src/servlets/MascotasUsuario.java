package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mascotas.Mascotas;
import usuarios.Usuarios;
import util.Validaciones;

/**
 * Servlet implementation class MascotasUsuario
 */
@WebServlet("/MascotasUsuario")
public class MascotasUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MascotasUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "panel.jsp?verMascotas=1";
		String success = "";
		String errores = "";
		int idUsuario = 0;
		
		if (session.getAttribute("logeado") == null) {
			url = "index.jsp";
			response.sendRedirect(url);
			return;
		}
		
		idUsuario = (int) session.getAttribute("id");
		
		if(request.getParameter("borrarMascota") != null) {
			int idMascota = Integer.parseInt(request.getParameter("borrarMascota"));
			Mascotas.getInstance().deleteMascota(idMascota);
			success = "Mascota borrada exitosamente";
		}
		
		
		request.setAttribute("success", success);
		request.setAttribute("listaMascotas", Mascotas.getInstance().getListaMascotas(idUsuario));
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		int idUsuario = (int) session.getAttribute("id");
		String nombre = request.getParameter("nombre").trim();
		String tipo = request.getParameter("tipo").trim();
		String edad = request.getParameter("edad").trim();
		String peso = request.getParameter("peso").trim();
		String errores = "";
		String success = "";
		String url = "panel.jsp?verMascotas=1";
		
		if(Validaciones.validaCampo(nombre)) {
			if(Validaciones.validaCampo(tipo)) {
				if(Validaciones.validaCaracteres(edad) && Validaciones.validaNumInt(edad)) {
					if(Validaciones.validaCaracteres(peso) && Validaciones.validaNumDouble(peso)) {
						double pesoMascota = Validaciones.stringToDouble(peso);
						int edadMascota = Integer.parseInt(edad);
						Mascotas.getInstance().addMascota(idUsuario, nombre, tipo, edadMascota, pesoMascota);
						success = "¡Mascota agregada con exito!";
					}else errores = "Peso inválido.";					
				}else errores = "Edad inválida.";
			}else errores = "Tipo inválido.";
		}else errores = "Nombre inválido.";
		
		request.setAttribute("listaMascotas", Mascotas.getInstance().getListaMascotas(idUsuario));
		request.setAttribute("success", success);
		request.setAttribute("errores", errores);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
