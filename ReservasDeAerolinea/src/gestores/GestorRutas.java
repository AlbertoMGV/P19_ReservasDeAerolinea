package gestores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import datos.Airport;

public class GestorRutas {
	
	private static ArrayList<String[]> rutas;
	
	
	public static void main(String[] args){
		getRuta("BIO", "LHR", 3);
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
					if(vecino[0].equals(destino)){
						System.out.println("encontrada ruta, profundidad :" + depth + ", parada anterior: " + v.getIATA());
						break;
					}
					if(!visited.contains(aVecino)) {
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
	

}
