package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.fases.Viaje;

class NavieraTest {
	
	Naviera unaNaviera;
	Circuito circuito1;
	Circuito circuito2;
	Circuito circuito3;
	Buque buque;
	Set<Circuito> circuitos;
	Viaje viaje;
	Set<Viaje> viajes;
	
	@BeforeEach
	void setUp() {
		circuito1 = mock(Circuito.class);
		circuito2 = mock(Circuito.class);
		circuito3 = mock(Circuito.class);
		buque = mock(Buque.class);
		circuitos = new HashSet<Circuito>();
		circuitos.add(circuito1);
		circuitos.add(circuito2);
		circuitos.add(circuito3);
		viaje = mock(Viaje.class);
		viajes = new HashSet<Viaje>();
		viajes.add(viaje);
		unaNaviera = new Naviera("Naviera del Sur", circuitos, Arrays.asList(buque), viajes);
	}

	@Test
	void testUnaNavieraPuedeResponderLosCircuitosQueOfrece() {
		assertEquals(circuitos, unaNaviera.getCircuitos());
	}
	
	@Test
	void testUnaNavieraPuedeResponderLosViajesQueOfrece() {
		assertEquals(viajes, unaNaviera.getViajes());
	}
	
	@Test
	void testSePuedenRegistrarViajesEnUnaNaviera() {
		Viaje viajeNuevo = mock(Viaje.class);
		when(viajeNuevo.getCircuito()).thenReturn(circuito1);
		unaNaviera.registrarViaje(viajeNuevo);
		assertTrue(unaNaviera.getViajes().contains(viajeNuevo));
	}
	
	@Test
	void testSePuedenRegistrarCircuitosEnUnaNaviera() {
		Circuito unCircuito = mock(Circuito.class);
		unaNaviera.registrarCircuito(unCircuito);
		assertTrue(unaNaviera.getCircuitos().contains(unCircuito));
	}
	
	@Test
	void testNoSePuedenRegistrarViajesCuyoCircuitoNoEstaRegistrado() {
		Viaje viajeNuevo = mock(Viaje.class);
		Circuito nuevoCircuito = mock(Circuito.class);
		when(viajeNuevo.getCircuito()).thenReturn(nuevoCircuito);
		assertThrows(RuntimeException.class, () -> unaNaviera.registrarViaje(viajeNuevo));	
	}

}
