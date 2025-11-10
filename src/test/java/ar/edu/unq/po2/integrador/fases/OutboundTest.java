package ar.edu.unq.po2.integrador.fases;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.fases.FaseDeViaje;
import ar.edu.unq.po2.integrador.fases.Inbound;
import ar.edu.unq.po2.integrador.fases.Outbound;
import ar.edu.unq.po2.integrador.fases.Viaje;

class OutboundTest {
	
	FaseDeViaje faseOutbound;
	Viaje viajeCorrespondiente;
	FaseDeViaje faseSiguiente;
	
	@BeforeEach
	void setUp() throws Exception {
		faseOutbound = new Outbound();
		viajeCorrespondiente = mock(Viaje.class);
		faseSiguiente = mock(Inbound.class);
	}

	@Test
	void testLaFaseOutboundCambiaLaFaseDeSuViajeCorrespondienteCuandoLaDistanciaDelMismoRespectoDeLaTerminalEsMenorA50km() {
		when(viajeCorrespondiente.distanciaATerminalGestionada()).thenReturn(40d);
		
		faseOutbound.actualizarPosicionPara(viajeCorrespondiente);
		
		verify(viajeCorrespondiente).setFase(any(Inbound.class));
	}
	
	@Test
	void testLaFaseOutboundNoCambiaDeFaseSiLaDistanciaEsMayorOIgualA50Km() {
	    when(viajeCorrespondiente.distanciaATerminalGestionada()).thenReturn(70d);

	    faseOutbound.actualizarPosicionPara(viajeCorrespondiente);
	    
	    verify(viajeCorrespondiente, never()).setFase(any());
	}
	
	@Test
	void testLaFaseNoRealizaNingunaAccionConElViajeCorrespondiente() {
		faseOutbound.realizarAccionPara(viajeCorrespondiente);
		verifyNoInteractions(viajeCorrespondiente);
	}

}
