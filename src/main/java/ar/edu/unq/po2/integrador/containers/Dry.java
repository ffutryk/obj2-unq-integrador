package ar.edu.unq.po2.integrador.containers;

import ar.edu.unq.po2.integrador.bl.IBillOfLanding;

public class Dry extends Container {

	public Dry(String id, double alto, double ancho, double profundidad, double peso, IBillOfLanding bl) {
		super(id, ancho, alto, profundidad, peso, bl);
	}
}
