package ar.edu.unq.po2.integrador.filtros.comparadores;

import java.time.LocalDateTime;

public class DespuesDeFecha extends EstrategiaComparacionFecha {
	public DespuesDeFecha(LocalDateTime fecha) {
		super(fecha);
	}

	@Override
	public boolean compararCon(LocalDateTime unaFecha) {
		return unaFecha.isAfter(fecha);
	}

}
