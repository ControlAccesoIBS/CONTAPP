package consulta.compania;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.CodigoRP.DatosCliente;
import com.CodigoRP.DatosCompania;
import com.CodigoRP.DatosPerfil;

import conexionSQL.ConexionSQL;

public class ConsultaCompCli {
	private ConexionSQL con = new ConexionSQL();
	private PreparedStatement pst;
	private ResultSet rst;
	
	public List<DatosComp> ConsultaComp(){
		try {
			Connection cnn = con.getConexion();
			
			String query = "SELECT ID_COMPANIA,ID_CLIENTE FROM COMPANIA";
			ArrayList<DatosComp> datosCompania = new ArrayList<DatosComp>();       
				pst = cnn.prepareStatement(query);
				rst = pst.executeQuery();
				while(rst.next()){
					DatosComp comp = cursorToContactC(rst);
					datosCompania.add(comp);
				}					
				rst.close();
				cnn.close();
				return  datosCompania;
			} catch (SQLException e) {
				System.out.println(e.getMessage().toString());
			}
			
		return  null;		
	}

	private DatosComp cursorToContactC(ResultSet cursor) throws SQLException {
		DatosComp datos = new DatosComp(
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
					cnn.close();
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
		
		public List<DatosPerfil> ConsultaPerfil(){
			try {
				Connection cnn = con.getConexion();
				
				String query = "SELECT ID_PERFIL,ID_CLIENTE,ID_COMPANIA FROM PERFIL";
				ArrayList<DatosPerfil> datosPerfil = new ArrayList<DatosPerfil>();       
					pst = cnn.prepareStatement(query);
					rst = pst.executeQuery();
					while(rst.next()){
						DatosPerfil perfiles = cursorToContactP(rst);
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
	
		private DatosPerfil cursorToContactP(ResultSet cursor) throws SQLException {
			DatosPerfil datos = new DatosPerfil(
	                cursor.getString(1),
	                cursor.getString(2),
	                cursor.getString(3)
	                );
	        return datos;
	    }
		
		public List<DatosPerfil> ConsultaPerfilV(String perfil, String cliente,String comp){
			try {
				Connection cnn = con.getConexion();
				
				String query = "SELECT ID_PERFIL,ID_CLIENTE,ID_COMPANIA FROM PERFIL WHERE ID_PERFIL='"+perfil.replace(" ", "")+"' AND ID_CLIENTE='"+cliente.replace(" ", "")+"' AND ID_COMPANIA='"+comp.replace(" ", "")+"'";
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
		
		public List<DatosComp> ConsultaCompV(String comp, String cli){
			try {
				Connection cnn = con.getConexion();
				
				String query = "SELECT ID_COMPANIA,ID_CLIENTE FROM COMPANIA WHERE ID_COMPANIA='"+comp.replace(" ", "")+"' AND ID_CLIENTE='"+cli.replace(" ", "")+"'";
				ArrayList<DatosComp> datosCompania = new ArrayList<DatosComp>();       
					pst = cnn.prepareStatement(query);
					rst = pst.executeQuery();
					while(rst.next()){
						DatosComp comp1 = cursorToContactC(rst);
						datosCompania.add(comp1);
					}					
					rst.close();
					cnn.close();
					return  datosCompania;
				} catch (SQLException e) {
					System.out.println(e.getMessage().toString());
				}
				
			return  null;		
		}
}
