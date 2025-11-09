package ar.edu.unq.po2.integrador;

public class Buque {
	
	private IGPS gps;
	private PosicionGeografica pos;
	private Viaje viaje;
	
	public Buque(IGPS unGPS) {
		this.gps = unGPS;
		this.pos = gps.posicionDe(this);
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

}
