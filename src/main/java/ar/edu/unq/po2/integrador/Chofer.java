package ar.edu.unq.po2.integrador;

import java.util.Objects;

public class Chofer {

	private String nombre;
	
	public Chofer(String unNombre) {
		this.nombre = unNombre;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                        // mismo objeto
        if (obj == null || getClass() != obj.getClass())     // null o distinta clase
            return false;
        Chofer chofer = (Chofer) obj;
        return Objects.equals(this.nombre, chofer.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nombre);
    }	

}
