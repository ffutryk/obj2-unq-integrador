package ar.edu.unq.po2.integrador.fases;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.TerminalGestionada;

class ArrivedTest {

	FaseDeViaje faseArrived;
	Viaje viajeCorrespondiente;
	TerminalGestionada gestionada;
	
	@BeforeEach
	void setUp() throws Exception {
		faseArrived = new Arrived();
		viajeCorrespondiente = mock(Viaje.class);
		gestionada = mock(TerminalGestionada.class);
		when(viajeCorrespondiente.getGestionada()).thenReturn(gestionada);
	}

	@Test
	void testLaFaseNoInteractuaConElViajeAlActualizarPosicion() {
		faseArrived.actualizarPosicionPara(viajeCorrespondiente);
		verifyNoInteractions(viajeCorrespondiente);
	}
	
	@Test
	void testLaFaseNotificaALaTerminalElArriboDelViaje() {
		faseArrived.realizarAccionPara(viajeCorrespondiente);
		verify(gestionada).registrarArribo(viajeCorrespondiente);
	}
	
	@Test
	void testAlDarleLaOrdenDeTrabajar_SeLeCambiaLaFaseAlViajeCorrespondiente() {
		faseArrived.trabajar(viajeCorrespondiente);
		verify(viajeCorrespondiente).setFase(any(Working.class));
	}
	
}
