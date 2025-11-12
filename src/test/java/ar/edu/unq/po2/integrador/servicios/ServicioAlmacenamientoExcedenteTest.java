package ar.edu.unq.po2.integrador.servicios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

import ar.edu.unq.po2.integrador.containers.Tanque;
import ar.edu.unq.po2.integrador.ordenes.Orden;
import ar.edu.unq.po2.integrador.ordenes.OrdenExportacion;
import ar.edu.unq.po2.integrador.ordenes.OrdenImportacion;
import ar.edu.unq.po2.integrador.containers.Container;


public class ServicioAlmacenamientoExcedenteTest {
	
	private ServicioAlmacenamientoExcedente servicioAE;
    private Container container;
    private Orden ordenI;
    private Orden ordenE;
    
    @BeforeEach
    void setUp() {
    	servicioAE = new ServicioAlmacenamientoExcedente(100.0);
        container = mock(Tanque.class);
        ordenI = mock(OrdenImportacion.class);
        ordenE = mock(OrdenExportacion.class);
    }

    @Test
    void testImporteParaOrdenImportacion() {
        when(ordenI.esDeImportacion()).thenReturn(true);
        when(ordenI.diasDeServicio()).thenReturn(3.0);
        double importe = servicioAE.importePara(container, ordenI);
        assertEquals(200.0, importe);
        verify(ordenI).esDeImportacion();
        verify(ordenI).diasDeServicio();
    }
    
    @Test
    void testNoCobraCuandoDiasSonNegativos() {
        when(ordenI.esDeImportacion()).thenReturn(true);
        when(ordenI.diasDeServicio()).thenReturn(0.0);
        double importe = servicioAE.importePara(container, ordenI);
        assertEquals(0, importe);
        verify(ordenI).esDeImportacion();
        verify(ordenI).diasDeServicio();
    }
    
    @Test
    void testImporteParaOrdenNoImportacionDevuelveCero() {
    	Container container2 = mock(Tanque.class);
    	Orden ordenE = mock(OrdenExportacion.class);
        when(ordenE.esDeImportacion()).thenReturn(false);
        double importe = servicioAE.importePara(container2, ordenE);
        assertEquals(0.0, importe);
        verify(ordenE).esDeImportacion();
    }
    
    @Test
    void testObtenerDesgloseCreaDesgloseCorrectamente() {
        LocalDateTime fechaFacturacion = LocalDateTime.of(2025, 11, 15, 8, 0);
        when(ordenI.esDeImportacion()).thenReturn(true);
        when(ordenI.diasDeServicio()).thenReturn(2.0);
        when(ordenI.getFechaDeFacturacion()).thenReturn(fechaFacturacion);
        DesgloseDeServicio desglose = servicioAE.obtenerDesglose(container, ordenI);
        fechaFacturacion = LocalDateTime.of(2025, 11, 15, 8, 0);
        when(ordenE.esDeImportacion()).thenReturn(true);
        when(ordenE.diasDeServicio()).thenReturn(2.0);
        when(ordenE.getFechaDeFacturacion()).thenReturn(fechaFacturacion);
        desglose = servicioAE.obtenerDesglose(container, ordenE);
        assertEquals("ServicioAlmacenamientoExcedente", desglose.getNombre());
        assertEquals(fechaFacturacion, desglose.getFecha());
        assertEquals(100.0, desglose.getCosto());
        verify(ordenI).getFechaDeFacturacion();
        verify(ordenI).diasDeServicio();
    }
    
}
