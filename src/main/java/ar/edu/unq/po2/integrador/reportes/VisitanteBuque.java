package ar.edu.unq.po2.integrador.reportes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.integrador.Buque;
import ar.edu.unq.po2.integrador.ordenes.OrdenExportacion;
import ar.edu.unq.po2.integrador.ordenes.OrdenImportacion;

public class VisitanteBuque extends VisitanteReportable {
	private List<String> idsImportacion = new ArrayList<>();
    private List<String> idsExportacion = new ArrayList<>();
	
	@Override
	public void visitar(Buque buque) { }

	@Override
    public void visitar(OrdenImportacion orden) {
        idsImportacion.add(orden.getContainer().getId()); 
    }

    @Override
    public void visitar(OrdenExportacion orden) {
    	idsExportacion.add(orden.getContainer().getId());
    }

	@Override
	protected String generarReporte() {
		StringBuilder reporte = new StringBuilder();
        reporte.append("<report>\n");
        
        reporte.append(generarSeccionDeIds("import", idsImportacion));
        reporte.append(generarSeccionDeIds("export", idsExportacion));
        
        reporte.append("</report>");
        
        return reporte.toString();
	}
	
	private String generarSeccionDeIds(String nombre, List<String> ids) {
		StringBuilder seccion = new StringBuilder();
		
		seccion.append("\t<" + nombre + ">\n");
        for (String id : ids) {
        	seccion.append("\t\t<item>" + id + "</item>\n");
        }
        seccion.append("\t</" + nombre + ">\n");
		
		return seccion.toString();
	}
}
