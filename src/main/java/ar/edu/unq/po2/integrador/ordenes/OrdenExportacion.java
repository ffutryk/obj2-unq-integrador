package ar.edu.unq.po2.integrador.ordenes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.integrador.Cliente;
import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.email.Email;
import ar.edu.unq.po2.integrador.email.IEmailService;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.reportes.VisitanteReportable;

public class OrdenExportacion extends Orden {

	private LocalDateTime fechaIngreso;
	
	public OrdenExportacion(Viaje viaje, Container container, String camion, String chofer, Cliente cliente, LocalDateTime fechaSalida) {
		super(viaje, container, camion, chofer, cliente, fechaSalida);
	}
	
	public void registrarIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public double diasDeServicio() {
		if (fechaIngreso == null) return 0; //deberia dar 0 si todavia no se registro el retiro? o dar los dias hasta now()?
		return (double) ChronoUnit.DAYS.between(this.getTurno(), fechaIngreso);
	}
	
	public boolean esDeImportacion() {
		return false;
	}
	
	public LocalDateTime getFechaDeFacturacion() {
		return fechaIngreso;
	}

	@Override
	public void aceptar(VisitanteReportable visitante) {
		visitante.visitar(this);
	}
	
	public void enviarMail(IEmailService emailService) {
		Cliente destinatario = this.cliente;
		String asunto = "Partida exitosa de la carga " + this.container.getId();
		String cuerpo = "Tu carga ha partido correctamente...";
		Email email = new Email(destinatario.getDirecciomMail(), asunto, cuerpo);
		emailService.mandarEmail(email);
	}
}
