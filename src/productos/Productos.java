package productos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import util.DBConnection;


public class Productos{
	private static Productos Productos = null;
	
	private Productos() {
	}
	
	public static Productos getInstance() {
		if(Productos == null){
			Productos = new Productos();
		}
		return Productos;
	}
	
	
	/**
	 * Agrega un nuevo producto a la base de datos. Previamente comprueba que no exista el NOMBRE unico para cada producto.
	 * @param nombre
	 * @param precio	
	 * @param cantidad
	 * @param descripcion
	 * @return true si el producto fue agregado, false en caso de que ya exista.
	 * @throws SQLException
	 */
	public boolean addProducto(String nombre, double precio, int cantidad, String descripcion) {
		if(existeProducto(nombre)) {
			return false;
		}
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("INSERT INTO productos (Nombre,Precio,Cantidad,Descripcion)"
								+ " VALUES ('"+nombre+"','"+precio+"','"+cantidad+"','"+descripcion+"')",con);
			System.out.println("producto agregado");
			con.close();
		} catch (SQLException e) {
			System.out.println("producto NO agregado");
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Modifica los datos del producto, no devuelve nada.
	 * @param idProducto
	 * @param nombre
	 * @param precio
	 * @param cantidad
	 * @param descripcion
	 * @throws SQLException
	 */
	public boolean updateProducto(int idProducto, String nombre, double precio, int cantidad, String descripcion) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("UPDATE productos SET Nombre='"+nombre+"', Precio ='"+precio+"', Cantidad ='"+cantidad+"', Descripcion = '"+descripcion+"' WHERE IdProducto='"+idProducto+"'",con);
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Borra un producto segun su idProducto unico de la tabla Productos. no devuelve nada
	 * @param idProducto
	 * @throws SQLException
	 */
	public boolean deleteProducto(int idProducto) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("DELETE FROM productos WHERE IdProducto='"+idProducto+"'",con);
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	/**
	 * Trae todos los productos de la tabla Productos y devuelve un arraylist de Producto
	 * @return Arraylist del objeto Producto.
	 * @throws SQLException
	 */
	public ArrayList<Producto> getProductoLista() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet productos = DBConnection.sqlSelect("SELECT * FROM productos",con);
		ArrayList<Producto> prodList = new ArrayList<Producto>();
		try {
			while(productos.next()) {
				int idProducto = productos.getInt(1);
				String nombre = productos.getString(2);
				Double precio = productos.getDouble(3);
				int cantidad = productos.getInt(4);	
				String descripcion = productos.getString(5);
				String imgLink = productos.getString(6);
				Producto producto = new Producto(idProducto,nombre,precio,cantidad,descripcion);
				producto.setImgLink(imgLink);
				prodList.add(producto);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodList;
	}
	
	/**
	 * Obtiene los datos de un producto segun su idProducto unico.
	 * @param idProducto
	 * @return El objeto Producto con todos sus datos.
	 * @throws SQLException
	 */
	public Producto getProducto(int idProducto) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet productos = DBConnection.sqlSelect("SELECT * FROM productos WHERE IdProducto='"+idProducto+"'", con);
		Producto producto = null;
		try {
			while(productos.next()) {
				int idProd = productos.getInt(1);
				String nombre = productos.getString(2);
				Double precio = productos.getDouble(3);
				int cantidad = productos.getInt(4);	
				String descripcion = productos.getString(5);
				String imgLink = productos.getString(6);
				producto = new Producto(idProd,nombre,precio,cantidad,descripcion);
				producto.setImgLink(imgLink);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}
	
	
	/**
	 * Busca el idProducto de un producto dado.
	 * @param id del producto a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeProducto(int idProducto) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select IdProducto from productos where IdProducto='"+idProducto+"'",con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	/**
	 * Busca el nombre de un producto dado.
	 * @param id del producto a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeProducto(String nombre) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select Nombre from productos where Nombre='"+nombre.trim().toLowerCase()+"'",con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	

	/**
	 * Setea la cantidad de un producto, segun su idProducto.
	 * @param codigo.
	 * @param cantidad. 
	 */
	public void setCantidad(int idProducto, int cantidad) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		
		try {
			DBConnection.sqlUpdate("UPDATE productos SET Cantidad='"+cantidad+"' WHERE IdProducto='"+idProducto+"'",con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * En realidad no hace el update, solo comprueba de que no exista un nombre en la DB sin tener en cuenta el que ya tengo con mi propia id.
	 * @param nombre
	 * @param codigo
	 * @return true si existe, false si no.
	 */
	public boolean updateNombre(String nombre, int idProducto) {
		DBConnection DBConnection = new DBConnection();
		boolean ok = DBConnection.sqlExist("Select Nombre from productos where Nombre='"+nombre+"' and IdProducto<>"+idProducto);
		return ok;
	}
	
	/**
	 * Setea una descripcion al producto.
	 * @param idProducto Int
	 * @param descripcion String
	 */
	public void setDescripcion(int idProducto, String descripcion) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		
		try {
			DBConnection.sqlUpdate("UPDATE productos SET Descripcion='"+descripcion+"' WHERE IdProducto='"+idProducto+"'",con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Busca la descripcion de un producto.
	 * @param idProducto Int
	 * @return String descripcion, o null si no lo encuentra.
 	 */
	public String getDescripcion(int idProducto) {
		String descripcion = null;
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet productos = DBConnection.sqlSelect("Select Descripcion from productos WHERE IdProducto='"+idProducto+"'",con);
		try {
			while(productos.next()) {
				descripcion = productos.getString(1);			
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descripcion;
	}
	
	/**
	 * Agrega un string como link de imagen en la tabla de productos.
	 * @param imgLink
	 * @return
	 */
	public boolean addImgLink(String imgLink, String nombre) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		String sql = "UPDATE productos SET ImgLink= ? WHERE Nombre= ?";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, imgLink);
			ps.setString(2, nombre);
			ps.executeUpdate();
			
			System.out.println(imgLink);
			System.out.println(nombre);
			//DBConnection.sqlUpdate("UPDATE Productos SET ImgLink='"+imgLink+"' WHERE Nombre='"+nombre+"'",con);
			System.out.println("imagen agregado");
			con.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("imagen NO agregado");
			e.printStackTrace();
		}
		return true;
	}
}