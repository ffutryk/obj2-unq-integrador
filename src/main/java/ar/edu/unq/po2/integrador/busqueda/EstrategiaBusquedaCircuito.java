package ar.edu.unq.po2.integrador.busqueda;

import java.util.List;

import ar.edu.unq.po2.integrador.Circuito;
import ar.edu.unq.po2.integrador.Terminal;

public interface EstrategiaBusquedaCircuito {
	Circuito mejorCircuitoHacia(List<Circuito> circuitosDisponibles, Terminal destino); 
}
