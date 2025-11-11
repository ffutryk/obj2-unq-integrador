package ar.edu.unq.po2.integrador.containers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Pair;
import ar.edu.unq.po2.integrador.bl.BillOfLandingCompuesto;
import ar.edu.unq.po2.integrador.bl.IBillOfLanding;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

public class DryTest {
	
	private Dry dry;
	private IBillOfLanding blC;
	
	@BeforeEach
	public void setup() {
		blC = mock(BillOfLandingCompuesto.class);
		dry = new Dry("d1", 2.0, 3.0, 4.0, 1000.0, blC);
	}
	
	@Test
	public void testIdYVolumen() {
		assertEquals("d1", dry.getId());
        assertEquals(24.0, dry.volumen());
        assertTrue(dry.superaVolumen(10.0));
	}
	
	@Test
	public void testPesos() {
		assertEquals(1000.0, dry.getPeso());
		when(blC.pesoCarga()).thenReturn(500.0);
		assertEquals(dry.pesoTotal(), 1500.0);
		verify(blC).pesoCarga();
	}
	
	@Test
    public void testCargaSeObtieneDelBL() {
		when(blC.carga()).thenReturn(List.of(new Pair<>("Cajas", 500.0), new Pair<>("Maiz", 400.0)));
        assertEquals(2, dry.carga().size());
        verify(blC).carga();
        assertEquals(dry.getBL(), blC);
    }
	
	@Test
    public void testTipoEsDry() {
        assertEquals(TipoContainer.DRY, dry.getTipo());
        assertFalse(dry.esRefeer());
        assertEquals(0, dry.getConsumoKwHora());
    }
}
