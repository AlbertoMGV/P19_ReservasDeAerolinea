package gestores;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.Usuario;


/*
 * Tablas: 
 * creada=USER [dni,nom,pass,email]
 * falta=RESERVA []
 * falta=VUELO[]
 * falta=RUTA[]
 * 
 * 
 */




public class GestorDB {

/*		public static void main(String[] args) {

		//ejecutad este main para ver lo que hay en la bd				
		//el usuario de prueba --> ("11111111H", "Admin", "admin1", "admin@deustoair.es");
		//deleteUser("72844994S");
			
		crearDB();
		//insertarDBAirport();
		//displayDB();
	}  
*/

	public static boolean reg(String dni,String name, String pass, String email) {
		insertarDB(dni,name, pass, email); 		
		return true;
	}



	public static Usuario log(String dni,String pass) {
		Usuario usuario = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String Query = "SELECT * FROM USER";
			ResultSet rs = stat.executeQuery(Query);

			while (rs.next() && usuario == null) {
				if (dni.equals(rs.getString(1))) {
					if (pass.equals(rs.getString(3))) {
						usuario = new Usuario(rs.getString(1), rs.getString(2), "");
					}
				}				
			}
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}

	public static String emailBD(String dni) {
		String email="";
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String Query = "SELECT email FROM USER WHERE dni='"+dni+"'";
			ResultSet rs = stat.executeQuery(Query);
			email = rs.getString(1);
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;

	}

	public static void displayDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String Query = "SELECT * FROM USER";
			ResultSet rs = stat.executeQuery(Query);
			int nuser = 1;
			while (rs.next()) {
				System.out.println("----USUARIO "+nuser+"----");
				System.out.println("DNI: "+rs.getString(1));
				System.out.println("Name: "+rs.getString(2));
				System.out.println("Pass: "+rs.getString(3));
				System.out.println("Email: "+rs.getString(4));
				nuser++;
			}
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteUser(String dni) {
		String sentencia = "DELETE FROM USER WHERE dni = '"+dni+"'";
		runSentenciaDB(sentencia);
	}

	public static void deleteTableDB(){
		String sentencia = "DROP TABLE USER";
		runSentenciaDB(sentencia);
	}

	public static boolean insertarDB(String dni, String name, String pass, String email) {
		String sentencia = "INSERT INTO USER VALUES ('"+dni+"','"+name+"','"+pass+"','"+email+"')";
		runSentenciaDB(sentencia);
		return true;
	}
	
	public static boolean insertarDBAirport() {
		String sentencia = "INSERT INTO Airport VALUES (2, 'EPEEP', 'CIUDAD', 'PAIS', 'IATA', 'ICAO', 5.6, 6.7);";
		runSentenciaDB(sentencia);
		return true;
	}

	public static void crearColumna() {
		String sentencia = "ALTER TABLE USER ADD COLUMN email VARCHAR(254);)";
		runSentenciaDB(sentencia);
	}

	public static void crearDB() {
		//String sentencia = "CREATE TABLE USER(dni STRING PRIMARY KEY,name STRING, pass STRING, email STRING)";
		//String sentencia = "CREATE TABLE Airport(airportId int(5) NOT NULL PRIMARY KEY,	name_ap String(20),	city String(20), country String(20), IATA String(3) NOT NULL, ICAO String(4) NOT NULL, lat double, lon double);";
		//String sentencia = "CREATE TABLE Aircraft(IATA String(3) NOT NULL,ICAO String(4) NOT NULL,name_ac String(12) NOT NULL UNIQUE,seating int(4),speed double(4,2),PRIMARY KEY(IATA, ICAO, name_ac));";
		//String sentencia = "CREATE TABLE Route(origin String(20) NOT NULL,destination String(20) NOT NULL,distance int(8),airline String(20),PRIMARY KEY(origin,destination));";
		//String sentencia = "CREATE TABLE Vuelo(vueloId int(7) NOT NULL PRIMARY KEY,horarioSalida DATETIME,horarioLlegada DATETIME,origin String(20) NOT NULL REFERENCES Route(origin),destination String(20) NOT NULL REFERENCES Route(destination));";
		//String sentencia = "CREATE TABLE Reserva(COD_R int(9) NOT NULL PRIMARY KEY,vueloId int(7) NOT NULL  REFERENCES Vuelo(vueloId),dni String(9) NOT NULL REFERENCES USER(dni));";
		String sentencia = "";
		runSentenciaDB(sentencia);
		
	}


	public static void runSentenciaDB(String sentencia) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();		
			stat.executeUpdate(sentencia);
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	//crear lista con todo los vuelos COMO NO HAY VUELLOS LO TESTEO CON USUARIOS

	public static ArrayList<String> listVuelos(Usuario u) {		
		ArrayList<String> vuelos = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String Query = "SELECT * FROM Reserva WHERE dni = '"+u.getDni()+"';";
			ResultSet rs = stat.executeQuery(Query);
			
			String vuelo= "";
			int indx = 1;
			while (rs.next()) {
				vuelo = indx +". ";
				vuelo = vuelo + rs.getString(1) + " | ";
				vuelo = vuelo + rs.getString(2) + " | ";
				vuelo = vuelo + rs.getString(4);
				vuelos.add(vuelo);
				indx++;

			}
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vuelos;
	}



	



}
