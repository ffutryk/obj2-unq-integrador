package ar.edu.unq.po2.integrador;

import java.util.List;

public class EmpresaTransportista {
	
	private List<Camion> camiones;
	private List<Chofer> choferes;
	
	public EmpresaTransportista(List<Camion> camiones, List<Chofer> choferes) {
		this.camiones = camiones;
		this.choferes = choferes;
	}

	public boolean tieneRegistradaA(Camion unCamion) {
		return this.camiones.contains(unCamion);
	}
	
	public boolean tieneRegistradaA(Chofer unChofer) {
		return this.choferes.contains(unChofer);
	}
}
