package ar.edu.unq.po2.integrador.containers;

import ar.edu.unq.po2.integrador.bl.IBillOfLanding;

public class Tanque extends Container{

	public Tanque(String id, double peso, double alto, double ancho, double profundidad, IBillOfLanding bl) {
		super(id, ancho, alto, profundidad, peso, bl);
	}

}
