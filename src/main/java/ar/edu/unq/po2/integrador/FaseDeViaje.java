package ar.edu.unq.po2.integrador;

public abstract class FaseDeViaje {
	
	public abstract void realizarAccionPara(Viaje unViaje);

	public abstract void actualizarPosicionPara(Viaje unViaje);

	public void trabajar(Viaje unViaje) {
		// Ya que solo se redefine trabajar en la subclase Arrived.
	}
	
	public void depart(Viaje unViaje) {
		// Ya que solo se redefine depart en la subclase Working.
	}
	
	public boolean estaHabilitadaParaExportacion() {
		return false; // Ya que solo retorna true en la subclase Outbound.
	}
	
	public boolean estaHabilitadaParaImportacion() {
		return false; // Ya que solo retorna true en la subclase Outbound.
	}
	
	public boolean estaHabilitadaParaPagar() {
		return false; // Ya que solo retorna true en la subclase Departing.
	}
}
