package conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
	private static Connection con;
	public static  Connection getConexion(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=control_acceso","sa","malitos15");
			//con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=control_acceso","sa","Sofactory17");
			return con;           
		}catch(Exception e){
			e.printStackTrace();
			con = null;  
		}
		return null;
	}
	public void cerrarConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
