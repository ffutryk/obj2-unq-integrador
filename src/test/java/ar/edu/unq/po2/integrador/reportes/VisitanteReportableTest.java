package ar.edu.unq.po2.integrador.reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Buque;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.ordenes.Orden;
import ar.edu.unq.po2.integrador.ordenes.OrdenExportacion;
import ar.edu.unq.po2.integrador.ordenes.OrdenImportacion;

class VisitanteReportableTest {
    static class VisitanteDummy extends VisitanteReportable {
        boolean visitoBuque = false;
        boolean visitoImportacion = false;
        boolean visitoExportacion = false;

        @Override
        public void visitar(Buque buque) {
            visitoBuque = true;
        }

        @Override
        public void visitar(OrdenImportacion orden) {
            visitoImportacion = true;
        }

        @Override
        public void visitar(OrdenExportacion orden) {
            visitoExportacion = true;
        }

        @Override
        protected String generarReporte() {
            return "REPORTE_GENERADO";
        }
    }

    VisitanteDummy visitante;
    Viaje viaje;
    Viaje otroViaje;
    Buque buque;
    OrdenImportacion ordenImportacion;
    OrdenExportacion ordenExportacion;
    OrdenExportacion ordenNoDelViaje;

    @BeforeEach
    void setUp() {
        visitante = new VisitanteDummy();
        viaje = mock(Viaje.class);
        otroViaje = mock(Viaje.class);
        buque = mock(Buque.class);
        ordenImportacion = mock(OrdenImportacion.class);
        ordenExportacion = mock(OrdenExportacion.class);
        ordenNoDelViaje = mock(OrdenExportacion.class);
        
        when(viaje.getBuque()).thenReturn(buque);
        
        doAnswer(inv -> { visitante.visitar(buque); return null; }).when(buque).aceptar(any());
        doAnswer(inv -> { visitante.visitar(ordenImportacion); return null; }).when(ordenImportacion).aceptar(any());
        doAnswer(inv -> { visitante.visitar(ordenExportacion); return null; }).when(ordenExportacion).aceptar(any());

        when(ordenImportacion.getViaje()).thenReturn(viaje);
        when(ordenExportacion.getViaje()).thenReturn(viaje); 
        when(ordenNoDelViaje.getViaje()).thenReturn(otroViaje);
    }

    @Test
    void testGenerarReporteParaLlamaAceptarEnBuqueYOrdenesDelViaje() {
        List<Orden> ordenes = List.of(ordenImportacion, ordenExportacion);

        String resultado = visitante.generarReportePara(viaje, ordenes);

        assertEquals("REPORTE_GENERADO", resultado);
        assertTrue(visitante.visitoBuque);
        assertTrue(visitante.visitoImportacion);
        assertTrue(visitante.visitoExportacion);

        verify(viaje).getBuque();
        verify(buque).aceptar(visitante);
        verify(ordenImportacion).aceptar(visitante);
        verify(ordenExportacion).aceptar(visitante);
    }

    @Test
    void testGenerarReporteParaFiltraOrdenesDeOtrosViajesCorrectamente() {
        List<Orden> todasLasOrdenes = List.of(ordenImportacion, ordenExportacion, ordenNoDelViaje);

        String resultado = visitante.generarReportePara(viaje, todasLasOrdenes);

        assertEquals("REPORTE_GENERADO", resultado);
        assertTrue(visitante.visitoBuque);
        assertTrue(visitante.visitoImportacion);
        assertTrue(visitante.visitoExportacion);

        verify(ordenImportacion).getViaje(); 
        verify(ordenExportacion).getViaje();
        verify(ordenNoDelViaje).getViaje();

        verify(ordenImportacion).aceptar(visitante);
        verify(ordenExportacion).aceptar(visitante);
        verify(ordenNoDelViaje, never()).aceptar(visitante);
    }

    @Test
    void testGenerarReporteParaSinOrdenesSoloVisitaBuque() {
        String resultado = visitante.generarReportePara(viaje, List.of());

        assertEquals("REPORTE_GENERADO", resultado);
        assertTrue(visitante.visitoBuque);
        assertFalse(visitante.visitoImportacion);
        assertFalse(visitante.visitoExportacion);

        verify(viaje).getBuque();
        verify(buque).aceptar(visitante);
        
        verifyNoInteractions(ordenImportacion, ordenExportacion, ordenNoDelViaje);
    }
}