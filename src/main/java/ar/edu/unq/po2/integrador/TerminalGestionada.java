package ar.edu.unq.po2.integrador;



import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.servicios.Servicio;
import ar.edu.unq.po2.ordenes.Orden;
import ar.edu.unq.po2.ordenes.OrdenExportacion;
import ar.edu.unq.po2.ordenes.OrdenImportacion;

public class TerminalGestionada extends Terminal {

	private List<Orden> ordenes;
	private List<Naviera> navieras;
	private List<Cliente> clientes;

	public TerminalGestionada(String unNombre, PosicionGeografica unaPosicionGeografica) {
		super(unNombre, unaPosicionGeografica);
		this.ordenes = new ArrayList<Orden>(); 
		this.navieras = new ArrayList<Naviera>();
		this.clientes = new ArrayList<Cliente>();
	}

	public Orden exportar(Viaje unViaje, Container unContainer, Camion unCamion, Chofer unChofer, Cliente unCliente) {
		if(!unViaje.estaHabilitadoParaExportacion()) {
			throw new RuntimeException("No se pueden registrar nuevas exportaciones en este viaje...");
		}
		Orden orden = new OrdenExportacion(unViaje, unContainer, unCamion.getMatricula(), unChofer.getNombre(), unCliente, unViaje.fechaDeArriboA(this));
		this.ordenes.add(orden);
		return orden;
	}

	protected Integer cantidadDeOrdenes() { // Creo que sería protected no public, para que solo sea enviado por el Test...
		return this.ordenes.size();
	}

	public Orden importar(Viaje unViaje, Container unContainer, Camion unCamion, Chofer unChofer, Cliente unCliente) {
		if(!unViaje.estaHabilitadoParaImportacion()) {
			throw new RuntimeException("No se pueden registrar nuevas importaciones en este viaje...");
		}
		Orden orden = new OrdenImportacion(unViaje, unContainer, unCamion.getMatricula(), unChofer.getNombre(), unCliente, unViaje.fechaDeArriboA(this));
		this.ordenes.add(orden);
		return orden;
	}

	public void contratarServicio(Orden unaOrden, Servicio unServicio) {
		unaOrden.agregarDesgloseServicio(unServicio);
	}

	public void registrarNaviera(Naviera unaNaviera) {
		this.navieras.add(unaNaviera);
	}
	
	protected Integer cantidadDeNavieras() {
		return this.navieras.size();
	}

	public void registrarCliente(Cliente unCliente) {
		this.clientes.add(unCliente);
	}

	protected Integer cantidadDeClientes() {
		return this.clientes.size();
	}
}
