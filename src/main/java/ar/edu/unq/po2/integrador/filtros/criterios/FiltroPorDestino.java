package ar.edu.unq.po2.integrador.filtros.criterios;

import ar.edu.unq.po2.integrador.Terminal;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.filtros.IFiltroViaje;

public class FiltroPorDestino implements IFiltroViaje {
	protected final Terminal destino;
	
	public FiltroPorDestino(Terminal destino) {
		this.destino = destino;
	}

	@Override
	public boolean cumpleFiltro(Viaje viaje) {
		return viaje.pasaPorLaTerminal(destino);
	}
}
