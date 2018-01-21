package datos;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

public class Aircraft {
	
	private String ICAO;
	private String IATA;
	private String name;
	
	private File[] images;
	private int speed;
	
	public Aircraft(String ICAO, String IATA, String name, int speed) {
		this.ICAO = ICAO;
		this.IATA = IATA;
		this.name = name;
		this.speed = speed;
	}
	
	
	public ImageIcon getImage() {
		if(this.images == null) {
			File directory = new File("res/imagenes/" + IATA + "_images");
			this.images = directory.listFiles();
			return getImage();
		}else {
			int random = ThreadLocalRandom.current().nextInt(0, images.length);
			return new ImageIcon(images[random].getPath());
		}		
	}
	
	public String toString() {
		return this.name;
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
