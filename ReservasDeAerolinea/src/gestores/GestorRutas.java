package gestores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import datos.Aircraft;
import datos.Airport;
import datos.Escala;
import datos.Route;

public class GestorRutas {
	
	
	public static void main(String[] args){
		ArrayList<Route> rutas = leerRutas("BIO", "ALC", 0 , 1, new Route());
		for(Route r : rutas){
			System.out.println(r.toString());
			ArrayList<Route> escalas = r.getEscalas();
			for(Route e : escalas){
				System.out.println("ESCALA: " + e.toString());
			}
		}
	}
	
	
	public static ArrayList<Route> leerRutas(String origen, String destino, int depth,int maxDepth, Route baseRoute){
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
	}

}
