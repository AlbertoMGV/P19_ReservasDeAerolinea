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
		ArrayList<Route> rutas = leerRutas("ORY");
			for(Route r  : rutas){
				System.out.println(r.toString());
		}		
	}
	
	
	public static ArrayList<Route> leerRutas(String origen){
		ArrayList<Route> result = new ArrayList<Route>();
		File rutas = new File("res/routes_distances.dat");
		try{
			FileReader fr = new FileReader(rutas);
			BufferedReader bfr = new BufferedReader(fr);
			String linea;
			
			while((linea = bfr.readLine()) != null){
				String[] datos = linea.split(",");
				if(datos[2].equals(origen)){
					result.add(new Route(Airport.get(origen), datos[9], Airport.get(datos[4]), new ArrayList<Escala>(), new ArrayList<Aircraft>(), datos[0]));
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}

}
