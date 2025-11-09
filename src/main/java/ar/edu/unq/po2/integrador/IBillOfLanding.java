package ar.edu.unq.po2.integrador;
import java.util.List;

public interface IBillOfLanding {

	List<Pair<String, Double>> carga();
	
	double pesoCarga();
	
	String getId();
	
}
