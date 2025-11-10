package ar.edu.unq.po2.integrador.fases;

public class Arrived extends FaseDeViaje {

	@Override
	public void realizarAccionPara(Viaje unViaje) {
		unViaje.getGestionada().registrarArribo(unViaje);
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
