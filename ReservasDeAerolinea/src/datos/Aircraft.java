package datos;

public class Aircraft {
	
	private String ICAO;
	private String IATA;
	private String name;
	
	private int speed;
	
	public Aircraft(String ICAO, String IATA, String name, int speed) {
		this.ICAO = ICAO;
		this.IATA = IATA;
		this.name = name;
		this.speed = speed;
	}

	public String getICAO() {
		return ICAO;
	}

	public String getIATA() {
		return IATA;
	}

	public String getName() {
		return name;
	}

	public int getSpeed() {
		return speed;
	}

	public void setICAO(String iCAO) {
		ICAO = iCAO;
	}

	public void setIATA(String iATA) {
		IATA = iATA;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	

}
