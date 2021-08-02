package pedidos;

import java.sql.Date;

public class Pedido {
	private int idPedido;
	private int idCliente;
	private Date fecha;
	private String estado;
	
	public Pedido(int idPedido, int idCliente, Date fecha) {
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.fecha = fecha;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
}