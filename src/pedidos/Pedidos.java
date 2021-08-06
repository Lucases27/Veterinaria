package pedidos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import productos.Producto;
import util.DBConnection;

public class Pedidos {
	public static Pedidos Pedidos = null;
	Pedidos pedidos = null;
	
	private Pedidos() {	
	}
	
	public static Pedidos getInstance() {
		if (Pedidos==null) {
			Pedidos = new Pedidos();
		}
		return Pedidos;
	}
	
	/**
	 * Trae todos los pedidos de la tabla Pedidos y devuelve un arraylist de Pedido
	 * @return Arraylist del objeto Pedido.
	 * @throws SQLException
	 */
	public ArrayList<Pedido> getListaPedido() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet pedidos = DBConnection.sqlSelect("SELECT * FROM PEDIDOS",con);
		ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
		try {
			while(pedidos.next()) {
				int nPedido = pedidos.getInt(1);
				int nCliente = pedidos.getInt(2);
				Date fecha = pedidos.getDate(3);	
				Pedido pedido = new Pedido(nPedido,nCliente,fecha);
				listaPedidos.add(pedido);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPedidos;
	}
	
	/**
	 * Trae todos los pedidos de la tabla Pedidos correspondientes a un ID y devuelve un arraylist de Pedido.
	 * @return Arraylist del objeto Pedido.
	 * @throws SQLException
	 */
	public ArrayList<Pedido> getListaPedido(int idUsuario) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet pedidos = DBConnection.sqlSelect("SELECT * FROM PEDIDOS WHERE IDCLIENTE="+idUsuario,con);
		ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
		try {
			while(pedidos.next()) {
				int idPedido = pedidos.getInt(1);
				int idCliente = pedidos.getInt(2);
				Date fecha = pedidos.getDate(3);
				Pedido pedido = new Pedido(idPedido,idCliente,fecha);
				listaPedidos.add(pedido);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPedidos;
	}
	
	/**
	 * Devuelve un ArrayList de Productos, con los detalles del pedido numero: idPedido.
	 * @param idPedido
	 * @return
	 */
	public ArrayList<Producto> getDetallesPedido(int idPedido) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet pedidos = DBConnection.sqlSelect("SELECT * FROM Pedidos_detalles WHERE IdPedido="+idPedido,con);
		ArrayList<Producto> pedido = new ArrayList<Producto>();
		try {
			while(pedidos.next()) {
				int idProducto = pedidos.getInt(3);
				String nombre = pedidos.getString(4);
				double precio = pedidos.getDouble(7);
				int cantidad = pedidos.getInt(6);
				String descripcion = pedidos.getString(5);
				double precioTotal = pedidos.getDouble(8);
				Producto producto = new Producto(idProducto,nombre,precio,cantidad,descripcion);
				producto.setPrecioTotal(precioTotal);
				pedido.add(producto);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedido;
	}
	
	/**
	 * Trae un pedido de un cliente, segun su idPedido
	 * @param idUsuario
	 * @return objeto Pedido
	 */
	public Pedido getPedido(int idPedido) {
		Pedido pedido = null;
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet pedidos = DBConnection.sqlSelect("SELECT * FROM PEDIDOS WHERE IdPedido="+idPedido,con);
		try {
			while(pedidos.next()) {
				int idCliente = pedidos.getInt(2);
				Date fecha = pedidos.getDate(3);
				pedido = new Pedido(idPedido,idCliente,fecha);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedido;
	}
	
	/**
	 * Borra un pedido segun su idPedido.
	 * @param idPedido
	 */
	public void deletePedido(int idPedido) {
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		try {
			dbConnection.sqlUpdate("DELETE FROM PEDIDOS WHERE IdPedido="+idPedido, con);
			System.out.println("Pedidos.deletePedido() --> Borrado");
			dbConnection.sqlUpdate("DELETE FROM PEDIDOS_DETALLES WHERE IdPedido="+idPedido, con);
		} catch (SQLException e) {
			System.out.println("error al borrar mascota en deletePedido()");
		}
	}
	
	/**
	 * Agrega un pedido a la tabla Pedidos y Detalles_Pedidos, es necesario un 
	 * @param session
//	 */
//	public boolean addPedido(HttpSession session) {
//		DBConnection dbConnection = new DBConnection();
//		Connection con = dbConnection.getConexion();
//		int idUsuario;
//		int idPedido;
//		
//		try {
//			dbConnection.sqlUpdate("INSERT INTO Pedidos (IdCliente)"
//					+ " VALUES ("+idUsuario+")",con);
//			//creo que esto esta mal, ordenado ascendentemente traera el menor primero, 
//			//pero dentro while se pisara el valor y npedido tendra el ultimo valor siempre, en este caso el mayor.
//			ResultSet rs = dbConnection.sqlSelect("SELECT IdPedido from Pedidos where IdCliente="+idUsuario+" order by IdPedido ASC",con);
//			while(rs.next()) {
//				idPedido = rs.getInt("IdPedido");
//			}
//			/*
//			 * Reemplazar iterable por el objeto iterable dentro de la session, que contenta la lista de Productos del pedido.
//			 * Luego hacer .clear al objeto de sesion.
//			 */
//			for (Producto producto : iterable) {
//				double total = producto.getPrecio()*producto.getCantidad();
//				dbConnection.sqlUpdate("INSERT INTO Pedidos_detalles (IdPedido,IdProducto,NombreProducto,DescripcionProducto,Cantidad,"
//						+ "PrecioUnidad,PrecioTotal) VALUES ("+idPedido+","+producto.getIdProducto()+",'"+producto.getNombre()+"','"+producto.getDescripcion()+"',"
//						+ ""+producto.getCantidad()+","+producto.getPrecio()+","+total+")",con);
//				//resta la cantidad comprada al stock.
//				dbConnection.sqlUpdate("UPDATE Productos SET Cantidad = Cantidad - "+producto.getCantidad()+" WHERE IdProducto="+producto.getIdProducto(),con);
//			}
//			
//			return true;
//		} catch (SQLException e) {
//			return false;
//		}
//	}
}
