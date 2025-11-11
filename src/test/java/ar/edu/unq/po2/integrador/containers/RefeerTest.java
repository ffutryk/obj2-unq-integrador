package ar.edu.unq.po2.integrador.containers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Pair;
import ar.edu.unq.po2.integrador.bl.BillOfLanding;
import ar.edu.unq.po2.integrador.bl.IBillOfLanding;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class RefeerTest {

	private Refeer refeer;
	private IBillOfLanding bl;
	
	@BeforeEach
	public void setup() {
		bl = mock(BillOfLanding.class);
		refeer = new Refeer("r1", 2.5, 2.0, 3.0, 1500.0, bl, 5.0);
	}
	
	@Test
    public void testIdYVolumen() {
		assertEquals("r1", refeer.getId());
        assertEquals(2.5 * 2.0 * 3.0, refeer.volumen());
        assertFalse(refeer.superaVolumen(20.0));
    }
	
	@Test
	public void testPesos() {
		assertEquals(1500.0, refeer.getPeso());
		when(bl.pesoCarga()).thenReturn(400.0);
		assertEquals(refeer.pesoTotal(), 1900.0);
		verify(bl).pesoCarga();
	}
	
	@Test
    public void testCargaSeObtieneDelBL() {
		when(bl.carga()).thenReturn(List.of(new Pair<>("Cajas", 500.0)));
        assertEquals(1, refeer.carga().size());
        verify(bl).carga();
    }
	
	@Test
    public void testEsRefeerYConsumo() {
        assertTrue(refeer.esRefeer());
        assertEquals(5.0, refeer.getConsumoKwHora());
        assertEquals(TipoContainer.REFEER, refeer.getTipo());
    }
}
