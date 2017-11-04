package gestores;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;


public class GestorDB {
	
	public static void main(String[] args) {
		
		//deleteTableDB();
		//crearDB();
		//insertarDB("11111111H", "admin", "admin1");
		//ejecutad este main para ver lo que hay en la bd
		displayDB();
	}
	
	public static boolean reg(String dni,String name, String pass) {
		insertarDB(dni,name, pass); 		
		return true;
	}
	
	

	public static boolean log(String dni,String pass) {
		boolean log = false;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String Query = "SELECT * FROM USER";
			ResultSet rs = stat.executeQuery(Query);
			int nuser = 1;
			while (rs.next() && log==false) {				
				if (dni.equals(rs.getString(1))) {
					if (pass.equals(rs.getString(3))) {
						log=true;
					}
				}				
				nuser++;
			}
			rs.close();
			stat.close();
			conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return log;
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
	
	public static boolean insertarDB(String dni, String name, String pass) {
		String sentencia = "INSERT INTO USER VALUES ('"+dni+"','"+name+"','"+pass+"')";
		runSentenciaDB(sentencia);
		return true;
	}
	
	public static void crearDB() {
		String sentencia = "CREATE TABLE USER(dni STRING PRIMARY KEY,name STRING, pass STRING)";
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

}
