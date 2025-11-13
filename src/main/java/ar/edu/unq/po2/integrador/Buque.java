package ar.edu.unq.po2.integrador;

import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.reportes.IReportable;
import ar.edu.unq.po2.integrador.reportes.VisitanteReportable;

public class Buque implements IReportable {
	private String nombre;
	private IGPS gps;
	private PosicionGeografica pos;
	private Viaje viaje;
	
	public Buque(IGPS unGPS, String nombre) {
		this.gps = unGPS;
		this.pos = gps.posicionDe(this);
		this.nombre = nombre;
		this.viaje = null;
	}

	public PosicionGeografica getPosicion() {
		return pos;
	}
	
	public void asignarViaje(Viaje unViaje) {
		this.viaje = unViaje;
	}
	
	public void actualizarPosicion(PosicionGeografica nuevaPos) {
		this.pos = nuevaPos;
		if(this.viaje != null) {
			this.viaje.actualizarPosicion();
		}
	}

	public void cargaYDescarga() {
		// No se pide modelar en este trabajo...
	}
	
	public Viaje getViaje() {
		return viaje;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public void aceptar(VisitanteReportable visitante) {
		visitante.visitar(this);
	}

}
