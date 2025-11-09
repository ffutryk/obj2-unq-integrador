package ar.edu.unq.po2.integrador.filtros.comparadores;

import java.time.LocalDateTime;

public abstract class EstrategiaComparacionFecha {
	protected final LocalDateTime fecha;
	
	public EstrategiaComparacionFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	abstract public boolean compararCon(LocalDateTime unaFecha);
}
