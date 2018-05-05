package com.codigoMAPP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionSQL.ConexionSQL;

public class ConsultaAplicaciones {
	
	private PreparedStatement pst;
	private ResultSet rst;
	private Connection cnn;
	private ConexionSQL con = new ConexionSQL();
	
	public List<DatosAplicaciones> getConsultaAplicaciones(){
		cnn = con.getConexion();
		String query = "SELECT ID_APP,NOMBRE_APP,DESCRIPCION_APP,REQ_AUTORIZACION,ESTATUS,URL_IMG,URL_PAG FROM APLICACION";
		try {
			ArrayList<DatosAplicaciones> resultadosApp = new ArrayList<DatosAplicaciones>();
			pst = cnn.prepareStatement(query);
			rst = pst.executeQuery();
			while(rst.next()){
				DatosAplicaciones datosApps = ingresoResultados(rst);
				resultadosApp.add(datosApps);
			}
			rst.close();
			cnn.close();
			return resultadosApp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: "+e.getMessage().toString());
		}
		
		return null;
	}
	
	public DatosAplicaciones ingresoResultados(ResultSet cursor) throws SQLException{
		DatosAplicaciones dA = new DatosAplicaciones(
				cursor.getString(1),
				cursor.getString(2),
				cursor.getString(3),
				cursor.getString(4),
				cursor.getString(5),
				cursor.getString(6),
				cursor.getString(7)				
				);
		
		return dA;
		
	}

}
