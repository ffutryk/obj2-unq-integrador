package ar.edu.unq.po2.integrador.busqueda;

import java.util.List;

import ar.edu.unq.po2.integrador.Circuito;
import ar.edu.unq.po2.integrador.Terminal;
import ar.edu.unq.po2.integrador.busqueda.estrategias.EstrategiaBusquedaCircuito;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.filtros.IFiltroViaje;

public class MotorDeBusqueda {
	private EstrategiaBusquedaCircuito estrategiaBusqueda;
	private final ICircuitosProveedor proveedorCircuitos;
	private final IViajesProveedor proveedorViajes;
	
	public MotorDeBusqueda(ICircuitosProveedor proveedorCircuitos, IViajesProveedor proveedorViajes, EstrategiaBusquedaCircuito estrategiaBusqueda) {
		this.estrategiaBusqueda = estrategiaBusqueda;
		this.proveedorCircuitos = proveedorCircuitos;
		this.proveedorViajes = proveedorViajes;
	}

	public void setEstrategiaBusqueda(EstrategiaBusquedaCircuito estrategia) {
		this.estrategiaBusqueda = estrategia;
	}
	
	public List<Viaje> buscarViajes(IFiltroViaje filtro) {
		return proveedorViajes.viajesDisponibles()
				.stream()
				.filter(v -> filtro.cumpleFiltro(v))
				.toList();
	}
	
	public Circuito mejorCircuitoHacia(Terminal destino) {
		return estrategiaBusqueda.mejorCircuitoHacia(proveedorCircuitos.circuitosDisponibles(), destino);
	}
}
