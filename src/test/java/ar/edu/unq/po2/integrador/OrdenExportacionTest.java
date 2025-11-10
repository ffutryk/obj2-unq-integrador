package ar.edu.unq.po2.integrador;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.servicios.Servicio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrdenExportacionTest {
	
	private OrdenExportacion orden;
    private Viaje viaje;
    private Container container;
    private String camion;
    private String chofer;
    private Cliente cliente;
    private LocalDate fechaSalida;
    private Servicio servicio1;
    private Servicio servicio2;
    
    @BeforeEach
    void setUp() {
        viaje = mock(Viaje.class);
        container = mock(Container.class);
        camion = "TER564";
        chofer = "Jorge";
        cliente = mock(Cliente.class);
        fechaSalida = LocalDate.of(2025, 11, 1);
        
        servicio1 = mock(Servicio.class);
        servicio2 = mock(Servicio.class);

        orden = new OrdenExportacion(viaje, container, camion, chofer, cliente, fechaSalida);
    }
    
    @Test
    void testEsDeImportacionDevuelveFalse() {
        assertFalse(orden.esDeImportacion());
    }
    
    @Test
    void testDiasDeServicioSinRegistrarIngresoDaCero() {
        assertEquals(0, orden.diasDeServicio());
    }
    
    @Test
    void testDiasDeServicioConIngreso() {
        orden.registrarIngreso(LocalDate.of(2025, 11, 6));
        assertEquals(5, orden.diasDeServicio());
    }
    
    @Test
    void testGetFechaDeFacturacionDevuelveFechaDeIngreso() {
        LocalDate ingreso = LocalDate.of(2025, 11, 6);
        orden.registrarIngreso(ingreso);
        assertEquals(ingreso, orden.getFechaDeFacturacion());
    }
    
    @Test
    void testGetTurnoDevuelveFechaSalida() {
        assertEquals(fechaSalida, orden.getTurno());
    }

}
