package ar.edu.unq.po2.integrador.fases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.fases.Departing;
import ar.edu.unq.po2.integrador.fases.FaseDeViaje;
import ar.edu.unq.po2.integrador.fases.Outbound;
import ar.edu.unq.po2.integrador.fases.Viaje;

class DepartingTest {
	
	FaseDeViaje faseDeparting;
	Viaje unViaje;

	@BeforeEach
	void setUp() throws Exception {
		faseDeparting = new Departing();
		unViaje = mock(Viaje.class);
	}

	@Test
	void testAlActualizarPosicionDentroDelAreaDeLaTerminalGestionada_NoSeCambiaLaFaseDelViajeCorrespondiente() {
		when(unViaje.distanciaATerminalGestionada()).thenReturn(0d);
		faseDeparting.actualizarPosicionPara(unViaje);
		verify(unViaje, never()).setFase(any());;
	}
	
	@Test
	void testAlActualizarPosicionFueraDelAreaDeLaTerminalGestionada_SeCambiaLaFaseDelViajeCorrespondienteAOutbound() {
		when(unViaje.distanciaATerminalGestionada()).thenReturn(5d);
		faseDeparting.actualizarPosicionPara(unViaje);
		verify(unViaje).setFase(any(Outbound.class));
	}
	
	@Test
	void testLaFaseEstaHabilitadaParaRegistrarPagos() {
		assertTrue(faseDeparting.estaHabilitadaParaPagar());
	}

}
