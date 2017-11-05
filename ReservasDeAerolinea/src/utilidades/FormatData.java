package utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FormatData {

	//Clase ajena al proyecto principal, para manipular los datos que utilizaremos 
	
	
	public static void main(String[] args) {
		rellenarDistancias();
	}
	
	
	public static void rellenarDistancias(){
		HashMap<String, Coordenadas> datos = leerCoordenadas();
		
		File rutas = new File("res/routes_ICAO.dat");
		File rutasKM = new File("res/routes_distances.dat");
		
		try{
			String linea;
			FileReader fr = new FileReader(rutas);
			BufferedReader bfr = new BufferedReader(fr);
			FileWriter fw = new FileWriter(rutasKM);
			String origin, destination;
			double lat1, lon1, lat2, lon2;
			int distance;
			
			while((linea = bfr.readLine()) != null){
				String[] data = linea.split(",");
				String[] newData = new String[data.length+1];
				
				origin = data[2];
				destination = data[4];				
				
				lat1 = datos.get(origin).getLat();
				lon1 = datos.get(origin).getLon();
				
				lat2 = datos.get(destination).getLat();
				lon2 = datos.get(destination).getLon();
				
				distance = (int) distancia(lat1, lon1, lat2, lon2);
				
				for(int i = 0; i < data.length; i++){
					newData[i] = data[i];
				}
				
				newData[data.length] = distance+"";
				
				fw.write(arrayToString(newData, ',') + "\n");
			}
			bfr.close();
			fr.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* metodo para calcular la distancia recorrida por cada ruta. la calcularemos aproximadamente
	 * mediante las coordenadas del aeropuerto inicial y el de destino. Utilizaremos la formula de Haversine
	 * fuente: http://www.movable-type.co.uk/scripts/latlong.html
	 */
	
	public static double distancia(double lat1, double lon1, double lat2, double lon2){
		int radio = 6371; //radio de la tierra (en km.)
		
		double dLat = Math.toRadians((lat2-lat1));
		double dLon = Math.toRadians((lon2-lon1));
		
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		
		double a = haversin(dLat) + Math.cos(lat1) * Math.cos(lat2) * haversin(dLon);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		return radio * c;
		
	}
	
	
	public static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}
		
	
	/*
	 * ya que en el archivo routes.dat el código de avion está en el estandar IATA
	 * y la página que utilizaremos para obtener datos del avión (https://doc8643.com)
	 * funciona con códigos ICAO, utilizaremos este método y el archivo aircraft.dat (que contiene ambas
	 * designaciones) para convertir los códigos de routes.dat de IATA a ICAO
	 */
	
	public static HashMap<String, Coordenadas> leerCoordenadas(){
		HashMap<String, Coordenadas> result = new HashMap<String, Coordenadas>();
		
		File airports = new File("res/airports_new.dat");
		try{
			String linea;
			FileReader fr = new FileReader(airports);
			BufferedReader bfr = new BufferedReader(fr);
			
			while((linea = bfr.readLine()) != null){
				String[] data = linea.split(",");
				result.put(data[4].replace("\"", ""), new Coordenadas(data[6], data[7]));
			}
			bfr.close();
			fr.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static void IATAToICAO(){
		File aircraft = new File("res/aircraft.dat");
		File routes = new File("res/routes_new.dat");
		File routesICAO = new File("res/routes_ICAO.dat");
		HashMap<String, String> codes = new HashMap<String, String>();
		
		try{
			String line;
			FileReader fr = new FileReader(aircraft);
			FileReader rfr = new FileReader(routes);
			BufferedReader bfr = new BufferedReader(fr);
			BufferedReader rbfr = new BufferedReader(rfr);
			FileWriter fw = new FileWriter(routesICAO);
			
			while((line = bfr.readLine()) != null){
				String[] data = line.split(",");
				codes.put(data[0], data[1]);
			}
		
			
			while((line = rbfr.readLine()) != null){
				String[] data = line.split(",");
				String[] aircrafts = data[8].split(" ");
				
				for(int i = 0; i < aircrafts.length; i++){
					if(codes.get(aircrafts[i]).equals("n/a")){
						System.out.println(aircrafts[i]);
					}
					aircrafts[i] = codes.get(aircrafts[i]);
				}
				
				data[8] = arrayToString(aircrafts, ' ');
				
				fw.write(arrayToString(data, ',') + "\n");
				
			}
			
			fw.close();			
			fr.close();
			rfr.close();
			bfr.close();
			rbfr.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminarAeropuertos(){
		
		File original = new File("res/airports.dat");
		File destination = new File("res/airports_new.dat");
		try {
			String line;
			FileReader fr = new FileReader(original);
			BufferedReader bfr = new BufferedReader(fr);
			FileWriter fw = new FileWriter(destination);
			int code = 0;
			while((line = bfr.readLine()) != null){
				
				String[] data = line.split(",");
				
				//comprobamos si es europeo o no
				if(data[11].contains("Europe")){
					//descartamos bases militares, etc
					if(data[1].contains("Airport")){
						fw.write(arrayToString(data, ',') + "\n");
					}
				}
			}
			fw.flush();
			fw.close();
			bfr.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<String> leerAeropuertos(){
		File datos = new File("res/airports_new.dat");
		ArrayList<String> result = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(datos);
			BufferedReader bfr = new BufferedReader(fr);
			String linea;
			
			while((linea = bfr.readLine()) != null){
				String[] data = linea.split(",");
				result.add(data[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void eliminarRutas(){
		File original = new File("res/routes.dat");
		File destination = new File("res/routes_new.dat");
		ArrayList<String> europeanAirports = leerAeropuertos();
		try {
			String line;
			FileReader fr = new FileReader(original);
			BufferedReader bfr = new BufferedReader(fr);
			FileWriter fw = new FileWriter(destination);
			int code = 0;
			while((line = bfr.readLine()) != null){				
				String[] data = line.split(",");
				
				//eliminamos rutas que no salgan de europa
				
				for(int i = 0; i < europeanAirports.size(); i++){
					if(data[3].equals(europeanAirports.get(i))){
						
						//eliminamos rutas que no tengan como destino europa
						
						for(int j = 0; j < europeanAirports.size(); j++){
							if(data[5].equals(europeanAirports.get(j))){
								fw.write(line + "\n");
							}
						}
					}
				}
				
			}
			fw.flush();
			fw.close();
			bfr.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String arrayToString(String[] string, char separador){
		String result = "";
		
		for(int i = 0; i < string.length; i++){
			if(i != (string.length - 1)){
				result += string[i] + separador;
			}else{
				result += string[i];
			}
		}
		

		
		return result;
	}

}
