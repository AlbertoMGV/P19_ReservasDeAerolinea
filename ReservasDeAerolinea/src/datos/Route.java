package datos;

import java.util.ArrayList;
import java.util.Random;

public class Route {
	
	private Airport origin;
	private Airport destination;
	private int distance;
	private String airline;
	private String aircraft;
	
	public Route(){
		
	}
	
	public Route(Route route) {
		this.origin = route.origin;
		this.destination = route.destination;
		this.distance = route.distance;
		this.airline = route.airline;
		this.aircraft = route.aircraft;
	}

	public Route(Airport origin, String distance, Airport destination, String aircraft, String airline) {
		this.origin = origin;
		this.distance = Integer.parseInt(distance);
		this.destination = destination;
		this.airline = airline;
		this.aircraft = aircraft;		
	}
	
	public String getAirline(){
		return this.airline;
	}
	
	public int getDistance(){
		return this.distance;
	}

	public Airport getOrigin() {
		return origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public String getAircraft() {
		return aircraft;
	}
	
	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
		
	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	

}
