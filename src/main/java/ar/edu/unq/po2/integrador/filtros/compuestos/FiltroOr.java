package ar.edu.unq.po2.integrador.filtros.compuestos;

import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.filtros.IFiltroViaje;

public class FiltroOr extends FiltroCompuesto {
	public FiltroOr(IFiltroViaje izquierda, IFiltroViaje derecha) {
        super(izquierda, derecha);
    }
    
    @Override
    public boolean cumpleFiltro(Viaje viaje) {
        return izquierda.cumpleFiltro(viaje) || derecha.cumpleFiltro(viaje);
    }
}
