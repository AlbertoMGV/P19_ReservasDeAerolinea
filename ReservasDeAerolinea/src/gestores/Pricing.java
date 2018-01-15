package gestores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import datos.Multiplicadores;

public class Pricing {
	
	private static final double PRECIO_BASE = 35;
	private static final double IVA = 1.21;
	
	/*
	 * claseId = 0 -> "Económica", 
	 * claseId = 1 -> "Turista", 
	 * claseId = 2 -> "Business"
	 */
	
	public static void main(String[] args) {
		String[] ruta = {"BIO", "BCN" ,"LHR"};
		System.out.println(procesarPrecio(ruta, null, 1, 0));
	}
	
	public static double procesarPrecio(String[] ruta, String[] fechas, int pasajeros, int claseId) {
		
		double precioTotal = 0;
		
		if(pasajeros > 20) {
			pasajeros = 20;
			System.out.println("error, max pasajeros = 20");
		}
		
		double totalDistance = procesarDistancia(ruta);
		
		precioTotal += totalDistance/8;
		
		String aerolinea = getAerolinea(ruta);
		
		double multiplicadorAerolinea = Multiplicadores.aerolinea(aerolinea);
		double multiplicadorClase = Multiplicadores.clase(claseId);
		double multiplicadorAdicional = Multiplicadores.adicional();
		
		precioTotal = ((precioTotal * multiplicadorAerolinea * multiplicadorClase) * pasajeros) * multiplicadorAdicional;
		
		precioTotal = (precioTotal + PRECIO_BASE)*IVA;
		
		return precioTotal;		
	}

	private static double procesarDistancia(String[] ruta) {
		double total = 0;
		for(int i = 0; i < ruta.length - 1; i++) {
			total += GestorDB.getDistancia(ruta[i], ruta[i+1]);
		}
		//recorrer cada una de las rutas y sumar sus distancias
		return total;
	}
	
	private static String getAerolinea(String[] ruta) {
		//leer las aerolineas de cada ruta y devolver una aleatoria.
		ArrayList<String> aerolineas = new ArrayList<String>();
		for(int i = 0; i < ruta.length - 1; i++) {
			aerolineas.addAll(GestorDB.getAerolineas(ruta[i], ruta[i+1]));
		}
		
		int random = ThreadLocalRandom.current().nextInt(0, aerolineas.size() - 1);
		
		System.out.println("Aerolinea: " + aerolineas.get(random));
		System.out.println("Ruta: " + Arrays.toString(ruta));
		
		return aerolineas.get(random);
	}

}
