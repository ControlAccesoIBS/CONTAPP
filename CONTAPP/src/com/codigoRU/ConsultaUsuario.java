package com.codigoRU;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.CodigoRP.DatosPerfil;

import conexionSQL.ConexionSQL;

public class ConsultaUsuario {
	private PreparedStatement pst;
	private ResultSet rst;
	private Connection cnn;
	private ConexionSQL con = new ConexionSQL();
	
	public List<DatosUser> ConsultaUsuario(String user,String id_cliente, String id_compania){
		try {
			Connection cnn = con.getConexion();
			
			String query = "SELECT USERNAME FROM USUARIO WHERE USERNAME ='"+user+"' AND ID_CLIENTE='"+id_cliente+"' AND ID_COMPANIA='"+id_compania+"'";
			ArrayList<DatosUser> datosPerfil = new ArrayList<DatosUser>();       
				pst = cnn.prepareStatement(query);
				rst = pst.executeQuery();
				while(rst.next()){
					DatosUser perfiles = cursorToContactU(rst);
					datosPerfil.add(perfiles);
				}					
				rst.close();
				cnn.close();
				return  datosPerfil;
			} catch (SQLException e) {
				System.out.println(e.getMessage().toString());
			}
			
		return  null;		
	}

	private DatosUser cursorToContactU(ResultSet cursor) throws SQLException {
		DatosUser datos = new DatosUser(
                cursor.getString(1)
                );
        return datos;
    }
	
	public List<DatosUserRFC> ConsultaRFC(String rfc,String id_cliente, String id_compania){
		try {
			Connection cnn = con.getConexion();
			
			String query = "SELECT RFC_USUARIO FROM USUARIO WHERE RFC ='"+rfc+"' AND ID_CLIENTE='"+id_cliente+"' AND ID_COMPANIA='"+id_compania+"'";
			ArrayList<DatosUserRFC> datosRFC = new ArrayList<DatosUserRFC>();       
				pst = cnn.prepareStatement(query);
				rst = pst.executeQuery();
				while(rst.next()){
					DatosUserRFC userRFC = cursorToContactRFC(rst);
					datosRFC.add(userRFC);
				}					
				rst.close();
				cnn.close();
				return  datosRFC;
			} catch (SQLException e) {
				System.out.println(e.getMessage().toString());
			}
			
		return  null;		
	}

	private DatosUserRFC cursorToContactRFC(ResultSet cursor) throws SQLException {
		DatosUserRFC datos = new DatosUserRFC(
                cursor.getString(1)
                );
        return datos;
    }
	
	public List<DatosUserEmail> ConsultaEmail(String email,String id_cliente, String id_compania){
		try {
			Connection cnn = con.getConexion();
			
			String query = "SELECT EMAIL_USER FROM USUARIO WHERE RFC ='"+email+"' AND ID_CLIENTE='"+id_cliente+"' AND ID_COMPANIA='"+id_compania+"'";
			ArrayList<DatosUserEmail> datosEmail = new ArrayList<DatosUserEmail>();       
				pst = cnn.prepareStatement(query);
				rst = pst.executeQuery();
				while(rst.next()){
					DatosUserEmail userEmail = cursorToContactEmail(rst);
					datosEmail.add(userEmail);
				}					
				rst.close();
				cnn.close();
				return  datosEmail;
			} catch (SQLException e) {
				System.out.println(e.getMessage().toString());
			}
			
		return  null;		
	}

	private DatosUserEmail cursorToContactEmail(ResultSet cursor) throws SQLException {
		DatosUserEmail datos = new DatosUserEmail(
                cursor.getString(1)
                );
        return datos;
    }
}
