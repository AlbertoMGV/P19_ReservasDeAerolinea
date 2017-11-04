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

	/*Clase ajena al proyecto principal, para eliminar datos que no necesitamos,
	 * como aeropuertos no europeos...
	 */
	
	
	public static void main(String[] args) {
		IATAToICAO();
	}
	
	
	/*
	 * ya que en el archivo routes.dat el código de avion está en el estandar IATA
	 * y la página que utilizaremos para obtener datos del avión (https://doc8643.com)
	 * funciona con códigos ICAO, utilizaremos este método y el archivo aircraft.dat (que contiene ambas
	 * designaciones) para convertir los códigos de routes.dat de IATA a ICAO
	 */
	
	
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
			// TODO Auto-generated catch block
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
