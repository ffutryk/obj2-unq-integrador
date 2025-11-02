package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class NavieraTest {

	@Test
	void testUnaNavieraPuedeResponderLosCircuitosQueOfrece() {
		Circuito circuito1 = mock(Circuito.class);
		Circuito circuito2 = mock(Circuito.class);
		Circuito circuito3 = mock(Circuito.class);
		Buque buque = mock(Buque.class);
		Set<Circuito> circuitos = new HashSet<Circuito>();
		circuitos.add(circuito1);
		circuitos.add(circuito2);
		circuitos.add(circuito3);
		Naviera unaNaviera = new Naviera("Naviera del Sur", circuitos, Arrays.asList(buque));
		assertEquals(circuitos, unaNaviera.getCircuitos());
	}

}
