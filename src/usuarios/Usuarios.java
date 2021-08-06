package usuarios;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import util.*;

public class Usuarios {
	
	public static Usuarios Usuarios = null;
	Usuario usuario = null;
	
	private Usuarios() {
	}
	
	public static Usuarios getInstance() {
		if (Usuarios==null) {
			Usuarios = new Usuarios();
		}
		return Usuarios;
	}
	
	/**
	 * Agrega un nuevo usuario a la base de datos. Previamente comprueba que no exista el usuario.
	 * @param user
	 * @param pass
	 * @param nombreApellido
	 * @param email
	 * @param telefono
	 * @return true si el usuario fue agregado, false en caso de que ya exista.
	 * @throws SQLException
	 */
	public boolean addUser(String user, String pass, String nombreApellido, String email,String telefono) {
		if(existeUsuario(user)) {
			return false;
		}
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("INSERT INTO usuarios (Usuario,Password,NombreApellido,Email,Telefono,isAdmin)"
								+ " VALUES ('"+user+"','"+pass+"','"+nombreApellido+"','"+email+"','"+telefono+"',0)",con);
			System.out.println("se agrego supuestamente");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("> " +user+" - "+ pass+ " - "+ nombreApellido+" - "+email+" - "+telefono+" <" );
		}
		return true;
	}
	
	/**
	 * Modifica los datos del usuario, no devuelve nada.
	 * @param user
	 * @param nombreApellido
	 * @param pass
	 * @param email
	 * @param telefono
	 * @throws SQLException
	 */
	public void updateUser(String user, String nombreApellido, String pass, String email, String telefono) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("UPDATE usuarios SET NombreApellido='"+nombreApellido+"', Password='"+pass+"', Email ='"+email+"', Telefono ='"+telefono+"' WHERE Usuario='"+user+"'",con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Borra un usuario segun su nombre de usuario. no devuelve nada
	 * @param user
	 * @throws SQLException
	 */
	public void deleteUser(String user) throws SQLException {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		DBConnection.sqlUpdate("DELETE FROM usuarios WHERE Usuario='"+user+"'",con);
		con.close();
	}
	
	/**
	 * Trae todos los usuarios de la tabla usuarios y devuelve un arraylist de Usuario
	 * @return Arraylist del objeto Usuario.
	 * @throws SQLException
	 */
	public ArrayList<Usuario> getUserList() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet users = DBConnection.sqlSelect("SELECT * FROM usuarios",con);
		ArrayList<Usuario> userList = new ArrayList<Usuario>();
		try {
			while(users.next()) {
				int id = users.getInt(1);
				String user = users.getString(2);
				String pass = users.getString(3);
				String nombreApellido = users.getString(4);
				String email = users.getString(5);
				String telefono = users.getString(6);
				boolean isAdmin = users.getBoolean(7);
				
				Usuario usuario = new Usuario(user,pass,nombreApellido,email,telefono);
				usuario.setIdUsuario(id);
				usuario.setAdmin(isAdmin);
				userList.add(usuario);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	
	/**
	 * Se le pasa un argumento nombre/email o parte del nombre/email y trae un arraylist con todas las coincidencias 
	 * que hayan en la tabla Usuarios.
	 * @param nombre
	 * @return ArrayList de Usuarios que matcheen con el argumento.
	 */
	public ArrayList<Usuario> buscarUsuariosPorNombre(String nombre) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		//ResultSet users = DBConnection.sqlSelect("SELECT * FROM USUARIOS WHERE="+columna+" LIKE '"+nombre+"%'",con);
		
		ArrayList<Usuario> userList = new ArrayList<Usuario>();
		try {
			java.sql.PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE NombreApellido LIKE '%"+nombre+"%'");
			//ps.setString(1, columna);
			//ps.setString(2, "'%"+nombre+"%'");
			ResultSet users = ps.executeQuery();
			while(users.next()) {
				int id = users.getInt(1);
				String user = users.getString(2);
				String pass = users.getString(3);
				String nombreApellido = users.getString(4);
				String email = users.getString(5);
				String telefono = users.getString(6);
				boolean isAdmin = users.getBoolean(7);
				
				Usuario usuario = new Usuario(user,pass,nombreApellido,email,telefono);
				usuario.setIdUsuario(id);
				usuario.setAdmin(isAdmin);
				userList.add(usuario);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	
	
	
	/**
	 * Obtiene los datos de un usuario segun su nombre de usuario
	 * @param userName
	 * @return El objeto Usuario con todos sus datos.
	 * @throws SQLException
	 */
	public Usuario getUser(String userName) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet users = DBConnection.sqlSelect("SELECT * FROM usuarios WHERE Usuario='"+userName+"'", con);
		try {
			while(users.next()) {
				int id = users.getInt(1);
				String user = users.getString(2);
				String pass = users.getString(3);
				String nick = users.getString(4);
				String email = users.getString(5);
				String telefono = users.getString(6);
				boolean isAdmin = users.getBoolean(7);
				
				usuario = new Usuario(user,pass,nick,email,telefono);
				usuario.setIdUsuario(id);
				usuario.setAdmin(isAdmin);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	/**
	 * Obtiene los datos de un usuario segun su ID de usuario
	 * @param userName
	 * @return El objeto Usuario con todos sus datos.
	 * @throws SQLException
	 */
	public Usuario getUser(int idUsuario) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet users = DBConnection.sqlSelect("SELECT * FROM usuarios WHERE IdUsuario="+idUsuario, con);
		
		try {
			while(users.next()) {
				int id = users.getInt(1);
				String user = users.getString(2);
				String pass = users.getString(3);
				String nick = users.getString(4);
				String email = users.getString(5);
				String telefono = users.getString(6);
				boolean isAdmin = users.getBoolean(7);
				
				usuario = new Usuario(user,pass,nick,email,telefono);
				usuario.setIdUsuario(id);
				usuario.setAdmin(isAdmin);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
		
	
	/**
	 * Este metodo retorna que tipo de usuario es segun su ID.
	 * @param id (int): ID del user que queremos saber que tipo de ususario es.
	 * @return True si es administrador. False si no lo es.
	 */
	public Boolean isAdmin(int id){
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		ResultSet rs = dbConnection.sqlSelect("Select IsAdmin from usuarios where IdUsuario="+id,con);
		boolean isAdmin=false;
		try {
			while(rs.next()){
				isAdmin=rs.getBoolean(1);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return isAdmin;
	}
	
	/**
	 * Setea el parametro booleano de isAdmin a True.
	 * @param id (int): El id del user que quiero que sea administrador.
	 */
	public void setAdmin(int id) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("UPDATE usuarios SET isAdmin='1' WHERE IdUsuario='"+id+"'",con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setea el parametro booleano de isAdmin a False.
	 * @param id (int): El id del user que NO quiero que sea administrador.
	 */
	
	public void removeAdmin(int id) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("UPDATE usuarios SET isAdmin='0' WHERE IdUsuario='"+id+"'",con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Comprueba que un usuario y contraseña se correspondan.
	 * @param user
	 * @param pass
	 * @return True si encuentra coincidencia, False de lo contrario.
	 */
	public boolean validaUser(String user, String pass) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select Usuario from usuarios where Usuario='"+user+"' and Password='"+pass+"'",con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	/**
	 * Busca un usuario mediante su nombre de usuario unico.
	 * @param usuario string del nombre de usuario a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeUsuario(String user) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select Usuario from usuarios where Usuario='"+user+"'",con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	/**
	 * Busca un usuario mediante su id unico.
	 * @param id int del usuario a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeUsuario(int id) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select Usuario from usuarios where IdUsuario="+id,con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	
	/**
	 * busca un email si existe retorna true.
	 * @param email
	 * @return
	 */
	public boolean existeEmail(String email) {
		DBConnection dbConnection = new DBConnection();
		return dbConnection.sqlExist("SELECT Email from usuarios where Email='"+email+"'");
	}
	
	/**
	 * No hace update del mail, busca un email en la tabla usuarios sin contar el de la ID que se le pasa.	
	 * Sirve para actualizar email. 
	 * @param email
	 * @param id
	 * @return
	 */
	public boolean updateEmail(String email, int id) {
		DBConnection dbConnection = new DBConnection();
		return dbConnection.sqlExist("SELECT Email from usuarios where email='"+email+"' and IdUsuario<>"+id);
	}
	
	/**
	 * busca un telefono si existe retorna true.
	 * @param telefono
	 * @return
	 */
	public boolean existeTelefono(String telefono) {
		DBConnection dbConnection = new DBConnection();
		return dbConnection.sqlExist("SELECT Telefono from usuarios where Telefono='"+telefono+"'");
	}
	
	public boolean updateTelefono(String telefono, int id) {
		DBConnection dbConnection = new DBConnection();
		return dbConnection.sqlExist("SELECT Telefono from usuarios where Telefono='"+telefono+"' and IdUsuario<>"+id);
	}
	
	/**
	 * Carga la session del usuario. ejemplo: en el login.
	 * @param user
	 * @param pass
	 * @param session
	 */
	public void cargarSession(String user, String pass, HttpSession session) {
		ArrayList<Integer> listaProductos = new ArrayList<Integer>();
		ArrayList<Integer> listaMascotas = new ArrayList<Integer>();
		session.setAttribute("user", user);
		session.setAttribute("pass", pass);		
		session.setAttribute("email", getInstance().getUser(user).getEmail());
		session.setAttribute("telefono", getInstance().getUser(user).getTelefono());
		session.setAttribute("nombreApellido", getInstance().getUser(user).getNombreApellido());
		session.setAttribute("id", getInstance().getUser(user).getIdUsuario());
		session.setAttribute("logeado", true);
		session.setAttribute("listaProductos", listaProductos);
		session.setAttribute("listaProductos", listaMascotas);
		if(getInstance().isAdmin((Integer)session.getAttribute("id"))) {
			session.setAttribute("isAdmin", true);
		}
		else {
			session.setAttribute("isAdmin", false);
		}
	}
	
	/**
	 * Carga la session del usuario, una vez que ya se encuentra logeado. ejemplo: cada vez que volvemos al perfil de usuario.
	 * @param user
	 * @param pass
	 * @param session
	 */
	public void cargarSession(String user,HttpSession session) {
		session.setAttribute("user", user);
		session.setAttribute("pass", getInstance().getUser(user).getPassword());		
		session.setAttribute("email", getInstance().getUser(user).getEmail());
		session.setAttribute("telefono", getInstance().getUser(user).getTelefono());
		session.setAttribute("nombreApellido", getInstance().getUser(user).getNombreApellido());
		session.setAttribute("id", getInstance().getUser(user).getIdUsuario());
		session.setAttribute("logeado", true);
		if(getInstance().isAdmin((Integer)session.getAttribute("id"))) {
			session.setAttribute("isAdmin", true);
			System.out.println(session.getAttribute("id"));
		}
		else {
			session.setAttribute("isAdmin", false);
			System.out.println(session.getAttribute("id"));
		}
	}
}