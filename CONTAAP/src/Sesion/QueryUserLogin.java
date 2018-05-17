package Sesion;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionSQL.ConexionSQL;

public class QueryUserLogin {
	private PreparedStatement pst;
	private ResultSet rst;

	public List<DatosLogin> ConsultaUser(String user,String comp){
		ConexionSQL con = new ConexionSQL();
		try {
		Connection cnn = con.getConexion();
		
		String query = "SELECT USERNAME,PASSWORD_U,ID_CLIENTE,ID_COMPANIA,ID_PERFIL"
				+ " FROM USUARIO WHERE USERNAME ='"+user+
				"' AND ID_COMPANIA ='"+comp+"'";
		ArrayList<DatosLogin> datosUser = new ArrayList<DatosLogin>();       
			pst = cnn.prepareStatement(query);
			rst = pst.executeQuery();
			while(rst.next()){
				DatosLogin usaurio = cursorToContact(rst);
				datosUser.add(usaurio);
			}					
			rst.close();
			return  datosUser;
		} catch (SQLException e) {
			System.out.println(e.getMessage().toString());
		}
		
	return  null;
	}
	
	 private DatosLogin cursorToContact(ResultSet cursor) throws SQLException {
		 DatosLogin datos = new DatosLogin(
	                cursor.getString(1),
	                cursor.getString(2),
	                cursor.getString(3),
	                cursor.getString(4),
	                cursor.getString(5)
	                );
	        return datos;
	    }
}
