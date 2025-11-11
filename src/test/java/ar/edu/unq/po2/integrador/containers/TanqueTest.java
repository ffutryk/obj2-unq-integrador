package ar.edu.unq.po2.integrador.containers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Pair;
import ar.edu.unq.po2.integrador.bl.BillOfLanding;
import ar.edu.unq.po2.integrador.bl.IBillOfLanding;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class TanqueTest {
	
	private Tanque tanque;
	private IBillOfLanding bl;
	
	@BeforeEach
	public void setup() {
		bl = mock(BillOfLanding.class);
		tanque = new Tanque("t1", 2.5, 3.0, 4.0, 2000.0, bl);
	}
	
	@Test
    public void testIdYVolumen() {
		assertEquals("t1", tanque.getId());
        assertEquals(2.5 * 3.0 * 4.0, tanque.volumen());
        assertTrue(tanque.superaVolumen(20.0));
    }
	
	@Test
	public void testPesos() {
		assertEquals(2000.0, tanque.getPeso());
		when(bl.pesoCarga()).thenReturn(300.0);
		assertEquals(tanque.pesoTotal(), 2300.0);
		verify(bl).pesoCarga();
	}
	
	@Test
    public void testCargaSeObtieneDelBL() {
		when(bl.carga()).thenReturn(List.of(new Pair<>("Cajas", 500.0)));
        assertEquals(1, tanque.carga().size());
        verify(bl).carga();
    }
	
	@Test
    public void testTipoEsTanque() {
        assertFalse(tanque.esRefeer());
        assertEquals(0, tanque.getConsumoKwHora());
        assertEquals(TipoContainer.TANQUE, tanque.getTipo());
    }

}
