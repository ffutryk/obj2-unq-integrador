package ar.edu.unq.po2.integrador;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.servicios.DesgloseDeServicio;
import ar.edu.unq.po2.integrador.servicios.Servicio;
import ar.edu.unq.po2.integrador.fases.Viaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrdenImportacionTest {
	
	private OrdenImportacion orden;
    private Viaje viaje;
    private Container container;
    private String camion;
    private String chofer;
    private Cliente cliente;
    private LocalDate fechaLlegada;
    private Servicio servicio1;
    private Servicio servicio2;
    
    @BeforeEach
    void setUp() {
        viaje = mock(Viaje.class);
        container = mock(Container.class);
        camion = "TER564";
        chofer = "Jorge";
        cliente = mock(Cliente.class);
        fechaLlegada = LocalDate.of(2025, 11, 1);

        servicio1 = mock(Servicio.class);
        servicio2 = mock(Servicio.class);

        
        orden = new OrdenImportacion(viaje, container, camion, chofer, cliente, fechaLlegada);
    }
    
    @Test
    void testEsDeImportacionDevuelveTrue() {
        assertTrue(orden.esDeImportacion());
    }
    
    @Test
    void testDiasDeServicioSinRegistrarRetiroDaCero() {
        assertEquals(0, orden.diasDeServicio());
    }
    
    @Test
    void testDiasDeServicioConRetiro() {
        orden.registrarRetiro(LocalDate.of(2025, 11, 5));
        assertEquals(4, orden.diasDeServicio());
    }

    @Test
    void testGetFechaDeFacturacionDevuelveFechaDeRetiro() {
        LocalDate retiro = LocalDate.of(2025, 11, 10);
        orden.registrarRetiro(retiro);
        assertEquals(retiro, orden.getFechaDeFacturacion());
    }
    
    @Test
    void testGetTurnoDevuelveFechaLlegada() {
        assertEquals(fechaLlegada, orden.getTurno());
    }
    
    @Test
    public void testGettersBasicos() {
        assertEquals(container, orden.getContainer());
        assertEquals(cliente, orden.getCliente());
        assertEquals(viaje, orden.getViaje());
        assertEquals(fechaLlegada, orden.getTurno());
    }
    
    @Test
    public void testAgregarServicioLoGuardaEnSuListaDeServicios() {
    	assertEquals(0, orden.getDesgloses().size());
    	DesgloseDeServicio desglose1 = mock(DesgloseDeServicio.class);
        when(servicio1.obtenerDesglose(container, orden)).thenReturn(desglose1);
    	orden.agregarDesgloseServicio(servicio1);
    	assertEquals(1, orden.getDesgloses().size());
    	assertTrue(orden.getDesgloses().contains(desglose1));
    	verify(servicio1).obtenerDesglose(container, orden);
    }
    
    @Test
    public void testSumaTotalServicios() {
    	DesgloseDeServicio desg1 = new DesgloseDeServicio("Servicio A", LocalDate.now(), 50.0);
        DesgloseDeServicio desg2 = new DesgloseDeServicio("Servicio B", LocalDate.now(), 70.0);
        when(servicio1.obtenerDesglose(container, orden)).thenReturn(desg1);
        when(servicio2.obtenerDesglose(container, orden)).thenReturn(desg2);
        orden.agregarDesgloseServicio(servicio1);
        orden.agregarDesgloseServicio(servicio2);   
        assertEquals(120.0, orden.totalServicios());
        verify(servicio1).obtenerDesglose(container, orden);
        verify(servicio2).obtenerDesglose(container, orden);
    }
}
