package datos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Multiplicadores {
	
	//multiplicadores para el precio de los vuelos
	
	private static String[] aerolineasPremium = {"IB", "AF" , "BA" , "ZB" , "TK", "LH", "UX"};
	private static String[] aerolineasLowCost = {"X3", "AB", "FR", "EI", "YW", "I2", "VY", "U2"};
	
	public static double aerolineas(String[] aerolineas) {
		double result = 1;
		for(int i = 0; i < aerolineas.length; i++) {
			result = result * aerolinea(aerolineas[i]);
		}
		return result;
	}
	
	public static double aerolinea(String aerolinea) {
		if(Arrays.asList(aerolineasPremium).contains(aerolinea)) {
			double random = ThreadLocalRandom.current().nextInt(5, 15);
			random = (110 + random)/100;
			return random;
		}else {
			if(Arrays.asList(aerolineasLowCost).contains(aerolinea)) {
				double random = ThreadLocalRandom.current().nextInt(5, 15);
				random = (85 + random)/100; 
				return random;
			}else {
				double random = ThreadLocalRandom.current().nextInt(-15, 20);
				random = (100 + random)/100; 
				return random;
			}
		}
	}
	
	public static double clase(int claseId) {
		double random = ThreadLocalRandom.current().nextInt(0, 12);		
		random = ((90 + random) * (claseId + 1))/100;
		return random;
	}
	
	public static double adicional(String fechaI, String fechaV) {
		return adicional(fechaI) * adicional(fechaV);
	}
	
	public static double adicional(String fecha) {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		Calendar cal = Calendar.getInstance();
		try {
			date = df.parse(fecha);
			cal.setTime(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		double random;
		
		//añadir extra por día
		
		switch(cal.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			random = ThreadLocalRandom.current().nextInt(0, 20);
			break;
		case 6:
			random = ThreadLocalRandom.current().nextInt(5, 15);
			break;
		case 7:
			random = ThreadLocalRandom.current().nextInt(-5, 15);
			break;
		default:
			random = ThreadLocalRandom.current().nextInt(-10, 10);
			break;
		}
		
		//añadir extra por mes
		
		switch(cal.get(Calendar.MONTH)) {
		case 5:
			random += ThreadLocalRandom.current().nextInt(5, 15);
			break;
		case 6:
			random += ThreadLocalRandom.current().nextInt(10, 20);
			break;
		case 7:
			random += ThreadLocalRandom.current().nextInt(10, 25);
			break;
		case 11:
			random += ThreadLocalRandom.current().nextInt(-15, 20);
			break;
		case 2:
			random += ThreadLocalRandom.current().nextInt(-15, 0);
			break;
		default:
			random = ThreadLocalRandom.current().nextInt(-10, 10);
			break;
		}
			
		
		random = (100 + random)/100;
		return random;
	}

}
