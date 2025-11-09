package ar.edu.unq.po2.integrador.filtros.criterios;

import ar.edu.unq.po2.integrador.filtros.IFiltroViaje;
import ar.edu.unq.po2.integrador.filtros.comparadores.EstrategiaComparacionFecha;

public abstract class FiltroPorFecha implements IFiltroViaje {
	protected final EstrategiaComparacionFecha estrategiaComparacion;
	
	public FiltroPorFecha(EstrategiaComparacionFecha estrategiaComparacion) {
		this.estrategiaComparacion = estrategiaComparacion;
	}
}
