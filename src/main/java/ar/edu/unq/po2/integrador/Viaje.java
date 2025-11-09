package ar.edu.unq.po2.integrador;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Viaje {
	
	private LocalDateTime fechaDeSalida; // Usé LocalDateTime a pesar de que en el UML pusimos LocalDate porque LocalDate no tiene la hora y la duracion de los tramos al estar en horas necesito sumar las duraciones para obtener la fecha estimada.
	private Circuito circuito;
	private Buque buque;
	private FaseDeViaje fase;
	private Terminal gestionada;
	
	public Viaje(Buque unBuque, Circuito unCircuito, LocalDateTime unaFechaDeSalida, Terminal terminal) {
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

	protected void notificarArrived() {
		this.gestionada.registrarArribo(this);
	}
	
	public void trabajar() {
		this.fase.trabajar(this);
	}

	protected void notificarDepart() {
		this.gestionada.anunciarPartida(this);	  // Notificar via mail 
		this.gestionada.enviarFacturasPara(this); // Enviar las facturas via mail con desglose de servicios a quien corresponda.
												  // Nota: anunciarPartida y enviarFacturasPara no deberían ser mensajes publicos de la terminal gestionada, deberían ser package o algo por el estilo y que solo se los envie un Viaje a la terminal y no sean mensajes que le puede enviar un cliente (shipper o consignee).
	}

	protected void notificarInbound() {
		this.gestionada.anunciarInminenteLlegada(this);
	}	
	
	public void depart() {
		this.fase.depart(this);
	}
	// Los mensajes cuyo modificador de visibilidad son protected se esperan ser enviados unicamente por la Fase hacia su Viaje correspondiente.
}
