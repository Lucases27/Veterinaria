package usuarios;

import java.util.ArrayList;
import java.util.Iterator;

public class Usuario implements Iterable{
	private int idUsuario;
	private String usuario;
	private String password;
	private String nombreApellido;
	private String email;
	private String telefono;
	private boolean isAdmin;
	
	protected Usuario(String usuario, String password, String nombreApellido, String email, String telefono) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.nombreApellido = nombreApellido;
		this.email = email;
		this.telefono = telefono;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNombreApellido() {
		return nombreApellido;
	}
	
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String toString() {
		return "ID: "+idUsuario+" Usuario: "+usuario+" NombreApellido: "+nombreApellido+" Password: "+password+ " Email: "+email+
				"Telefono:"+telefono+" isAdmin: "+isAdmin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public boolean validarPassword(String password) {
		return this.getPassword().equals(password);
	}
	
	//Para poder iterar objetos tipo Usuario dentro de un foreach
	@Override
	public Iterator<Object> iterator() {
		ArrayList<Object> userIterable = new ArrayList<Object>();
		userIterable.add(idUsuario);
		userIterable.add(usuario);
		userIterable.add(password);
		userIterable.add(nombreApellido);
		userIterable.add(email);
		userIterable.add(telefono);
		userIterable.add(isAdmin);
		return userIterable.iterator();
	}
	
}
