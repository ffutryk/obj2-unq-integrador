package ar.edu.unq.po2.integrador;

public class Inbound extends FaseDeViaje {

	@Override
	public void realizarAccionPara(Viaje unViaje) {
		unViaje.notificarInbound();
	}

	@Override
	public void actualizarPosicionPara(Viaje unViaje) {
		if(unViaje.distanciaATerminalGestionada() == 0d) {
			unViaje.setFase(new Arrived());
			this.realizarAccionPara(unViaje);
		}
		else if(unViaje.distanciaATerminalGestionada() >= 50d) {
			unViaje.setFase(new Outbound()); // Ante un eventual problema de cambio climático.
		}
	}
}
