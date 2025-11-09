package ar.edu.unq.po2.integrador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BillOfLandingTest {

	private BillOfLanding bl1;
	private BillOfLandingCompuesto bl2;
	private Pair<String, Double> carga1;
	
	@BeforeEach
	public void setup() {
		carga1 = new Pair("Patitos", 100.0);
		bl1 = new BillOfLanding("xx3", carga1);
		bl2 = new BillOfLandingCompuesto("xx4");
	}
	
	@Test
	public void agregarUnBLAUnBLCompuesto() {
		bl2.agregarBL(bl1);
	}
	
	
	
	
}
