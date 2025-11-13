package ar.edu.unq.po2.integrador;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.integrador.busqueda.ICircuitosProveedor;
import ar.edu.unq.po2.integrador.busqueda.IViajesProveedor;
import ar.edu.unq.po2.integrador.busqueda.MotorDeBusqueda;
import ar.edu.unq.po2.integrador.busqueda.estrategias.MasBarata;
import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.email.Email;
import ar.edu.unq.po2.integrador.email.IEmailService;
import ar.edu.unq.po2.integrador.fases.Viaje;
import ar.edu.unq.po2.integrador.filtros.criterios.FiltroPorDestino;
import ar.edu.unq.po2.integrador.ordenes.Orden;
import ar.edu.unq.po2.integrador.ordenes.OrdenExportacion;
import ar.edu.unq.po2.integrador.ordenes.OrdenImportacion;
import ar.edu.unq.po2.integrador.reportes.VisitanteAduana;
import ar.edu.unq.po2.integrador.reportes.VisitanteBuque;
import ar.edu.unq.po2.integrador.reportes.VisitanteMuelle;
import ar.edu.unq.po2.integrador.reportes.VisitanteReportable;
import ar.edu.unq.po2.integrador.servicios.Servicio;
import ar.edu.unq.po2.integrador.servicios.ServicioAlmacenamientoExcedente;

public class TerminalGestionada extends Terminal implements ICircuitosProveedor, IViajesProveedor{

	private List<Orden> ordenes;
	private List<Naviera> navieras;
	private List<Cliente> clientes;
	private List<EmpresaTransportista> empresasTransportistas;
	private MotorDeBusqueda motorDeBusqueda;
	private IEmailService emailService;

	public TerminalGestionada(String unNombre, PosicionGeografica unaPosicionGeografica, IEmailService servicioEmail) {
		super(unNombre, unaPosicionGeografica);
		this.ordenes = new ArrayList<Orden>(); 
		this.navieras = new ArrayList<Naviera>();
		this.clientes = new ArrayList<Cliente>();
		this.motorDeBusqueda = new MotorDeBusqueda(this, this, new MasBarata());
		this.empresasTransportistas = new ArrayList<EmpresaTransportista>();
		this.emailService = servicioEmail;
	}

	public Orden exportar(Viaje unViaje, Container unContainer, Camion unCamion, Chofer unChofer, Cliente unCliente) {
		if(!unViaje.estaHabilitadoParaExportacion()) {
			throw new RuntimeException("No se pueden registrar nuevas exportaciones en este viaje...");
		}
		Orden orden = new OrdenExportacion(unViaje, unContainer, unCamion, unChofer, unCliente, unViaje.fechaDeArriboA(this));
		this.ordenes.add(orden);
		return orden;
	}
	

	protected Integer cantidadDeOrdenes() { // Creo que sería protected no public, para que solo sea enviado por el Test...
		return this.ordenes.size();
	}

	public Orden importar(Viaje unViaje, Container unContainer, Camion unCamion, Chofer unChofer, Cliente unCliente) {
		if(!unViaje.estaHabilitadoParaImportacion()) {
			throw new RuntimeException("No se pueden registrar nuevas importaciones en este viaje...");
		}
		Orden orden = new OrdenImportacion(unViaje, unContainer, unCamion, unChofer, unCliente, unViaje.fechaDeArriboA(this));
		this.ordenes.add(orden);
		return orden;
	}

	public void contratarServicio(Orden unaOrden, Servicio unServicio) {
		unaOrden.agregarDesgloseServicio(unServicio);
	}

	public void registrarNaviera(Naviera unaNaviera) {
		this.navieras.add(unaNaviera);
	}
	
	protected Integer cantidadDeNavieras() {
		return this.navieras.size();
	}

	public void registrarCliente(Cliente unCliente) {
		this.clientes.add(unCliente);
	}

	protected Integer cantidadDeClientes() {
		return this.clientes.size();
	}
	
	public void registrarEmpresaTransportista(EmpresaTransportista empresa) {
		this.empresasTransportistas.add(empresa);
	}

	@Override
	public List<Circuito> circuitosDisponibles() {
		List<Circuito> circuitos = new ArrayList<Circuito>();
		for(Naviera n : navieras) {
			circuitos.addAll(n.getCircuitos());
		}
		return circuitos;
	}

	@Override
	public List<Viaje> viajesDisponibles() {
		List<Viaje> viajes = new ArrayList<Viaje>();
		for(Naviera n : navieras) {
			viajes.addAll(n.getViajes());
		}
		return viajes;
	}
	
	public Circuito mejorCircuitoHacia(Terminal unaTerminal) {
		return this.motorDeBusqueda.mejorCircuitoHacia(unaTerminal);
	}

	protected Integer cantidadDeEmpresasTransportistas() {
		return this.empresasTransportistas.size();
	}
	

	public void anunciarInminenteLlegada(Viaje unViaje) { // Este mensaje es enviado por la fase Inbound...
		//this.mandarMail(unViaje);
		this.ordenes.stream().filter(orden -> orden.esDeImportacion() && orden.getViaje().equals(unViaje)).forEach(orden -> orden.enviarMail(emailService));
	}

