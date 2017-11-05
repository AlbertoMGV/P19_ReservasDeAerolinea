package datos;

import java.util.ArrayList;
import java.util.Random;

public class Route {
	
	private Airport origin;
	private Airport destination;
	private int distance;
	private String airline;
	private ArrayList<Route> escalas;
	private ArrayList<Aircraft> aircraft;
	
	public Route(){
		
	}
	
	//constructor base para crear rutas con escalas
	
	public Route(Airport origin, Airport destination){
		this.origin = origin;
		this.destination = destination;
		this.distance = 0;
		this.escalas = new ArrayList<Route>();
		this.aircraft = new ArrayList<Aircraft>();
		this.airline = "";
	}
	

	
	public Route(Route route) {
		this.origin = route.origin;
		this.destination = route.destination;
		this.distance = route.distance;
		this.airline = route.airline;
		this.escalas = route.escalas;
		this.aircraft = route.aircraft;
	}

	public Route(Airport origin, String distance, Airport destination, ArrayList<Route> escalas, ArrayList<Aircraft> aircraft, String airline) {
		this.origin = origin;
		this.distance = Integer.parseInt(distance);
		this.destination = destination;
		this.escalas = escalas;
		this.airline = airline;
		this.aircraft = aircraft;
		
		/*ya que algunas rutas se vuelan con diferentes aviones dependiendo del día,
		 * recibimos como parámetro un arraylist con todas las posibilidades y se elige
		 * uno al azar.
		 */
		
		if(!aircraft.isEmpty()){
			Random r = new Random(System.currentTimeMillis());
			this.aircraft.add(aircraft.get(r.nextInt(aircraft.size())));
		}else{
			this.aircraft.add(new Aircraft("B738", "738", "Boeing 737-800", 150, 850));
		}
	}
	
	public void addEscala(Route e, int maxDepth){
		if(this.escalas.size() < maxDepth + 1){
			this.escalas.add(e);
			
			//actualizar distancia, aerolineas y aviones
			if(this.escalas.size() == 1){
				this.distance = e.distance;
				this.airline = e.airline;
				if(this.aircraft == null){
					this.aircraft = new ArrayList<Aircraft>();
				}else{
					this.aircraft.addAll(e.aircraft);
				}
			}else{
				this.distance += e.distance;
				this.airline = this.airline + " + " + e.airline;
				if(this.aircraft == null){
					this.aircraft = new ArrayList<Aircraft>();
				}else{
					this.aircraft.addAll(e.aircraft);
				}
			}
		}
		
	}
	
	public String getAirline(){
		return this.airline;
	}
	
	public ArrayList<Route> getEscalas(){
		return this.escalas;
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

	public ArrayList<Aircraft> getAircraft() {
		return aircraft;
	}
	
	public String getAircraftString(){
		String result = "";
		for(Aircraft a : aircraft){
			result += a.getIATA() +  " ";
		}
		return result;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public void setAircraft(ArrayList<Aircraft> aircraft) {
		this.aircraft = aircraft;
	}
	
	public void setEscalas(ArrayList<Route> escalas){
		this.escalas = escalas;
	}

	@Override
	public String toString() {
		if(escalas.size() == 0){
			return "Route [origin=" + origin.getIATA() + ", destination=" + destination.getIATA() + ", distance=" + distance + ", airline="
					+ airline + ", escalas=" + escalas.size() + ", aircraft=" + getAircraftString() + "]";
		}else{
			return "Route [origin=" + origin.getIATA() + ", destination=" + destination.getIATA() + ", distance=" + distance + ", airline="
					+ airline + ", escalas=" + (escalas.size()-1) + ", aircraft=" + getAircraftString() + "]";
		}
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	

}
