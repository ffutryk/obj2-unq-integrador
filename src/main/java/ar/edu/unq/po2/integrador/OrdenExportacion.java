package ar.edu.unq.po2.integrador;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.fases.Viaje;

public class OrdenExportacion extends Orden {

	private LocalDate fechaIngreso;
	
	public OrdenExportacion(Viaje viaje, Container container, String camion, String chofer, Cliente cliente, LocalDate fechaSalida) {
		super(viaje, container, camion, chofer, cliente, fechaSalida);
	}
	
	public void registrarIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public double diasDeServicio() {
		if (fechaIngreso == null) return 0; //deberia dar 0 si todavia no se registro el retiro? o dar los dias hasta now()?
		return (double) ChronoUnit.DAYS.between(this.getTurno(), fechaIngreso);
	}
	
	public boolean esDeImportacion() {
		return false;
	}
	
	public LocalDate getFechaDeFacturacion() {
		return fechaIngreso;
	}
}
