package ar.edu.unq.po2.integrador.servicios;

import java.time.LocalDateTime;

public class DesgloseDeServicio {

	private String nombre;
	private LocalDateTime fecha;
	private double costo;
	
	public DesgloseDeServicio(String nombre, LocalDateTime fecha, double costo) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.costo = costo;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public double getCosto() {
		return costo;
	}
	
}
