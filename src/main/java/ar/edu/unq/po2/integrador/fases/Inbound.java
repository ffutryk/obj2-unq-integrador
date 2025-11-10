package ar.edu.unq.po2.integrador.fases;

public class Inbound extends FaseDeViaje {

	@Override
	public void realizarAccionPara(Viaje unViaje) {
		unViaje.getGestionada().anunciarInminenteLlegada(unViaje);
	}

	@Override
	public void actualizarPosicionPara(Viaje unViaje) {
		if(unViaje.distanciaATerminalGestionada() == 0d) {
			unViaje.setFase(new Arrived());
		}
		else if(unViaje.distanciaATerminalGestionada() >= 50d) {
			unViaje.setFase(new Outbound()); // Ante un eventual problema de cambio climático.
		}
	}
}
