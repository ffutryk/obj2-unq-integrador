package ar.edu.unq.po2.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Circuito;
import ar.edu.unq.po2.integrador.Terminal;
import ar.edu.unq.po2.integrador.busqueda.estrategias.EstrategiaBusquedaCircuito;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.filtros.IFiltroViaje;

class MotorDeBusquedaTest {

    ICircuitosProveedor proveedorCircuitos;
    IViajesProveedor proveedorViajes;
    EstrategiaBusquedaCircuito estrategia;
    MotorDeBusqueda motor;

    Terminal destino;
    Circuito c1, c2;
    Viaje v1, v2, v3;
    IFiltroViaje filtro;

    @BeforeEach
    void setUp() {
        proveedorCircuitos = mock(ICircuitosProveedor.class);
        proveedorViajes = mock(IViajesProveedor.class);
        estrategia = mock(EstrategiaBusquedaCircuito.class);

        motor = new MotorDeBusqueda(proveedorCircuitos, proveedorViajes, estrategia);

        destino = mock(Terminal.class);
        c1 = mock(Circuito.class);
        c2 = mock(Circuito.class);

        v1 = mock(Viaje.class);
        v2 = mock(Viaje.class);
        v3 = mock(Viaje.class);

        filtro = mock(IFiltroViaje.class);
    }

    @Test
    void buscarViajesFiltraCorrectamente() {
        when(proveedorViajes.viajesDisponibles()).thenReturn(List.of(v1, v2, v3));
        when(filtro.cumpleFiltro(v1)).thenReturn(true);
        when(filtro.cumpleFiltro(v2)).thenReturn(false);
        when(filtro.cumpleFiltro(v3)).thenReturn(true);

        List<Viaje> resultado = motor.buscarViajes(filtro);

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(v1));
        assertTrue(resultado.contains(v3));
        assertFalse(resultado.contains(v2));
    }

    @Test
    void mejorCircuitoHaciaUsaEstrategia() {
        when(proveedorCircuitos.circuitosDisponibles()).thenReturn(List.of(c1, c2));
        when(estrategia.mejorCircuitoHacia(List.of(c1, c2), destino)).thenReturn(c2);

        Circuito resultado = motor.mejorCircuitoHacia(destino);

        assertEquals(c2, resultado);
        verify(estrategia).mejorCircuitoHacia(List.of(c1, c2), destino);
    }

    @Test
    void cambiarEstrategiaBusquedaFunciona() {
        EstrategiaBusquedaCircuito nuevaEstrategia = mock(EstrategiaBusquedaCircuito.class);
        motor.setEstrategiaBusqueda(nuevaEstrategia);

        when(proveedorCircuitos.circuitosDisponibles()).thenReturn(List.of(c1, c2));
        when(nuevaEstrategia.mejorCircuitoHacia(List.of(c1, c2), destino)).thenReturn(c1);

        Circuito resultado = motor.mejorCircuitoHacia(destino);

        assertEquals(c1, resultado);
        verify(nuevaEstrategia).mejorCircuitoHacia(List.of(c1, c2), destino);
    }

    @Test
    void buscarViajesSinResultadosDevuelveListaVacia() {
        when(proveedorViajes.viajesDisponibles()).thenReturn(List.of(v1, v2, v3));
        when(filtro.cumpleFiltro(any())).thenReturn(false);

        List<Viaje> resultado = motor.buscarViajes(filtro);

        assertTrue(resultado.isEmpty());
    }
}
