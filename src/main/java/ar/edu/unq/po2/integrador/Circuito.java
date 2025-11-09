package ar.edu.unq.po2.integrador;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Circuito {
	
	private Terminal origen;
	private List<Tramo> tramos;
	
	public Circuito(Terminal unaTerminal, List<Tramo> tramos) throws RuntimeException {
		if(tramos.isEmpty()) {
			throw new RuntimeException("No se puede crear un circuito que no tenga tramos!");
		}
		this.origen = unaTerminal;
		this.tramos = tramos;
	}

	public Terminal getOrigen() {
		return this.origen;
	}

	public Duration duracionTotal() {
		return tramos.stream()
                .map(Tramo::getDuracion)
                .reduce(Duration.ZERO, Duration::plus);
	}

	public Double costoTotal() {
		return tramos.stream().mapToDouble(tramo -> tramo.getCosto()).sum();
	}

	public Double costoEntre(Tramo unTramo, Tramo otroTramo) throws RuntimeException {
		asertarTramosIncluidos(unTramo, otroTramo);
		int indiceMenor = Math.min(tramos.indexOf(unTramo), tramos.indexOf(otroTramo));
		Tramo inicio = tramos.get(indiceMenor);
		Tramo fin = (inicio == unTramo) ? otroTramo : unTramo; // Me quedo con el tramo restante por descarte...
		double costo = 0;
		for(int index = tramos.indexOf(inicio); index <= tramos.indexOf(fin); index++) {
			costo += tramos.get(index).getCosto(); // Sumo el costo del tramo actual...
		}
		return costo;
	}

	private void asertarTramosIncluidos(Tramo unTramo, Tramo otroTramo) {
		if(!tramos.contains(unTramo) || !tramos.contains(otroTramo)) {
			throw new RuntimeException("Alguno de los tramos no pertenece al circuito en cuestión...");
		}
	}

	public Duration duracionEntre(Terminal unaTerminal, Terminal otraTerminal) {
	    Duration duracion = Duration.ZERO;
	    boolean contando = false;

	    for (Tramo tramo : tramos) {
	        if (tramo.contieneA(unaTerminal) || contando) {
	            contando = true;
	            duracion = duracion.plus(tramo.getDuracion());
	        }
	        if (tramo.contieneA(otraTerminal) && contando) {
	            break;
	        }
	    }

	    if (!contando) {
	        throw new RuntimeException("Alguno de los terminales no pertenece al circuito");
	    }

	    return duracion;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // mismo objeto
        if (obj == null || getClass() != obj.getClass()) return false;
        Circuito circuito = (Circuito) obj;
        return this.origen.equals(circuito.getOrigen()) && this.tramos.equals(circuito.getTramos());// criterio de igualdad
    }
	
	@Override
	public int hashCode() {
	    return Objects.hash(origen, tramos);
	}

	private List<Tramo> getTramos() {
		return this.tramos;
	}
	
	public boolean incluyeLaTerminal(Terminal terminal) {
	    return this.getTramos()
	    		   .stream()
	               .anyMatch(tramo -> tramo.contieneA(terminal)); 
	}

	public int cantidadDeParadasHasta(Terminal terminal) {
	    int paradas = 0;

	    for (Tramo tramo : tramos) {
	        paradas++;
	        if (tramo.contieneA(terminal)) {
	            break;
	        }
	    }

	    if (paradas == 0 || !tramos.stream().anyMatch(t -> t.contieneA(terminal))) {
	        throw new RuntimeException("La terminal no pertenece al circuito");
	    }

	    return paradas;
	}

	public Duration duracionHasta(Terminal destino) {
		// Precondición: La terminal dada está en el circuito.
		return duracionEntre(origen, destino);
	}

}
