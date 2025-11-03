package ar.edu.unq.po2.integrador.filtros.comparadores;

import java.time.LocalDateTime;

public class AntesDeFecha extends EstrategiaComparacionFecha {
	public AntesDeFecha(LocalDateTime fecha) {
		super(fecha);
	}

	@Override
	public boolean compararCon(LocalDateTime unaFecha) {
		return unaFecha.isBefore(fecha);
	}

}
