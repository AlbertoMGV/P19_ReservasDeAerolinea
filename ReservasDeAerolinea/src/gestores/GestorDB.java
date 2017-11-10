package gestores;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

import datos.Usuario;


public class GestorDB {
	
/*	public static void main(String[] args) {
		
		//ejecutad este main para ver lo que hay en la bd				
		//el usuario de prueba --> ("11111111H", "Admin", "admin1", "admin@deustoair.es");
		displayDB();
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
	
	public static void crearColumna() {
		String sentencia = "ALTER TABLE USER ADD COLUMN email VARCHAR(254);)";
		runSentenciaDB(sentencia);
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
