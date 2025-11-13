package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmpresaTransportistaTest {
	
	EmpresaTransportista empresa;
	Camion unCamion;
	Chofer unChofer;
	
	@BeforeEach
	void setUp() throws Exception {
		unCamion = mock(Camion.class);
		empresa = new EmpresaTransportista(new ArrayList<Camion>(), new ArrayList<Chofer>(), "20-45333111-1");
		unChofer = mock(Chofer.class);
	}
	
	@Test
	void testUnaEmpresaSabeResponderSuCuit() {
		assertEquals("20-45333111-1", empresa.getCuit());
	}
	
	@Test
	void testUnaEmpresaSabeSiTieneRegistradoAUnCamion() {
		assertFalse(empresa.tieneRegistradaA(unCamion));
	}
	
	@Test
	void testUnaEmpresaPuedeRegistrarNuevosCamiones() {
		empresa.registrar(unCamion);
		assertTrue(empresa.tieneRegistradaA(unCamion));
	}
	
	@Test
	void testUnaEmpresaSabeSiTieneRegistradoAUnChofer() {
		assertFalse(empresa.tieneRegistradaA(unChofer));
	}
	
	@Test
	void testUnaEmpresaPuedeRegistrarNuevosChoferes() {
		empresa.registrar(unChofer);
		assertTrue(empresa.tieneRegistradaA(unChofer));
	}

}