	/*private void mandarMail(Viaje unViaje) {
		this.ordenes.stream().filter(orden -> orden.getViaje().equals(unViaje)).forEach(orden -> orden.enviarMail(this.emailService));
	}*/
	
	public void anunciarPartida(Viaje unViaje) {
		this.arribados.remove(unViaje); 
		this.ordenes.stream().filter(orden -> !orden.esDeImportacion() && orden.getViaje().equals(unViaje)).forEach(orden -> orden.enviarMail(emailService));
		this.enviarFacturasPara(unViaje);  
	}
	
	private void enviarFacturasPara(Viaje unViaje) {
		this.ordenes.stream().filter(orden -> orden.getViaje().equals(unViaje)).forEach(orden -> orden.enviarFactura(this.emailService));
	}
	
	public Duration tiempoDeViajePorA(Naviera unaNaviera, Terminal unaTerminal) {
		asertarNavieraIncluida(unaNaviera);
		asertarTerminalIncluidaEnCircuitoDe(unaNaviera, unaTerminal);
		return unaNaviera.getCircuitos().stream()
		        .filter(circuito -> circuito.incluyeLaTerminal(unaTerminal))
		        .map(circuito -> circuito.duracionHasta(unaTerminal))
		        .min(Duration::compareTo).get();
	}

	private void asertarTerminalIncluidaEnCircuitoDe(Naviera unaNaviera, Terminal unaTerminal) {
		List<Circuito> circuitosNaviera = unaNaviera.getCircuitos();
		if(!circuitosNaviera.stream().anyMatch(circuito -> circuito.incluyeLaTerminal(unaTerminal))) {
			throw new RuntimeException("La terminal no pertenece a ningún circuito de la naviera dada...");
		}
		
	}

	private void asertarNavieraIncluida(Naviera unaNaviera) {
		if(!navieras.contains(unaNaviera)) {
			throw new RuntimeException("La naviera no está registrada en la terminal...");
		}
	}
	
	protected String generarReportePara(VisitanteReportable visitante, Viaje viaje) {
		return visitante.generarReportePara(viaje, ordenes);
	}

	public String generarReporteMuellePara(Viaje unViaje) {
		return generarReportePara(new VisitanteMuelle(), unViaje);
	}
	
	public String generarReporteAduanaPara(Viaje unViaje) {
		return generarReportePara(new VisitanteAduana(), unViaje);
	}
	
	public String generarReporteBuquePara(Viaje unViaje) {
		return generarReportePara(new VisitanteBuque(), unViaje);
	}
	
	public LocalDateTime proximaFechaDePartidaHasta(Terminal unDestino) {
		asertarDestinoIncluidoEnViajes(unDestino);
		return this.motorDeBusqueda.buscarViajes(new FiltroPorDestino(unDestino)).stream()
				.map(viaje -> viaje.fechaDeArriboA(this)) 
				.min( (date1, date2) -> date1.compareTo(date2)) 
				.get();
	}

	private void asertarDestinoIncluidoEnViajes(Terminal unDestino) {
		if(!this.viajesDisponibles().stream().anyMatch(viaje -> viaje.pasaPorLaTerminal(unDestino))) {
			throw new RuntimeException("El destino no es alcanzado por ninguno de los viajes disponibles...");
		}
	}

	void agregarOrden(Orden unaOrden) {
		this.ordenes.add(unaOrden);
	}

	public void autorizarEntrega(Orden unaOrden, Camion unCamion, Chofer unChofer) {
		asertarTurnoPara(unaOrden);
		asertarCamionAutorizado(unaOrden, unCamion);
		asertarChoferAutorizado(unaOrden, unChofer);
	}

	private void asertarChoferAutorizado(Orden unaOrden, Chofer unChofer) {
		if(!empresasTransportistas.stream().anyMatch(empresa -> empresa.tieneRegistradaA(unChofer)) ||
		   !unaOrden.getChofer().equals(unChofer)) {
			throw new RuntimeException("El chofer no está autorizado para ésta orden...");
		}
	}

	private void asertarCamionAutorizado(Orden unaOrden, Camion unCamion) {
		if(!empresasTransportistas.stream().anyMatch(empresa -> empresa.tieneRegistradaA(unCamion)) ||
		   !unaOrden.getCamion().equals(unCamion)) {
			throw new RuntimeException("El camión no está autorizado para ésta orden...");
		}
	}

	private void asertarTurnoPara(Orden unaOrden) {
		long diferencia = Duration.between(unaOrden.getTurno(), LocalDateTime.now()).toHours();
		if(diferencia > 3) {
			throw new RuntimeException("Venció su turno...");
		}
	}
	
	public void autorizarRetiro(Orden unaOrden, Camion unCamion, Chofer unChofer) {
		long diferencia = Duration.between(unaOrden.getTurno(), LocalDateTime.now()).toHours();
		if(diferencia > 24) {
			this.contratarServicio(unaOrden, new ServicioAlmacenamientoExcedente(20d));
		}
		asertarCamionAutorizado(unaOrden, unCamion);
		asertarChoferAutorizado(unaOrden, unChofer);
	}
}
