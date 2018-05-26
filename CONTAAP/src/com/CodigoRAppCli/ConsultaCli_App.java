package com.CodigoRAppCli;

import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import com.CodigoCIA.DatosCliente;

import conexionSQL.ConexionSQL;

public class ConsultaCli_App {
	private ConexionSQL con = new ConexionSQL();
	private PreparedStatement pst;
	private ResultSet rst;
	
	public List<DatosCompania> ConsultaComp(){
		try {
			Connection cnn = con.getConexion();
			
			String query = "SELECT ID_COMPANIA FROM COMPANIA";
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
	
	
	public List<DatosCliente> ConsultaCliente(){
		try {
			Connection cnn = con.getConexion();
			
			String query = "SELECT ID_CLIENTE FROM CLIENTE";
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
	
		
		public List<DatosAplicacion> ConsultaAplicacion(){
			try {
				Connection cnn = con.getConexion();
				
				String query = "SELECT ID_APP FROM APLICACION";
				ArrayList<DatosAplicacion> datosAplicacion = new ArrayList<DatosAplicacion>();       
					pst = cnn.prepareStatement(query);
					rst = pst.executeQuery();
					while(rst.next()){
						DatosAplicacion aplicacion = cursorToContact3(rst);
						datosAplicacion.add(aplicacion);
					}					
					rst.close();
					return  datosAplicacion;
				} catch (SQLException e) {
					System.out.println(e.getMessage().toString());
				}
			return  null;		
		}
	
		private DatosAplicacion cursorToContact3(ResultSet cursor2) throws SQLException {
		 DatosAplicacion datos = new DatosAplicacion(
	                cursor2.getString(1)
	                );
	        return datos;
	    }
		
		public List<DatosCompania> ConsultaCompC(String compania, String cliente){
			try {
				Connection cnn = con.getConexion();
				
				String query = "SELECT ID_COMPANIA,ID_CLIENTE FROM COMPANIA WHERE ID_COMPANIA='"+compania+"' AND ID_CLIENTE='"+cliente+"'";
				ArrayList<DatosCompania> datosCompania2 = new ArrayList<DatosCompania>();       
					pst = cnn.prepareStatement(query);
					rst = pst.executeQuery();
					while(rst.next()){
						DatosCompania comp = cursorToContactC(rst);
						datosCompania2.add(comp);
					}					
					rst.close();
					return  datosCompania2;
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
		public List<DatosCompania> ConsultaCompCc(String compania, String cliente, String app){
			try {
				Connection cnn = con.getConexion();
				
				String query = "SELECT ID_COMPANIA,ID_CLIENTE FROM REL_CIA_APP WHERE ID_COMPANIA='"+compania+"' AND ID_CLIENTE='"+cliente+"' AND ID_APP='"+app+"'";
				ArrayList<DatosCompania> datosCompania2 = new ArrayList<DatosCompania>();       
					pst = cnn.prepareStatement(query);
					rst = pst.executeQuery();
					while(rst.next()){
						DatosCompania comp = cursorToContactCc(rst);
						datosCompania2.add(comp);
					}					
					rst.close();
					return  datosCompania2;
				} catch (SQLException e) {
					System.out.println(e.getMessage().toString());
				}	
			return  null;		
		}

		private DatosCompania cursorToContactCc(ResultSet cursor) throws SQLException {
			DatosCompania datos = new DatosCompania(
	                cursor.getString(1)
	                );
	        return datos;
	    }
}
