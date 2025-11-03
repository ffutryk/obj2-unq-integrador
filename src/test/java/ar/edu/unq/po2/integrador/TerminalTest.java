package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class TerminalTest {
	
	@Test
	void testUnaTerminalSabeResponderSuUbicacionGeografica() {
		PosicionGeografica pos = mock(PosicionGeografica.class);
		Terminal unaTerminal = new Terminal("Terminal BsAs", pos);
		assertEquals(pos, unaTerminal.getUbicacion());
	}

}
