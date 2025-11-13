package ar.edu.unq.po2.integrador;

import java.util.List;

public class EmpresaTransportista {
	
	private String cuit;
	private List<Camion> camiones;
	private List<Chofer> choferes;
	
	public EmpresaTransportista(List<Camion> camiones, List<Chofer> choferes, String unCuit) {
		this.cuit = unCuit;
		this.camiones = camiones;
		this.choferes = choferes;
	}

	public String getCuit() {
		return this.cuit;
	}
	
	public boolean tieneRegistradaA(Camion unCamion) {
		return this.camiones.contains(unCamion);
	}
	
	public boolean tieneRegistradaA(Chofer unChofer) {
		return this.choferes.contains(unChofer);
	}

	public void registrar(Camion unCamion) {
		this.camiones.add(unCamion);
	}

	public void registrar(Chofer unChofer) {
		this.choferes.add(unChofer);
	}
}
