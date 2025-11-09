package ar.edu.unq.po2.integrador.filtros.criterios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Terminal;
import ar.edu.unq.po2.integrador.Viaje;

class FiltroPorDestinoTest {

	FiltroPorDestino filtro;
	Terminal destino;
	Viaje viaje;

	@BeforeEach
	void setUp() throws Exception {
		destino = mock(Terminal.class);
		viaje = mock(Viaje.class);
		filtro = new FiltroPorDestino(destino);
	}

	@Test
	void testCumpleFiltroDevuelveTrueCuandoViajePasaPorLaTerminal() {
		when(viaje.pasaPorLaTerminal(destino)).thenReturn(true);

		assertTrue(filtro.cumpleFiltro(viaje));
	}

	@Test
	void testCumpleFiltroDevuelveFalseCuandoViajeNoPasaPorLaTerminal() {
		when(viaje.pasaPorLaTerminal(destino)).thenReturn(false);

		assertFalse(filtro.cumpleFiltro(viaje));
	}
}
