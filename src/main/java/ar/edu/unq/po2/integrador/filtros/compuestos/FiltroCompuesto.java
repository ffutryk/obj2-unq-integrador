package ar.edu.unq.po2.integrador.filtros.compuestos;

import ar.edu.unq.po2.integrador.filtros.IFiltroViaje;

public abstract class FiltroCompuesto implements IFiltroViaje {
    protected final IFiltroViaje izquierda;
    protected final IFiltroViaje derecha;

    protected FiltroCompuesto(IFiltroViaje izquierda, IFiltroViaje derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }
}
