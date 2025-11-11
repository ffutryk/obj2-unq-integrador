package ar.edu.unq.po2.integrador.servicios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import ar.edu.unq.po2.integrador.OrdenExportacion;
import ar.edu.unq.po2.integrador.Orden;
import ar.edu.unq.po2.integrador.containers.Refeer;
import ar.edu.unq.po2.integrador.containers.Tanque;
import ar.edu.unq.po2.integrador.containers.Container;

public class ServicioElectricidadTest {
	
	private ServicioElectricidad servicioE;
    private Container container;
    private OrdenExportacion ordenE;
    
    @BeforeEach
    void setUp() {
    	servicioE = new ServicioElectricidad(10.0);
        container = mock(Refeer.class);
        ordenE = mock(OrdenExportacion.class);
    }
    
    @Test
    void testImporteParaRefeer() {
        when(container.esRefeer()).thenReturn(true);
        when(container.getConsumoKwHora()).thenReturn(2.0);
        when(ordenE.diasDeServicio()).thenReturn(5.0);
        ordenE.registrarIngreso(LocalDateTime.of(2024, 11, 5, 8, 0));
        double importe = servicioE.importePara(container, ordenE);
        assertEquals(2400.0, importe);
        verify(container).esRefeer();
        verify(container).getConsumoKwHora();
        verify(ordenE).diasDeServicio();
    }
    
    @Test
    void testImporteParaNoRefeerDevuelveCero() {
    	Container container2 = mock(Tanque.class);
        when(container2.esRefeer()).thenReturn(false);
        Orden ordenI2 = mock(OrdenExportacion.class);
        double importe = servicioE.importePara(container2, ordenI2);
        assertEquals(0.0, importe);
        verify(container2).esRefeer();
    }

    @Test
    void testObtenerDesgloseCreaDesgloseCorrectamente() {
        LocalDateTime fecha = LocalDateTime.of(2025, 11, 10, 8, 0);
        when(container.esRefeer()).thenReturn(true);
        when(container.getConsumoKwHora()).thenReturn(1.0);
        when(ordenE.diasDeServicio()).thenReturn(1.0);
        when(ordenE.getFechaDeFacturacion()).thenReturn(fecha);
        DesgloseDeServicio desglose = servicioE.obtenerDesglose(container, ordenE);
        assertEquals("ServicioElectricidad", desglose.getNombre());
        assertEquals(fecha, desglose.getFecha());
        assertEquals(240.0, desglose.getCosto());
        verify(container).esRefeer();
        verify(ordenE).getFechaDeFacturacion();
    }
}
