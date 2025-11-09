package ar.edu.unq.po2.integrador;

public class ServicioPesado implements Servicio{

	private double montoBase;
	
	public ServicioPesado(double montoBase) {
		this.montoBase = montoBase;
	}

	@Override
	public double importePara(Container c, Orden o) {
		return montoBase;
	}
	
	@Override
	public DesgloseDeServicio obtenerDesglose(Container c, Orden o) {
		return new DesgloseDeServicio("ServicioPesado", o.getTurno(), importePara(c, o));
	} //Esta bien el segundo  parametro? 
	
}
