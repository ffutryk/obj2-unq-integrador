package ar.edu.unq.po2.integrador.filtros.compuestos;

import ar.edu.unq.po2.integrador.Viaje;
import ar.edu.unq.po2.integrador.filtros.IFiltroViaje;

public class FiltroAnd extends FiltroCompuesto {
    public FiltroAnd(IFiltroViaje izquierda, IFiltroViaje derecha) {
        super(izquierda, derecha);
    }
    
    @Override
    public boolean cumpleFiltro(Viaje viaje) {
        return izquierda.cumpleFiltro(viaje) && derecha.cumpleFiltro(viaje);
    }
}
