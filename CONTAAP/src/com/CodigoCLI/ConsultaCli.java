package com.CodigoCLI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.CodigoCIA.DatosCompania;

import conexionSQL.ConexionSQL;

public class ConsultaCli {

		private ConexionSQL con = new ConexionSQL();
		private PreparedStatement pst;
		private ResultSet rst;
		
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
		
		
		
			
	}


