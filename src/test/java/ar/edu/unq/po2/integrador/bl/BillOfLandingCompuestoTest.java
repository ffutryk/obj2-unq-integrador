package ar.edu.unq.po2.integrador.bl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Pair;
import ar.edu.unq.po2.integrador.bl.BillOfLanding;
import ar.edu.unq.po2.integrador.bl.BillOfLandingCompuesto;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class BillOfLandingCompuestoTest {

	private BillOfLandingCompuesto blC;
	private BillOfLanding bl1;
    private BillOfLanding bl2;
    private Pair<String, Double> carga1;
    private Pair<String, Double> carga2;

	@BeforeEach
	public void setup() {
		carga1 = new Pair<>("Soja", 3000.0);
		carga2 = new Pair<>("Trigo", 1500.0);
		bl1 = new BillOfLanding("xx1", carga1);
		bl2 = new BillOfLanding("xx2", carga2);
		blC = new BillOfLandingCompuesto("xx4");
		
		blC.agregarBL(bl1);
		blC.agregarBL(bl2);
	}
	
	@Test
    void testCargaDevuelveUnionDeLasCargasInternas() {
        List<Pair<String, Double>> cargas = blC.carga();

        assertEquals(2, cargas.size());
        assertTrue(cargas.stream().anyMatch(p -> p.first().equals("Trigo") && p.second() == 1500.0));
        assertTrue(cargas.stream().anyMatch(p -> p.first().equals("Soja") && p.second() == 3000.0));
    }
	
	@Test
    void testPesoCargaSumaLosPesosDeTodasLasBL() {
        assertEquals(4500.0, blC.pesoCarga());
    }
	
	@Test
    void testGetIdDevuelveIdCorrecto() {
        assertEquals("xx4", blC.getId());
    }
	
	@Test
    void testAgregarOtraBLAnidada() {
        BillOfLandingCompuesto subCompuesto = new BillOfLandingCompuesto("xx5");
        subCompuesto.agregarBL(new BillOfLanding("xx6", new Pair<>("Maiz", 1000.0)));

        blC.agregarBL(subCompuesto);

        assertEquals(3, blC.carga().size());
        assertEquals(5500.0, blC.pesoCarga());
    }
}
