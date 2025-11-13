package ar.edu.unq.po2.integrador.reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Buque;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.ordenes.OrdenExportacion;
import ar.edu.unq.po2.integrador.ordenes.OrdenImportacion;

class VisitanteMuelleTest {

    VisitanteMuelle visitante;
    Buque buque;
    Viaje viaje;
    OrdenImportacion ordenImportacion;
    OrdenExportacion ordenExportacion;

    @BeforeEach
    void setUp() {
        visitante = new VisitanteMuelle();
        buque = mock(Buque.class);
        viaje = mock(Viaje.class);
        ordenImportacion = mock(OrdenImportacion.class);
        ordenExportacion = mock(OrdenExportacion.class);
    }

    @Test
    void testVisitarBuqueSeteaDatosCorrectamente() {
        LocalDateTime fecha = LocalDateTime.of(2025, 5, 5, 12, 0);
        when(buque.getViaje()).thenReturn(viaje);
        when(buque.getNombre()).thenReturn("Lorem Ipsum");
        when(viaje.fechaDeArriboA(viaje.getGestionada())).thenReturn(fecha);

        visitante.visitar(buque);

        String reporte = visitante.generarReporte();

        assertTrue(reporte.contains("Lorem Ipsum"));
        assertTrue(reporte.contains("05/05/2025 12:00"));
    }

    @Test
    void testVisitarOrdenesIncrementaContadores() {
        LocalDateTime fecha = LocalDateTime.of(2025, 10, 10, 10, 0);
        when(buque.getViaje()).thenReturn(viaje);
        when(buque.getNombre()).thenReturn("Lorem Ipsum");
        when(viaje.fechaDeArriboA(viaje.getGestionada())).thenReturn(fecha);
        
        visitante.visitar(buque);
        visitante.visitar(ordenImportacion);
        visitante.visitar(ordenExportacion);
        
        String reporte = visitante.generarReporte();
        assertTrue(reporte.contains("Contenedores Operados: 2"));
    }

    @Test
    void testGenerarReporteTieneFormatoCorrecto() {
        LocalDateTime fecha = LocalDateTime.of(2025, 5, 5, 12, 0);
        when(buque.getViaje()).thenReturn(viaje);
        when(buque.getNombre()).thenReturn("Lorem Ipsum");
        when(viaje.fechaDeArriboA(viaje.getGestionada())).thenReturn(fecha);

        visitante.visitar(buque);
        
        String reporte = visitante.generarReporte();

        assertTrue(reporte.contains("Reporte de Muelle"));
        assertTrue(reporte.contains("Buque:"));
        assertTrue(reporte.contains("Contenedores Operados:"));
    }
}
