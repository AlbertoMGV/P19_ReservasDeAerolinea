package utilidades;

import gestores.GestorDB;

public class LoginRegister {
	public static boolean log(String dni, String pass) {
		boolean logSuccess=false;
		if (comprobarDNI(dni)==true) {
			if (comprobarPass(pass)) {				
				logSuccess = GestorDB.log(dni, pass);
			}

		}
		return logSuccess;		
	}
	public static boolean reg(String dni, String name, String pass, String confirPass) {
		if (comprobarDNI(dni)==true) {
			if (comprobarNombre(name)==true) {
				if (comprobarPass(pass)) {
					if (pass.equals(confirPass)) {
						GestorDB.reg(dni, name, pass);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean comprobarPass(String passAComprobar) {
		int nletrasok = 0;
		if (passAComprobar.length()>=6 && passAComprobar.length()<=15) {
			for (int i = 0; i < passAComprobar.length(); i++) {
				char c = passAComprobar.charAt(i);
				if (Character.isDigit(c)==true || Character.isLetter(c)==true) {
					nletrasok++;
				}
			}
			if (nletrasok == passAComprobar.length()) {
				return true;
			}
		}
		return false;
	}

	public static boolean comprobarNombre(String nombreAComprobar) {
		int nletrasok = 0;
		nombreAComprobar = nombreAComprobar.toLowerCase();
		if (nombreAComprobar.length()>=3 && nombreAComprobar.length()<=20) {
			for (int i = 0; i < nombreAComprobar.length(); i++) {
				char c = nombreAComprobar.charAt(i);
				if (Character.isDigit(c)==true || Character.isLetter(c)==true) {
					nletrasok++;
				}
			}
			if (nletrasok == nombreAComprobar.length()) {
				return true;
			}
		}
		return false;
	}

	public static boolean comprobarDNI(String dniAComprobar){
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


