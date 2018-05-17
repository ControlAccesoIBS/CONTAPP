package com.CodigoRP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionSQL.ConexionSQL;

public class ConsultaCliComp {
	private ConexionSQL con = new ConexionSQL();
	private PreparedStatement pst;
	private ResultSet rst;
	
	public List<DatosCompania> ConsultaComp(String cliente){
		try {
			Connection cnn = con.getConexion();
			
			String query = "SELECT ID_COMPANIA FROM COMPANIA WHERE ID_CLIENTE='"+cliente+"'";
			ArrayList<DatosCompania> datosCompania = new ArrayList<DatosCompania>();       
				pst = cnn.prepareStatement(query);
				rst = pst.executeQuery();
				while(rst.next()){
					DatosCompania comp = cursorToContactC(rst);
					datosCompania.add(comp);
				}					
				rst.close();
				return  datosCompania;
			} catch (SQLException e) {
				System.out.println(e.getMessage().toString());
			}
			
		return  null;		
	}

	private DatosCompania cursorToContactC(ResultSet cursor) throws SQLException {
		DatosCompania datos = new DatosCompania(
                cursor.getString(1)
                );
        return datos;
    }
	
		public List<DatosCliente> ConsultaCliente(String cli){
			try {
				Connection cnn = con.getConexion();
				
				String query = "SELECT ID_CLIENTE FROM CLIENTE WHERE ID_CLIENTE ='"+cli+"'";
				ArrayList<DatosCliente> datosCliente = new ArrayList<DatosCliente>();       
					pst = cnn.prepareStatement(query);
					rst = pst.executeQuery();
					while(rst.next()){
						DatosCliente cliente = cursorToContact(rst);
						datosCliente.add(cliente);
					}					
					rst.close();
					return  datosCliente;
				} catch (SQLException e) {
					System.out.println(e.getMessage().toString());
				}
				
			return  null;		
		}
	
		private DatosCliente cursorToContact(ResultSet cursor) throws SQLException {
		 DatosCliente datos = new DatosCliente(
	                cursor.getString(1)
	                );
	        return datos;
	    }
	
		//Consulta de perfiles
		
		public List<DatosPerfil> ConsultaPerfil(String perfil,String id_cliente, String id_compania){
			try {
				Connection cnn = con.getConexion();
				
				String query = "SELECT ID_PERFIL,ID_CLIENTE,ID_COMPANIA FROM PERFIL WHERE ID_PERFIL ='"+perfil+"' AND ID_CLIENTE='"+id_cliente+"' AND ID_COMPANIA='"+id_compania+"'";
				ArrayList<DatosPerfil> datosPerfil = new ArrayList<DatosPerfil>();       
					pst = cnn.prepareStatement(query);
					rst = pst.executeQuery();
					while(rst.next()){
						DatosPerfil perfiles = cursorToContactP(rst);
						datosPerfil.add(perfiles);
					}					
					rst.close();
					return  datosPerfil;
				} catch (SQLException e) {
					System.out.println(e.getMessage().toString());
				}
				
			return  null;		
		}
	
		private DatosPerfil cursorToContactP(ResultSet cursor) throws SQLException {
			DatosPerfil datos = new DatosPerfil(
	                cursor.getString(1),
	                cursor.getString(2),
	                cursor.getString(3)
	                );
	        return datos;
	    }
}
