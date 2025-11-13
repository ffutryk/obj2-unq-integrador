package ar.edu.unq.po2.integrador;

import java.util.Objects;

public class Camion {
	
	private String matricula;
	
	public Camion(String unaMatricula) {
		this.matricula = unaMatricula;
	}

	public String getMatricula() {
		return this.matricula;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                        // mismo objeto
        if (obj == null || getClass() != obj.getClass())     // null o distinta clase
            return false;
        Camion camion = (Camion) obj;
        return Objects.equals(this.matricula, camion.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.matricula);
    }	

}
