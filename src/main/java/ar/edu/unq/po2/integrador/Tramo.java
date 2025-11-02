package ar.edu.unq.po2.integrador;

import java.time.Duration;

public class Tramo {
	
	private Duration duracion;
	private double costo;
	private Terminal origen;
	private Terminal destino;

	public Tramo(Terminal origen, Terminal destino, long duracion, double costo) {
		this.duracion = Duration.ofHours(duracion);
		this.origen = origen;
		this.destino = destino;
		this.costo = costo;
	}

	public double getDuracion() {
		return this.duracion.toHours();
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
