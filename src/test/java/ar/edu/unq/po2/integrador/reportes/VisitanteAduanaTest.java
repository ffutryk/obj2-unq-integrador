package ar.edu.unq.po2.integrador.reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Buque;
import ar.edu.unq.po2.integrador.OrdenExportacion;
import ar.edu.unq.po2.integrador.OrdenImportacion;
import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.containers.TipoContainer;
import ar.edu.unq.po2.integrador.fases.Viaje;

class VisitanteAduanaTest {

    VisitanteAduana visitante;
    Buque buque;
    Viaje viaje;
    OrdenImportacion ordenImportacion;
    OrdenExportacion ordenExportacion;
    Container container1;
    Container container2;
    TipoContainer tipo1;
    TipoContainer tipo2;

    @BeforeEach
    void setUp() {
        visitante = new VisitanteAduana();
        buque = mock(Buque.class);
        viaje = mock(Viaje.class);
        ordenImportacion = mock(OrdenImportacion.class);
        ordenExportacion = mock(OrdenExportacion.class);
        container1 = mock(Container.class);
        container2 = mock(Container.class);
        tipo1 = mock(TipoContainer.class);
        tipo2 = mock(TipoContainer.class);
    }

    @Test
    void testVisitarBuqueSeteaDatosCorrectamente() {
        LocalDateTime fecha = LocalDateTime.of(2025, 10, 10, 10, 0);
        when(buque.viaje()).thenReturn(viaje);
        when(buque.nombre()).thenReturn("Lorem Ipsum");
        when(viaje.fechaDeArriboA(viaje.getGestionada())).thenReturn(fecha);

        visitante.visitar(buque);

        String reporte = visitante.generarReporte();

        assertTrue(reporte.contains("Lorem Ipsum"));
        assertTrue(reporte.contains("10/10/2025 10:00"));
    }
    
    @Test
    void testGenerarReporteSinContenedores() {
        LocalDateTime fecha = LocalDateTime.of(2025, 10, 10, 10, 0);
        when(buque.viaje()).thenReturn(viaje);
        when(buque.nombre()).thenReturn("Lorem Ipsum");
        when(viaje.fechaDeArriboA(viaje.getGestionada())).thenReturn(fecha);

        visitante.visitar(buque);
        String reporte = visitante.generarReporte();

        assertTrue(reporte.contains("<ul>"));
        assertTrue(reporte.contains("</ul>"));
        assertFalse(reporte.contains("<li>"));
    }

    @Test
    void testGenerarReporteConContenedores() {
        when(ordenImportacion.getContainer()).thenReturn(container1);
        when(container1.getId()).thenReturn("C1");
        when(container1.getTipo()).thenReturn(tipo1);
        when(tipo1.getTexto()).thenReturn("Refeer");

        when(ordenExportacion.getContainer()).thenReturn(container2);
        when(container2.getId()).thenReturn("C2");
        when(container2.getTipo()).thenReturn(tipo2);
        when(tipo2.getTexto()).thenReturn("Dry");

        visitante.visitar(ordenImportacion);
        visitante.visitar(ordenExportacion);

        LocalDateTime fecha = LocalDateTime.of(2025, 10, 10, 10, 0);
        when(buque.viaje()).thenReturn(viaje);
        when(buque.nombre()).thenReturn("Lorem Ipsum");
        when(viaje.fechaDeArriboA(viaje.getGestionada())).thenReturn(fecha);
        visitante.visitar(buque);

        String reporte = visitante.generarReporte();

        assertTrue(reporte.contains("<li>ID: C1 - Tipo: Refeer</li>"));
        assertTrue(reporte.contains("<li>ID: C2 - Tipo: Dry</li>"));
    }


    @Test
    void testGenerarReporteIncluyeEstructuraHTML() {
        LocalDateTime fecha = LocalDateTime.of(2025, 10, 10, 10, 0);
        when(buque.viaje()).thenReturn(viaje);
        when(buque.nombre()).thenReturn("Lorem Ipsum");
        when(viaje.fechaDeArriboA(viaje.getGestionada())).thenReturn(fecha);
        
        visitante.visitar(buque);
        
        String html = visitante.generarReporte();

        assertTrue(html.contains("<html>"));
        assertTrue(html.contains("<body>"));
        assertTrue(html.contains("</html>"));
    }
}
