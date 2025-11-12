package ar.edu.unq.po2.integrador.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DesgloseDeServicioTest {
	
	private DesgloseDeServicio desglose;
    private LocalDateTime fecha;
    
    @BeforeEach
    void setUp() {
        fecha = LocalDateTime.of(2025, 11, 11, 14, 30);
        desglose = new DesgloseDeServicio("ServicioLavado", fecha, 250.0);
    }
    
    @Test
    void testConstructorYGetters() {
        assertEquals("ServicioLavado", desglose.getNombre());
        assertEquals(fecha, desglose.getFecha());
        assertEquals(250.0, desglose.getCosto());
    }

    @Test
    void testImprimirDevuelveFormatoCorrecto() {
        String resultado = desglose.imprimir();
        assertTrue(resultado.startsWith("\tServicioLavado"));
        assertTrue(resultado.contains(fecha.toString()));
        assertTrue(resultado.contains("250.0"));
        assertTrue(resultado.endsWith("\n"));
    }
}
