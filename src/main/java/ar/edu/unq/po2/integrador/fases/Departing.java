package ar.edu.unq.po2.integrador.fases;

public class Departing extends FaseDeViaje {

	@Override
	public void realizarAccionPara(Viaje unViaje) {
		if(unViaje.distanciaATerminalGestionada() > 1d) {
			unViaje.setFase(new Outbound());
			unViaje.getGestionada().anunciarPartida(unViaje);
			unViaje.getGestionada().enviarFacturasPara(unViaje); // Esto creo que sería algo internamente que debería hacer la terminal gestionada en su método para *anunciarPartida(Viaje)*...
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
