package ar.edu.unq.po2.integrador;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unq.po2.integrador.ordenes.Orden;
import ar.edu.unq.po2.integrador.servicios.DesgloseDeServicio;

public class Factura {

	private LocalDateTime fechaEmision;
	private Cliente cliente;
	private Orden orden;
	
	public Factura(Cliente cliente) {
		this.cliente = cliente;
		this.fechaEmision = LocalDateTime.now();
		this.orden = cliente.getOrden();
	}
	
	public List<DesgloseDeServicio> desgloseDeServicios(){
		return orden.getDesgloses();
	}
	
	public double montoTotalFacturado() {
		double costoViaje = 0;
		if(orden.esDeImportacion()) {
			costoViaje = orden.getViaje().getCircuito().costoTotal();
		}
		return desgloseDeServicios().stream().mapToDouble(d->d.getCosto()).sum() + costoViaje;
	}

	public LocalDateTime getFechaEmision() {
		return fechaEmision;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public String imprimir() {
		StringBuilder facturaImpresa = new StringBuilder();
		List<DesgloseDeServicio> desgloses = this.desgloseDeServicios();
		facturaImpresa.append("\tServicio\tFecha\tCosto\n");
		desgloses.stream().forEach(desglose -> facturaImpresa.append(desglose.imprimir()));
		return facturaImpresa.toString();
	}
}
