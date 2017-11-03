package utilidades;

import gestores.GestorDB;

public class LoginRegister {
	public static boolean log(String dni, String pass) {
		boolean logSuccess=false;
		if (comprobar(dni)==true) {
			if (true) {
				//comprobar que la pass es legal
				logSuccess = GestorDB.log(dni, pass);
			}
			
		}
		
		//comprobacion de que los datos name y pass cumplen las condiciones
		//para que no nos metan codigo malisioso :(
		//y devuelvo si true si coincide el name y la pass
		return logSuccess;		
	}
	public static boolean reg(String dni, String name, String pass, String confirPass) {
		boolean regSuccess=false;
		if (comprobar(dni)==true) {
			if (true) {
				//comprobar que el name solo tiene letras
				if (true) {
					//comprobar que la pass solo tiene letras o nums
					if (pass.equals(confirPass)) {
						//comprobar que la pass es igual que confirpass
						regSuccess=GestorDB.reg(dni, name, pass, confirPass);
						
					}
				}
			}
		}
		
		//comprobacion de que los datos name y pass cumplen las condiciones
		return regSuccess;
	}


	public static boolean comprobar(String dniAComprobar){
		char[] letraDni = {
				'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D',  'X',  'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'
		};  
		String num= "";
		int ind = 0;  
		boolean valido;

		if(dniAComprobar.length() == 8) {
			dniAComprobar = "0" + dniAComprobar;
		}
		if (!Character.isLetter(dniAComprobar.charAt(8))) {
			return false;  
		}
		if (dniAComprobar.length() != 9){   
			return false;
		}
		for (int i=0; i<8; i++) {

			if(!Character.isDigit(dniAComprobar.charAt(i))){
				return false;    
			}

			num += dniAComprobar.charAt(i);     
		}
		ind = Integer.parseInt(num);
		ind %= 23;
		if ((Character.toUpperCase(dniAComprobar.charAt(8))) != letraDni[ind]){
			return false;
		}  

		return true;
	}

}


