package ar.edu.unq.po2.integrador.fases;

public class Outbound extends FaseDeViaje {

	@Override
	public void realizarAccionPara(Viaje unViaje) {
		// No hace nada al entrar en esta fase...
	}

	@Override
	public void actualizarPosicionPara(Viaje unViaje) {
		if(unViaje.distanciaATerminalGestionada() < 50d) {
			unViaje.setFase(new Inbound());
		}
	}

	@Override
	public boolean estaHabilitadaParaExportacion() {
		return true;
	}

	@Override
	public boolean estaHabilitadaParaImportacion() {
		return true;
	}
}
