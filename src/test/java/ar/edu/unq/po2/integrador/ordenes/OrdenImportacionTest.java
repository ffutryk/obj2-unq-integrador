package ar.edu.unq.po2.integrador.ordenes;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Camion;
import ar.edu.unq.po2.integrador.Chofer;
import ar.edu.unq.po2.integrador.Cliente;
import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.email.Email;
import ar.edu.unq.po2.integrador.email.IEmailService;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.ordenes.OrdenImportacion;
import ar.edu.unq.po2.integrador.reportes.VisitanteReportable;
import ar.edu.unq.po2.integrador.servicios.DesgloseDeServicio;
import ar.edu.unq.po2.integrador.servicios.Servicio;
import ar.edu.unq.po2.integrador.fases.Viaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrdenImportacionTest {
	
	private OrdenImportacion orden;
    private Viaje viaje;
    private Container container;
    private Camion camion;
    private Chofer chofer;
    private Cliente cliente;
    private LocalDateTime fechaLlegada;
    private Servicio servicio1;
    private Servicio servicio2;
    
    @BeforeEach
    void setUp() {
        viaje = mock(Viaje.class);
        container = mock(Container.class);
        camion = mock(Camion.class);
        chofer = mock(Chofer.class);
        cliente = mock(Cliente.class);
        fechaLlegada = LocalDateTime.of(2025, 11, 1, 8, 0);

        servicio1 = mock(Servicio.class);
        servicio2 = mock(Servicio.class);

        
        orden = new OrdenImportacion(viaje, container, camion, chofer, cliente, fechaLlegada);
    }
    
    @Test
    void testEsDeImportacionDevuelveTrue() {
        assertTrue(orden.esDeImportacion());
    }
    
    @Test
    void testHorasDeServicioSinRegistrarRetiroDaCero() {
        assertEquals(0, orden.horasDeServicio());
    }
    
    @Test
    void testHorasDeServicioConRetiro() {
        orden.registrarRetiro(LocalDateTime.of(2025, 11, 5, 8, 0));
        assertEquals(96, orden.horasDeServicio());
    }

    @Test
    void testGetFechaDeFacturacionDevuelveFechaDeRetiro() {
        LocalDateTime retiro = LocalDateTime.of(2025, 11, 10, 8, 0);
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
    	DesgloseDeServicio desg1 = new DesgloseDeServicio("Servicio A", LocalDateTime.now(), 50.0);
        DesgloseDeServicio desg2 = new DesgloseDeServicio("Servicio B", LocalDateTime.now(), 70.0);
        when(servicio1.obtenerDesglose(container, orden)).thenReturn(desg1);
        when(servicio2.obtenerDesglose(container, orden)).thenReturn(desg2);
        orden.agregarDesgloseServicio(servicio1);
        orden.agregarDesgloseServicio(servicio2);   
        assertEquals(120.0, orden.totalServicios());
        verify(servicio1).obtenerDesglose(container, orden);
        verify(servicio2).obtenerDesglose(container, orden);
    }
    
    @Test
    void testEnviarMailCreaYEnvíaUnEmailConDatosCorrectos() {
        IEmailService emailService = mock(IEmailService.class);
        when(container.getId()).thenReturn("CONT-999");
        when(cliente.getDirecciomMail()).thenReturn("cliente@test.com");
        orden.enviarMail(emailService);
        verify(emailService).mandarEmail(any(Email.class));
    }
    
    @Test
    void testAceptarLlamaAlVisitanteConLaOrden() {
        VisitanteReportable visitante = mock(VisitanteReportable.class);
        orden.aceptar(visitante);
        verify(visitante).visitar(orden);
    }
}
