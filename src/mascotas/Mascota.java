package mascotas;

public class Mascota {
	private int idMascota;
	private int idUsuario;
	private String nombre;
	private String tipo;
	private int edad;
	private double peso;
	
	public Mascota(int idMascota,int idUsuario, String nombre, String tipo, int edad, double peso) {
		this.idMascota = idMascota;
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.tipo = tipo;
		this.edad = edad;
		this.peso = peso;
	}
	
	public Mascota(int idUsuario, String nombre) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
	}
	
	public int getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public String toString() {
		return "IdMascota="+idMascota+", IdUsuario="+idUsuario+", Nombre="+nombre+", Tipo="+tipo+", Edad="+edad+", Peso="+peso;
	}
}
