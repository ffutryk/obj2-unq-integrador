package ar.edu.unq.po2.integrador.filtros.criterios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Terminal;
import ar.edu.unq.po2.integrador.Viaje;
import ar.edu.unq.po2.integrador.filtros.comparadores.EstrategiaComparacionFecha;

class FiltroFechaLlegadaTest {

	FiltroFechaLlegada filtro;
	EstrategiaComparacionFecha estrategia;
	Terminal terminal;
	Viaje viaje;

	@BeforeEach
	void setUp() throws Exception {
		terminal = mock(Terminal.class);
		estrategia = mock(EstrategiaComparacionFecha.class);
		viaje = mock(Viaje.class);
		filtro = new FiltroFechaLlegada(estrategia, terminal);
	}

	@Test
	void testCumpleFiltroDevuelveTrueCuandoEstrategiaDevuelveTrue() {
		when(viaje.fechaDeArriboA(terminal)).thenReturn(LocalDateTime.of(2024, 5, 10, 0, 0));
		when(estrategia.compararCon(any())).thenReturn(true);

		assertTrue(filtro.cumpleFiltro(viaje));
	}

	@Test
	void testCumpleFiltroDevuelveFalseCuandoEstrategiaDevuelveFalse() {
		when(viaje.fechaDeArriboA(terminal)).thenReturn(LocalDateTime.of(2024, 5, 10, 0, 0));
		when(estrategia.compararCon(any())).thenReturn(false);

		assertFalse(filtro.cumpleFiltro(viaje));
	}
}