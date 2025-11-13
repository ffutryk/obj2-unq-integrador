package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.reportes.VisitanteReportable;

class BuqueTest {

	@Test
	void testUnBuqueSabeResponderSuUbicacionGeografica() {
		IGPS gps = spy(IGPS.class);
		PosicionGeografica pos = mock(PosicionGeografica.class);
		Buque unBuque = new Buque(gps, "lorem ipsum");
		unBuque.actualizarPosicion(pos);
		assertEquals(pos, unBuque.getPosicion());
		verify(gps).posicionDe(unBuque);
	}
	
	@Test
	void testUnBuquePuedeActualizarSuPosicion() {
		IGPS gps = spy(IGPS.class);
		PosicionGeografica posInicial = mock(PosicionGeografica.class);
		Buque unBuque = new Buque(gps, "lorem ipsum");
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
		Buque unBuque = new Buque(gps, "lorem ipsum");
		unBuque.asignarViaje(unViaje);
		
		unBuque.actualizarPosicion(mock(PosicionGeografica.class));
		
		verify(unViaje).actualizarPosicion();
	}
	
	@Test
	void testUnBuqueSabeResponderSuNombre() {
		IGPS gps = spy(IGPS.class);
		Buque unBuque = new Buque(gps, "lorem ipsum");
		assertEquals("lorem ipsum", unBuque.getNombre());
	}
	
	@Test
	void testUnBuqueSabeResponderSuViajeAsociado() {
		Viaje unViaje = mock(Viaje.class);
		IGPS gps = spy(IGPS.class);
		Buque unBuque = new Buque(gps, "lorem ipsum");
		unBuque.asignarViaje(unViaje);
		assertEquals(unViaje, unBuque.getViaje());
	}
	
	@Test
	void testUnBuqueDelegaUnaVisitaEnSuVisitante() {
		Viaje unViaje = mock(Viaje.class);
		IGPS gps = spy(IGPS.class);
		Buque unBuque = new Buque(gps, "lorem ipsum");
		unBuque.asignarViaje(unViaje);
		VisitanteReportable visitante = mock(VisitanteReportable.class);
		
		unBuque.aceptar(visitante);
		
		verify(visitante).visitar(unBuque);
	}
	
	@Test
	void testLaCargayDescargaDeUnBuqueNoInteractuaConSuViajeAsociado() {
		Viaje unViaje = mock(Viaje.class);
		IGPS gps = spy(IGPS.class);
		Buque unBuque = new Buque(gps, "lorem ipsum");
		unBuque.asignarViaje(unViaje);
		
		unBuque.cargaYDescarga();
		
		verifyNoInteractions(unViaje);
	}
	
	
}
