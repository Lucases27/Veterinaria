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

import historia.Historia;
import historia.Historias;
import mascotas.Mascota;
import mascotas.Mascotas;
import util.Validaciones;

/**
 * Servlet implementation class Historial
 */
@WebServlet("/Historial")
public class Historial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Historial() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "panel.jsp?verHistorialAdmin=1";
		String success = "";
		String errores = "";
		String nombreUsuario = "";
		int idUsuario = 0;
		int idMascota = 0;
		int idHistoria = 0;
		ArrayList<Historia> listaHistorias = null;

		if (session.getAttribute("logeado") == null) {
			url = "index.jsp";
			response.sendRedirect(url);
			return;
		}
		
		if(request.getParameter("getHistoria") != null) {
			idMascota = Integer.parseInt(request.getParameter("getHistoria"));
			listaHistorias = Historias.getInstance().getListaHistorias(idMascota);
			String nombreMascota = Mascotas.getInstance().getMascota(idMascota).getNombre();
			request.setAttribute("idMascotaHistorial", idMascota);
			request.setAttribute("nombreMascota", nombreMascota);
			request.setAttribute("historial", listaHistorias);
			url = "panel.jsp?verHistorial=1";
		}
		
		if(request.getParameter("agregar") != null) {
			if(request.getParameter("agregar") != "") {
				idMascota = Integer.parseInt(request.getParameter("agregar"));
				listaHistorias = Historias.getInstance().getListaHistorias(idMascota);
				String nombreMascota = Mascotas.getInstance().getMascota(idMascota).getNombre();
				idUsuario = Mascotas.getInstance().getMascota(idMascota).getIdUsuario();
				request.setAttribute("idUsuarioHistorial", idUsuario);
				request.setAttribute("idMascotaHistorial", idMascota);
				request.setAttribute("nombreMascota", nombreMascota);
				request.setAttribute("historial", listaHistorias);
				url += "&agregar="+idMascota;
			}
		}
		
		if(request.getParameter("eliminar") != null) {
			idHistoria = Integer.parseInt(request.getParameter("eliminar"));
			idMascota = Historias.getInstance().getHistoriaPorId(idHistoria).getIdMascota();
			Historias.getInstance().deleteHistoria(idHistoria);
			listaHistorias = Historias.getInstance().getListaHistorias(idMascota);
			String nombreMascota = Mascotas.getInstance().getMascota(idMascota).getNombre();
			idUsuario = Mascotas.getInstance().getMascota(idMascota).getIdUsuario();
			request.setAttribute("idUsuarioHistorial", idUsuario);
			request.setAttribute("idMascotaHistorial", idMascota);
			request.setAttribute("nombreMascota", nombreMascota);
			request.setAttribute("historial", listaHistorias);
			url = "panel.jsp?verHistorialAdmin=1";
		}
		
		request.setAttribute("success", success);
		request.setAttribute("errores", errores);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "panel.jsp?verHistorialAdmin=1";
		String success = "";
		String errores = "";
		String patologia = "";
		String vacunas = "";
		String descripcion = "";
		String nombreMascota = "";
		int idUsuario = 0;
		int idMascota = 0;
		ArrayList<Historia> listaHistorias = null;
		
		if(request.getParameter("agregarHistoria") != null) {
			idMascota = Integer.parseInt(request.getParameter("agregarHistoria"));
			url += "&agregar="+idMascota;
			nombreMascota = Mascotas.getInstance().getMascota(idMascota).getNombre();
			listaHistorias = Historias.getInstance().getListaHistorias(idMascota);
			patologia = request.getParameter("patologia").trim();
			vacunas = request.getParameter("vacunas").trim();
			descripcion = request.getParameter("descripcion").trim();
			idUsuario = Mascotas.getInstance().getMascota(idMascota).getIdUsuario();
			if(Validaciones.validaCampo(patologia)) {
				if(Validaciones.validaCampo(vacunas)) {
					if(Validaciones.validaCampo(descripcion)) {
						Historias.getInstance().addHistoria(idMascota, patologia, vacunas, descripcion);
						listaHistorias = Historias.getInstance().getListaHistorias(idMascota);
						success = "Historia agregada!";							
					}else errores = "Descripción inválido";
				}else errores = "Campo vacunas inválida";
			}else errores = "Patología inválida";
		}
		
		request.setAttribute("idUsuarioHistorial", idUsuario);
		request.setAttribute("historial", listaHistorias);
		request.setAttribute("nombreMascota", nombreMascota);
		request.setAttribute("idMascotaHistorial", idMascota);
		request.setAttribute("nombreMascota", nombreMascota);
		request.setAttribute("historial", listaHistorias);
		request.setAttribute("success", success);
		request.setAttribute("errores", errores);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}

}
