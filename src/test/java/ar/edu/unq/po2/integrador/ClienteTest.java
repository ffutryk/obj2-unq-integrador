package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.ordenes.Orden;
import ar.edu.unq.po2.ordenes.OrdenImportacion;


public class ClienteTest {
	
	private Cliente cliente;
    private Orden ordenI;
    
    @BeforeEach
    void setUp() {
        cliente = new Cliente("C001", "Juan Pérez", "juanperez@mail.com");
        ordenI = mock(OrdenImportacion.class);
    }
    
    @Test
    void testCrearClienteGuardaDatosCorrectamente() {
        assertEquals("C001", cliente.getId());
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("juanperez@mail.com", cliente.getDirecciomMail());
        assertNull(cliente.getOrden());
    }
    
    @Test
    void testGuardarOrdenAsociaCorrectamente() {
        cliente.guardarOrden(ordenI);
        assertEquals(ordenI, cliente.getOrden());
    }

}
