package productos;
import java.io.Serializable;

public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idProducto;
	private String nombre;
	private double precio;
	private int cantidad;
	private String descripcion;
	private double precioTotal;
	private String imgLink;
	

	public Producto(int idProducto, String nombre, double precio, int cantidad, String descripcion) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.setDescripcion(descripcion);
	}
	
	public Producto(String nombre, double precio, int cantidad, String descripcion) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.setDescripcion(descripcion);
	}
	
	//copiamos el contenido de otro objeto tipo Producto
	public Producto(Producto prodCopia) {
		this.idProducto = prodCopia.idProducto;
		this.nombre = prodCopia.nombre;
		this.cantidad = prodCopia.cantidad;
		this.precio = prodCopia.precio;
		this.descripcion = prodCopia.descripcion;
	}
	
	public Producto() {
	}

	public int getCantidad() {
		return cantidad;
	}

	public boolean setCantidad(int cantidad) {
		this.cantidad = cantidad;
		return true;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public boolean setIdProducto(int idProducto) {
		this.idProducto = idProducto;
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean setNombre(String descripcion) {
		this.nombre = descripcion;
		return true;
	}

	public double getPrecio() {
		return precio;
	}

	public boolean setPrecio(double precio) {
		this.precio = precio;
		return true;
	}
	
	public double getTotal() {
		return precio*cantidad;
	}
	
	public String toString() {
		return "IdProducto: "+idProducto+" Nombre: "+nombre+" Precio: $"+precio+" Cantidad: "+cantidad+" Descripcion: "+descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
}