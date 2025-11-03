package ar.edu.unq.po2.integrador.filtros.comparadores;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.filtros.comparadores.AntesDeFecha;

class AntesDeFechaTest {

	AntesDeFecha estrategia;
	LocalDateTime fechaReferencia;
	
	@BeforeEach
	void setUp() throws Exception {
		fechaReferencia = LocalDateTime.of(2024, 5, 10, 0, 0);
		estrategia = new AntesDeFecha(fechaReferencia);
	}
	
	@Test
	void testUnaFechaAnteriorCumpleLaCondicion() {
		LocalDateTime fecha = LocalDateTime.of(2024, 5, 1, 0, 0);
		assertTrue(estrategia.compararCon(fecha));
	}
	
	@Test
	void testUnaFechaPosteriorNoCumpleLaCondicion() {
		LocalDateTime fecha = LocalDateTime.of(2024, 5, 20, 0, 0);
		assertFalse(estrategia.compararCon(fecha));
	}
	
	@Test
	void testUnaFechaIgualNoCumpleLaCondicion() {
		LocalDateTime fecha = LocalDateTime.of(2024, 5, 10, 0, 0);
		assertFalse(estrategia.compararCon(fecha));
	}
}
