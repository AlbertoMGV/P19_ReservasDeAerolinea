package gestores;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import datos.Aircraft;
import datos.Route;
import datos.Usuario;
import utilidades.FormatData;


public class GestorDB {


	public static void main(String[] args) {
		crearDB();
	}  
	
	//Registra reservas en la bd ("Nº Vuelo", "Origen", "Escalas", "Destino", "Precio", "H.Salida", "H.Llegada")
	public static boolean regReserva(ArrayList<String> selectVuelo) {
		String nvuelo = selectVuelo.get(0);
		String origen = selectVuelo.get(1);
		String escalas = selectVuelo.get(2);
		String destino = selectVuelo.get(3);
		String precio = selectVuelo.get(4);
		String hsalida = selectVuelo.get(5);
		String hllegada = selectVuelo.get(6);
		
		String sentencia = "INSERT INTO Vuelo VALUES ('"+nvuelo+"','"+origen+"','"+escalas+"','"+destino+"','"+hsalida+"','"+hllegada+"','"+precio+"')";
		runSentenciaDB(sentencia);
		return true;
	}
	
	
	//Registra Users en la bd
	public static boolean reg(String dni,String name, String pass, String email) {
		insertarDB(dni,name, pass, email); 		
		return true;
	}


	//loggin. Devuelve el user con el dni y pass que recibe. 
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
	
	//devuelve el email del usuario que hemos pasado dni.
	
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

	//lista todos los usuarias de la bd
	public static void displayDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String Query = "SELECT * FROM Vuelo";
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
	
	//borra usuario de la bd con el dni pasado.
	
	public static void deleteUser(String dni) {
		String sentencia = "DELETE FROM USER WHERE dni = '"+dni+"'";
		runSentenciaDB(sentencia);
	}
	
	
	//borra la tabla pasada
	public static void deleteTableDB(String table){
		String sentencia = "DROP TABLE '"+table+"'";
		runSentenciaDB(sentencia);
	}
	
	//inserta users en la tabla USER.

	public static boolean insertarDB(String dni, String name, String pass, String email) {
		String sentencia = "INSERT INTO USER VALUES ('"+dni+"','"+name+"','"+pass+"','"+email+"')";
		runSentenciaDB(sentencia);
		return true;
	}
	
	//inserta aviones en la tabla Aircraft.
	public static boolean insertarAircraft(String ICAO, String IATA, int speed, String name) {
		String sentencia = "INSERT INTO Aircraft VALUES ('"+IATA+"','"+ICAO+"', '"+name+"' ,"+speed+");";
		runSentenciaDB(sentencia);
		return true;
	}
	
	//inserta aerolinea en la tabla Airline.
	public static boolean insertarAirline(int id, String ICAO, String IATA, String name) {
		String sentencia = "INSERT INTO Airline VALUES ("+id+",'"+name+"','"+IATA+"','"+ICAO+"');";
		runSentenciaDB(sentencia);
		return true;
	}
	//inserta routas en la tabla Route.
	public static boolean insertarRuta(String origin, String destino, int distance, String airline, String aircraft) {
		String sentencia = "INSERT INTO Route VALUES ('" + origin + "', '" + destino + "', " + distance + ", '"+airline+"', '"+aircraft+"');";
		runSentenciaDB(sentencia);
		return true;
	}
	
	
	//devuelve un avion aleatorio de todos los disponibles en la ruta
	
	public static Aircraft getAircraft(String origen, String destino, String aerolinea) {
		Aircraft result = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String sentencia = "SELECT r.aircraft FROM Route r WHERE r.origin='"+origen+"' and r.destination='"+destino + "' and r.airline='"+aerolinea+"';";
			ResultSet rs = stat.executeQuery(sentencia);
			String allAc = "";
			while(rs.next()) {
				String ac = rs.getString(1);
				if(ac.contains(" ")) {
					String[] data = ac.split(" ");
					for(int i = 0; i < data.length; i++) {
						allAc += data[i]+",";
					}
				}else {
					allAc += ac + ",";
				}
			}

			if(allAc.length() > 0) {
				allAc = allAc.substring(0, allAc.length()-1);
			}
						
			String[] acArray = allAc.split(",");
			int random = ThreadLocalRandom.current().nextInt(0, acArray.length);
			
			sentencia = "SELECT ICAO, IATA, name_ac, speed from Aircraft WHERE IATA='"+acArray[random]+"';";
						
			rs= stat.executeQuery(sentencia);
			
			result = new Aircraft(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));


			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int getDistancia(String origin, String destino) {
		int distance = -1;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String sentencia = "SELECT MIN(distance) FROM Route WHERE origin='"+origin+"' and destination='" + destino+"';";
			ResultSet rs = stat.executeQuery(sentencia);
			distance = rs.getInt(1);
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return distance;
	}
	
	public static ArrayList<String> getAerolineas(String origin, String destino) {
		ArrayList<String> aerolineas = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String sentencia = "SELECT DISTINCT airline FROM Route WHERE origin='"+origin+"' and destination='" + destino+"';";
			ResultSet rs = stat.executeQuery(sentencia);
			while(rs.next()) {
				aerolineas.add(rs.getString(1));
			}
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aerolineas;
	}
	
	public static String getAircraft() {
		String result = "";
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String sentencia = "SELECT DISTINCT IATA FROM Aircraft WHERE IATA NOT LIKE '';";
			ResultSet rs = stat.executeQuery(sentencia);
			while(rs.next()) {
				result += rs.getString(1)+",";
			}
			result = result.substring(0, result.length()-1);
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getAirlineName(String IATA) {
		String result = "";
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String sentencia = "SELECT name FROM Airline WHERE IATA='"+ IATA +"';";
			ResultSet rs = stat.executeQuery(sentencia);
			result = rs.getString(1);
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getAirlineICAO(String IATA) {
		String result = "";
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String sentencia = "SELECT ICAO FROM Airline WHERE IATA='"+ IATA +"';";
			ResultSet rs = stat.executeQuery(sentencia);
			result = rs.getString(1);
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

	public static void crearColumna() {
		String sentencia = "ALTER TABLE USER ADD COLUMN email VARCHAR(254);)";
		runSentenciaDB(sentencia);
	}

	public static void crearDB() {
		//String sentencia = "CREATE TABLE USER(dni STRING PRIMARY KEY,name STRING, pass STRING, email STRING)";
		//String sentencia = "CREATE TABLE Airport(airportId int(5) NOT NULL PRIMARY KEY,	name_ap String(20),	city String(20), country String(20), IATA String(3) NOT NULL, ICAO String(4) NOT NULL, lat double, lon double);";
		//String sentencia = "CREATE TABLE Aircraft(IATA String(3),ICAO String(4) NOT NULL,name_ac String(12) NOT NULL UNIQUE,speed double(4,2),PRIMARY KEY(ICAO, name_ac));";
		//String sentencia = "CREATE TABLE Route(origin String(20) NOT NULL,destination String(20) NOT NULL,distance int(8),airline String(20),aircraft String(20), PRIMARY KEY(origin,destination, airline));";
		//String sentencia = "CREATE TABLE Vuelo(vueloId int(7) NOT NULL PRIMARY KEY,origin String(20) NOT NULL REFERENCES Route(origin),escalas varchar(100),destination String(20) NOT NULL REFERENCES Route(destination),horarioSalida DATETIME,horarioLlegada DATETIME, precio FLOAT);";		                     
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
				vuelo = vuelo + "Codigo Reserva: " + rs.getString(1) + " | ";
				vuelo = vuelo + "Identificador del Vuelo: "+ rs.getString(2);
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
