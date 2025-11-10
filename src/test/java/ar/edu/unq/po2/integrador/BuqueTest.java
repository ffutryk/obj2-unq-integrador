package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.fases.Viaje;

class BuqueTest {

	@Test
	void testUnBuqueSabeResponderSuUbicacionGeografica() {
		IGPS gps = spy(IGPS.class);
		PosicionGeografica pos = mock(PosicionGeografica.class);
		Buque unBuque = new Buque(gps);
		unBuque.actualizarPosicion(pos);
		assertEquals(pos, unBuque.getPosicion());
		verify(gps).posicionDe(unBuque);
	}
	
	@Test
	void testUnBuquePuedeActualizarSuPosicion() {
		IGPS gps = spy(IGPS.class);
		PosicionGeografica posInicial = mock(PosicionGeografica.class);
		Buque unBuque = new Buque(gps);
		unBuque.actualizarPosicion(posInicial);
		PosicionGeografica posNueva = mock(PosicionGeografica.class);
		when(gps.posicionDe(unBuque)).thenReturn(posNueva);
		
		unBuque.actualizarPosicion(gps.posicionDe(unBuque));
		
		assertEquals(posNueva, unBuque.getPosicion());
	}
	
	@Test
	void testAlActualizarLaPosicionDeUnBuque_SeActualizaLaPosicionDelViajeQueTieneAsignado() {
		Viaje unViaje = mock(Viaje.class);
		IGPS gps = spy(IGPS.class);
		PosicionGeografica posInicial = mock(PosicionGeografica.class);
		Buque unBuque = new Buque(gps);
		unBuque.asignarViaje(unViaje);
		
		unBuque.actualizarPosicion(mock(PosicionGeografica.class));
		
		verify(unViaje).actualizarPosicion();
	}
}
