package ar.edu.unq.po2.integrador;

public class PosicionGeografica {

	private double latitud;
	private double longitud;
	
	private static final double RADIO_TIERRA_KM = 6371.0;
	
	public PosicionGeografica(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public double distanciaHasta(PosicionGeografica otra) {
        double lat1Rad = Math.toRadians(this.latitud);
        double lon1Rad = Math.toRadians(this.longitud);
        double lat2Rad = Math.toRadians(otra.getLatitud());
        double lon2Rad = Math.toRadians(otra.getLongitud());

        double difLat = lat2Rad - lat1Rad;
        double difLon = lon2Rad - lon1Rad;

        double a = Math.pow(Math.sin(difLat / 2), 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.pow(Math.sin(difLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIO_TIERRA_KM * c; // Formula Haversine
    }

	public double getLatitud() {
		return this.latitud;
	}
	
	public double getLongitud() {
		return this.longitud;
	}
}
