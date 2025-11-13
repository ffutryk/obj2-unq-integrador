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
import ar.edu.unq.po2.integrador.servicios.Servicio;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.ordenes.OrdenExportacion;
import ar.edu.unq.po2.integrador.reportes.VisitanteReportable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrdenExportacionTest {
	
	private OrdenExportacion orden;
    private Viaje viaje;
    private Container container;
    private Camion camion;
    private Chofer chofer;
    private Cliente cliente;
    private LocalDateTime fechaSalida;
    private Servicio servicio1;
    private Servicio servicio2;
    
    @BeforeEach
    void setUp() {
        viaje = mock(Viaje.class);
        container = mock(Container.class);
        camion = mock(Camion.class);
        chofer = mock(Chofer.class);
        cliente = mock(Cliente.class);
        fechaSalida = LocalDateTime.of(2025, 11, 1, 8, 0);
        
        servicio1 = mock(Servicio.class);
        servicio2 = mock(Servicio.class);

        orden = new OrdenExportacion(viaje, container, camion, chofer, cliente, fechaSalida);
    }
    
    @Test
    void testEsDeImportacionDevuelveFalse() {
        assertFalse(orden.esDeImportacion());
    }
    
    @Test
    void testHorasDeServicioSinRegistrarIngresoDaCero() {
        assertEquals(0, orden.horasDeServicio());
    }
    
    @Test
    public void testGettersBasicos() {
        assertEquals(container, orden.getContainer());
        assertEquals(cliente, orden.getCliente());
        assertEquals(viaje, orden.getViaje());
    }
    
    @Test
    void testHorasDeServicioConIngreso() {
        orden.registrarIngreso(LocalDateTime.of(2025, 11, 6, 8, 0));
        assertEquals(120, orden.horasDeServicio());
    }
    
    @Test
    void testGetFechaDeFacturacionDevuelveFechaDeIngreso() {
        LocalDateTime ingreso = LocalDateTime.of(2025, 11, 6, 8, 0);
        orden.registrarIngreso(ingreso);
        assertEquals(ingreso, orden.getFechaDeFacturacion());
    }
    
    @Test
    void testGetTurnoDevuelveFechaSalida() {
        assertEquals(fechaSalida, orden.getTurno());
    }
    
    @Test
    void testEnviarMailEnviaCorreoCorrectamente() {
        IEmailService emailService = mock(IEmailService.class);
        when(container.getId()).thenReturn("CONT-001");
        when(cliente.getDirecciomMail()).thenReturn("cliente@mail.com");
        orden.enviarMail(emailService);
        verify(emailService).mandarEmail(any(Email.class));
    }
    
    @Test
    void testAceptarLlamaAlVisitante() {
        VisitanteReportable visitante = mock(VisitanteReportable.class);
        orden.aceptar(visitante);
        verify(visitante).visitar(orden);
    }
    
    @Test
    void testEnviarFacturaCreaYEnviaFacturaPorMail() {
        IEmailService emailService = mock(IEmailService.class);
        when(container.getId()).thenReturn("CONT-001");
        when(cliente.getDirecciomMail()).thenReturn("cliente@mail.com");
        when(cliente.getOrden()).thenReturn(orden);
        orden.enviarFactura(emailService);
        verify(emailService).mandarEmail(any(Email.class));
    }

}
