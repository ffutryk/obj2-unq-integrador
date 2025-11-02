package ar.edu.unq.po2.integrador;

import java.util.Objects;

public class Viaje {
	
	private Circuito circuito;
	private Buque buque;

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // mismo objeto
        if (obj == null || getClass() != obj.getClass()) return false;
        Viaje viaje = (Viaje) obj;
        return this.buque.equals(viaje.getBuque()) && this.circuito.equals(viaje.getCircuito()); // criterio de igualdad
    }
	
	 @Override
	    public int hashCode() {
	        return Objects.hash(circuito, buque);
	    }

	public Object getBuque() {
		return this.buque;
	}

	public Circuito getCircuito() {
		return this.circuito;
	}

}
