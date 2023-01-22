package Clases;

public class Venta {
	private int idVenta;
	private String fecha, cliente, usuario_ci;
	private double total;
	public Venta(int idVenta, String fecha, String cliente, String usuario_ci, double total) {

		this.idVenta = idVenta;
		this.fecha = fecha;
		this.cliente = cliente;
		this.usuario_ci = usuario_ci;
		this.total = total;
	}
	public Venta() {}
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getUsuario_ci() {
		return usuario_ci;
	}
	public void setUsuario_ci(String usuario_ci) {
		this.usuario_ci = usuario_ci;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
