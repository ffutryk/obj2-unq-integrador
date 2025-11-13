package ar.edu.unq.po2.integrador.busqueda.estrategias;

import java.util.Comparator;
import java.util.List;

import ar.edu.unq.po2.integrador.Circuito;
import ar.edu.unq.po2.integrador.Terminal;
import ar.edu.unq.po2.integrador.Tramo;

public class ParadasMinimas implements EstrategiaBusquedaCircuito {
	@Override
	public Circuito mejorCircuitoHacia(List<Circuito> circuitosDisponibles, Terminal destino) {
        if(circuitosDisponibles.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un circuito disponible");
        }

        return circuitosDisponibles.stream()
                .filter(c -> c.incluyeLaTerminal(destino))
                .min(Comparator.comparingInt(c -> cantidadDeParadasPorHasta(c, destino)))
                .orElseThrow(() -> new IllegalArgumentException("No hay circuitos que lleguen al destino especificado"));
	}
	
	public int cantidadDeParadasPorHasta(Circuito circuito, Terminal terminal) {
	    int paradas = 0;

	    for (Tramo tramo : circuito.tramos()) {
	        paradas++;
	        if (tramo.contieneA(terminal)) {
	            break;
	        }
	    }

	    if (paradas == 0 || !circuito.tramos().stream().anyMatch(t -> t.contieneA(terminal))) {
	        throw new RuntimeException("La terminal no pertenece al circuito");
	    }

	    return paradas;
	}
}
