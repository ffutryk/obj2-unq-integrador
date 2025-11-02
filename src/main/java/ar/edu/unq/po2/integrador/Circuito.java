package ar.edu.unq.po2.integrador;

import java.util.ArrayList;

public class Circuito {
	
	private Terminal origen;
	private ArrayList<Tramo> tramos;
	
	public Circuito(Terminal unaTerminal, ArrayList<Tramo> tramos) throws RuntimeException {
		if(tramos.isEmpty()) {
			throw new RuntimeException("No se puede crear un circuito que no tenga tramos!");
		}
		this.origen = unaTerminal;
		this.tramos = tramos;
	}

	public Terminal getOrigen() {
		return this.origen;
	}

	public Double duracionTotal() {
		return tramos.stream().mapToDouble(tramo -> tramo.getDuracion()).sum();
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

	public double duracionEntre(Tramo unTramo, Tramo otroTramo) {
		asertarTramosIncluidos(unTramo, otroTramo);
		int indiceMenor = Math.min(tramos.indexOf(unTramo), tramos.indexOf(otroTramo));
		Tramo inicio = tramos.get(indiceMenor);
		Tramo fin = (inicio == unTramo) ? otroTramo : unTramo; // Me quedo con el tramo restante por descarte...
		double duracion = 0;
		for(int index = tramos.indexOf(inicio); index <= tramos.indexOf(fin); index++) {
			duracion += tramos.get(index).getDuracion(); // Sumo el costo del tramo actual...
		}
		return duracion;
	}

}
