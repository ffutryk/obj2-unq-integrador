package ar.edu.unq.po2.integrador;

public class Buque {
	
	private IGPS gps;
	private PosicionGeografica pos;
	
	public Buque(IGPS unGPS) {
		this.gps = unGPS;
		this.pos = gps.posicionDe(this);
	}

	public PosicionGeografica getPosicion() {
		return pos;
	}
	
	public void actualizarPosicion(PosicionGeografica nuevaPos) {
		this.pos = nuevaPos;
	}

}
