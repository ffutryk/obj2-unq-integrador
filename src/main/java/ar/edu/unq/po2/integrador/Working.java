package ar.edu.unq.po2.integrador;

public class Working extends FaseDeViaje {

	@Override
	public void realizarAccionPara(Viaje unViaje) {
		// Trabajos de carga y descarga en curso. Lo cual no se pide implementar en este TP.
		unViaje.getBuque().cargaYDescarga();
	}

	@Override
	public void actualizarPosicionPara(Viaje unViaje) {
		// Esta fase no cambia por posición, cambia por orden de la terminal gestionada...
		this.realizarAccionPara(unViaje); // Seguir realizando carga y descarga...
	}

	@Override
	public void depart(Viaje unViaje) {
		unViaje.setFase(new Departing());
	}
	
}
