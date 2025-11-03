package ar.edu.unq.po2.integrador.filtros.compuestos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Viaje;
import ar.edu.unq.po2.integrador.filtros.IFiltroViaje;

class FiltroOrTest {

	FiltroOr filtroOr;
	IFiltroViaje filtroIzquierda;
	IFiltroViaje filtroDerecha;
	Viaje unViaje;

	@BeforeEach
	void setUp() throws Exception {
		filtroIzquierda = mock(IFiltroViaje.class);
		filtroDerecha = mock(IFiltroViaje.class);
		filtroOr = new FiltroOr(filtroIzquierda, filtroDerecha);
		unViaje = mock(Viaje.class);
	}
	
	@Test
	void testUnFiltroOrCumpleCuandoAmbosFiltrosCumplen() {
		when(filtroIzquierda.cumpleFiltro(unViaje)).thenReturn(true);
		when(filtroDerecha.cumpleFiltro(unViaje)).thenReturn(true);
		
		assertTrue(filtroOr.cumpleFiltro(unViaje));
	}
	
	@Test
	void testUnFiltroCumpleOrCuandoElFiltroIzquierdoCumple() {
		when(filtroIzquierda.cumpleFiltro(unViaje)).thenReturn(true);
		when(filtroDerecha.cumpleFiltro(unViaje)).thenReturn(false);
		
		assertTrue(filtroOr.cumpleFiltro(unViaje));
	}
	
	@Test
	void testUnFiltroCumpleOrCuandoElFiltroDerechoCumple() {
		when(filtroIzquierda.cumpleFiltro(unViaje)).thenReturn(false);
		when(filtroDerecha.cumpleFiltro(unViaje)).thenReturn(true);
		
		assertTrue(filtroOr.cumpleFiltro(unViaje));
	}
	
	@Test
	void testUnFiltroOrNoCumpleCuandoNingunoDeLosFiltrosCumple() {
		when(filtroIzquierda.cumpleFiltro(unViaje)).thenReturn(false);
		when(filtroDerecha.cumpleFiltro(unViaje)).thenReturn(false);
		
		assertFalse(filtroOr.cumpleFiltro(unViaje));
	}
}
