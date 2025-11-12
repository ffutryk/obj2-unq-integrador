package ar.edu.unq.po2.integrador.servicios;

import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.ordenes.Orden;

public interface Servicio {

	double importePara(Container c, Orden o);
	
	DesgloseDeServicio obtenerDesglose(Container c, Orden o);
}
