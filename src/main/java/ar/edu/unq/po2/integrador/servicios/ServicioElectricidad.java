package ar.edu.unq.po2.integrador.servicios;

import java.time.LocalDate;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.ordenes.Orden;

public class ServicioElectricidad implements Servicio{

	private double montoPorKwConsumido;
	
	public ServicioElectricidad(double montoPorKwConsumido) {
		this.montoPorKwConsumido = montoPorKwConsumido;
	}

	public double importePara(Container c, Orden o) {
		if(! c.esRefeer()) return 0;
		double horas = o.horasDeServicio();
		return c.getConsumoKwHora() * horas * montoPorKwConsumido;
	}
	
	public DesgloseDeServicio obtenerDesglose(Container c, Orden o) {
		return new DesgloseDeServicio("ServicioElectricidad", o.getFechaDeFacturacion(), importePara(c, o));
	}	//Esta bien el segundo  parametro? o deberia ser el turno para los dias excedentes?
}
