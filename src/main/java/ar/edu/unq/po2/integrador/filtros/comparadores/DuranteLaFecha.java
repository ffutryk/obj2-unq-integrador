package ar.edu.unq.po2.integrador.filtros.comparadores;

import java.time.LocalDateTime;

public class DuranteLaFecha extends EstrategiaComparacionFecha {
	public DuranteLaFecha(LocalDateTime fecha) {
		super(fecha);
	}

	@Override
	public boolean compararCon(LocalDateTime unaFecha) {
		return unaFecha.isEqual(fecha);
	}

}
