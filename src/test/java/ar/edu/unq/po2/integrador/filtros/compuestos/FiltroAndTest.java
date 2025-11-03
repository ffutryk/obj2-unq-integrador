package ar.edu.unq.po2.integrador.filtros.compuestos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Viaje;
import ar.edu.unq.po2.integrador.filtros.IFiltroViaje;

class FiltroAndTest {

	FiltroAnd filtroAnd;
	IFiltroViaje filtroIzquierda;
	IFiltroViaje filtroDerecha;
	Viaje unViaje;

	@BeforeEach
	void setUp() throws Exception {
		filtroIzquierda = mock(IFiltroViaje.class);
		filtroDerecha = mock(IFiltroViaje.class);
		filtroAnd = new FiltroAnd(filtroIzquierda, filtroDerecha);
		unViaje = mock(Viaje.class);
	}
	
	@Test
	void testUnFiltroAndCumpleCuandoAmbosFiltrosCumplen() {
		when(filtroIzquierda.cumpleFiltro(unViaje)).thenReturn(true);
		when(filtroDerecha.cumpleFiltro(unViaje)).thenReturn(true);
		
		assertTrue(filtroAnd.cumpleFiltro(unViaje));
	}
	
	@Test
	void testUnFiltroAndNoCumpleCuandoElFiltroIzquierdoNoCumple() {
		when(filtroIzquierda.cumpleFiltro(unViaje)).thenReturn(false);
		when(filtroDerecha.cumpleFiltro(unViaje)).thenReturn(true);
		
		assertFalse(filtroAnd.cumpleFiltro(unViaje));
	}
	
	@Test
	void testUnFiltroAndNoCumpleCuandoElFiltroDerechoNoCumple() {
		when(filtroIzquierda.cumpleFiltro(unViaje)).thenReturn(true);
		when(filtroDerecha.cumpleFiltro(unViaje)).thenReturn(false);
		
		assertFalse(filtroAnd.cumpleFiltro(unViaje));
	}
	
	@Test
	void testUnFiltroAndNoCumpleCuandoNingunoDeLosFiltrosCumple() {
		when(filtroIzquierda.cumpleFiltro(unViaje)).thenReturn(false);
		when(filtroDerecha.cumpleFiltro(unViaje)).thenReturn(false);
		
		assertFalse(filtroAnd.cumpleFiltro(unViaje));
	}
}
