package ar.edu.unq.po2.integrador;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Viaje {
	
	private LocalDateTime fechaDeSalida; // Usé LocalDateTime a pesar de que en el UML pusimos LocalDate porque LocalDate no tiene la hora y la duracion de los tramos al estar en horas necesito sumar las duraciones para obtener la fecha estimada.
	private Circuito circuito;
	private Buque buque;

	public Viaje(Buque unBuque, Circuito unCircuito, LocalDateTime unaFechaDeSalida) {
		this.fechaDeSalida = unaFechaDeSalida;
		this.circuito = unCircuito;
		this.buque = unBuque;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // mismo objeto
        if (obj == null || getClass() != obj.getClass()) return false;
        Viaje viaje = (Viaje) obj;
        return this.buque.equals(viaje.getBuque()) && this.circuito.equals(viaje.getCircuito()); // criterio de igualdad
    }
	
	@Override
	public int hashCode() {
	    return Objects.hash(circuito, buque);
	}

	public Object getBuque() {
		return this.buque;
	}

	public Circuito getCircuito() {
		return this.circuito;
	}
	
	public LocalDateTime fechaDeArriboA(Terminal destino) {
		// Precondición: La terminal dada está dentro del circuito abarcado por el viaje
		LocalDateTime fechaArribo = this.fechaDeSalida;
		Duration horas = this.circuito.duracionHasta(destino);
		return fechaArribo.plus(horas);
	}
	
	public boolean pasaPorLaTerminal(Terminal terminal) {
		return this.circuito.incluyeLaTerminal(terminal);
	}
	
	public LocalDateTime getFechaSalida() {
		return this.fechaDeSalida;
	}
}
