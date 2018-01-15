package gestores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import datos.Airport;

public class GestorRutas {
	
	private static ArrayList<String[]> rutas;
	
	
	public static void main(String[] args){
		getRuta("AVN", "ESB", 3);
	}
	
	public static ArrayList<String[]> getRuta(String origen, String destino, int maxEscalas){
		ArrayList<String[]> result = new ArrayList<String[]>();
		LinkedList<Airport> cola = new LinkedList<Airport>();
		LinkedList<Integer> profundidad = new LinkedList<Integer>();
		ArrayList<Airport> visited = new ArrayList<Airport>();
		Airport aOrigen = Airport.get(origen);
		cola.push(aOrigen);
		profundidad.push(0);
		visited.add(aOrigen);
		while(!cola.isEmpty()) {
			Airport v = cola.pop();
			int depth = profundidad.pop();
			if(depth <= maxEscalas) {
				for(String[] vecino : v.getDestinos()) {
					Airport aVecino = Airport.get(vecino[0]);
					if(aVecino.getIATA().equals(destino)){
						//Destino alcanzado
						result.add(getEscalas(aOrigen, v, aVecino).split(","));
						break;
					}
					if(!visited.contains(aVecino)) {
						aVecino.setPrevious(v);
						cola.push(aVecino);
						profundidad.push(depth + 1);
						visited.add(aVecino);
					}
				}
			}
		}
		
		
		 
		return result;
	}
	
	
	public static ArrayList<String[]> leerRutas(String origen){
		ArrayList<String[]> result = new ArrayList<String[]>();
		if(rutas == null) {
			rutas = new ArrayList<String[]>();
			File rutasFile = new File("res/routes_distances.dat");
			try{
				FileReader fr = new FileReader(rutasFile);
				BufferedReader bfr = new BufferedReader(fr);
				String linea;
				
				while((linea = bfr.readLine()) != null){
					String[] datos = linea.split(",");
					rutas.add(datos);
					//encontrar todas las rutas que salen del aeropuerto de origen

					
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		for(String[] ruta : rutas) {
			if(ruta[2].equals(origen)){
				String[] resultado = {ruta[4], ruta[0] ,ruta[9]};
				result.add(resultado);
			}
		}
		return result;
	}
	
	private static String getEscalas(Airport origen, Airport ultimaEscala, Airport destino) {
		if(ultimaEscala.getPrevious() == null) {
			return origen.getIATA() + ","+destino.getIATA();
		}else {
			String result = origen.getIATA() + ",";
			
			Airport escala = ultimaEscala;
			
			while(!escala.equals(origen)) {
				result = result + escala.getIATA()+",";
				escala = escala.getPrevious();
				
			}
			
			result += destino.getIATA();
			
			//reordenar escalas
			
			String[] array = result.split(",");
			String[] temp = new String[array.length];
			

			temp[0] = array[0];
			temp[temp.length-1] = array[temp.length-1];

			
			for(int i = 1; i < temp.length-1; i++) {
				temp[i] = array[temp.length-i-1];
			}
			
			result = "";
			
			for(int i = 0; i < temp.length - 1; i++) {
				result += temp[i] + ",";
			}
			
			result += array[temp.length-1];
			
			return result;
		}
		
		
	}
	

}
