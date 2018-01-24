package gestores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import datos.Aircraft;
import datos.Multiplicadores;

public class Pricing {
	
	private static final double PRECIO_BASE = 35;
	private static final double IVA = 1.21;
	
	private double precio;
	private String[] aerolineas;
	private String hSalida;
	private String hLlegada;
	private double dTotal;
	/*
	 * claseId = 0 -> "Económica", 
	 * claseId = 1 -> "Turista", 
	 * claseId = 2 -> "Business"
	 */
	
	public Pricing(double precio, String[] aerolineas, double dTotal) {
		this.precio = precio;
		this.dTotal = dTotal;
		this.hSalida = generarHSalida();
		this.aerolineas = new String[aerolineas.length];
		for(int i = 0; i < aerolineas.length; i++) {
			int n_vuelo = ThreadLocalRandom.current().nextInt(1, 9999);
			String s_vuelo = n_vuelo+"";
			while(s_vuelo.length() < 4) {
				s_vuelo = 0 + s_vuelo;
			}
			this.aerolineas[i] = aerolineas[i] +"-"+ s_vuelo;
		}
		this.hLlegada = generarHLlegada();
	}
	
	public String gethSalida() {
		return hSalida;
	}
	public String gethLlegada() {
		return hLlegada;
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
		
		//eliminar decimales sobrantes
		precioTotal = Math.round(precioTotal * 100.0) / 100.0;
		
		return new Pricing(precioTotal, aerolineas, totalDistance);		
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

	private String generarHSalida() {
		String resultado, minutos;
		int horas, minRandom;
		//generar un número entre el 0 y el 23 (horas)
		horas = ThreadLocalRandom.current().nextInt(0, 23);
		minRandom = ThreadLocalRandom.current().nextInt(0, 3); //generar número del 0 al 3 (0: 00min, 1: 15min,..., 3: 45min)
	
		switch(minRandom) {
		case 0:
			minutos = "00";
			break;
		case 1: 
			minutos = "15";
			break;
		case 2: 
			minutos = "30";
			break;
		case 3:
			minutos = "45";
			break;
		default:
			minutos = "00";
			break;
		}
		
		resultado = horas + ":" + minutos;
				
		return resultado;

	}

	private String generarHLlegada() {
		String result;
		String[] horaS = hSalida.split(":");
		int horaLlegada, minLlegada;
		//mínimo de tiempo de un vuelo
		int minHoras = 1;
		//calcular duracion
		double duracion = this.dTotal / 800; //800 es la velocidad por defecto de todos los aviones, en una futura version debe leerse la velocidad del avion que realiza cada escala.
		int horas = (int) duracion;
		int minutos = (int) Math.abs(duracion - horas) * 60;
		
		if(horas < minHoras) {
			minutos = 0;
			horas = minHoras;
		}
		
		horaLlegada = Integer.parseInt(horaS[0]) + horas;
		minLlegada = Integer.parseInt(horaS[1]) + minutos;
		
		if(minLlegada > 59) {
			minLlegada = minLlegada - 60;
			horaLlegada++;
		}if(this.aerolineas.length > 1) {
			horaLlegada += 1 * aerolineas.length - 1;
		}if(horaLlegada >= 24) {
			horaLlegada = horaLlegada - 24;
		}
		
		result = horaLlegada + ":" + minLlegada;
		return result;
	}
}
