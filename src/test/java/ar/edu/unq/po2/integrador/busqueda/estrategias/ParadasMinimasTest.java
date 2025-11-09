package ar.edu.unq.po2.integrador.busqueda.estrategias;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Circuito;
import ar.edu.unq.po2.integrador.Terminal;

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
        when(c1.cantidadDeParadasHasta(destino)).thenReturn(4);
        when(c2.cantidadDeParadasHasta(destino)).thenReturn(2);
        when(c3.cantidadDeParadasHasta(destino)).thenReturn(3);

        ParadasMinimas estrategia = new ParadasMinimas();
        Circuito resultado = estrategia.mejorCircuitoHacia(List.of(c1, c2, c3), destino);

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
}
