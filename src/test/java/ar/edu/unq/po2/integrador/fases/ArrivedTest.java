package ar.edu.unq.po2.integrador.fases;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.fases.Arrived;
import ar.edu.unq.po2.integrador.fases.FaseDeViaje;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.fases.Working;

class ArrivedTest {

	FaseDeViaje faseArrived;
	Viaje viajeCorrespondiente;
	
	@BeforeEach
	void setUp() throws Exception {
		faseArrived = new Arrived();
		viajeCorrespondiente = mock(Viaje.class);
	}

	@Test
	void testLaFaseNoInteractuaConElViajeAlActualizarPosicion() {
		faseArrived.actualizarPosicionPara(viajeCorrespondiente);
		verifyNoInteractions(viajeCorrespondiente);
	}
	
	@Test
	void testLaFaseNotificaAlViajeQueEntroEnElla() {
		faseArrived.realizarAccionPara(viajeCorrespondiente);
		verify(viajeCorrespondiente).notificarArrived();
	}
	
	@Test
	void testAlDarleLaOrdenDeTrabajar_SeLeCambiaLaFaseAlViajeCorrespondiente() {
		faseArrived.trabajar(viajeCorrespondiente);
		verify(viajeCorrespondiente).setFase(any(Working.class));
	}
	
}
