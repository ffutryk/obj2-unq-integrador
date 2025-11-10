package ar.edu.unq.po2.integrador.bl;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.integrador.Pair;

public class BillOfLandingCompuesto implements IBillOfLanding {

	private String id;
	private List<IBillOfLanding> bls;
	
	public BillOfLandingCompuesto(String id) {
		this.id = id;
		bls = new ArrayList<IBillOfLanding>();
	}
	
	@Override
	public List<Pair<String, Double>> carga(){
		List<Pair<String, Double>> carga = new ArrayList<>();
        for (IBillOfLanding bl : bls) {
            carga.addAll(bl.carga());
        }
        return carga;
	}
	
	@Override
	public double pesoCarga() {
		return bls.stream().mapToDouble(b -> b.pesoCarga()).sum();
	}
	
	@Override
	public String getId() {
		return id;
	}

	public void agregarBL(IBillOfLanding bl) {
        bls.add(bl);
    }
	
	
	
}
