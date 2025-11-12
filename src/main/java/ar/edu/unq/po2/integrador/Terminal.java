package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.integrador.fases.Viaje;

public class Terminal {
	
	private String nombre;
	private PosicionGeografica ubicacion;
	protected List<Viaje> arribados;

	public Terminal(String nombre, PosicionGeografica pos) {
		this.nombre = nombre;
		this.ubicacion = pos;
		this.arribados = new ArrayList<Viaje>();
	}

	public PosicionGeografica getUbicacion() {
		return this.ubicacion;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // mismo objeto
        if (obj == null || getClass() != obj.getClass()) return false;
        Terminal term = (Terminal) obj;
        return this.nombre.equals(term.getNombre()); // criterio de igualdad
    }

	public String getNombre() {
		return this.nombre;
	}

	public void registrarArribo(Viaje unViaje) {
		this.arribados.add(unViaje);
	}

	protected Integer cantidadDeArribados() {
		return this.arribados.size();
	}
	
}
