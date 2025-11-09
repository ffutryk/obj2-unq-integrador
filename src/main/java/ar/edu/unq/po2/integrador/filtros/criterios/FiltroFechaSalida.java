package ar.edu.unq.po2.integrador.filtros.criterios;

import ar.edu.unq.po2.integrador.Viaje;
import ar.edu.unq.po2.integrador.filtros.comparadores.EstrategiaComparacionFecha;

public class FiltroFechaSalida extends FiltroPorFecha {
	public FiltroFechaSalida(EstrategiaComparacionFecha estrategiaComparacion) {
		super(estrategiaComparacion);
	}

	@Override
	public boolean cumpleFiltro(Viaje viaje) {
		return estrategiaComparacion.compararCon(viaje.getFechaSalida());
	}

}
