package ar.edu.unq.po2.integrador.servicios;

import ar.edu.unq.po2.integrador.Orden;
import ar.edu.unq.po2.integrador.containers.Container;

public interface Servicio {

	double importePara(Container c, Orden o);
	
	DesgloseDeServicio obtenerDesglose(Container c, Orden o);
}
