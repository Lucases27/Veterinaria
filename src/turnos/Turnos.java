package turnos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import mascotas.Mascota;
import mascotas.Mascotas;
import util.DBConnection;

public class Turnos {
	public static Turnos Turnos = null;
	
	
	private Turnos() {
		
	}
	
	public static Turnos getInstance() {
		if (Turnos==null) {
			Turnos = new Turnos();
		}
		return Turnos;
	}
	
	/**
	 * Devuelve un ArrayList de todos los turnos de la tabla TURNOS en la DB.
	 * @return ArrayList de Turno.
	 */
	public ArrayList<Turno> getListaTurnos() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet turnosRS = DBConnection.sqlSelect("SELECT * FROM TURNOS ORDER BY FECHA DESC",con);
		ArrayList<Turno> listaTurnos = new ArrayList<Turno>();
		try {
			while(turnosRS.next()) {
				int idTurno = turnosRS.getInt(1);
				int idUsuario = turnosRS.getInt(2);
				String nombreApellido = turnosRS.getString(3);
				String nombreMascota = turnosRS.getString(4);
				String tipoMascota = turnosRS.getString(5);
				String telefono = turnosRS.getString(6);
				Date fecha = turnosRS.getDate(9);
				boolean vigente = turnosRS.getBoolean(11);
				// obtenemos un java.sql.Timestamp y lo guardamos en un java.util.Date
				Date hora = new Date(turnosRS.getTimestamp(9).getTime());
				Turno turno = new Turno(idTurno,idUsuario,nombreApellido,nombreMascota,tipoMascota,telefono,fecha,hora);
				turno.setVigente(vigente);
				listaTurnos.add(turno);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaTurnos;
	}
	
	/**
	 * Devuelve un ArrayList de todos los turnos de un unico usuario,de la tabla TURNOS en la DB.
	 * @param idUsuario el usuario del que queremos traer los turnos.
	 * @return ArrayList de Turno.
	 */
	public ArrayList<Turno> getListaTurnos(int idUsuario) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet turnosRS = DBConnection.sqlSelect("SELECT * FROM TURNOS WHERE IDUSUARIO="+idUsuario+" ORDER BY FECHA DESC",con);
		ArrayList<Turno> listaTurnos = new ArrayList<Turno>();
		try {
			while(turnosRS.next()) {
				int idTurno = turnosRS.getInt(1);
				String nombreApellido = turnosRS.getString(3);
				String nombreMascota = turnosRS.getString(4);
				String tipoMascota = turnosRS.getString(5);
				String telefono = turnosRS.getString(6);
				Date fecha = turnosRS.getDate(9);
				boolean vigente = turnosRS.getBoolean(11);
				// obtenemos un java.sql.Timestamp y lo guardamos en un java.util.Date
				Date hora = new Date(turnosRS.getTimestamp(9).getTime());
				Turno turno = new Turno(idTurno,idUsuario,nombreApellido,nombreMascota,tipoMascota,telefono,fecha,hora);
				turno.setVigente(vigente);
				listaTurnos.add(turno);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaTurnos;
	}
	
	/**
	 * Se le pasa un idUsuario y busca si tiene turnos vigentes, si los tiene devuelve True, sino False.
	 * @param idUsuario
	 * @return boolean.
	 */
	public boolean tieneTurno(int idUsuario) {
		DBConnection DBConnection = new DBConnection();
		String query  = "SELECT * FROM TURNOS WHERE IDUSUARIO="+idUsuario+" AND VIGENTE = 1";
		return DBConnection.sqlExist(query);
	}
	
	/**
	 * Devuelve el ultimo turno (por fecha y hora) cargado en la tabla Turnos
	 * @return Object Turno
	 */
	public Turno getUltimoTurno() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet turnosRS = DBConnection.sqlSelect("SELECT * FROM TURNOS WHERE Fecha = (SELECT MAX(Fecha) FROM TURNOS)",con);
		Turno turno = null;
		try {
			while(turnosRS.next()) {
				int idTurno = turnosRS.getInt(1);
				int idUsuario = turnosRS.getInt(2);
				String nombreApellido = turnosRS.getString(3);
				String nombreMascota = turnosRS.getString(4);
				String tipoMascota = turnosRS.getString(5);
				String telefono = turnosRS.getString(6);
				Date fecha = turnosRS.getDate(9);
				boolean vigente = turnosRS.getBoolean(11);
				// obtenemos un java.sql.Timestamp y lo guardamos en un java.util.Date
				// el getDate solo trae la fecha sin la hora.
				Date hora = new Date(turnosRS.getTimestamp(9).getTime());
				System.out.println("ultimo turno metodo:");
				System.out.println("fecha: "+fecha);
				System.out.println("hora: "+hora);
				turno = new Turno(idTurno,idUsuario,nombreApellido,nombreMascota,tipoMascota,telefono,fecha,hora);
				turno.setVigente(vigente);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return turno;
	}
	
	/**
	 * Agrega un nuevo turno a la tabla Turnos.
	 * @param Turno turno.
	 */
	public void addTurno(Turno turno) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		String query = "INSERT INTO TURNOS (IdUsuario, NombreApellido, Telefono, Fecha) VALUES (?,?,?,?)";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, turno.getIdUsuario());
			ps.setString(2, turno.getNombreApellido());
			ps.setString(3, turno.getTelefono());
			// Ver manual tecnico.
			Timestamp timestamp = new Timestamp(turno.getFecha().getTime());
			ps.setTimestamp(4, timestamp);
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
		}
	}
	
	/**
	 * Borra un registro en la tabla turnos, correspondiente al idTurno que se le pasa.
	 * @param idTurno
	 */
	public void deleteTurno(int idTurno) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		try {
			dbConnection.sqlUpdate("DELETE FROM TURNOS WHERE IdTurno="+idTurno, con);
		} catch (SQLException e) {
		}
	}
	
	/**
	 * Cancela un turno dado.
	 * @param idTurno
	 */
	public void cancelarTurno(int idTurno) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		try {
			dbConnection.sqlUpdate("UPDATE TURNOS SET VIGENTE = 0 WHERE IdTurno="+idTurno, con);
		} catch (SQLException e) {
		}
	}
	
	
}
