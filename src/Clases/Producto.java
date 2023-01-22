package Clases;

import java.io.FileInputStream;


public class Producto {
	private int id;
	private String nombreProducto, desc;
	private double precio;
	private int idCategoria;
	private	FileInputStream img;
	private int lon;
	private boolean estado;
	public Producto(int id, String nombreProducto, double precio, int idCategoria) {

		this.id = id;
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.idCategoria = idCategoria;
	}
	public Producto() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public FileInputStream getImg() {
		return img;
	}
	public void setImg(FileInputStream img) {
		this.img = img;
	}
	public int getLon() {
		return lon;
	}
	public void setLon(int lon) {
		this.lon = lon;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	

	
}
