package ar.edu.unq.po2.integrador.fases;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import ar.edu.unq.po2.integrador.Buque;
import ar.edu.unq.po2.integrador.Circuito;
import ar.edu.unq.po2.integrador.Terminal;
import ar.edu.unq.po2.integrador.TerminalGestionada;

public class Viaje {
	
	private LocalDateTime fechaDeSalida; // Usé LocalDateTime a pesar de que en el UML pusimos LocalDate porque LocalDate no tiene la hora y la duracion de los tramos al estar en horas necesito sumar las duraciones para obtener la fecha estimada.
	private Circuito circuito;
	private Buque buque;
	private FaseDeViaje fase;
	private TerminalGestionada gestionada;
	
	public Viaje(Buque unBuque, Circuito unCircuito, LocalDateTime unaFechaDeSalida, TerminalGestionada terminal) {
		this.fechaDeSalida = unaFechaDeSalida;
		this.circuito = unCircuito;
		this.buque = unBuque;
		this.fase = new Outbound(); // Fase inicial
		this.gestionada = terminal;
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

	public Buque getBuque() {
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

	public void actualizarPosicion() {
		this.fase.actualizarPosicionPara(this);
	}

	protected double distanciaATerminalGestionada() {
		return this.buque.getPosicion().distanciaHasta(this.gestionada.getUbicacion());
	}

	protected void setFase(FaseDeViaje unaFase) {
		this.fase = unaFase;
		this.fase.realizarAccionPara(this);
	}
	
	public void trabajar() {
		this.fase.trabajar(this);
	}
	
	public void depart() {
		this.fase.depart(this);
	}
	// Los mensajes cuyo modificador de visibilidad son protected se esperan ser enviados unicamente por la Fase hacia su Viaje correspondiente.

	public TerminalGestionada getGestionada() {
		return this.gestionada;
	}

	public boolean estaHabilitadoParaExportacion() {
		return this.fase.estaHabilitadaParaExportacion();
	}

	public boolean estaHabilitadoParaImportacion() {
		return this.fase.estaHabilitadaParaImportacion();
	}
}
