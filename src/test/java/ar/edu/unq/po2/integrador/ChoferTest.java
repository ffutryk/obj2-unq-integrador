package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChoferTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testUnChoferSabeResponderSuNombre() {
		Chofer unChofer = new Chofer("Pepito");
		assertEquals("Pepito", unChofer.getNombre());
	}
	
	@Test
	void testUnChoferSabeSiEsIgualAOtroChoferDado() {
		Chofer unChofer = new Chofer("Pepito");
		Chofer otroChofer = new Chofer("Pepita");
		assertFalse(unChofer.equals(otroChofer));
	}
	
	@Test
	void testChoferNoEsIgualANull() {
		Chofer unChofer = new Chofer("Pepito");
		Chofer otroChofer = new Chofer("Pepita");
		assertFalse(unChofer.equals(null));
	}
	
	@Test
	void testChoferNoEsIgualACualquierOtroObjeto() {
		Chofer unChofer = new Chofer("Pepito");
		Chofer otroChofer = new Chofer("Pepita");
		Object o = mock(Object.class);
		assertFalse(unChofer.equals(o));
	}
	
	@Test
	void testChoferIgualAOtroTieneElMismoHashCode() {
		Chofer unChofer = new Chofer("Pepito");
		Chofer otroChofer = new Chofer("Pepito");
		assertEquals(unChofer.hashCode(), otroChofer.hashCode());
	}

}
