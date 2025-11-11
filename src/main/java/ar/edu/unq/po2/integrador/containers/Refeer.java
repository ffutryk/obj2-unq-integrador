package ar.edu.unq.po2.integrador.containers;

import ar.edu.unq.po2.integrador.bl.IBillOfLanding;

public class Refeer extends Container{
	
	private double consumoKwHora;

	public Refeer(String id, double peso, double alto, double ancho, double profundidad, IBillOfLanding bl, double consumoKwHora) {
		super(id, ancho, alto, profundidad, peso, bl);
		this.consumoKwHora = consumoKwHora;
	}
	
	@Override
	public double getConsumoKwHora() {
		return consumoKwHora;
	}
	
	@Override
	public boolean esRefeer() {
		return true;
	}
	
	@Override
	public TipoContainer getTipo() {
		return TipoContainer.REFEER;
	}
}
