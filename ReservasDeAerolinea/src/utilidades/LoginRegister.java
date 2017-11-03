package utilidades;

import gestores.GestorDB;

public class LoginRegister {
	public static boolean log(String name, String pass) {
		//comprobacion de que los datos name y pass cumplen las condiciones
		//para que no nos metan codigo malisioso :(
		//y devuelvo si true si coincide el name y la pass
		return GestorDB.log(name, pass);		
	}
	public static boolean reg(String user, String pass, String confirPass) {
		boolean regSuccess=false;
		//comprobacion de que los datos name y pass cumplen las condiciones
		return regSuccess;
	}
}
