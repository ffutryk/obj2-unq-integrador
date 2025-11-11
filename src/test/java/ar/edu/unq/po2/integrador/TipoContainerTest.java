package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.containers.TipoContainer;

class TipoContainerTest {

    @Test
    void testGetTextoDevuelveValorCorrecto() {
        assertEquals("Dry", TipoContainer.DRY.getTexto());
        assertEquals("Refeer", TipoContainer.REFEER.getTexto());
        assertEquals("Tanque", TipoContainer.TANQUE.getTexto());
    }

    @Test
    void testValueOfRetornaEnumCorrecto() {
        assertEquals(TipoContainer.DRY, TipoContainer.valueOf("DRY"));
        assertEquals(TipoContainer.REFEER, TipoContainer.valueOf("REFEER"));
        assertEquals(TipoContainer.TANQUE, TipoContainer.valueOf("TANQUE"));
    }
    
    @Test
    void testValueOfLanzaExcepcionConNombreInvalido() {
        assertThrows(IllegalArgumentException.class, () -> TipoContainer.valueOf("INVALIDO"));
    }
}
