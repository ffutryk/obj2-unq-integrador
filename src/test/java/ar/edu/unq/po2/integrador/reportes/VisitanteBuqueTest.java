package ar.edu.unq.po2.integrador.reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Buque;
import ar.edu.unq.po2.integrador.OrdenExportacion;
import ar.edu.unq.po2.integrador.OrdenImportacion;
import ar.edu.unq.po2.integrador.containers.Container;

class VisitanteBuqueTest {

    VisitanteBuque visitante;
    Buque buque;
    OrdenImportacion ordenImportacion;
    OrdenExportacion ordenExportacion;
    Container container1;
    Container container2;

    @BeforeEach
    void setUp() {
        visitante = new VisitanteBuque();
        buque = mock(Buque.class);
        ordenImportacion = mock(OrdenImportacion.class);
        ordenExportacion = mock(OrdenExportacion.class);
        container1 = mock(Container.class);
        container2 = mock(Container.class);
    }

    @Test
    void testVisitarOrdenImportacionAgregaId() {
        when(ordenImportacion.getContainer()).thenReturn(container1);
        when(container1.getId()).thenReturn("C1");

        visitante.visitar(ordenImportacion);

        String reporte = visitante.generarReporte();
        assertTrue(reporte.contains("<item>C1</item>"));
    }

    @Test
    void testVisitarOrdenExportacionAgregaId() {
        when(ordenExportacion.getContainer()).thenReturn(container2);
        when(container2.getId()).thenReturn("C2");

        visitante.visitar(ordenExportacion);

        String reporte = visitante.generarReporte();
        assertTrue(reporte.contains("<item>C2</item>"));
    }
    
    @Test
    void testVisitarBuqueNoHaceNada() {
        visitante.visitar(buque);
        
        String reporte = visitante.generarReporte();

        assertTrue(reporte.contains("<import>"));
        assertTrue(reporte.contains("</import>"));
        assertTrue(reporte.contains("<export>"));
        assertTrue(reporte.contains("</export>"));
        assertFalse(reporte.contains("<item>"));

        assertDoesNotThrow(() -> visitante.visitar(buque));
    }


    @Test
    void testGenerarReporteContieneEstructuraXML() {
        String xml = visitante.generarReporte();

        assertTrue(xml.startsWith("<report>"));
        assertTrue(xml.contains("<import>"));
        assertTrue(xml.contains("<export>"));
        assertTrue(xml.endsWith("</report>"));
    }
}
