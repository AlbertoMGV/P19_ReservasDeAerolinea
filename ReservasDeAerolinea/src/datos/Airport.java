package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Airport {
	
	private int airportId;
	
	private static HashMap<String, Airport> aeropuertos;
	
	private String name;
	private String city;
	private String country;	
	private String IATA;
	private String ICAO;	
	private String lat;
	private String lon;
	
	public Airport(String airportId, String name, String city, String country, String IATA, String ICAO, String lat, String lon) {
		this.airportId = Integer.parseInt(airportId);
		this.name = name;
		this.city = city;
		this.country = country;
		this.IATA = IATA;
		this.ICAO = ICAO;
		this.lat = lat;
		this.lon = lon;
	}
	
	//Devuelve un objeto Airport con su código IATA
	
	public static Airport get(String IATA){
		if(aeropuertos == null){
			aeropuertos = new HashMap<String, Airport>();
			File datos = new File("res/airports_new.dat");
			try {
				FileReader fr = new FileReader(datos);
				BufferedReader bfr = new BufferedReader(fr);
				String linea;
				
				while((linea = bfr.readLine()) != null){
					String[] data = linea.split(",");
					aeropuertos.put(data[4].replace("\"", ""), new Airport(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return aeropuertos.get(IATA);
	}

	public int getAirportId() {
		return airportId;
	}

	public String getName() {
		return name.replace("\"", "");
	}

	public String getCity() {
		return city.replace("\"", "");
	}

	public String getCountry() {
		return country.replace("\"", "");
	}

	public String getIATA() {
		return IATA.replace("\"", "");
	}

	public String getICAO() {
		return ICAO.replace("\"", "");
	}

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}
	
	
}
