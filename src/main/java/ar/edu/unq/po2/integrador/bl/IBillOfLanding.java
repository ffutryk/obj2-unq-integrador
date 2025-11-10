package ar.edu.unq.po2.integrador.bl;
import java.util.List;

import ar.edu.unq.po2.integrador.Pair;

public interface IBillOfLanding {

	List<Pair<String, Double>> carga();
	
	double pesoCarga();
	
	String getId();
	
}
