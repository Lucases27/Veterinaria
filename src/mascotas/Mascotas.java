package mascotas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;

public class Mascotas {
	public static Mascotas Mascotas = null;

	
	private Mascotas() {	
	}
	
	public static Mascotas getInstance() {
		if (Mascotas==null) {
			Mascotas = new Mascotas();
		}
		return Mascotas;
	}
	
	/**
	 * Trae todos las mascotas de la tabla Mascotas y devuelve un arraylist de Mascota
	 * @return Arraylist del objeto Mascota.
	 * @throws SQLException
	 */
	public ArrayList<Mascota> getListaMascotas() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet mascotaRs = DBConnection.sqlSelect("SELECT * FROM MASCOTAS",con);
		ArrayList<Mascota> listaMascotas = new ArrayList<Mascota>();
		try {
			while(mascotaRs.next()) {
				int idMascota = mascotaRs.getInt(1);
				int idUsuario = mascotaRs.getInt(2);
				String nombre = mascotaRs.getString(3);
				String tipo = mascotaRs.getString(4);
				int edad = mascotaRs.getInt(5);
				double peso = mascotaRs.getDouble(6);
				Mascota mascota = new Mascota(idMascota,idUsuario,nombre,tipo,edad,peso);
				listaMascotas.add(mascota);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMascotas;
	}
	
	
	
	/**
	 * Trae todos las mascotas de la tabla Mascotas correspondientes a un ID y devuelve un arraylist de Mascota.
	 * @return Arraylist del objeto Mascota.
	 * @throws SQLException
	 */
	public ArrayList<Mascota> getListaMascotas(int idUsuario) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet mascotaRs = DBConnection.sqlSelect("SELECT * FROM MASCOTAS WHERE IDUSUARIO="+idUsuario,con);
		ArrayList<Mascota> listaMascotas = new ArrayList<Mascota>();
		try {
			while(mascotaRs.next()) {
				int idMascota = mascotaRs.getInt(1);
				String nombre = mascotaRs.getString(3);
				String tipo = mascotaRs.getString(4);
				int edad = mascotaRs.getInt(5);
				double peso = mascotaRs.getDouble(6);
				Mascota mascota = new Mascota(idMascota,idUsuario,nombre,tipo,edad,peso);
				mascota.setIdUsuario(idUsuario);
				listaMascotas.add(mascota);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMascotas;
	}
	
	/**
	 * Trae una mascota segun su ID
	 * @param idMascota
	 * @return objeto Mascota
	 */
	public Mascota getMascota(int idMascota) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		Mascota mascota = null;
		try {
			ResultSet mascotasRs = dbConnection.sqlSelect("SELECT * FROM MASCOTAS WHERE IdMascota="+idMascota, con);
			//System.out.println(idMascota);
			while(mascotasRs.next()) {
				int idUsuario = mascotasRs.getInt(2);
				String nombre = mascotasRs.getString(3);
				String tipo = mascotasRs.getString(4);
				int edad = mascotasRs.getInt(5);
				double peso = mascotasRs.getDouble(6);
				mascota = new Mascota(idMascota, idUsuario, nombre, tipo, edad, peso);
				//System.out.println(mascota.toString());
			}
		} catch (SQLException e) {
			System.out.println("error al traer mascota getMascota()");
		}
		return mascota;
	}
	
	/**
	 * Agrega una mascota con todos sus datos. No devuelve nada.
	 * @param idUsuario
	 * @param nombre
	 * @param tipo
	 * @param edad
	 * @param peso
	 */
	public void addMascota(int idUsuario, String nombre, String tipo, int edad, double peso) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		try {
			dbConnection.sqlUpdate("INSERT INTO MASCOTAS (IdUsuario, Nombre, Tipo, Edad, Peso)"
					+ "VALUES("+idUsuario+",'"+nombre+"','"+tipo+"',"+edad+","+peso+")", con);
			System.out.println("Mascotas.addMascota() --> Agregado");
		} catch (SQLException e) {
			System.out.println("error al agregar mascota addMascota()");
		}
	}
	
	/**
	 * Modifica todos los datos de una mascota, es necesario el idMascota.
	 * @param idMascota
	 * @param nombre
	 * @param tipo
	 * @param edad
	 * @param peso
	 */
	public void updateMascota(int idMascota, String nombre, String tipo, int edad, double peso) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		try {
			dbConnection.sqlUpdate("UPDATE MASCOTAS SET Nombre='"+nombre+"', Tipo='"+tipo+"', "
					+ "Edad="+edad+", peso="+peso+" WHERE IdMascota="+idMascota, con);
			System.out.println("Mascotas.updateMascota() --> Modificado");
		} catch (SQLException e) {
			System.out.println("error al modificar mascota updateMascota()");
		}
	}
	
	/**
	 * Borra una mascota de la tabla mascotas por su idMascota
	 * @param idMascota
	 */
	public void deleteMascota(int idMascota) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		try {
			dbConnection.sqlUpdate("DELETE FROM MASCOTAS WHERE IdMascota="+idMascota, con);
			System.out.println("Mascotas.deleteMascota() --> Borrado");
		} catch (SQLException e) {
			System.out.println("error al borrar mascota en deleteMascota()");
		}
	}
	
	/**
	 * Busca una mascota mediante su idMascota.
	 * @param id int de la mascota a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeMascota(int idMascota) {
		DBConnection dBConnection = new DBConnection();
		Connection con = dBConnection.getConexion();
		boolean ok = dBConnection.sqlExist("Select IdMascota from Mascotas where IdMascota="+idMascota,con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
}