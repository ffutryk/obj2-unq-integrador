package ar.edu.unq.po2.integrador;

import java.time.LocalDate;

public class ServicioElectricidad implements Servicio{

	private double montoPorKwConsumido;
	
	public ServicioElectricidad(double montoPorKwConsumido) {
		this.montoPorKwConsumido = montoPorKwConsumido;
	}

	public double importePara(Container c, Orden o) {
		if(! c.esRefeer()) return 0;
		double horas = o.diasDeServicio()*24;
		return c.getConsumoKwHora() * horas * montoPorKwConsumido;
	}
	
	public DesgloseDeServicio obtenerDesglose(Container c, Orden o) {
		return new DesgloseDeServicio("ServicioAlmacenamientoExcedente", o.getTurno(), importePara(c, o));
	}	//Esta bien el segundo  parametro? o deberia ser el turno para los dias excedentes?
}
