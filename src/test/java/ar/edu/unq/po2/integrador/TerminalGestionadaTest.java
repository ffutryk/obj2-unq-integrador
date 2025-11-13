package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.containers.TipoContainer;
import ar.edu.unq.po2.integrador.email.IEmailService;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.ordenes.Orden;
import ar.edu.unq.po2.integrador.ordenes.OrdenExportacion;
import ar.edu.unq.po2.integrador.ordenes.OrdenImportacion;
import ar.edu.unq.po2.integrador.reportes.VisitanteAduana;
import ar.edu.unq.po2.integrador.reportes.VisitanteBuque;
import ar.edu.unq.po2.integrador.reportes.VisitanteMuelle;
import ar.edu.unq.po2.integrador.reportes.VisitanteReportable;
import ar.edu.unq.po2.integrador.servicios.Servicio;

class TerminalGestionadaTest {
	
	TerminalGestionada terminal;
	PosicionGeografica ubicacion;
	Cliente unCliente;
	Camion unCamion;
	Chofer unChofer;
	Viaje unViaje;
	Container unContainer;
	Servicio unServicio;
	IEmailService servicioEmail;
	
	@BeforeEach
	void setUp() throws Exception {
		ubicacion = mock(PosicionGeografica.class);
		servicioEmail = mock(IEmailService.class);
		terminal = new TerminalGestionada("Puerto Buenos Aires", ubicacion, servicioEmail);
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
	
	@Test
	void testLaTerminalGestionadaSabeRegistrarUnaEmpresaTransportista() {
		EmpresaTransportista empresa = mock(EmpresaTransportista.class);
		terminal.registrarEmpresaTransportista(empresa);
		assertEquals(1, terminal.cantidadDeEmpresasTransportistas());
	}
	
	@Test
	void testLaTerminalSabeResponderLosCircuitosDisponibles() {
		Naviera unaNaviera = mock(Naviera.class);
		Naviera otraNaviera = mock(Naviera.class);
		Circuito c1 = mock(Circuito.class);
		Circuito c2 = mock(Circuito.class);
		Circuito c3 = mock(Circuito.class);
		Circuito c4 = mock(Circuito.class);
		terminal.registrarNaviera(unaNaviera);
		terminal.registrarNaviera(otraNaviera);
		when(unaNaviera.getCircuitos()).thenReturn(Arrays.asList(c1, c2, c3));
		when(otraNaviera.getCircuitos()).thenReturn(Arrays.asList(c4));
		
		assertEquals(Arrays.asList(c1, c2, c3, c4), terminal.circuitosDisponibles());
	}
	
	@Test
	void testLaTerminalSabeResponderLosViajesDisponibles() {
		Naviera unaNaviera = mock(Naviera.class);
		Naviera otraNaviera = mock(Naviera.class);
		Viaje v1 = mock(Viaje.class);
		Viaje v2 = mock(Viaje.class);
		Viaje v3 = mock(Viaje.class);
		Viaje v4 = mock(Viaje.class);
		terminal.registrarNaviera(unaNaviera);
		terminal.registrarNaviera(otraNaviera);
		when(unaNaviera.getViajes()).thenReturn(Arrays.asList(v1, v2, v3));
		when(otraNaviera.getViajes()).thenReturn(Arrays.asList(v4));
		
		assertEquals(Arrays.asList(v1, v2, v3, v4), terminal.viajesDisponibles());
	}
	
	@Test
	void testLaTerminalSabeResponderCualEsElMejorCircuitoQueIncluyaALaTerminalDestinoDada() {
		Terminal terminalDestino = mock(Terminal.class);
		Naviera unaNaviera = mock(Naviera.class);
		Circuito c1 = mock(Circuito.class);
		Circuito c2 = mock(Circuito.class);
		Circuito c3 = mock(Circuito.class);
		Circuito c4 = mock(Circuito.class);
		terminal.registrarNaviera(unaNaviera);
		when(unaNaviera.getCircuitos()).thenReturn(Arrays.asList(c1, c2, c3, c4));
		when(c2.costoTotal()).thenReturn(50d);
		when(c4.costoTotal()).thenReturn(55d);
		when(c2.incluyeLaTerminal(terminalDestino)).thenReturn(true);
		when(c4.incluyeLaTerminal(terminalDestino)).thenReturn(true);
		
		assertEquals(c2, terminal.mejorCircuitoHacia(terminalDestino)); // Porque por defecto está seteada la estrategia más barata...
	}
	
	@Test
	void testLaTerminalNotificaViaMailALosConsigneesQueEstanEsperandoPorLaLlegadaDeUnViajeDado() {
		Orden ordenImpo = mock(Orden.class);
		when(ordenImpo.getViaje()).thenReturn(unViaje);
		Orden ordenExpo = mock(Orden.class);
		Viaje otroViaje = mock(Viaje.class);
		when(ordenExpo.getViaje()).thenReturn(otroViaje);
		when(ordenExpo.esDeImportacion()).thenReturn(false);
		when(ordenImpo.esDeImportacion()).thenReturn(true);
		terminal.agregarOrden(ordenImpo);
		terminal.agregarOrden(ordenExpo);
		
		terminal.anunciarInminenteLlegada(unViaje);
		
		verify(ordenImpo).enviarMail(servicioEmail);
		verify(ordenExpo, never()).enviarMail(servicioEmail);
	}
	
	@Test
	void testLaTerminalNotificaALosShippersQueEstanEsperandoPorLaPartidaDeUnViajeDeterminado() {
		Viaje otroViaje = mock(Viaje.class);
		Orden ordenImpo = mock(Orden.class);
		when(ordenImpo.getViaje()).thenReturn(otroViaje);
		Orden ordenExpo = mock(Orden.class);
		when(ordenExpo.getViaje()).thenReturn(unViaje);
		when(ordenExpo.esDeImportacion()).thenReturn(false);
		when(ordenImpo.esDeImportacion()).thenReturn(true);
		terminal.agregarOrden(ordenImpo);
		terminal.agregarOrden(ordenExpo);
		
		terminal.anunciarPartida(unViaje);
		
		verify(ordenExpo).enviarMail(servicioEmail);
		verify(ordenImpo, never()).enviarMail(servicioEmail);
	}
	
	@Test
	void testNoSePuedePreguntarPorElTiempoDeViajeDeUnaNavieraQueNoEstaRegistradaEnLaTerminal() {
		Naviera unaNaviera = mock(Naviera.class);
		assertThrows(RuntimeException.class, () -> terminal.tiempoDeViajePorA(unaNaviera, mock(Terminal.class)));
	}
	
	@Test
	void testNoSePuedePreguntarPorElTiempoDeViajeDePorUnaNavieraHastaUnaTerminalQueNoPerteneceANingunCircuito() {
		Terminal terminalDestino = mock(Terminal.class);
		Naviera unaNaviera = mock(Naviera.class);
		Circuito c1 = mock(Circuito.class);
		Circuito c2 = mock(Circuito.class);
		Circuito c3 = mock(Circuito.class);
		Circuito c4 = mock(Circuito.class);
		terminal.registrarNaviera(unaNaviera);
		when(unaNaviera.getCircuitos()).thenReturn(Arrays.asList(c1, c2, c3, c4));
		when(c1.incluyeLaTerminal(terminalDestino)).thenReturn(false);
		when(c2.incluyeLaTerminal(terminalDestino)).thenReturn(false);
		when(c3.incluyeLaTerminal(terminalDestino)).thenReturn(false);
		when(c4.incluyeLaTerminal(terminalDestino)).thenReturn(false);
		assertThrows(RuntimeException.class, () -> terminal.tiempoDeViajePorA(unaNaviera, terminalDestino));
	}
	
	@Test
	void testLaTerminalSabeResponderCuantoTardaUnaNavieraEnRecorrerUnCircuitoQueIncluyeEnSusCircuitos() {
		Terminal terminalDestino = mock(Terminal.class);
		Naviera unaNaviera = mock(Naviera.class);
		Circuito c1 = mock(Circuito.class);
		Circuito c2 = mock(Circuito.class);
		Circuito c3 = mock(Circuito.class);
		Circuito c4 = mock(Circuito.class);
		terminal.registrarNaviera(unaNaviera);
		when(unaNaviera.getCircuitos()).thenReturn(Arrays.asList(c1, c2, c3, c4));
		when(c1.incluyeLaTerminal(terminalDestino)).thenReturn(false);
		when(c2.incluyeLaTerminal(terminalDestino)).thenReturn(false);
		when(c3.incluyeLaTerminal(terminalDestino)).thenReturn(true);
		when(c4.incluyeLaTerminal(terminalDestino)).thenReturn(false);
		Duration duracion = mock(Duration.class);
		when(c3.duracionHasta(terminalDestino)).thenReturn(duracion);
		
		assertEquals(duracion, terminal.tiempoDeViajePorA(unaNaviera, terminalDestino));
	}
	
	@Test
	void testLaTerminalNoPuedeResponderLaProximaFechaDeSalidaDeUnBuqueAUnaTerminalDestinoSiElDestinoNoFormaParteDeNingunoDeSusViajes() {
		Terminal destino = mock(Terminal.class);
		Naviera unaNaviera = mock(Naviera.class);
		Naviera otraNaviera = mock(Naviera.class);
		Viaje v1 = mock(Viaje.class);
		Viaje v2 = mock(Viaje.class);
		Viaje v3 = mock(Viaje.class);
		Viaje v4 = mock(Viaje.class);
		terminal.registrarNaviera(unaNaviera);
		terminal.registrarNaviera(otraNaviera);
		when(unaNaviera.getViajes()).thenReturn(Arrays.asList(v1, v2, v3));
		when(otraNaviera.getViajes()).thenReturn(Arrays.asList(v4));
		when(v1.pasaPorLaTerminal(destino)).thenReturn(false);
		when(v2.pasaPorLaTerminal(destino)).thenReturn(false);
		when(v3.pasaPorLaTerminal(destino)).thenReturn(false);
		when(v4.pasaPorLaTerminal(destino)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> terminal.proximaFechaDePartidaHasta(destino));
	}
	
	@Test
	void testLaTerminalSabeResponderCuandoEsLaProximaFechaDeSalidaDeUnBuqueHaciaUnaTerminalDestino() {
		Terminal destino = mock(Terminal.class);
		Naviera unaNaviera = mock(Naviera.class);
		Naviera otraNaviera = mock(Naviera.class);
		Viaje v1 = mock(Viaje.class);
		Viaje v2 = mock(Viaje.class);
		Viaje v3 = mock(Viaje.class);
		Viaje v4 = mock(Viaje.class);
		terminal.registrarNaviera(unaNaviera);
		terminal.registrarNaviera(otraNaviera);
		when(unaNaviera.getViajes()).thenReturn(Arrays.asList(v1, v2, v3));
		when(otraNaviera.getViajes()).thenReturn(Arrays.asList(v4));
		LocalDateTime dateViaje1 = mock(LocalDateTime.class);
		LocalDateTime dateViaje2 = mock(LocalDateTime.class);
		when(v1.pasaPorLaTerminal(destino)).thenReturn(true);
		when(v2.pasaPorLaTerminal(destino)).thenReturn(false);
		when(v3.pasaPorLaTerminal(destino)).thenReturn(true);
		when(v4.pasaPorLaTerminal(destino)).thenReturn(false);
		when(v1.fechaDeArriboA(terminal)).thenReturn(dateViaje1);
		when(v3.fechaDeArriboA(terminal)).thenReturn(dateViaje2);
		when(dateViaje1.compareTo(dateViaje2)).thenReturn(-1); // Es menor fecha...

		assertEquals(dateViaje1, terminal.proximaFechaDePartidaHasta(destino));
	}

	@Test
    void testGenerarReporteParaDelegaCorrectamenteAlVisitante() {
        VisitanteReportable visitanteMock = mock(VisitanteReportable.class);
        String reporteEsperado = "REPORTE_DELEGADO"; 

        when(visitanteMock.generarReportePara(any(Viaje.class), anyList()))
            .thenReturn(reporteEsperado);


        String resultado = terminal.generarReportePara(visitanteMock, unViaje);

        assertEquals(reporteEsperado, resultado);
        verify(visitanteMock, times(1)).generarReportePara(eq(unViaje), anyList());
    }
	
	@Test
	void testGenerarReporteAduanaDelegaCorrectamente() {
		TerminalGestionada spy = spy(terminal);
		
		doReturn("REPORTE_ADUANA_OK")
			.when(spy)
			.generarReportePara(any(VisitanteAduana.class), eq(unViaje));
		
		String resultado = spy.generarReporteAduanaPara(unViaje);

		assertEquals("REPORTE_ADUANA_OK", resultado);

		verify(spy, times(1)).generarReportePara(any(VisitanteAduana.class), eq(unViaje));
	}

	@Test
	void testGenerarReporteMuelleDelegaCorrectamente() {
		TerminalGestionada spy = spy(terminal);
		
		doReturn("REPORTE_MUELLE_OK")
			.when(spy)
			.generarReportePara(any(VisitanteMuelle.class), eq(unViaje));
		
		String resultado = spy.generarReporteMuellePara(unViaje);

		assertEquals("REPORTE_MUELLE_OK", resultado);

		verify(spy, times(1)).generarReportePara(any(VisitanteMuelle.class), eq(unViaje));
	}
	
	@Test
	void testGenerarReporteBuqueDelegaCorrectamente() {
		TerminalGestionada spy = spy(terminal);
		
		doReturn("REPORTE_BUQUE_OK")
			.when(spy)
			.generarReportePara(any(VisitanteBuque.class), eq(unViaje));
		
		String resultado = spy.generarReporteBuquePara(unViaje);

		assertEquals("REPORTE_BUQUE_OK", resultado);

		verify(spy, times(1)).generarReportePara(any(VisitanteBuque.class), eq(unViaje));
	}
	
	@Test
	void testLaTerminalRechazaLaEntregaDeUnaCargaSiDifiereEnMasDe3HorasConElTurnoAsignado() {
		Orden ordenExpo = mock(Orden.class);
		LocalDateTime turnoOrden = LocalDateTime.of(2025, 11, 12, 19, 30);
		when(ordenExpo.getTurno()).thenReturn(turnoOrden);
		terminal.agregarOrden(ordenExpo);
		assertThrows(RuntimeException.class, () -> terminal.autorizarEntrega(ordenExpo, unCamion, unChofer));
	}
	
	@Test
	void testLaTerminalRechazaLaEntregaDeUnaCargaSiElCamionNoEstaAutorizadoEnLaTerminal() {
		Orden ordenExpo = mock(Orden.class);
		LocalDateTime turnoOrden = LocalDateTime.of(2025, 11, 13, 0, 0);
		Camion camionQueTrajo = mock(Camion.class);
		when(ordenExpo.getTurno()).thenReturn(turnoOrden);
		when(ordenExpo.getCamion()).thenReturn(camionQueTrajo);
		EmpresaTransportista empresa = mock(EmpresaTransportista.class);
		terminal.registrarEmpresaTransportista(empresa);
		when(empresa.tieneRegistradaA(camionQueTrajo)).thenReturn(false);
		terminal.agregarOrden(ordenExpo);
		
		assertThrows(RuntimeException.class, () -> terminal.autorizarEntrega(ordenExpo, camionQueTrajo, unChofer));
	}
	
	@Test
	void testLaTerminalRechazaLaEntregaDeUnaCargaSiElCamionNoCoincideConElDeclaradoEnLaOrden() {
		Orden ordenExpo = mock(Orden.class);
		LocalDateTime turnoOrden = LocalDateTime.of(2025, 11, 13, 0, 0);
		Camion camionQueTrajo = mock(Camion.class);
		when(ordenExpo.getTurno()).thenReturn(turnoOrden);
		when(ordenExpo.getCamion()).thenReturn(unCamion);
		EmpresaTransportista empresa = mock(EmpresaTransportista.class);
		terminal.registrarEmpresaTransportista(empresa);
		when(empresa.tieneRegistradaA(camionQueTrajo)).thenReturn(true);
		terminal.agregarOrden(ordenExpo);
		
		assertThrows(RuntimeException.class, () -> terminal.autorizarEntrega(ordenExpo, camionQueTrajo, unChofer));
	}
	
	@Test
	void testLaTerminalRechazaLaEntregaDeUnaCargaSiElChoferNoEstaAutorizadoEnLaTerminal() {
		Orden ordenExpo = mock(Orden.class);
		LocalDateTime turnoOrden = LocalDateTime.of(2025, 11, 13, 0, 0);
		when(ordenExpo.getTurno()).thenReturn(turnoOrden);
		when(ordenExpo.getCamion()).thenReturn(unCamion);
		when(ordenExpo.getChofer()).thenReturn(unChofer);
		EmpresaTransportista empresa = mock(EmpresaTransportista.class);
		terminal.registrarEmpresaTransportista(empresa);
		when(empresa.tieneRegistradaA(unCamion)).thenReturn(true);
		when(empresa.tieneRegistradaA(unChofer)).thenReturn(false);
		terminal.agregarOrden(ordenExpo);
		
		assertThrows(RuntimeException.class, () -> terminal.autorizarEntrega(ordenExpo, unCamion, unChofer));
	}
	
	@Test
	void testLaTerminalRechazaLaEntregaDeUnaCargaSiElChoferNoCoincideConElDeclaradoEnLaOrden() {
		Orden ordenExpo = mock(Orden.class);
		LocalDateTime turnoOrden = LocalDateTime.of(2025, 11, 13, 0, 0);
		Chofer otroChofer = mock(Chofer.class);
		when(ordenExpo.getTurno()).thenReturn(turnoOrden);
		when(ordenExpo.getCamion()).thenReturn(unCamion);
		when(ordenExpo.getChofer()).thenReturn(otroChofer);
		EmpresaTransportista empresa = mock(EmpresaTransportista.class);
		terminal.registrarEmpresaTransportista(empresa);
		when(empresa.tieneRegistradaA(unCamion)).thenReturn(true);
		when(empresa.tieneRegistradaA(otroChofer)).thenReturn(true);
		when(empresa.tieneRegistradaA(unChofer)).thenReturn(true);
		terminal.agregarOrden(ordenExpo);
		
		assertThrows(RuntimeException.class, () -> terminal.autorizarEntrega(ordenExpo, unCamion, unChofer));
	}
	
	
}
