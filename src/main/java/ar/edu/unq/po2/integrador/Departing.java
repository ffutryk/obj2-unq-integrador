package ar.edu.unq.po2.integrador;

public class Departing extends FaseDeViaje {

	@Override
	public void realizarAccionPara(Viaje unViaje) {
		if(unViaje.distanciaATerminalGestionada() > 1d) {
			unViaje.setFase(new Outbound());
			unViaje.notificarDepart();
		}
	}

	@Override
	public void actualizarPosicionPara(Viaje unViaje) {
		this.realizarAccionPara(unViaje);
	}

	@Override
	public boolean estaHabilitadaParaPagar() {
		return true;
	}
	
}
