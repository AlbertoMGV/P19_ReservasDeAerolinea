package datos;

public class Airport {
	
	private int airportId;
	
	private String name;
	private String city;
	private String country;	
	private String IATA;
	private String ICAO;	
	private String lat;
	private String lon;
	
	public Airport(int airportId, String name, String city, String country, String IATA, String ICAO, String lat, String lon) {
		this.airportId = airportId;
		this.name = name;
		this.city = city;
		this.country = country;
		this.IATA = IATA;
		this.ICAO = ICAO;
		this.lat = lat;
		this.lon = lon;
	}

	public int getAirportId() {
		return airportId;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getIATA() {
		return IATA;
	}

	public String getICAO() {
		return ICAO;
	}

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}
	
	
}
