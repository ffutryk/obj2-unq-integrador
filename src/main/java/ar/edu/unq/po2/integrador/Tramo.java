package ar.edu.unq.po2.integrador;

import java.time.Duration;

public class Tramo {
	
	private Duration duracion;
	private double costo;
	private Terminal origen;
	private Terminal destino;
	
	public Tramo() {
		
	}

	public Tramo(Terminal origen, Terminal destino, Duration duracion, double costo) {
		this.duracion = duracion;
		this.origen = origen;
		this.destino = destino;
		this.costo = costo;
	}

	public Duration getDuracion() {
		return this.duracion;
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

	public boolean contieneA(Terminal unaTerminal) {
		return this.getOrigen().equals(unaTerminal) || this.getDestino().equals(unaTerminal);
	}

}
