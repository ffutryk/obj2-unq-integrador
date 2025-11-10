package ar.edu.unq.po2.integrador.bl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Pair;
import ar.edu.unq.po2.integrador.bl.BillOfLanding;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class BillOfLandingTest {

	private BillOfLanding bl1;
	private Pair<String, Double> carga1;
	
	@BeforeEach
	public void setup() {
		carga1 = new Pair<>("Soja", 2500.0);
		bl1 = new BillOfLanding("xx3", carga1);
	}
	
	@Test
    void testCargaDevuelveListaConUnElemento() {
        List<Pair<String, Double>> carga = bl1.carga();
        assertEquals(1, carga.size());
        assertEquals("Soja", carga.get(0).first());
        assertEquals(2500.0, carga.get(0).second());
    }
	
	@Test
    void testPesoCarga() {
        assertEquals(2500.0, bl1.pesoCarga());
    }
	
	 @Test
	 void testTipoCargaDevuelveNombreCorrecto() {
		 assertEquals("Soja", bl1.tipoCarga());
	 }
	
	 @Test
	 void testGetIdDevuelveElId() {
		 assertEquals("xx3", bl1.getId());
	 }
	
}
