package ar.edu.unq.po2.integrador;

import java.util.List;
import java.util.Set;

import ar.edu.unq.po2.integrador.fases.Viaje;

public class Naviera {
	
	private String nombre;
	private Set<Circuito> circuitos;
	private List<Buque> buques; // Lo puse porque asi lo tenemos en UML pero no se que tan necesario es...
	private Set<Viaje> viajes;

	public Naviera(String unNombre, Set<Circuito> circuitos, List<Buque> buques, Set<Viaje> viajes) {
		this.nombre = unNombre;
		this.circuitos = circuitos;
		this.buques = buques;
		this.viajes = viajes;
	}

	public Set<Circuito> getCircuitos() {
		return this.circuitos;
	}

	public Set<Viaje> getViajes() {
		return this.viajes;
	}

	public void registrarViaje(Viaje unViaje) {
		if(!this.circuitos.contains(unViaje.getCircuito())) {
			throw new RuntimeException("Viaje inválido para ésta naviera...");
		}
		this.viajes.add(unViaje);
	}

	public void registrarCircuito(Circuito unCircuito) {
		this.circuitos.add(unCircuito);
	}

}
