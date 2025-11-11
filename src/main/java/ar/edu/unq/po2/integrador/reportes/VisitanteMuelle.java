package ar.edu.unq.po2.integrador.reportes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ar.edu.unq.po2.integrador.Buque;
import ar.edu.unq.po2.integrador.OrdenExportacion;
import ar.edu.unq.po2.integrador.OrdenImportacion;
import ar.edu.unq.po2.integrador.fases.Viaje;

public class VisitanteMuelle extends VisitanteReportable {
    private String nombreBuque;
    private LocalDateTime fechaArribo;
    private LocalDateTime fechaPartida;
    private int totalContenedores = 0;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public void visitar(Buque buque) {
        Viaje viaje = buque.viaje();
        
        this.nombreBuque = buque.nombre();
        this.fechaArribo = viaje.fechaDeArriboA(viaje.getGestionada());
        this.fechaPartida = viaje.fechaDeArriboA(viaje.getGestionada());
    }

    @Override
    public void visitar(OrdenImportacion orden) {
        this.totalContenedores++;
    }

    @Override
    public void visitar(OrdenExportacion orden) {
        this.totalContenedores++;
    }

    @Override
    protected String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Muelle\n");
        reporte.append("-----------------\n");
        reporte.append("Buque: " + nombreBuque + "\n");
        reporte.append("Fecha Arribo: " + fechaArribo.format(formatter) + "\n");
        reporte.append("Fecha Partida: " + fechaPartida.format(formatter) + "\n");
        reporte.append("Contenedores Operados: " + this.totalContenedores + "\n");
        
        return reporte.toString();
    }
}