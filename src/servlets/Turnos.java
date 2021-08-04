package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import turnos.Turno;
import usuarios.Usuarios;

/**
 * Servlet implementation class Turnos
 */
@WebServlet("/Turnos")
public class Turnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Turnos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "panel.jsp?verTurnos=1", errores = "", success = "";
		int idTurno = 0, idUsuario = 0;
		
		if(session == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		else {
			//refrescamos los datos de session
			String user = (String) session.getAttribute("user");
			Usuarios.getInstance().cargarSession(user, session);
		}
		
		if(request.getParameter("borrarTurno") != null) {
			idTurno = Integer.parseInt(request.getParameter("borrarTurno"));
			turnos.Turnos.getInstance().deleteTurno(idTurno);
			success = "Turno eliminado.";
		}
		idUsuario = (int) session.getAttribute("id");
		ArrayList<Turno> listaTurnos = new ArrayList<Turno>();
		
		if((boolean) session.getAttribute("isAdmin")) {
			if(request.getParameter("cancelarTurno") != null) {
				idTurno = Integer.parseInt(request.getParameter("cancelarTurno"));
				turnos.Turnos.getInstance().cancelarTurno(idTurno);
				success = "Turno cancelado.";
			}
			
			for (Turno turno : turnos.Turnos.getInstance().getListaTurnos()) {
				turno.setDiaYHora(diaConHoraString(turno.getHora()));
				listaTurnos.add(turno);
			}
		}else {
			for (Turno turno : turnos.Turnos.getInstance().getListaTurnos(idUsuario)) {
				turno.setDiaYHora(diaConHoraString(turno.getHora()));
				listaTurnos.add(turno);
			}			
		}
		
		request.setAttribute("listaTurnos", listaTurnos);
		request.setAttribute("success", success);
		request.setAttribute("errores", errores);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "panel.jsp?verTurnos=1";
				
		int idUsuario = (int) session.getAttribute("id");
		String nombreApellido = (String) session.getAttribute("nombreApellido");
		String telefono = (String) session.getAttribute("telefono");
		String success = "", errores = "";
		Turno turno = null;
		Turno nuevoTurno = null;
		turno = turnos.Turnos.getInstance().getUltimoTurno();
		Date fechaAnterior = new Date();
		Date nuevaFecha = new Date();

		if(turno != null) {
			//compareTo para ver si la fecha de la db es menor que el dia de hoy. 
			if(turno.getHora().compareTo(new Date()) >= 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String fechaConHora = turno.getFecha().toString()+" ";
				fechaConHora += turno.getHora().getHours()==9?"0":"";
				fechaConHora += turno.getHora().getHours()+":00:00";
				try {
					fechaAnterior = sdf.parse(fechaConHora);
					nuevaFecha = fechaAnterior;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		int diaDeLaSemana = fechaAnterior.getDay();
		int horaTurnoAnterior = fechaAnterior.getHours();
		nuevaFecha.setMinutes(0);
		nuevaFecha.setSeconds(0);
		
		switch (horaTurnoAnterior) {
		case 9: nuevaFecha.setHours(horaTurnoAnterior+1);
			break;
		case 10: nuevaFecha.setHours(horaTurnoAnterior+1);
			break;
		case 11: nuevaFecha.setHours(horaTurnoAnterior+1);
			break;
		case 12: nuevaFecha.setHours(horaTurnoAnterior+5);
			break;
		case 17: nuevaFecha.setHours(horaTurnoAnterior+1);
			break;
		case 18: nuevaFecha.setHours(horaTurnoAnterior-9);
				 if(diaDeLaSemana < 5) {
					 nuevaFecha.setDate(nuevaFecha.getDate()+1);
				 }else {
					 nuevaFecha.setDate(nuevaFecha.getDate()+3);
				 }
			break;
			//Si no hay turno previo, la hora esta vacia y viene por el default
		default: nuevaFecha.setHours(9);
				 if(diaDeLaSemana < 5) {
					 nuevaFecha.setDate(nuevaFecha.getDate()+1);
				 }else {
					 nuevaFecha.setDate(nuevaFecha.getDate()+3);
				 }
		}
		
		nuevoTurno = new Turno(idUsuario,nombreApellido,telefono,nuevaFecha);
		if (turnos.Turnos.getInstance().tieneTurno(idUsuario)){
			errores = "Usted ya tiene un turno vigente.";
		}else {
			turnos.Turnos.getInstance().addTurno(nuevoTurno);
			success = "Tu proximo turno es el "+diaConHoraConNumeroString(nuevaFecha);
		}
		
		ArrayList<Turno> listaTurnos = new ArrayList<Turno>();
		
		for (Turno turn : turnos.Turnos.getInstance().getListaTurnos(idUsuario)) {
			//le pasamos getHora y no getFecha, porque Hora tiene la fecha completa. Ver turnos.Turnos linea 108
			turn.setDiaYHora(diaConHoraString(turn.getHora()));
			listaTurnos.add(turn);
		}
		
		request.setAttribute("success", success);
		request.setAttribute("errores", errores);
		request.setAttribute("listaTurnos", listaTurnos);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Recibe un objeto del tipo Date, que contenga fecha y hora, y devuelve un String 
	 * con formato Dia + Hora. ej: Lunes 17:00hs
	 * @param turno
	 * @return
	 */
	private String diaConHoraString(Date fecha) {
		int dia = fecha.getDay();
		String diaYHora = ""; 
		switch (dia) {
		case 1:diaYHora += "Lunes ";
			break;
		case 2:diaYHora += "Martes ";
			break;
		case 3:diaYHora += "Miercoles ";
			break;
		case 4:diaYHora += "Jueves ";
			break;
		case 5:diaYHora += "Viernes ";
			break;
		}
		int hora = fecha.getHours();
		String horaStr = hora==9?"0"+hora+":00hs":hora+":00hs";
		diaYHora += horaStr;
		
		return diaYHora;
	}
	
	/**
	 * Este metodo devuelve un String con la fecha del turno a mostrar cuando el usuario saca un turno.
	 * @param fecha
	 * @return
	 */
	private String diaConHoraConNumeroString(Date fecha) {
		int dia = fecha.getDay();
		String diaYHora = ""; 
		switch (dia) {
		case 1:diaYHora += "Lunes ";
			break;
		case 2:diaYHora += "Martes ";
			break;
		case 3:diaYHora += "Miercoles ";
			break;
		case 4:diaYHora += "Jueves ";
			break;
		case 5:diaYHora += "Viernes ";
			break;
		}
		int hora = fecha.getHours();
		diaYHora += fecha.getDate()+"/"+(fecha.getMonth()+1)+" a las ";
		String horaStr = hora==9?"0"+hora+":00hs":hora+":00hs";
		diaYHora += horaStr;
		
		return diaYHora;
	}
}
