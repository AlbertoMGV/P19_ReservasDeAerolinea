package gestores;

import datos.Multiplicadores;

public class Pricing {
	
	private static final double PRECIO_BASE = 35;
	private static final double IVA = 1.21;
	
	/*
	 * claseId = 0 -> "Económica", 
	 * claseId = 1 -> "Turista", 
	 * claseId = 2 -> "Business"
	 */
	
	public static double procesarPrecio(String[] ruta, String[] fechas, int pasajeros, int claseId) {
		
		double precioTotal = 0;
		
		if(pasajeros > 20) {
			pasajeros = 20;
			System.out.println("error, max pasajeros = 20");
		}
		
		double totalDistance = procesarDistancia(ruta);
		
		precioTotal += totalDistance;
		
		String aerolinea = getAerolinea(ruta);
		
		double multiplicadorAerolinea = Multiplicadores.aerolinea(aerolinea);
		double multiplicadorClase = Multiplicadores.clase(claseId);
		double multiplicadorAdicional = Multiplicadores.adicional();
		
		precioTotal = ((precioTotal * multiplicadorAerolinea * multiplicadorClase) * pasajeros) * multiplicadorAdicional;
		
		precioTotal = (precioTotal + PRECIO_BASE)*IVA;
		
		return precioTotal;		
	}

	private static double procesarDistancia(String[] ruta) {
		//recorrer cada una de las rutas y sumar sus distancias
		return 0;
	}
	
	private static String getAerolinea(String[] ruta) {
		//leer las aerolineas de cada ruta y devolver una aleatoria.
		return "";
	}

}
