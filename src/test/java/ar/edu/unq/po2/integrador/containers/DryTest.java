package ar.edu.unq.po2.integrador.containers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.bl.BillOfLanding;
import ar.edu.unq.po2.integrador.bl.IBillOfLanding;
import ar.edu.unq.po2.integrador.containers.Dry;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DryTest {
	
	private Dry dry;
	private IBillOfLanding bl;
	
	@BeforeEach
	public void setup() {
		bl = mock(BillOfLanding.class);
		dry = new Dry("ford1234567", 2.0, 6.0, 2.0, 100.0, bl);
	}
	
	@Test
	public void testDimensiones() {
		assertEquals("ford1234567", dry.getId());
        assertEquals(24.0, dry.volumen());
	}
	
	@Test
	public void testPesos() {
		assertEquals(100.0, dry.getPeso());
		when(bl.pesoCarga()).thenReturn(300.0);
		assertEquals(dry.pesoTotal(), bl.pesoCarga() + dry.getPeso());
	}
	
	
}
