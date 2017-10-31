package prueba;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.SQLException;


public class Prueba1 {
	

	public static void crearTabla(Connection con) throws SQLException {
		//creo tabla
		java.sql.Statement stat = con.createStatement();
		String crear = "CREATE TABLE IF NOT EXIST user(name STRING, pass STRING)";
		stat.executeUpdate(crear);
		//añado un user admin admin
		String anadir = "INSERT IF NOT EXIT INTO user "+"VALUES (admin,admin)";
		stat.executeUpdate(anadir);
		stat.close();
	} 

}
