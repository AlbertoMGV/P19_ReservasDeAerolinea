package utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FormatData {

	/*Clase ajena al proyecto principal, para eliminar datos que no necesitamos,
	 * como aeropuertos no europeos...
	 */
	
	
	public static void main(String[] args) {
		eliminarAeropuertos();
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
						data[0] = code+"";
						fw.write(arrayToString(data) + "\n");
						code++;
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
	
	public static String arrayToString(String[] string){
		String result = "";
		
		for(int i = 0; i < string.length; i++){
			if(i != (string.length - 1)){
				result += string[i] + ",";
			}else{
				result += string[i];
			}
		}
		

		
		return result;
	}

}
