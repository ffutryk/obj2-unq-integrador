package ar.edu.unq.po2.integrador.servicios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

import ar.edu.unq.po2.integrador.containers.Tanque;
import ar.edu.unq.po2.integrador.ordenes.OrdenImportacion;
import ar.edu.unq.po2.integrador.containers.Container;

public class ServicioLavadoTest {
	
	private ServicioLavado servicioL;
    private Container container;
    private OrdenImportacion ordenI;
    
    @BeforeEach
    void setUp() {
    	servicioL = new ServicioLavado(800.0, 400.0);
        container = mock(Tanque.class);
        ordenI = mock(OrdenImportacion.class);
    }
    
    @Test
    void testImporteParaVolumenNormalDevuelveMontoBase() {
        when(container.superaVolumen(70.0)).thenReturn(false);
        double importe = servicioL.importePara(container, ordenI);
        assertEquals(400.0, importe);
        verify(container).superaVolumen(70.0);
    }
    
    @Test
    void testImporteParaVolumenExcedidoDevuelveMontoExcedente() {
        when(container.superaVolumen(70.0)).thenReturn(true);
        double importe = servicioL.importePara(container, ordenI);
        assertEquals(800.0, importe);
        verify(container).superaVolumen(70.0);
    }
    
    @Test
    void testObtenerDesgloseCreaCorrectamenteElDesglose() {
    	LocalDateTime fecha = LocalDateTime.of(2025, 11, 10, 8, 0);
        when(container.superaVolumen(70.0)).thenReturn(true);
        when(ordenI.getTurno()).thenReturn(fecha);
        DesgloseDeServicio desglose = servicioL.obtenerDesglose(container, ordenI);
        assertEquals("ServicioLavado", desglose.getNombre());
        assertEquals(fecha, desglose.getFecha());
        assertEquals(800.0, desglose.getCosto());
        verify(container).superaVolumen(70.0);
        verify(ordenI).getTurno();
    }

}
