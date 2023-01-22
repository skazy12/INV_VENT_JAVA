package Clases;

import java.util.ArrayList;

public class carro {
	private int id, cantidad;
	private String producto;
	private double precio;
	public carro(int id, int cantidad, String producto, double precio) {
		
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
		this.precio = precio;
	}
	public carro() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double calcularSubtotal(int cantidad, double precio) {
		double sub=(double) cantidad*precio;
		return sub;
	}
	public ArrayList<carro> duplicados(ArrayList<carro> objs){
		ArrayList<carro> nueva=new ArrayList<carro>();

		for(carro it: objs) {
			if(!(nueva.contains(it))) {
				nueva.add(it);
			}
			
			
		}
		

		return nueva;
	}
}
