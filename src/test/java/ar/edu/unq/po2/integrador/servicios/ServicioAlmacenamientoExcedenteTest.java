package ar.edu.unq.po2.integrador.servicios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import ar.edu.unq.po2.integrador.OrdenExportacion;
import ar.edu.unq.po2.integrador.OrdenImportacion;
import ar.edu.unq.po2.integrador.Orden;
import ar.edu.unq.po2.integrador.containers.Tanque;
import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.containers.Refeer;

public class ServicioAlmacenamientoExcedenteTest {
	
	private ServicioAlmacenamientoExcedente servicioAE;
    private Container container;
    private OrdenExportacion ordenE;
    
    @BeforeEach
    void setUp() {
    	servicioAE = new ServicioAlmacenamientoExcedente(100.0);
        container = mock(Tanque.class);
        ordenE = mock(OrdenExportacion.class);
    }

    @Test
    void testImporteParaOrdenImportacion() {
        when(ordenE.esDeImportacion()).thenReturn(true);
        when(ordenE.diasDeServicio()).thenReturn(3.0);
        double importe = servicioAE.importePara(container, ordenE);
        assertEquals(200.0, importe);
        verify(ordenE).esDeImportacion();
        verify(ordenE).diasDeServicio();
    }
    
    @Test
    void testImporteParaOrdenNoImportacionDevuelveCero() {
    	Container container2 = mock(Tanque.class);
    	Orden ordenI = mock(OrdenImportacion.class);
        when(ordenI.esDeImportacion()).thenReturn(false);
        double importe = servicioAE.importePara(container2, ordenI);
        assertEquals(0.0, importe);
        verify(ordenI).esDeImportacion();
    }
    
    @Test
    void testObtenerDesgloseCreaDesgloseCorrectamente() {
        LocalDate fechaFacturacion = LocalDate.of(2025, 11, 15);
        when(ordenE.esDeImportacion()).thenReturn(true);
        when(ordenE.diasDeServicio()).thenReturn(2.0);
        when(ordenE.getFechaDeFacturacion()).thenReturn(fechaFacturacion);
        DesgloseDeServicio desglose = servicioAE.obtenerDesglose(container, ordenE);
        assertEquals("ServicioAlmacenamientoExcedente", desglose.getNombre());
        assertEquals(fechaFacturacion, desglose.getFecha());
        assertEquals(100.0, desglose.getCosto());
        verify(ordenE).getFechaDeFacturacion();
        verify(ordenE).diasDeServicio();
    }
}
