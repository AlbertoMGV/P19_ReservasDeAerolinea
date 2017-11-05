package utilidades;

public class Coordenadas {
	
	private double lat;
	private double lon;
	
	public Coordenadas(String lat, String lon) {	
		this.lat = Double.parseDouble(lat);
		this.lon = Double.parseDouble(lon);
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	

}
