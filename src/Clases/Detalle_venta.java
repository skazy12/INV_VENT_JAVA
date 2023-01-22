package Clases;

import java.util.ArrayList;

public class Detalle_venta {
	private int iddetalle;
	private int idProducto;
	private int cantidad;
	private int idventa;
	public Detalle_venta(int iddetalle, int idProducto, int cantidad, int idventa) {

		this.iddetalle = iddetalle;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.idventa = idventa;
	}
	public Detalle_venta() {}
	public int getIddetalle() {
		return iddetalle;
	}
	public void setIddetalle(int iddetalle) {
		this.iddetalle = iddetalle;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getIdventa() {
		return idventa;
	}
	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}
	
	


	
}
