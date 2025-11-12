package ar.edu.unq.po2.ordenes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.integrador.Cliente;
import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.email.Email;
import ar.edu.unq.po2.integrador.email.IEmailService;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.reportes.VisitanteReportable;

public class OrdenImportacion extends Orden {

	private LocalDateTime fechaRetiro;
	
	public OrdenImportacion(Viaje viaje, Container container, String camion, String chofer, Cliente cliente, LocalDateTime fechaLlegada) {
		super(viaje, container, camion, chofer, cliente, fechaLlegada);
	}
	 
	public void registrarRetiro(LocalDateTime fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	 
	public double diasDeServicio() {
		if (fechaRetiro == null) return 0; //deberia dar 0 si todavia no se registro el retiro? o dar los dias hasta now()?
		return (double) ChronoUnit.DAYS.between(this.getTurno(), fechaRetiro);
	}
	
	@Override
	public boolean esDeImportacion() {
		return true;
	}
	
	public LocalDateTime getFechaDeFacturacion() {
		return fechaRetiro;
	}
	
	@Override
	public void aceptar(VisitanteReportable visitante) {
		visitante.visitar(this);
	}
	
	public void enviarMail(IEmailService emailService) {
		Cliente destinatario = this.cliente;
		String asunto = "Llegada inminente de la carga " + this.container.getId();
		String cuerpo = "Tu carga se encuentra a 50km de la terminal...";
		Email email = new Email(destinatario.getDirecciomMail(), asunto, cuerpo);
		emailService.mandarEmail(email);
	}
}
