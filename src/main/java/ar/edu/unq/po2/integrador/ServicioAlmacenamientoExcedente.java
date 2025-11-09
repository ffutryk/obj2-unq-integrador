package ar.edu.unq.po2.integrador;

import java.time.LocalDate;

public class ServicioAlmacenamientoExcedente implements Servicio{

	private double montoPorDia;
	
	public ServicioAlmacenamientoExcedente(double montoPorDia, LocalDate fechaLlegada) {
		this.montoPorDia = montoPorDia;
	}

	@Override
	public double importePara(Container c, Orden o) {
		if(! o.esDeImportacion()) return 0;
		double dias = o.diasDeServicio() -1; // puse -1 xq el primer dia no se cobra
		return dias * montoPorDia;
	}
	
	@Override
	public DesgloseDeServicio obtenerDesglose(Container c, Orden o) {
		return new DesgloseDeServicio("ServicioAlmacenamientoExcedente", o.getFechaDeFacturacion(), importePara(c, o));
	}	//Esta bien el segundo  parametro? o deberia ser el turno para los dias excedentes?

}
