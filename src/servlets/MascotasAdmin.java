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
import mascotas.*;
import usuarios.Usuarios;
import util.Validaciones;

/**
 * Servlet implementation class MascotasAdmin
 */
@WebServlet("/MascotasAdmin")
public class MascotasAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MascotasAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "panel.jsp?verMascotasAdmin=1";
		String success = "";
		String errores = "";
		String nombreUsuario = "";
		int idUsuario = 0;
		int idMascota;
		ArrayList<Mascota> listaMascotas = null;
		
		if (session.getAttribute("isAdmin") == null) {
			url = "index.jsp";
		}
		
		if(request.getParameter("userId") != null) {
			idUsuario = Integer.parseInt(request.getParameter("userId"));
			nombreUsuario = Usuarios.getInstance().getUser(idUsuario).getNombreApellido();
			listaMascotas = Mascotas.getInstance().getListaMascotas(idUsuario);
			if(listaMascotas.isEmpty()) {
				errores = nombreUsuario +" no tiene mascotas.";
				request.setAttribute("idUsuarioMascota", idUsuario);
			}else success = "Mascotas de "+ nombreUsuario;
		}
		
		if(request.getParameter("eliminar") != null) {
			idMascota = Integer.parseInt(request.getParameter("eliminar"));
			idUsuario = Mascotas.getInstance().getMascota(idMascota).getIdUsuario();
			Mascotas.getInstance().deleteMascota(idMascota);
			listaMascotas = Mascotas.getInstance().getListaMascotas(idUsuario);
			success = "Mascota eliminada.";
		}
		
		if(request.getParameter("modificar") != null) {
			idMascota = Integer.parseInt(request.getParameter("modificar"));
			Mascota mascota = Mascotas.getInstance().getMascota(idMascota);
			idUsuario = mascota.getIdUsuario();
			listaMascotas = Mascotas.getInstance().getListaMascotas(idUsuario);
			request.setAttribute("mascotaModificar", mascota);
		}
		
		if(request.getParameter("agregar") != null) {
			if(request.getParameter("agregar") != "") {
				idUsuario = Integer.parseInt(request.getParameter("agregar"));
				listaMascotas = Mascotas.getInstance().getListaMascotas(idUsuario);
				request.setAttribute("userID", idUsuario);	
				request.setAttribute("idUsuarioMascota", idUsuario);
			}
		}
		
		if(request.getParameter("historial") != null) {
			if(request.getParameter("historial") != "") {
				idMascota = Integer.parseInt(request.getParameter("historial"));
				idUsuario = Integer.parseInt(request.getParameter("idUser"));
				String nombreMascota = Mascotas.getInstance().getMascota(idMascota).getNombre();
				ArrayList<Historia> historia = Historias.getInstance().getListaHistorias(idMascota);
				request.setAttribute("idUsuarioHistorial", idUsuario);
				request.setAttribute("idMascotaHistorial", idMascota);
				request.setAttribute("nombreMascota", nombreMascota);
				request.setAttribute("historial", historia);
				url = "panel.jsp?verHistorialAdmin=1";
			}
		}
		
		
		
		request.setAttribute("nombreUsuario", nombreUsuario);
		request.setAttribute("success", success);
		request.setAttribute("errores", errores);
		request.setAttribute("listaMascotas", listaMascotas);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "panel.jsp?verMascotasAdmin=1";
		String success = "";
		String errores = "";
		String nombreUsuario = "";
		String nombre = "";
		String tipo = "";
		int edad;
		double peso;
		
		
		int idUsuario = 0;
		int idMascota = 0;
		ArrayList<Mascota> listaMascotas = null;
		
		if(request.getParameter("agregarMascota") != null) {
			idUsuario = Integer.parseInt(request.getParameter("agregarMascota"));
			request.setAttribute("idUsuarioMascota", idUsuario);
			url += "&agregar="+idUsuario;
			listaMascotas = Mascotas.getInstance().getListaMascotas(idUsuario);
			request.setAttribute("userID", idUsuario);
			nombre = request.getParameter("nombre").trim();
			tipo = request.getParameter("tipo").trim();
			if(Validaciones.validaCampo(nombre)) {
				if(Validaciones.validaCampo(tipo)) {
					if(Validaciones.validaNumInt(request.getParameter("edad").trim())) {
						edad = Integer.parseInt(request.getParameter("edad").trim());
						if(Validaciones.validaNumDouble(request.getParameter("peso").trim())) {
							peso = Validaciones.stringToDouble(request.getParameter("peso").trim());
							Mascotas.getInstance().addMascota(idUsuario, nombre, tipo, edad, peso);
							listaMascotas = Mascotas.getInstance().getListaMascotas(idUsuario);
							success = "Mascota agregada!";							
						}else errores = "Peso inválido";
					}else errores = "Edad inválida";
				}else errores = "Tipo inválido";
			}else errores = "Nombre inválido";
		}
		
		if(request.getParameter("modificarMascota") != null) {
			if(request.getParameter("modificarMascota") != "" ) {
				idMascota = Integer.parseInt(request.getParameter("modificarMascota"));
				idUsuario = Mascotas.getInstance().getMascota(idMascota).getIdUsuario();
				listaMascotas = Mascotas.getInstance().getListaMascotas(idUsuario);
				url +="&modificar="+idMascota;
				request.setAttribute("mascotaModificar", Mascotas.getInstance().getMascota(idMascota));
				nombre = request.getParameter("nombre").trim();
				tipo = request.getParameter("tipo").trim();
				if(Validaciones.validaCampo(nombre)) {
					if(Validaciones.validaCampo(tipo)) {
						if(Validaciones.validaNumInt(request.getParameter("edad").trim())) {
							edad = Integer.parseInt(request.getParameter("edad").trim());
							if(Validaciones.validaNumDouble(request.getParameter("peso").trim())) {
								peso = Validaciones.stringToDouble(request.getParameter("peso").trim());
								Mascotas.getInstance().updateMascota(idMascota, nombre, tipo, edad, peso);
								listaMascotas = Mascotas.getInstance().getListaMascotas(idUsuario);
								request.setAttribute("mascotaModificar", Mascotas.getInstance().getMascota(idMascota));
								success = "Mascota modificada!";								
							}else errores = "Peso inválido";
						}else errores = "Edad inválida";
					}else errores = "Tipo inválido";
				}else errores = "Nombre inválido";
			}
		}
		
		request.setAttribute("nombreUsuario", nombreUsuario);
		request.setAttribute("success", success);
		request.setAttribute("errores", errores);
		request.setAttribute("listaMascotas", listaMascotas);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
