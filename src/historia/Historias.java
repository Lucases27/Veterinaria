package historia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import util.DBConnection;

public class Historias {
	public static Historias Historias = null;
	Historia Historia = null;
	
	private Historias() {
	}
	
	public static Historias getInstance() {
		if (Historias==null) {
			Historias = new Historias();
		}
		return Historias;
	}
	
	/**
	 * Trae todos las historias de la tabla Historia y devuelve un arraylist de Historia
	 * @return Arraylist del objeto Historia.
	 * @throws SQLException
	 */
	public ArrayList<Historia> getListaHistorias() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet historiaRs = DBConnection.sqlSelect("SELECT * FROM historias",con);
		ArrayList<Historia> listaHistorias = new ArrayList<Historia>();
		try {
			while(historiaRs.next()) {
				int idHistoria = historiaRs.getInt(1);
				int idMascota = historiaRs.getInt(2);
				String patologia = historiaRs.getString(3);
				String vacunas = historiaRs.getString(4);
				String descripcion = historiaRs.getString(5);
				Date fecha = historiaRs.getDate(6);
				Historia historia = new Historia(idHistoria,idMascota,patologia,vacunas,descripcion);
				historia.setFecha(fecha);
				listaHistorias.add(historia);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHistorias;
	}
	
	/**
	 * Trae todos las historias de la tabla Historia correspondiente a un idMascota y devuelve un arraylist de Historia
	 * @return Arraylist del objeto Historia.
	 * @throws SQLException
	 */
	public ArrayList<Historia> getListaHistorias(int idMascota) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet historiaRs = DBConnection.sqlSelect("SELECT * FROM historias WHERE IdMascota="+idMascota,con);
		ArrayList<Historia> listaHistorias = new ArrayList<Historia>();
		try {
			while(historiaRs.next()) {
				int idHistoria = historiaRs.getInt(1);
				String patologia = historiaRs.getString(3);
				String vacunas = historiaRs.getString(4);
				String descripcion = historiaRs.getString(5);
				Date fecha = historiaRs.getDate(6);
				Historia historia = new Historia(idHistoria,idMascota,patologia,vacunas,descripcion);
				historia.setFecha(fecha);
				listaHistorias.add(historia);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHistorias;
	}
	
	/**
	 * Modifica la historia de una mascota, segun el idMascota.
	 * @param idMascota
	 * @param patologia
	 * @param vacunas
	 * @param descripcion
	 */
	public void updateHistoria(int idMascota, String patologia, String vacunas, String descripcion) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		Date fecha = new Date();
		try {
			dbConnection.sqlUpdate("UPDATE historias SET Patologia='"+patologia+"', Vacunas='"+vacunas+"', "
					+ "Descripcion='"+descripcion+"', Fecha='"+fecha+"' WHERE IdMascota="+idMascota, con);
			System.out.println("Historias.updateHistoria() --> Modificado");
		} catch (SQLException e) {
			System.out.println("error al modificar historia updateHistoria()");
		}
	}
	
	/**
	 * Trae la historia de una mascota segun su idmascota.
	 * @param idMascota
	 * @return objeto Historia
	 */
	public Historia getHistoria(int idMascota) {
		Historia historia = null;
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet historiaRs = DBConnection.sqlSelect("SELECT * FROM historias WHERE IdMascota="+idMascota,con);
		try {
			while(historiaRs.next()) {
				int idHistoria = historiaRs.getInt(1);
				String patologia = historiaRs.getString(3);
				String vacunas = historiaRs.getString(4);
				String descripcion = historiaRs.getString(5);
				Date fecha = historiaRs.getDate(6);
				historia = new Historia(idHistoria,idMascota,patologia,vacunas,descripcion);
				historia.setFecha(fecha);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historia;
	}
	
	/**
	 * busca y trae una historia en particular, segun el idHistoria que se le pase
	 * @param idHistoria
	 * @return objeto Historia
	 */
	public Historia getHistoriaPorId(int idHistoria) {
		Historia historia = null;
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet historiaRs = DBConnection.sqlSelect("SELECT * FROM historias WHERE IdHistoria="+idHistoria,con);
		try {
			while(historiaRs.next()) {
				int idMascota = historiaRs.getInt(2);
				String patologia = historiaRs.getString(3);
				String vacunas = historiaRs.getString(4);
				String descripcion = historiaRs.getString(5);
				Date fecha = historiaRs.getDate(6);
				historia = new Historia(idHistoria,idMascota,patologia,vacunas,descripcion);
				historia.setFecha(fecha);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historia;
	}
	
	/**
	 * Borra la historia de una mascota segun su idHistoria
	 * @param idMascota
	 */
	public void deleteHistoria(int idHistoria) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		try {
			dbConnection.sqlUpdate("DELETE FROM historias WHERE IdHistoria="+idHistoria, con);
			System.out.println("Historias.deleteHistoria() --> Borrado");
		} catch (SQLException e) {
			System.out.println("error al borrar la historia en deleteHistoria()");
		}
	}
	
	/**
	 * Agrega una historia a la tabla historias.
	 * @param idMascota
	 * @param patologia
	 * @param vacunas
	 * @param descripcion
	 * @return
	 */
	public void addHistoria(int idMascota, String patologia, String vacunas, String descripcion) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		String query = "INSERT INTO historias (IdMascota,Patologia,Vacunas,Descripcion) VALUES (?,?,?,?)";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, idMascota);
			ps.setString(2, patologia);
			ps.setString(3, vacunas);
			ps.setString(4, descripcion);
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
}
