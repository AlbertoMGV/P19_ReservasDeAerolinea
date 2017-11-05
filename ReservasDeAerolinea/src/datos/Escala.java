package datos;

import java.util.Random;

public class Escala {
	
	private Airport airport;
	private int duration;
	
	//si la duracion de la escala es conocida
	
	public Escala(Airport airport, int duration){
		this.airport = airport;
	}
	
	//si no conocemos la duracion
	
	public Escala(Airport airport){
		this.airport = airport;
		Random r = new Random(System.currentTimeMillis());
		
		//máxima duración de 2 h
		
		this.duration = r.nextInt(120);
	}

	public Airport getAirport() {
		return airport;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
