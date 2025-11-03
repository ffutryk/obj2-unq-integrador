package ar.edu.unq.po2.integrador.filtros.comparadores;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuranteLaFechaTest {

	DuranteLaFecha estrategia;
	LocalDateTime fechaReferencia;
	
	@BeforeEach
	void setUp() throws Exception {
		fechaReferencia = LocalDateTime.of(2024, 5, 10, 0, 0);
		estrategia = new DuranteLaFecha(fechaReferencia);
	}
	
	@Test
	void testUnaFechaIgualCumpleLaCondicion() {
		LocalDateTime fecha = LocalDateTime.of(2024, 5, 10, 0, 0);
		assertTrue(estrategia.compararCon(fecha));
	}
	
	@Test
	void testUnaFechaAnteriorNoCumpleLaCondicion() {
		LocalDateTime fecha = LocalDateTime.of(2024, 5, 9, 0, 0);
		assertFalse(estrategia.compararCon(fecha));
	}
	
	@Test
	void testUnaFechaPosteriorNoCumpleLaCondicion() {
		LocalDateTime fecha = LocalDateTime.of(2024, 5, 11, 0, 0);
		assertFalse(estrategia.compararCon(fecha));
	}
}
