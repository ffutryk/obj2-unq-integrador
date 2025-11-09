package ar.edu.unq.po2.integrador;

public interface Servicio {

	double importePara(Container c, Orden o);
	
	DesgloseDeServicio obtenerDesglose(Container c, Orden o);
}
