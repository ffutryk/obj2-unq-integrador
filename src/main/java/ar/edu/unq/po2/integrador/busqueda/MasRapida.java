package ar.edu.unq.po2.integrador.busqueda;

import java.util.Comparator;
import java.util.List;

import ar.edu.unq.po2.integrador.Circuito;
import ar.edu.unq.po2.integrador.Terminal;

public class MasRapida implements EstrategiaBusquedaCircuito {
	@Override
    public Circuito mejorCircuitoHacia(List<Circuito> circuitosDisponibles, Terminal destino) {
        if(circuitosDisponibles.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un circuito disponible");
        }

        return circuitosDisponibles.stream()
                .filter(c -> c.incluyeLaTerminal(destino))
                .min(Comparator.comparing(c -> c.duracionHasta(destino)))
                .orElseThrow(() -> new IllegalArgumentException("No hay circuitos que lleguen al destino especificado"));
    }
}
