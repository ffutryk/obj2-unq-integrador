package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircuitoTest {
	
	Circuito unCircuito;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSePuedeObtenerLaTerminalOrigenDeUnCircuito() {
		Terminal unaTerminal = mock(Terminal.class);
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		Tramo unTramo = mock(Tramo.class);
		tramos.add(unTramo);
		unCircuito = new Circuito(unaTerminal, tramos);
		assertEquals(unaTerminal, unCircuito.getOrigen());
	}
	
	@Test
	void testNoSePuedeCrearUnCircuitoSinTramos() {
		Terminal unaTerminal = mock(Terminal.class);
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		assertThrows(RuntimeException.class, () -> new Circuito(unaTerminal, tramos));
	}
	
	@Test
	void testLaDuracionDeUnCircuitoEsLaSumaDeLasDuracionesDeSusTramos() {
		// Setup
		Terminal origen = mock(Terminal.class);
		Tramo t1 = mock(Tramo.class);
		Tramo t2 = mock(Tramo.class);
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(t1);
		tramos.add(t2);
		when(t1.getDuracion()).thenReturn(5.0d);
		when(t2.getDuracion()).thenReturn(10.2d);
		unCircuito = new Circuito(origen, tramos);
		assertEquals(15.2, unCircuito.duracionTotal());
	}
	
	@Test
	void testElCostoTotalDeUnCircuitoConsisteEnLaSumaDelCostoDeSusTramos() {
		Terminal origen = mock(Terminal.class);
		Tramo t1 = mock(Tramo.class);
		Tramo t2 = mock(Tramo.class);
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(t1);
		tramos.add(t2);
		when(t1.getCosto()).thenReturn(23.5d);
		when(t2.getCosto()).thenReturn(30.1d);
		unCircuito = new Circuito(origen, tramos);
		assertEquals(53.6, unCircuito.costoTotal());
	}
	
	@Test
	void testUnCircuitoSabeResponderElCostoDeTrasladoEntreDosTerminalesQueLoComponen() {
		Terminal origen = mock(Terminal.class);
		Tramo t1 = mock(Tramo.class);
		Tramo t2 = mock(Tramo.class);
		Tramo t3 = mock(Tramo.class);
		Tramo t4 = mock(Tramo.class);
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(t1);
		tramos.add(t2);
		tramos.add(t3);
		tramos.add(t4);
		when(t1.getCosto()).thenReturn(23.5d);
		when(t2.getCosto()).thenReturn(30.1d);
		when(t3.getCosto()).thenReturn(10.7d);
		when(t4.getCosto()).thenReturn(13.9d);
		unCircuito = new Circuito(origen, tramos);
		assertEquals(54.7d, unCircuito.costoEntre(t4, t2), 0.0001d);
	}
	
	@Test
	void testUnCircuitoSabeResponderLaDuracionDelRecorridoEntreDosTerminalesQueLoComponen() {
		Terminal origen = mock(Terminal.class);
		Tramo t1 = mock(Tramo.class);
		Tramo t2 = mock(Tramo.class);
		Tramo t3 = mock(Tramo.class);
		Tramo t4 = mock(Tramo.class);
		Tramo t5 = mock(Tramo.class);
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(t1);
		tramos.add(t2);
		tramos.add(t3);
		tramos.add(t4);
		tramos.add(t5);
		when(t1.getDuracion()).thenReturn(23.5d);
		when(t2.getDuracion()).thenReturn(10d);
		when(t3.getDuracion()).thenReturn(20.5d);
		when(t4.getDuracion()).thenReturn(13.5d);
		when(t5.getDuracion()).thenReturn(7.5d);		
		unCircuito = new Circuito(origen, tramos);
		assertEquals(44d, unCircuito.duracionEntre(t2, t4), 0.0001d);
	}
	
	@Test
	void testUnCircuitoSabeResponderSiEsIgualAOtroCircuitoDado() {
		Terminal origen = mock(Terminal.class);
		when(origen.getNombre()).thenReturn("bsas");
		Tramo t1 = mock(Tramo.class);
		Tramo t2 = mock(Tramo.class);
		Tramo t3 = mock(Tramo.class);
		Tramo t4 = mock(Tramo.class);
		Tramo t5 = mock(Tramo.class);
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(t1);
		tramos.add(t2);
		tramos.add(t3);
		tramos.add(t4);
		tramos.add(t5);
		when(t1.getDuracion()).thenReturn(23.5d);
		when(t2.getDuracion()).thenReturn(10d);
		when(t3.getDuracion()).thenReturn(20.5d);
		when(t4.getDuracion()).thenReturn(13.5d);
		when(t5.getDuracion()).thenReturn(7.5d);
		unCircuito = new Circuito(origen, tramos);
		Circuito otroCircuito = new Circuito(origen, tramos);
		assertTrue(unCircuito.equals(otroCircuito));
	}

	@Test
	void testIncluyeLaTerminalDevuelveTrueSiLaTerminalEsOrigenDeAlgunoDeLosTramos() {
	    Terminal origen = mock(Terminal.class);
	    Terminal destino = mock(Terminal.class);
	    Tramo tramo = mock(Tramo.class);
	    when(tramo.getOrigen()).thenReturn(origen);
	    when(tramo.getDestino()).thenReturn(destino);
	    ArrayList<Tramo> tramos = new ArrayList<>();
	    tramos.add(tramo);
	    unCircuito = new Circuito(mock(Terminal.class), tramos);

	    assertTrue(unCircuito.incluyeLaTerminal(origen));
	}

	@Test
	void testIncluyeLaTerminalDevuelveTrueSiLaTerminalEsDestinoDeAlgunoDeLosTramos() {
	    Terminal origen = mock(Terminal.class);
	    Terminal destino = mock(Terminal.class);
	    Tramo tramo = mock(Tramo.class);
	    when(tramo.getOrigen()).thenReturn(mock(Terminal.class));
	    when(tramo.getDestino()).thenReturn(destino);
	    ArrayList<Tramo> tramos = new ArrayList<>();
	    tramos.add(tramo);
	    unCircuito = new Circuito(mock(Terminal.class), tramos);

	    assertTrue(unCircuito.incluyeLaTerminal(destino));
	}

	@Test
	void testIncluyeLaTerminalDevuelveFalseSiLaTerminalNoPerteneceANingunTramo() {
	    Terminal terminalNoIncluida = mock(Terminal.class);
	    Tramo tramo = mock(Tramo.class);
	    when(tramo.getOrigen()).thenReturn(mock(Terminal.class));
	    when(tramo.getDestino()).thenReturn(mock(Terminal.class));
	    ArrayList<Tramo> tramos = new ArrayList<>();
	    tramos.add(tramo);
	    unCircuito = new Circuito(mock(Terminal.class), tramos);

	    assertFalse(unCircuito.incluyeLaTerminal(terminalNoIncluida));
	}

}
