package gestores;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;


public class GestorDB {
	
	public static void main(String[] args) {	
		displayDB();
	}
	
	public static void reg(String name, String pass) {
		insertarDB(name, pass);
	}
	
	

	public static boolean log(String name, String pass) {
		boolean log = false;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:datos.db");
			Statement stat = conn.createStatement();
			String Query = "SELECT * FROM USER";
			ResultSet rs = stat.executeQuery(Query);
			int nuser = 1;
			while (rs.next() && log==false) {				
				if (name.equals(rs.getString(1))) {
					if (pass.equals(rs.getString(2))) {
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
				System.out.println("Name: "+rs.getString(1));
				System.out.println("Pass: "+rs.getString(2));
				nuser++;
			}
			rs.close();
			stat.close();
			conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void deleteTableDB(){
		String sentencia = "DROP TABLE USER";
		runSentenciaDB(sentencia);
	}
	
	public static void insertarDB(String name, String pass) {
		String sentencia = "INSERT INTO USER VALUES ('"+name+"','"+pass+"')";
		runSentenciaDB(sentencia);
	}
	
	public static void crearDB() {
		String sentencia = "CREATE TABLE USER(name STRING, pass STRING)";
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
