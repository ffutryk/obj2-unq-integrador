package ar.edu.unq.po2.integrador;

import ar.edu.unq.po2.ordenes.Orden;

public class Cliente {
	
	private String id;
	private String nombre;
	private String direcciomMail;
	private Orden orden;
	
	public Cliente(String id, String nombre, String direccionMail) {
		this.id = id;
		this.nombre = nombre;
		this.direcciomMail = direccionMail;
	}
	
	public void guardarOrden(Orden o) {
		this.orden = o;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDirecciomMail() {
		return direcciomMail;
	}

	public Orden getOrden() {
		return orden;
	}

}
