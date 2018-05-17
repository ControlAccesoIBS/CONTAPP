package com.CodigoCIA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionSQL.ConexionSQL;

public class ConsultaIDCliente {
	private ConexionSQL con = new ConexionSQL(); //se declara la conexion de la BD
	private PreparedStatement pst;
	private ResultSet rst;
	
	//consulta si la compañia ya esta vinculada con el cliente para evitar que se dupliquen el nombre de las compañias.
		public List<DatosCompania> ConsultaCompania(String comp, String rfc, String cli ){
			try {
				Connection cnn = con.getConexion(); //llama al metodo que devuelve la conexion de BD
				
				String query = "SELECT ID_COMPANIA,ID_CLIENTE FROM COMPANIA WHERE ID_COMPANIA ='"+comp+"' AND RFC_COMPANIA='"+rfc+"' AND ID_CLIENTE ='"+cli+"'";
				ArrayList<DatosCompania> datosCompania = new ArrayList<DatosCompania>();       
					pst = cnn.prepareStatement(query);
					rst = pst.executeQuery();
					while(rst.next()){
						DatosCompania compania = cursorToContactC(rst);
						datosCompania.add(compania);
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
	                cursor.getString(1),
	                cursor.getString(2)
	                );
	        return datos;
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
		
	
}
