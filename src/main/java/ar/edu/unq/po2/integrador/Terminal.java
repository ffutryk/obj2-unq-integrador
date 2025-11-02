package ar.edu.unq.po2.integrador;

public class Terminal {
	
	private PosicionGeografica ubicacion;

	public Terminal(PosicionGeografica pos) {
		this.ubicacion = pos;
	}

	public PosicionGeografica getUbicacion() {
		return this.ubicacion;
	}
}
