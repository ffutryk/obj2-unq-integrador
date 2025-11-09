package ar.edu.unq.po2.integrador.filtros.criterios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Viaje;
import ar.edu.unq.po2.integrador.filtros.comparadores.EstrategiaComparacionFecha;

class FiltroFechaSalidaTest {

	FiltroFechaSalida filtro;
	EstrategiaComparacionFecha estrategia;
	Viaje viaje;

	@BeforeEach
	void setUp() throws Exception {
		estrategia = mock(EstrategiaComparacionFecha.class);
		viaje = mock(Viaje.class);
		filtro = new FiltroFechaSalida(estrategia);
	}

	@Test
	void testCumpleFiltroDevuelveTrueCuandoEstrategiaDevuelveTrue() {
		when(viaje.getFechaSalida()).thenReturn(LocalDateTime.of(2024, 5, 10, 0, 0));
		when(estrategia.compararCon(any())).thenReturn(true);

		assertTrue(filtro.cumpleFiltro(viaje));
	}

	@Test
	void testCumpleFiltroDevuelveFalseCuandoEstrategiaDevuelveFalse() {
		when(viaje.getFechaSalida()).thenReturn(LocalDateTime.of(2024, 5, 10, 0, 0));
		when(estrategia.compararCon(any())).thenReturn(false);

		assertFalse(filtro.cumpleFiltro(viaje));
	}
}
