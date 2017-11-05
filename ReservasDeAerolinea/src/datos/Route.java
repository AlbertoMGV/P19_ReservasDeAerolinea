package datos;

import java.util.ArrayList;
import java.util.Random;

public class Route {
	
	private Airport origin;
	private Airport destination;
	private int distance;
	private String airline;
	private ArrayList<Escala> escalas;
	private Aircraft aircraft;
	
	public Route(Airport origin, String distance, Airport destination, ArrayList<Escala> escalas, ArrayList<Aircraft> aircraft, String airline) {
		this.origin = origin;
		this.distance = Integer.parseInt(distance);
		this.destination = destination;
		this.escalas = escalas;
		this.airline = airline;
		
		/*ya que algunas rutas se vuelan con diferentes aviones dependiendo del día,
		 * recibimos como parámetro un arraylist con todas las posibilidades y se elige
		 * uno al azar.
		 */
		
		if(!aircraft.isEmpty()){
			Random r = new Random(System.currentTimeMillis());
			this.aircraft = aircraft.get(r.nextInt(aircraft.size()));
		}else{
			this.aircraft = new Aircraft("B738", "738", "Boeing 737-800", 150, 850);
		}
	}
	
	public void addEscala(Escala e){
		this.escalas.add(e);
	}
	
	public ArrayList<Escala> getEscalas(){
		return this.escalas;
	}

	public Airport getOrigin() {
		return origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	@Override
	public String toString() {
		return "Route [origin=" + origin.getICAO() + ", destination=" + destination.getICAO() + ", distance=" + distance + ", airline="
				+ airline + ", escalas=" + escalas + ", aircraft=" + aircraft.getICAO() + "]";
	}
	
	

}
