package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		when(t1.getDuracion()).thenReturn(Duration.of(30, ChronoUnit.HOURS));
		when(t2.getDuracion()).thenReturn(Duration.of(30, ChronoUnit.HOURS));
		unCircuito = new Circuito(origen, tramos);
		assertEquals(Duration.of(60, ChronoUnit.HOURS), unCircuito.duracionTotal());
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
	void testUnCircuitoSabeResponderElCostoDeTrasladoEntreDosTerminalesQueLoComponen_SeaCualSeaElOrdenDado() {
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
		assertEquals(54.7d, unCircuito.costoEntre(t2, t4), 0.0001d);
	}
	
	@Test
	void testUnCircuitoSabeResponderLaDuracionDelRecorridoEntreDosTerminalesQueLoComponen() {
        Terminal origen = mock(Terminal.class);
        Terminal destino = mock(Terminal.class);

        Tramo t1 = mock(Tramo.class);
        Tramo t2 = mock(Tramo.class);
        Tramo t3 = mock(Tramo.class);
        Tramo t4 = mock(Tramo.class);
        Tramo t5 = mock(Tramo.class);

        ArrayList<Tramo> tramos = new ArrayList<>();
        tramos.add(t1);
        tramos.add(t2);
        tramos.add(t3);
        tramos.add(t4);
        tramos.add(t5);

        when(t1.getDuracion()).thenReturn(Duration.ofHours(2));
        when(t2.getDuracion()).thenReturn(Duration.ofHours(4));
        when(t3.getDuracion()).thenReturn(Duration.ofHours(6));
        when(t4.getDuracion()).thenReturn(Duration.ofHours(8));
        when(t5.getDuracion()).thenReturn(Duration.ofHours(10));

        when(t1.contieneA(origen)).thenReturn(false);
        when(t2.contieneA(origen)).thenReturn(true); 
        when(t3.contieneA(origen)).thenReturn(false);
        when(t4.contieneA(origen)).thenReturn(false);
        when(t5.contieneA(origen)).thenReturn(false);

        when(t1.contieneA(destino)).thenReturn(false);
        when(t2.contieneA(destino)).thenReturn(false);
        when(t3.contieneA(destino)).thenReturn(false);
        when(t4.contieneA(destino)).thenReturn(true); 
        when(t5.contieneA(destino)).thenReturn(false);

        Circuito unCircuito = new Circuito(origen, tramos);
        Duration duracionEsperada = Duration.ofHours(18);

        assertEquals(duracionEsperada, unCircuito.duracionEntre(origen, destino));
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
		when(t1.getDuracion()).thenReturn(Duration.of(2, ChronoUnit.HOURS));
		when(t2.getDuracion()).thenReturn(Duration.of(4, ChronoUnit.HOURS));
		when(t3.getDuracion()).thenReturn(Duration.of(6, ChronoUnit.HOURS));
		when(t4.getDuracion()).thenReturn(Duration.of(8, ChronoUnit.HOURS));
		when(t5.getDuracion()).thenReturn(Duration.of(10, ChronoUnit.HOURS));
		unCircuito = new Circuito(origen, tramos);
		Circuito otroCircuito = new Circuito(origen, tramos);
		assertTrue(unCircuito.equals(otroCircuito));
	}

	@Test
	void testIncluyeLaTerminalDevuelveTrueSiLaTerminalEsOrigenDeAlgunoDeLosTramos() {
	    Terminal origen = mock(Terminal.class);
	    Terminal destino = mock(Terminal.class);
	    Tramo tramo = spy(Tramo.class);
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
	    Tramo tramo = spy(Tramo.class);
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
	
	@Test
	void testNoSePuedePedirElCostoEntreDosTramosSiAlgunoDeLosTramosDadosNoPertenceAlCircuito() {
		Terminal origen = mock(Terminal.class);
		Tramo t1 = mock(Tramo.class);
		Tramo t2 = mock(Tramo.class);
		Tramo t3 = mock(Tramo.class);
		Tramo t4 = mock(Tramo.class);
		Tramo tramoFantasma = mock(Tramo.class);
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
		assertThrows(RuntimeException.class, () -> unCircuito.costoEntre(tramoFantasma, t2));
	}
	
	@Test
	void testUnCircuitoPuedeResponderLaDuracionHastaUnaTerminalQueFormeParteDelMismo() {
		Terminal origen = mock(Terminal.class);
		Tramo t1 = mock(Tramo.class);
		Tramo t2 = mock(Tramo.class);
		Tramo t3 = mock(Tramo.class);
		Terminal terminalBuscada = mock(Terminal.class);
		when(t1.getDuracion()).thenReturn(Duration.ofHours(2));
		when(t1.contieneA(origen)).thenReturn(true);
		when(t2.getDuracion()).thenReturn(Duration.ofHours(3));
		when(t3.getDuracion()).thenReturn(Duration.ofHours(5));
		when(t2.contieneA(terminalBuscada)).thenReturn(true);
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(t1);
		tramos.add(t2);
		tramos.add(t3);
		unCircuito = new Circuito(origen, tramos);
		assertEquals(Duration.ofHours(5), unCircuito.duracionHasta(terminalBuscada));
	}
	
	@Test
	void testDosCircuitosIgualesTienenElMismoHashCode() {
		Terminal origen = mock(Terminal.class);
		List<Tramo> tramos = Arrays.asList(mock(Tramo.class));
		Circuito circuito1 = new Circuito(origen, tramos);
		Circuito circuito2 = new Circuito(origen, tramos);
		assertTrue(circuito1.hashCode() == circuito2.hashCode());
	}
	
	@Test
	void testUnCircuitoPuedeResponderCuantasParadasHayHastaUnaTerminalDadaDelCircuito() {
		Terminal origen = mock(Terminal.class);
		Terminal terminalBuscada = mock(Terminal.class);
		Tramo t1 = mock(Tramo.class);
		Tramo t2 = mock(Tramo.class);
		when(t2.contieneA(terminalBuscada)).thenReturn(true);
		List<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(t1);
		tramos.add(t2);
		unCircuito = new Circuito(origen, tramos);
		assertEquals(2, unCircuito.cantidadDeParadasHasta(terminalBuscada));
	}
}
