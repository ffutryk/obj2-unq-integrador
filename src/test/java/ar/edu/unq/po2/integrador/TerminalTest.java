package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.fases.Viaje;

class TerminalTest {
	
	@Test
	void testUnaTerminalSabeResponderSuUbicacionGeografica() {
		PosicionGeografica pos = mock(PosicionGeografica.class);
		Terminal unaTerminal = new Terminal("Terminal BsAs", pos);
		assertEquals(pos, unaTerminal.getUbicacion());
	}
	
	@Test
	void testUnaTerminalSabeResponderSuNombre() {
		PosicionGeografica pos = mock(PosicionGeografica.class);
		Terminal unaTerminal = new Terminal("Terminal BsAs", pos);
		assertEquals("Terminal BsAs", unaTerminal.getNombre());
	}
	
	@Test
	void testUnaTerminalSabeResponderSiEsIgualAOtraTerminal() {
		PosicionGeografica pos = mock(PosicionGeografica.class);
		Terminal unaTerminal = new Terminal("Terminal BsAs", pos);
		PosicionGeografica otraPos = mock(PosicionGeografica.class);
		Terminal otraTerminal = new Terminal("Terminal BsAs", otraPos);
		assertTrue(unaTerminal.equals(otraTerminal));
	}
	
	@Test
	void testUnaTerminalPuedeRegistrarElArriboDeUnViajeDeterminado() {
		PosicionGeografica pos = mock(PosicionGeografica.class);
		Terminal unaTerminal = new Terminal("Terminal BsAs", pos);
		unaTerminal.registrarArribo(mock(Viaje.class));
		assertEquals(1, unaTerminal.cantidadDeArribados());
	}
}
