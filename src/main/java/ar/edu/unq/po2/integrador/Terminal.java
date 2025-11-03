package ar.edu.unq.po2.integrador;

public class Terminal {
	
	private String nombre;
	private PosicionGeografica ubicacion;

	public Terminal(String nombre, PosicionGeografica pos) {
		this.nombre = nombre;
		this.ubicacion = pos;
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
}
