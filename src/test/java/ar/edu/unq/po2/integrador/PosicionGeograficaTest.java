package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PosicionGeograficaTest {

	@Test
	void testUnaPosicionGeograficaSabeResponderAQueDistanciaSeEncuentraDeOtraPosicionGeografica() {
		PosicionGeografica puertoBsAs = new PosicionGeografica(34d, 58d);
		PosicionGeografica puertoRosario = new PosicionGeografica(32d, 60d);
		assertEquals(300d, puertoBsAs.distanciaHasta(puertoRosario), 10d);
	}

}
