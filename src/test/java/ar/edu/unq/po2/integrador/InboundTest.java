package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InboundTest {
	
	FaseDeViaje faseInbound;
	Viaje viajeCorrespondiente;
	
	@BeforeEach
	void setUp() throws Exception {
		faseInbound = new Inbound();
		viajeCorrespondiente = mock(Viaje.class);
	}

	@Test
	void testLaFaseInboundCambiaLaFaseDeSuViajeCorrespondienteAArrivedCuandoElMismoSeEncuentraEnLaMismaPosicionQueLaTerminal() {
		when(viajeCorrespondiente.distanciaATerminalGestionada()).thenReturn(0d);
		
		faseInbound.actualizarPosicionPara(viajeCorrespondiente);
		
		verify(viajeCorrespondiente).setFase(any(Arrived.class));
	}
	
	@Test
	void testLaFaseInboundCambiaLaFaseDeSuViajeAOutboundNuevamenteSiLaDistanciaALaTerminalEsMayorOIgualA50Km() {
		when(viajeCorrespondiente.distanciaATerminalGestionada()).thenReturn(50d);
		
		faseInbound.actualizarPosicionPara(viajeCorrespondiente);
		
		verify(viajeCorrespondiente).setFase(any(Outbound.class));
	}
	
	@Test
	void testLaFaseInboundNoCambiaLaFaseDeSuViajeSiNoEstaExactamenteEnLaTerminal() {
		when(viajeCorrespondiente.distanciaATerminalGestionada()).thenReturn(1d);
		
		faseInbound.actualizarPosicionPara(viajeCorrespondiente);
		
		verify(viajeCorrespondiente, never()).setFase(any()); // que no le hayan intentado cambiar la fase...
	}
	
	@Test
	void testAlIngresarEnEstaFase_LaMismaNotificaASuViajeCorrespondiente() {
		faseInbound.realizarAccionPara(viajeCorrespondiente);
		verify(viajeCorrespondiente).notificarInbound();
	}

}
