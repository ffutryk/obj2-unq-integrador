package ar.edu.unq.po2.integrador;

import java.util.List;

import ar.edu.unq.po2.integrador.fases.Viaje;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Orden {
	
	private Container container;
	private Cliente cliente;
	private List<DesgloseDeServicio> serviciosContratados;
	private LocalDate turno;  //fechaLlegada o fechaSalida (depende si es impo o expo)
	private Viaje viaje;
	private String chofer;
	private String camion;

	public Orden(Viaje viaje, Container container, String camion, String chofer, Cliente cliente, LocalDate turno) {
		this.cliente = cliente;
		this.container = container;
		this.serviciosContratados = new ArrayList<DesgloseDeServicio>();
		this.turno = turno;
		this.viaje = viaje;
		this.camion = camion;
		this.chofer = chofer;
	}
	
	public void agregarDesgloseServicio(Servicio s) {
		serviciosContratados.add(s.obtenerDesglose(this.container, this));
	}
	
	public LocalDate getTurno() {
		return turno;
	}
	
	public Container getContainer() {
        return container;
    }
	
	public abstract double diasDeServicio();
	
	public boolean esDeImportacion() {
		return false;
	}
	
	public abstract LocalDate getFechaDeFacturacion();
	
}
