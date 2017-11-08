package gestores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import datos.Aircraft;
import datos.Airport;
import datos.Route;

public class GestorRutas {
	
	private static ArrayList<String[]> rutas;
	
	
	public static void main(String[] args){
		getRuta("BIO", "LHR", 3);
	}
	
	
	/*public static ArrayList<Route> leerRutas(String origen, String destino, int depth,int maxDepth, Route baseRoute){
		ArrayList<Route> result = new ArrayList<Route>();
		ArrayList<Route> escalas = new ArrayList<Route>();
		ArrayList<Route> finalResult = new ArrayList<Route>();
		File rutas = new File("res/routes_distances.dat");
		if(baseRoute.getOrigin() == null){
			baseRoute = new Route(Airport.get(origen), Airport.get(destino));
		}
		try{
			FileReader fr = new FileReader(rutas);
			BufferedReader bfr = new BufferedReader(fr);
			String linea;
			
			while((linea = bfr.readLine()) != null){
				String[] datos = linea.split(",");
				
				//encontrar todas las rutas que salen del aeropuerto de origen
				
				if(datos[2].equals(origen)){
					result.add(new Route(Airport.get(origen), datos[9], Airport.get(datos[4]), new ArrayList<Route>(), new ArrayList<Aircraft>(), datos[0]));
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		int i = 0;
		for(Route r : result){

			if(r.getDestination().equals(baseRoute.getDestination())){
				//hemos llegado al destino
				if(depth == 0){
					//vuelo directo
					finalResult.add(r);
				}else{
					//vuelo con escalas
					if(!baseRoute.getOrigin().getIATA().equals(origen)){
						
						baseRoute.addEscala(r, maxDepth);	
						Route addRoute = new Route(baseRoute);
						finalResult.add(addRoute);
						baseRoute = new Route(baseRoute.getOrigin(), baseRoute.getDestination());
						baseRoute.addEscala(r, maxDepth);
					}
				}
			}else{
				
				if(depth < maxDepth){
					baseRoute.addEscala(r, maxDepth);
					finalResult.addAll(leerRutas(r.getDestination().getIATA(), destino, depth+1, maxDepth, baseRoute));
				}
			}
			i++;
		}
		
		return finalResult;
	}*/
	
	
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
