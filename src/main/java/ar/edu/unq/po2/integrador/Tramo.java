package ar.edu.unq.po2.integrador;

public class Tramo {
	
	private double duracionEnHoras; // Esto creo que seria mejor tiparlo de otra manera...
	private double costo;
	private Terminal origen;
	private Terminal destino;

	public Tramo(Terminal origen, Terminal destino, double duracion, double costo) {
		this.duracionEnHoras = duracion;
		this.origen = origen;
		this.destino = destino;
		this.costo = costo;
	}

	public double getDuracion() {
		return this.duracionEnHoras;
	}

	public double getCosto() {
		return this.costo;
	}

	public Terminal getOrigen() {
		return this.origen;
	}

	public Terminal getDestino() {
		return this.destino;
	}

}
