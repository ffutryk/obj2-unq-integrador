package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import org.junit.jupiter.api.Test;

class TramoTest {

	@Test
	void testUnTramoPuedeResponderCuantoTiempoTardaEnSerRecorrido() {
		Terminal origen = mock(Terminal.class);
		Terminal destino = mock(Terminal.class);
		Tramo unTramo = new Tramo(origen, destino, Duration.of(30, ChronoUnit.HOURS), 10.7d);
		assertEquals(Duration.of(30, ChronoUnit.HOURS), unTramo.getDuracion());
	}
	
	@Test
	void testUnTramoPuedeResponderCualEsSuTerminalOrigen() {
		Terminal origen = mock(Terminal.class);
		Terminal destino = mock(Terminal.class);
		Tramo unTramo = new Tramo(origen, destino, Duration.of(30, ChronoUnit.HOURS), 10.7d);
		assertEquals(origen, unTramo.getOrigen());
	}
	
	@Test
	void testUnTramoPuedeResponderCualEsSuTerminalDestino() {
		Terminal origen = mock(Terminal.class);
		Terminal destino = mock(Terminal.class);
		Tramo unTramo = new Tramo(origen, destino, Duration.of(30, ChronoUnit.HOURS), 10.7d);
		assertEquals(destino, unTramo.getDestino());
	}
	
	@Test
	void testUnTramoPuedeResponderCualEsSuCosto() {
		Terminal origen = mock(Terminal.class);
		Terminal destino = mock(Terminal.class);
		Tramo unTramo = new Tramo(origen, destino, Duration.of(30, ChronoUnit.HOURS), 10.7d);
		assertEquals(10.7, unTramo.getCosto());
	}
}
