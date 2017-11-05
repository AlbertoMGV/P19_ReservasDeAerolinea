package datos;

public class Aircraft {
	
	private String ICAO;
	private String IATA;
	private String name;
	
	private int seating;
	private long speed;
	
	public Aircraft(String ICAO, String IATA, String name, int seating, long speed) {
		this.ICAO = ICAO;
		this.IATA = IATA;
		this.name = name;
		this.seating = seating;
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

	public int getSeating() {
		return seating;
	}

	public long getSpeed() {
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

	public void setSeating(int seating) {
		this.seating = seating;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}
	
	

}
