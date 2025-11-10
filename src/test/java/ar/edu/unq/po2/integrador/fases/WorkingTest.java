package ar.edu.unq.po2.integrador.fases;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Buque;

class WorkingTest {

	Buque unBuque;
	Viaje unViaje;
	FaseDeViaje faseWorking;
	
	@BeforeEach
	void setUp() throws Exception {
		unBuque = mock(Buque.class);
		unViaje = mock(Viaje.class);
		when(unViaje.getBuque()).thenReturn(unBuque);
		faseWorking = new Working();
	}

	@Test
	void testLaFaseNoInteractuaConElViajeAlActualizarPosicion() {
		faseWorking.actualizarPosicionPara(unViaje);
		verifyNoInteractions(unViaje);
	}
	
	@Test
	void testLaFaseDaLaOrdenDeCargaYDescargaAlBuqueQueRealizaSuViajeCorrespondiente() {
		faseWorking.realizarAccionPara(unViaje);
		verify(unBuque).cargaYDescarga();
	}
	
	@Test
	void testSeCambiaLaFaseDelViajeCorrespondienteAlDarLaOrdenDeDepartALaFase() {
		faseWorking.depart(unViaje);
		verify(unViaje).setFase(any(Departing.class));
	}

}
