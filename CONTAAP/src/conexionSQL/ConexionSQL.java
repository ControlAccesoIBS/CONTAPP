package conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class ConexionSQL {
private static Connection con;
public Connection getConexion(){
	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=control_acceso","sa","cerrada");
		 
		return con;
		
	}catch(Exception e){
		e.printStackTrace();
		con = null;
	}
	return null;
	
}


}
