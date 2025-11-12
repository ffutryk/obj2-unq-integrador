package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.servicios.Servicio;
import ar.edu.unq.po2.ordenes.Orden;

class TerminalGestionadaTest {
	
	TerminalGestionada terminal;
	PosicionGeografica ubicacion;
	Cliente unCliente;
	Camion unCamion;
	Chofer unChofer;
	Viaje unViaje;
	Container unContainer;
	Servicio unServicio;
	
	@BeforeEach
	void setUp() throws Exception {
		ubicacion = mock(PosicionGeografica.class);
		terminal = new TerminalGestionada("Puerto Buenos Aires", ubicacion);
		unCliente = mock(Cliente.class);
		unCamion = mock(Camion.class);
		unChofer = mock(Chofer.class);
		unViaje = mock(Viaje.class);
		unContainer = mock(Container.class);	
		unServicio = mock(Servicio.class);
		when(unViaje.estaHabilitadoParaExportacion()).thenReturn(true);
		when(unViaje.estaHabilitadoParaImportacion()).thenReturn(true);
	}
	
	@Test
	void testLaTerminalGestionadaGeneraUnaOrdenDeExportacionCuandoUnClienteRealizaExitosamenteUnaOperacionDeExportacion() {
		terminal.exportar(unViaje, unContainer, unCamion, unChofer, unCliente); 
		assertEquals(1, terminal.cantidadDeOrdenes());
	}
	
	@Test
	void testLaTerminalGestionadaGeneraUnaOrdenDeImportacionCuandoUnClienteRealizaExitosamenteUnaOperacionDeImportacion() {
		terminal.importar(unViaje, unContainer, unCamion, unChofer, unCliente);
		assertEquals(1, terminal.cantidadDeOrdenes());
	}
	
	@Test
	void testLaTerminalGestionadaPermiteALosUsuariosContratarUnServicioParaUnaOrdenDeterminada() {
		Orden ordenGenerada = mock(Orden.class);
		terminal.contratarServicio(ordenGenerada, unServicio);
		verify(ordenGenerada).agregarDesgloseServicio(unServicio);
	}
	
	@Test
	void testSePuedenRegistrarLineasNavierasEnLaTerminalGestionada() {
		Naviera unaNaviera = mock(Naviera.class);
		terminal.registrarNaviera(unaNaviera);
		assertEquals(1, terminal.cantidadDeNavieras());
	}
	
	@Test
	void testSePuedenRegistrarClientesEnLaTerminalGestionada() {
		terminal.registrarCliente(unCliente);
		assertEquals(1, terminal.cantidadDeClientes());
	}
	
	@Test
	void testNoSePuedenRegistrarExportacionesSiElViajeNoEstaHabilitadoParaEso() {
		when(unViaje.estaHabilitadoParaExportacion()).thenReturn(false);
		assertThrows(RuntimeException.class, () -> terminal.exportar(unViaje, unContainer, unCamion, unChofer, unCliente));
	}
	
	@Test
	void testNoSePuedenRegistrarImportacionesSiElViajeNoEstaHabilitadoParaEso() {
		when(unViaje.estaHabilitadoParaImportacion()).thenReturn(false);
		assertThrows(RuntimeException.class, () -> terminal.importar(unViaje, unContainer, unCamion, unChofer, unCliente));
	}
}
