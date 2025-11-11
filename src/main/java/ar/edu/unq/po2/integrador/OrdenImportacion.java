package ar.edu.unq.po2.integrador;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.reportes.VisitanteReportable;

public class OrdenImportacion extends Orden {

	private LocalDate fechaRetiro;
	
	public OrdenImportacion(Viaje viaje, Container container, String camion, String chofer, Cliente cliente, LocalDate fechaLlegada) {
		super(viaje, container, camion, chofer, cliente, fechaLlegada);
	}
	 
	public void registrarRetiro(LocalDate fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	 
	public double diasDeServicio() {
		if (fechaRetiro == null) return 0; //deberia dar 0 si todavia no se registro el retiro? o dar los dias hasta now()?
		return (double) ChronoUnit.DAYS.between(this.getTurno(), fechaRetiro);
	}
	
	@Override
	public boolean esDeImportacion() {
		return true;
	}
	
	public LocalDate getFechaDeFacturacion() {
		return fechaRetiro;
	}
	
	@Override
	public void aceptar(VisitanteReportable visitante) {
		visitante.visitar(this);
	}
}
