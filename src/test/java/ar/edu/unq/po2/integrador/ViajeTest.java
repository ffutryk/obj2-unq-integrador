 package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViajeTest {

    Viaje unViaje;

    Buque unBuque;
    Circuito unCircuito;
    Terminal unaTerminal;
    LocalDateTime fechaSalida;

    @BeforeEach
    void setUp() throws Exception {
        unBuque = mock(Buque.class);
        unCircuito = mock(Circuito.class);
        unaTerminal = mock(Terminal.class);
        fechaSalida = LocalDateTime.of(2025, 1, 1, 8, 0);
        unViaje = new Viaje(unBuque, unCircuito, fechaSalida, unaTerminal);
    }

    @Test
    void testSePuedeObtenerElBuqueDeUnViaje() {
        assertEquals(unBuque, unViaje.getBuque());
    }

    @Test
    void testSePuedeObtenerElCircuitoDeUnViaje() {
        assertEquals(unCircuito, unViaje.getCircuito());
    }

    @Test
    void testSePuedeObtenerLaFechaDeSalidaDeUnViaje() {
        assertEquals(fechaSalida, unViaje.getFechaSalida());
    }

    @Test
    void testDosViajesSonIgualesSiTienenElMismoBuqueYCircuito() {
        Viaje otroViaje = new Viaje(unBuque, unCircuito, fechaSalida, unaTerminal);
        assertTrue(unViaje.equals(otroViaje));
    }

    @Test
    void testDosViajesDistintosTienenDistintoHashCode() {
        Circuito otroCircuito = mock(Circuito.class);
        Viaje otroViaje = new Viaje(unBuque, otroCircuito, fechaSalida, unaTerminal);
        assertNotEquals(unViaje.hashCode(), otroViaje.hashCode());
    }

    @Test
    void testFechaDeArriboAdevuelveFechaDeSalidaMasDuracionHastaDestino() {
        Terminal destino = mock(Terminal.class);
        when(unCircuito.duracionHasta(destino)).thenReturn(Duration.ofHours(5));

        LocalDateTime fechaEsperada = fechaSalida.plusHours(5);

        assertEquals(fechaEsperada, unViaje.fechaDeArriboA(destino));
    }

    @Test
    void testPasaPorLaTerminalDevuelveTrueSiCircuitoLaIncluye() {
        Terminal terminal = mock(Terminal.class);
        when(unCircuito.incluyeLaTerminal(terminal)).thenReturn(true);

        assertTrue(unViaje.pasaPorLaTerminal(terminal));
    }

    @Test
    void testPasaPorLaTerminalDevuelveFalseSiCircuitoNoLaIncluye() {
        Terminal terminal = mock(Terminal.class);
        when(unCircuito.incluyeLaTerminal(terminal)).thenReturn(false);

        assertFalse(unViaje.pasaPorLaTerminal(terminal));
    }

    @Test
    void testDistanciaATerminalGestionadaSeCalculaConLaPosicionDelBuqueYLaUbicacionDeLaTerminal() {
        PosicionGeografica posicionBuque = mock(PosicionGeografica.class);
        PosicionGeografica posicionTerminal = mock(PosicionGeografica.class);
        when(unBuque.getPosicion()).thenReturn(posicionBuque);
        when(unaTerminal.getUbicacion()).thenReturn(posicionTerminal);
        when(posicionBuque.distanciaHasta(posicionTerminal)).thenReturn(120.5);

        double distancia = unViaje.distanciaATerminalGestionada();

        assertEquals(120.5, distancia);
    }

    @Test
    void testCuandoUnViajeSeEncuentraAMenosDe50KmDeLaTerminalGestionada_SeAvisaALaTerminalSobreElInminenteArribo() {
    	PosicionGeografica posicionBuque = mock(PosicionGeografica.class);
        PosicionGeografica posicionTerminal = mock(PosicionGeografica.class);
        when(unBuque.getPosicion()).thenReturn(posicionBuque);
        when(unaTerminal.getUbicacion()).thenReturn(posicionTerminal);
        when(posicionBuque.distanciaHasta(posicionTerminal)).thenReturn(40d);
    	unViaje.actualizarPosicion();
  
    	verify(unaTerminal).anunciarInminenteLlegada(unViaje);
    }
    
    @Test
    void testCuandoUnViajeSeEncuentraEnLaTerminalGestionada_SeRegistraSuArriboEnLaTerminal() {
    	PosicionGeografica posicionBuque = mock(PosicionGeografica.class);
        PosicionGeografica posicionTerminal = mock(PosicionGeografica.class);
        when(unBuque.getPosicion()).thenReturn(posicionBuque);
        when(unaTerminal.getUbicacion()).thenReturn(posicionTerminal);
        when(posicionBuque.distanciaHasta(posicionTerminal)).thenReturn(0d);
        unViaje.actualizarPosicion(); // Simular primera actualizacion del GPS, pasando a Inbound...
        
        unViaje.actualizarPosicion(); // Simular segunda actualizacion del GPS, pasando a Arrived...
        verify(unaTerminal).registrarArribo(unViaje);
    }
}
 