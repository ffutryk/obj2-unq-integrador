package ar.edu.unq.po2.integrador.containers;

import java.util.List;

import ar.edu.unq.po2.integrador.Pair;
import ar.edu.unq.po2.integrador.bl.IBillOfLanding;

public abstract class Container {
	
	private String id;
	private double peso;
	private double ancho;
	private double alto;
	private double profundidad;
	private IBillOfLanding bl;
	
	public Container(String id, double ancho, double alto, double largo, double peso, IBillOfLanding bl) {
		this.id = id;
        this.ancho = ancho;
        this.alto = alto;
        this.profundidad = largo;
        this.peso = peso;
        this.bl = bl;
	}
	
	public double volumen() {
		return ancho * alto * profundidad;
	}
	
	public boolean superaVolumen(double dimension) {
		return volumen() < dimension;
	}
	
	public IBillOfLanding getBL() {
		return bl;
	}
	
	public List<Pair<String, Double>> carga(){
		return bl.carga();
	}
	
	public double getPeso() {
		return peso;
	}
	
	public String getId() {
		return id;
	}
	
	public double pesoTotal() {
		return peso + bl.pesoCarga();
	}
	
	public boolean esRefeer() {
		return false;
	}
	
	public double getConsumoKwHora() {
		return 0;
	}

}
