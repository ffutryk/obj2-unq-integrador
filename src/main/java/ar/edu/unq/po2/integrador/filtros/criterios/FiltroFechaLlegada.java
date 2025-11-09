package ar.edu.unq.po2.integrador.filtros.criterios;

import ar.edu.unq.po2.integrador.Terminal;
import ar.edu.unq.po2.integrador.Viaje;
import ar.edu.unq.po2.integrador.filtros.comparadores.EstrategiaComparacionFecha;

public class FiltroFechaLlegada extends FiltroPorFecha {
	private final Terminal terminal; 
	
	public FiltroFechaLlegada(EstrategiaComparacionFecha estrategiaComparacion, Terminal terminal) {
		super(estrategiaComparacion);
		this.terminal = terminal;
	}

	@Override
	public boolean cumpleFiltro(Viaje viaje) {
		return estrategiaComparacion.compararCon(viaje.fechaDeArriboA(terminal));
	}

}
