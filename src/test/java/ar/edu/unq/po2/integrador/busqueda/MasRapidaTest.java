package ar.edu.unq.po2.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Circuito;
import ar.edu.unq.po2.integrador.Terminal;

class MasRapidaTest {

    Terminal destino;
    Circuito c1, c2, c3;

    @BeforeEach
    void setUp() {
        destino = mock(Terminal.class);
        c1 = mock(Circuito.class);
        c2 = mock(Circuito.class);
        c3 = mock(Circuito.class);

        // Todos incluyen el destino
        when(c1.incluyeLaTerminal(destino)).thenReturn(true);
        when(c2.incluyeLaTerminal(destino)).thenReturn(true);
        when(c3.incluyeLaTerminal(destino)).thenReturn(true);
    }

    @Test
    void seleccionaCircuitoMasRapido() {
        when(c1.duracionHasta(destino)).thenReturn(Duration.ofHours(5));
        when(c2.duracionHasta(destino)).thenReturn(Duration.ofHours(2));
        when(c3.duracionHasta(destino)).thenReturn(Duration.ofHours(3));

        MasRapida estrategia = new MasRapida();
        Circuito resultado = estrategia.mejorCircuitoHacia(List.of(c1, c2, c3), destino);

        assertEquals(c2, resultado);
    }

    @Test
    void lanzaExcepcionSiNoHayCircuitosDisponibles() {
        MasRapida estrategia = new MasRapida();
        assertThrows(IllegalArgumentException.class,
                () -> estrategia.mejorCircuitoHacia(List.of(), destino));
    }
    
    @Test
    void lanzaExcepcionSiNingunCircuitoIncluyeDestino() {
        when(c1.incluyeLaTerminal(destino)).thenReturn(false);
        when(c2.incluyeLaTerminal(destino)).thenReturn(false);
        when(c3.incluyeLaTerminal(destino)).thenReturn(false);

        MasRapida estrategia = new MasRapida();
        assertThrows(IllegalArgumentException.class,
                () -> estrategia.mejorCircuitoHacia(List.of(c1, c2, c3), destino));
    }
}
