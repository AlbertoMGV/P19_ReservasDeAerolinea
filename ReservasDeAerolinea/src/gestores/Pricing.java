package gestores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import datos.Multiplicadores;

public class Pricing {
	
	private static final double PRECIO_BASE = 35;
	private static final double IVA = 1.21;
	
	private double precio;
	private String[] aerolineas;
	/*
	 * claseId = 0 -> "Económica", 
	 * claseId = 1 -> "Turista", 
	 * claseId = 2 -> "Business"
	 */
	
	public Pricing(double precio, String[] aerolineas) {
		this.precio = precio;
		this.aerolineas = new String[aerolineas.length];
		for(int i = 0; i < aerolineas.length; i++) {
			int n_vuelo = ThreadLocalRandom.current().nextInt(1, 9999);
			String s_vuelo = n_vuelo+"";
			while(s_vuelo.length() < 4) {
				s_vuelo = 0 + s_vuelo;
			}
			this.aerolineas[i] = aerolineas[i] +"-"+ s_vuelo;
		}
	}
	
	public double getPrecio() {
		return precio;
	}

	public String[] getAerolineas() {
		return aerolineas;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setAerolineas(String[] aerolineas) {
		this.aerolineas = aerolineas;
	}

	public static void main(String[] args) {
		String[] ruta = {"AMS", "BRU", "ZTH", "BIO"};
		System.out.println(Arrays.toString(getAerolineas(ruta)));
	}
	
	public static Pricing procesarPrecio(String[] ruta, String[] fechas, int pasajeros, int claseId) {
		
		double precioTotal = 0;
		
		if(pasajeros > 20) {
			pasajeros = 20;
			System.out.println("error, max pasajeros = 20");
		}
		
		double totalDistance = procesarDistancia(ruta);
		
		precioTotal += totalDistance/8;
		
		String[] aerolineas = getAerolineas(ruta);
		
		double multiplicadorAerolinea = Multiplicadores.aerolineas(aerolineas);
		double multiplicadorClase = Multiplicadores.clase(claseId);
		double multiplicadorAdicional;
		
		if(fechas[1] != null) {
			multiplicadorAdicional = Multiplicadores.adicional(fechas[0], fechas[1]);
		}else {
			multiplicadorAdicional = Multiplicadores.adicional(fechas[0]);
		}
		
		
		precioTotal = ((precioTotal * multiplicadorAerolinea * multiplicadorClase) * pasajeros) * multiplicadorAdicional;		
		
		precioTotal = (precioTotal + PRECIO_BASE)*IVA;
		
		/*
		System.out.println("Aerolineas: " + Arrays.toString(aerolineas)+ ", multiplicador: " + multiplicadorAerolinea);
		System.out.println("Clase: " + claseId + ", multiplicador: " + multiplicadorClase);
		System.out.println("Fechas: " + Arrays.toString(fechas)+ ", multiplicador: " + multiplicadorAdicional);
		*/
		precioTotal = Math.round(precioTotal * 100.0) / 100.0;
		
		return new Pricing(precioTotal, aerolineas);		
	}

	private static double procesarDistancia(String[] ruta) {
		double total = 0;
		for(int i = 0; i < ruta.length - 1; i++) {
			total += GestorDB.getDistancia(ruta[i], ruta[i+1]);
		}
		//recorrer cada una de las rutas y sumar sus distancias
		return total;
	}
	
	private static String[] getAerolineas(String[] ruta) {
		//leer las aerolineas de cada ruta y devolver una aleatoria.
		String result = "";
		String[] array;
		for(int i = 0; i < ruta.length - 1; i++) {
			ArrayList<String> aerolineas = new ArrayList<String>();
			aerolineas.addAll(GestorDB.getAerolineas(ruta[i], ruta[i+1]));
			if(aerolineas.size() > 0) {
				int random = ThreadLocalRandom.current().nextInt(0, aerolineas.size());
				result += aerolineas.get(random) + ", ";
			}

		}
		
		if(result.length() > 0) {
			result = result.substring(0, result.length()-1);
		}
		
		array = result.split(",");
		
		return array;
	}

}
