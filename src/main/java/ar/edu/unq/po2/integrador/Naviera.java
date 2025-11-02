package ar.edu.unq.po2.integrador;

import java.util.List;
import java.util.Set;

public class Naviera {
	
	private String nombre;
	private Set<Circuito> circuitos;
	private List<Buque> buques;

	public Naviera(String unNombre, Set<Circuito> circuitos, List<Buque> buques) {
		this.nombre = unNombre;
		this.circuitos = circuitos;
		this.buques = buques;
	}

	public Set<Circuito> getCircuitos() {
		return this.circuitos;
	}

}
