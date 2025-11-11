package ar.edu.unq.po2.integrador.reportes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.integrador.Buque;
import ar.edu.unq.po2.integrador.OrdenExportacion;
import ar.edu.unq.po2.integrador.OrdenImportacion;
import ar.edu.unq.po2.integrador.containers.Container;
import ar.edu.unq.po2.integrador.fases.Viaje;


public class VisitanteAduana extends VisitanteReportable {

    private String nombreBuque;
    private LocalDateTime fechaArribo;
    private LocalDateTime fechaPartida;
    private List<Container> contenedoresOperados = new ArrayList<>();
    
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
        this.contenedoresOperados.add(orden.getContainer());
    }

    @Override
    public void visitar(OrdenExportacion orden) {
        this.contenedoresOperados.add(orden.getContainer());
    }

    @Override
    protected String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("<html>\n<head>\n\t<title>Reporte de Aduana</title>\n</head>\n");
        reporte.append("<body>\n");
        reporte.append("\t<h1>Reporte de Aduana</h1>\n");
        reporte.append("\t<h2>Buque: " + nombreBuque + "</h2>\n");
        reporte.append("\t<p>Fecha Arribo: " + fechaArribo.format(formatter) + "</p>\n");
        reporte.append("\t<p>Fecha Partida: " + fechaPartida.format(formatter) + "</p>\n");
        
        reporte.append("\t<h3>Listado de Contenedores</h3>\n");
        reporte.append("\t<ul>\n");
        
        for (Container c : contenedoresOperados) {
            reporte.append("\t\t<li>ID: " + c.getId() + " - Tipo: " + c.getTipo().getTexto() + "</li>\n"); 
        }
        
        reporte.append("\t</ul>\n");
        reporte.append("</body>\n</html>");
        
        return reporte.toString();
    }
}