package datos;

import java.util.ArrayList;
import java.util.Random;

public class Route {
	
	private Airport origin;
	private Airport destination;
	private ArrayList<Escala> escalas;
	private Aircraft aircraft;
	
	public Route(Airport origin, Airport destination, ArrayList<Escala> escalas, ArrayList<Aircraft> aircraft) {
		this.origin = origin;
		this.destination = destination;
		this.escalas = escalas;
		
		/*ya que algunas rutas se vuelan con diferentes aviones dependiendo del día,
		 * recibimos como parámetro un arraylist con todas las posibilidades y se elige
		 * uno al azar.
		 */
		
		Random r = new Random(System.currentTimeMillis());
		this.aircraft = aircraft.get(r.nextInt(aircraft.size()));
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
	
	

}
