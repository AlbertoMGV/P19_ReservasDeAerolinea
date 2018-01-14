package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import gestores.GestorRutas;

public class Airport {
	
	private int airportId;
	
	private static HashMap<String, Airport> aeropuertos;
	
	private Airport prev;
	
	private ArrayList<String[]> destinos;
		
	private ArrayList<Airport> parent;
	private static ArrayList<Airport> todosAeropuertos;
	
	private String name;
	private String city;
	private String country;	
	private String IATA;
	private String ICAO;	
	private String lat;
	private String lon;
	
	public Airport(String airportId, String name, String city, String country, String IATA, String ICAO, String lat, String lon) {
		this.airportId = Integer.parseInt(airportId);
		this.IATA = IATA.replace("\"", "");
		this.destinos = GestorRutas.leerRutas(this.IATA);
		this.name = name;
		this.city = city;
		this.country = country;
		this.ICAO = ICAO;
		this.lat = lat;
		this.lon = lon;
	}
	
	public Airport clone() {
		Airport a = new Airport(this.airportId+"", this.name, this.city, this.country, this.IATA, this.ICAO, this.lat, this.lon);
		a.setPrevious(prev);
		return a;
		
	}
	
	//Devuelve un objeto Airport con su código IATA
	
	public ArrayList<String[]> getDestinos() {
		return destinos;
	}
	
	public void setPrevious(Airport a) {
		this.prev = a;
	}
	
	public Airport getPrevious() {
		return this.prev;
	}

	public static Airport get(String IATA){
		if(aeropuertos == null){
			aeropuertos = new HashMap<String, Airport>();
			File datos = new File("res/airports_new.dat");
			try {
				FileReader fr = new FileReader(datos);
				BufferedReader bfr = new BufferedReader(fr);
				String linea;
				while((linea = bfr.readLine()) != null){
					linea = linea.replaceAll("\"", "");
					String[] data = linea.split(",");
					Airport result = new Airport(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
					if(result.getIATA().length() > 2) {
						aeropuertos.put(data[4], result);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return aeropuertos.get(IATA);
	}
	
	public static ArrayList<Airport> getAll(){
		if(todosAeropuertos == null){
			todosAeropuertos = new ArrayList<Airport>();
			File datos = new File("res/airports_new.dat");
			try {
				FileReader fr = new FileReader(datos);
				BufferedReader bfr = new BufferedReader(fr);
				String linea;
				while((linea = bfr.readLine()) != null){
					System.out.println(linea);
					linea = linea.replace("\"", "");
					String[] data = linea.split(",");
					Airport result = new Airport(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
					if(result.getIATA().length() > 2) {
						todosAeropuertos.add(result);
					}
					}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return todosAeropuertos;
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
	
	public void setParent(Airport v) {
		if(this.parent == null) {
			this.parent = new ArrayList<Airport>();
		}
		this.parent.add(v);
	}
	
	public ArrayList<Airport> getParent() {
		return this.parent;
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
	
	public String toString() {
		return this.city + ", (" + this.IATA + ")";
	}
	
	
}
