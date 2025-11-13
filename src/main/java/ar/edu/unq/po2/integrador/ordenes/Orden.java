package ar.edu.unq.po2.integrador.ordenes;

import java.util.List;

import ar.edu.unq.po2.integrador.Camion;
import ar.edu.unq.po2.integrador.Chofer;
import ar.edu.unq.po2.integrador.Cliente;
import ar.edu.unq.po2.integrador.Factura;
import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.email.Email;
import ar.edu.unq.po2.integrador.email.IEmailService;
import ar.edu.unq.po2.integrador.servicios.DesgloseDeServicio;
import ar.edu.unq.po2.integrador.servicios.Servicio;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.reportes.IReportable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Orden implements IReportable {
	
	protected Container container;
	protected Cliente cliente;
	protected List<DesgloseDeServicio> serviciosContratados;
	protected LocalDateTime turno;  //fechaLlegada o fechaSalida (depende si es impo o expo)
	protected Viaje viaje;
	protected Chofer chofer;
	protected Camion camion;

	public Orden(Viaje viaje, Container container, Camion camion, Chofer chofer, Cliente cliente, LocalDateTime turno) {
		this.cliente = cliente;
		this.container = container;
		this.serviciosContratados = new ArrayList<DesgloseDeServicio>();
		this.turno = turno;
		this.viaje = viaje;
		this.camion = camion;
		this.chofer = chofer;
	}
	
	public void agregarDesgloseServicio(Servicio s) {
		serviciosContratados.add(s.obtenerDesglose(this.container, this));
	}
	
	public LocalDateTime getTurno() {
		return turno;
	}
	
	public Container getContainer() {
        return container;
    }
	
	public abstract double horasDeServicio();
	
	public abstract boolean esDeImportacion();
	
	public abstract LocalDateTime getFechaDeFacturacion();
	
	public Cliente getCliente() {
	    return cliente;
	}
	
	public Viaje getViaje() {
	    return viaje;
	}

	public List<DesgloseDeServicio> getDesgloses() {
	    return serviciosContratados;
	}
	
	public double totalServicios() {
	    return serviciosContratados.stream().mapToDouble(s->s.getCosto()).sum();
	}
	
	public void enviarFactura(IEmailService emailService) {
		Factura facturaCorrespondiente = new Factura(this.cliente);
		Cliente destinatario = this.cliente;
		String asunto = "Factura correspondiente a " + this.container.getId();
		String cuerpo = facturaCorrespondiente.imprimir();
		Email email = new Email(destinatario.getDirecciomMail(), asunto, cuerpo);
		emailService.mandarEmail(email);
	}
	
	public abstract void enviarMail(IEmailService emailService);

	public Chofer getChofer() {
		return this.chofer;
	}

	public Camion getCamion() {
		return this.camion;
	}
	
}
