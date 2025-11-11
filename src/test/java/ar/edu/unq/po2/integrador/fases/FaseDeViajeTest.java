package ar.edu.unq.po2.integrador.fases;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FaseDeViajeTest {

    FaseDeViaje fase;
    Viaje unViaje;

    @BeforeEach
    void setUp() {
        fase = mock(FaseDeViaje.class, CALLS_REAL_METHODS);
        unViaje = mock(Viaje.class);
    }

    @Test
    void testPorDefectoNoEstaHabilitadaParaExportacion() {
        assertFalse(fase.estaHabilitadaParaExportacion());
    }

    @Test
    void testPorDefectoNoEstaHabilitadaParaImportacion() {
        assertFalse(fase.estaHabilitadaParaImportacion());
    }

    @Test
    void testPorDefectoNoEstaHabilitadaParaPagar() {
        assertFalse(fase.estaHabilitadaParaPagar());
    }
    
    @Test
    void testPorDefectoTrabajarNoHaceNada() {
    	fase.trabajar(unViaje);
    	verifyNoInteractions(unViaje);
    }
    
    @Test
    void testPorDefectoDepartNoHaceNada() {
    	fase.depart(unViaje);
    	verifyNoInteractions(unViaje);
    }
}
