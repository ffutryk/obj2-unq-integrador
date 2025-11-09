package ar.edu.unq.po2.integrador;

public class Tanque extends Container{

	public Tanque(String id, double peso, double alto, double ancho, double profundidad, IBillOfLanding bl) {
		super(id, ancho, alto, profundidad, peso, bl);
	}

}
