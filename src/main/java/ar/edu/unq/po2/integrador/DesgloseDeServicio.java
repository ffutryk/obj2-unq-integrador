package ar.edu.unq.po2.integrador;

import java.time.LocalDate;

public class DesgloseDeServicio {

	private String nombre;
	private LocalDate fecha;
	private double costo;
	
	public DesgloseDeServicio(String nombre, LocalDate fecha, double costo) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.costo = costo;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public double getCosto() {
		return costo;
	}
	
}
