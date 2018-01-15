package gestores;

public class Pricing {
	
	/*
	 * claseId = 0 -> "Económica", 
	 * claseId = 1 -> "Turista", 
	 * claseId = 2 -> "Business"
	 */
	
	public static double procesarPrecio(String[] ruta, String[] fechas, int pasajeros, int claseId) {
		if(pasajeros > 20) {
			pasajeros = 20;
			System.out.println("error, max pasajeros = 20");
		}
		
		
		
		
	}

}
