package ar.edu.unq.po2.integrador.reportes;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.integrador.Buque;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.ordenes.Orden;
import ar.edu.unq.po2.ordenes.OrdenExportacion;
import ar.edu.unq.po2.ordenes.OrdenImportacion;

public abstract class VisitanteReportable {
	public abstract void visitar(Buque buque);
	public abstract void visitar(OrdenImportacion orden);
	public abstract void visitar(OrdenExportacion orden);
	protected abstract String generarReporte();
	
	String generarReportePara(Viaje viaje, List<Orden> ordenes) {
    	viaje.getBuque().aceptar(this);
    	
    	List<Orden> ordenesDelViaje = ordenes
                .stream()
                .filter(o -> o.getViaje().equals(viaje)) 
                .collect(Collectors.toList());
    	
    	ordenesDelViaje.forEach(orden -> orden.aceptar(this));
    	
    	return this.generarReporte();
	}
}
