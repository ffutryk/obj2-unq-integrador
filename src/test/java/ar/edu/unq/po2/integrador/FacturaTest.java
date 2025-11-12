package ar.edu.unq.po2.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.servicios.DesgloseDeServicio;
import ar.edu.unq.po2.ordenes.Orden;
import ar.edu.unq.po2.ordenes.OrdenExportacion;
import ar.edu.unq.po2.ordenes.OrdenImportacion;


public class FacturaTest {
	
	private Cliente cliente;
	private Cliente cliente2;
    private Orden ordenI;
    private Orden ordenE;
    private Viaje viaje;
    private Circuito circuito;
    private Factura factura;
    private Factura factura2;
	
	@BeforeEach
    void setUp() {
        cliente = new Cliente("C002", "María Gómez", "maria@mail.com");
        cliente2 = new Cliente("C003", "Jorge Mattew", "jorgeMat@mail.com");
        ordenI = mock(OrdenImportacion.class);
		ordenE = mock(OrdenExportacion.class);
        viaje = mock(Viaje.class);
        circuito = mock(Circuito.class);
        
        when(viaje.getCircuito()).thenReturn(circuito);
        when(circuito.costoTotal()).thenReturn(1000.0);

        DesgloseDeServicio d1 = new DesgloseDeServicio("ServicioLavado", null, 200.0);
        DesgloseDeServicio d2 = new DesgloseDeServicio("ServicioElectricidad", null, 300.0);
		when(ordenI.getDesgloses()).thenReturn(List.of(d1, d2));
        when(ordenE.getDesgloses()).thenReturn(List.of(d1, d2));

        when(ordenI.esDeImportacion()).thenReturn(true);
        when(ordenE.esDeImportacion()).thenReturn(false);

        when(ordenI.getViaje()).thenReturn(viaje);

        cliente.guardarOrden(ordenI);
        cliente2.guardarOrden(ordenE);

        factura = new Factura(cliente);
        factura2 = new Factura(cliente2);

    }
	
	@Test
    void testFacturaSeCreaConFechaYClienteCorrectos() {
        assertNotNull(factura.getFechaEmision());
        assertTrue(factura.getFechaEmision().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertEquals(cliente, factura.getCliente());
    } 

	@Test
    void testDesgloseDeServiciosDevuelveLosDeLaOrden() {
        assertEquals(2, factura.desgloseDeServicios().size());
    }
	
	@Test
    void testMontoTotalFacturadoIncluyeServiciosYViajeCuandoEsImportacion() {
        double esperado = 200 + 300 + 1000;
        assertEquals(esperado, factura.montoTotalFacturado());
    }
	
	@Test
    void testMontoTotalFacturadoNoIncluyeViajeCuandoNoEsImportacion() {
        double esperado = 200 + 300;
        assertEquals(esperado, factura2.montoTotalFacturado());
    }
}
