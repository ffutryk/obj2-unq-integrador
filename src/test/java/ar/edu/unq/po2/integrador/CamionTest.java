package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CamionTest {
	
	Camion miCamion;
	
	@BeforeEach
	void setUp() throws Exception {
		miCamion = new Camion("HGP456");
	}

	@Test
	void testUnCamionSabeResponderSuMatricula() {
		assertEquals("HGP456", miCamion.getMatricula());
	}
	
	@Test
	void testUnCamionSabeSiEsIgualAOtroCamion() {
		Camion otroCamion = new Camion("PLM230");
		assertFalse(miCamion.equals(otroCamion));
	}
	
	@Test
	void testUnCamionNoEsIgualANull() {
		assertFalse(miCamion.equals(null));
	}
	
	@Test
	void testUnCamionNoEsIgualACualquierOtroObjeto() {
		Object o = mock(Object.class);
		assertFalse(miCamion.equals(o));
	}
	
	@Test
	void testUnCamionIgualAOtroTienenMismoHashCode() {
		Camion otroCamion = new Camion("HGP456");
		assertEquals(miCamion.hashCode(), otroCamion.hashCode());
	}
}
