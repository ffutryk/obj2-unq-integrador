package ar.edu.unq.po2.integrador.busqueda.estrategias;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Circuito;
import ar.edu.unq.po2.integrador.Terminal;
import ar.edu.unq.po2.integrador.Tramo;

class ParadasMinimasTest {

    Terminal destino;
    Circuito c1, c2, c3;

    @BeforeEach
    void setUp() {
        destino = mock(Terminal.class);
        c1 = mock(Circuito.class);
        c2 = mock(Circuito.class);
        c3 = mock(Circuito.class);

        when(c1.incluyeLaTerminal(destino)).thenReturn(true);
        when(c2.incluyeLaTerminal(destino)).thenReturn(true);
        when(c3.incluyeLaTerminal(destino)).thenReturn(true);
    }

    @Test
    void seleccionaCircuitoConMenosParadas() {
        ParadasMinimas estrategia = new ParadasMinimas();
        ParadasMinimas spy = spy(estrategia);
        
        doReturn(4).when(spy).cantidadDeParadasPorHasta(c1, destino);
        doReturn(2).when(spy).cantidadDeParadasPorHasta(c2, destino);
        doReturn(3).when(spy).cantidadDeParadasPorHasta(c3, destino);
        
        Circuito resultado = spy.mejorCircuitoHacia(List.of(c1, c2, c3), destino);

        assertEquals(c2, resultado);
    }

    @Test
    void lanzaExcepcionSiListaVacia() {
        ParadasMinimas estrategia = new ParadasMinimas();
        assertThrows(IllegalArgumentException.class,
                () -> estrategia.mejorCircuitoHacia(List.of(), destino));
    }
    
    @Test
    void lanzaExcepcionSiNingunCircuitoIncluyeDestino() {
        when(c1.incluyeLaTerminal(destino)).thenReturn(false);
        when(c2.incluyeLaTerminal(destino)).thenReturn(false);
        when(c3.incluyeLaTerminal(destino)).thenReturn(false);

        ParadasMinimas estrategia = new ParadasMinimas();
        assertThrows(IllegalArgumentException.class,
                () -> estrategia.mejorCircuitoHacia(List.of(c1, c2, c3), destino));
    }

    @Test
    void devuelveCantidadCorrectaDeParadasHastaLaTerminal() {
        ParadasMinimas estrategia = new ParadasMinimas();
        Circuito circuito = mock(Circuito.class);
        Tramo t1 = mock(Tramo.class);
        Tramo t2 = mock(Tramo.class);
        Tramo t3 = mock(Tramo.class);

        when(circuito.tramos()).thenReturn(List.of(t1, t2, t3));
        when(t1.contieneA(destino)).thenReturn(false);
        when(t2.contieneA(destino)).thenReturn(true);
        when(t3.contieneA(destino)).thenReturn(false);

        int resultado = estrategia.cantidadDeParadasPorHasta(circuito, destino);

        assertEquals(2, resultado);
    }

    @Test
    void devuelveUnoSiLaTerminalEstaEnElPrimerTramo() {
        ParadasMinimas estrategia = new ParadasMinimas();
        Circuito circuito = mock(Circuito.class);
        Tramo t1 = mock(Tramo.class);
        Tramo t2 = mock(Tramo.class);

        when(circuito.tramos()).thenReturn(List.of(t1, t2));
        when(t1.contieneA(destino)).thenReturn(true);

        int resultado = estrategia.cantidadDeParadasPorHasta(circuito, destino);

        assertEquals(1, resultado);
    }

    @Test
    void lanzaExcepcionSiLaTerminalNoPerteneceAlCircuito() {
        ParadasMinimas estrategia = new ParadasMinimas();
        Circuito circuito = mock(Circuito.class);
        Tramo t1 = mock(Tramo.class);
        Tramo t2 = mock(Tramo.class);

        when(circuito.tramos()).thenReturn(List.of(t1, t2));
        when(t1.contieneA(destino)).thenReturn(false);
        when(t2.contieneA(destino)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> estrategia.cantidadDeParadasPorHasta(circuito, destino));
    }

    @Test
    void lanzaExcepcionSiCircuitoNoTieneTramos() {
        ParadasMinimas estrategia = new ParadasMinimas();
        Circuito circuito = mock(Circuito.class);
        when(circuito.tramos()).thenReturn(List.of());

        assertThrows(RuntimeException.class,
                () -> estrategia.cantidadDeParadasPorHasta(circuito, destino));
    }
}
