package ar.edu.unq.po2.integrador;
import java.util.List;

public class BillOfLanding implements IBillOfLanding{

	private String id;
	private Pair<String, Double> carga;
	
	public BillOfLanding(String id, Pair<String, Double> carga) {
        this.id = id;
		this.carga = carga;
    }
	
	@Override
	public List<Pair<String, Double>> carga(){
		return List.of(carga);
	}
	
	@Override
	public double pesoCarga() {
		return carga.second();
	}
	
	public String tipoCarga() {
		return carga.first();
	}
	
	@Override
	public String getId() {
		return id;
	}
}
