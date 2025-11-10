package ar.edu.unq.po2.integrador.servicios;

import ar.edu.unq.po2.integrador.Orden;
import ar.edu.unq.po2.integrador.containers.Container;

public class ServicioLavado implements Servicio{

	private double montoExcedente;
	private double montoBase;
	
	public ServicioLavado(double montoExcedente, double montoBase) {
		this.montoExcedente = montoExcedente;
		this.montoBase = montoBase;
	}
	
	@Override
	public double importePara(Container c, Orden o) {
		if(c.superaVolumen(70.0)) {
			return montoExcedente;
		}
		return montoBase;
	}
	
	@Override
	public DesgloseDeServicio obtenerDesglose(Container c, Orden o) {
		return new DesgloseDeServicio("ServicioLavado", o.getTurno(), importePara(c, o));
	} //Esta bien el segundo  parametro?
}
