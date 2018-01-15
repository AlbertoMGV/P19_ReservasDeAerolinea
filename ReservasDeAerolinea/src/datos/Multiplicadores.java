package datos;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Multiplicadores {
	
	//multiplicadores para el precio de los vuelos
	
	private static String[] aerolineasPremium = {"IB", "AF" , "BA" , "ZB" , "TK", "LH", "UX"};
	private static String[] aerolineasLowCost = {"X3", "AB", "FR", "EI", "YW", "I2", "VY", "U2"};
	
	public static double aerolinea(String aerolinea) {
		if(Arrays.asList(aerolineasPremium).contains(aerolinea)) {
			double random = ThreadLocalRandom.current().nextInt(0, 15);
			random = (110 + random)/100;
			return random;
		}else {
			if(Arrays.asList(aerolineasLowCost).contains(aerolinea)) {
				double random = ThreadLocalRandom.current().nextInt(5, 15);
				random = (85 + random)/100; 
				return random;
			}else {
				double random = ThreadLocalRandom.current().nextInt(0, 20);
				random = (90 + random)/100; 
				return random;
			}
		}
	}
	
	public static double clase(int claseId) {
		double random = ThreadLocalRandom.current().nextInt(0, 12);		
		random = ((90 + random) * (claseId + 1))/100;
		return random;
	}
	
	public static double adicional() {
		double random = ThreadLocalRandom.current().nextInt(0, 30);
		
		random = (85 + random)/100;
		return random;
	}

}
