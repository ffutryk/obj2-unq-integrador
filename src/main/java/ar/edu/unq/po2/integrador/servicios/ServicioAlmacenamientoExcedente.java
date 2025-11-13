package ar.edu.unq.po2.integrador.servicios;

import java.time.LocalDateTime;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.ordenes.Orden;

public class ServicioAlmacenamientoExcedente implements Servicio{

	private double montoPorDia;
	
	public ServicioAlmacenamientoExcedente(double montoPorDia) {
		this.montoPorDia = montoPorDia;
	}

	@Override
	public double importePara(Container c, Orden o) {
		if(! o.esDeImportacion()) return 0;
		double dias = (o.horasDeServicio() / 24.0) - 1; // puse -1 xq el primer dia no se cobra
		if (dias <= 0) dias = 0; //para evitar cobrar negativo 
		return dias * montoPorDia;
	}
	
	@Override
	public DesgloseDeServicio obtenerDesglose(Container c, Orden o) {
		return new DesgloseDeServicio("ServicioAlmacenamientoExcedente", o.getFechaDeFacturacion(), importePara(c, o));
	}	//Esta bien el segundo  parametro? o deberia ser el turno para los dias excedentes?

}
