package ar.edu.unq.po2.integrador;

public class Arrived extends FaseDeViaje {

	@Override
	public void realizarAccionPara(Viaje unViaje) {
		unViaje.notificarArrived();
	}

	@Override
	public void actualizarPosicionPara(Viaje unViaje) {
		// Esta fase no cambia por posición, cambia por orden de la terminal gestionada...
	}

	@Override
	public void trabajar(Viaje unViaje) {
		unViaje.setFase(new Working());
	}

}
