package ar.edu.unq.po2.integrador.servicios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

import ar.edu.unq.po2.integrador.containers.Tanque;
import ar.edu.unq.po2.ordenes.Orden;
import ar.edu.unq.po2.ordenes.OrdenImportacion;
import ar.edu.unq.po2.integrador.containers.Container;

public class ServicioPesadoTest {
	
	private ServicioPesado servicioP;
    private Container container;
    private OrdenImportacion ordenI;
    
    @BeforeEach
    void setUp() {
    	servicioP = new ServicioPesado(500.0);
        container = mock(Tanque.class);
        ordenI = mock(OrdenImportacion.class);
    }
    
    @Test
    void testImporteParaDevuelveMontoBase() {
        assertEquals(500.0, servicioP.importePara(container, ordenI));
    }
    
    @Test
    void testObtenerDesgloseUsaFechaTurnoDeOrden() {
        LocalDateTime fecha = LocalDateTime.of(2025, 11, 10, 8, 0);
        when(ordenI.getTurno()).thenReturn(fecha);
        DesgloseDeServicio desglose = servicioP.obtenerDesglose(container, ordenI);
        assertEquals("ServicioPesado", desglose.getNombre());
        assertEquals(fecha, desglose.getFecha());
        assertEquals(500.0, desglose.getCosto());
        verify(ordenI).getTurno();
    }

}
