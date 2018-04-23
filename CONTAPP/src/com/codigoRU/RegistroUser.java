package com.codigoRU;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;




import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import Sesion.CrearSesion;
import conexionSQL.ConexionSQL;

public class RegistroUser extends SelectorComposer<Component> {
	@Wire
	private Textbox USERNAME,RFC_USUARIO,NOMBRE,TEL_CEL,TEL_OFICINA,EMAIL_USER,PASSWORD_U,PASSWORD_U_C;
	
	@Wire
	private Combobox TIPO_USUARIO,ID_PERFIL,ID_CLIENTE,ID_COMPANIA,OCUPACION,ID_OCUPACION;
	
	private ConexionSQL con = new ConexionSQL();
	private int caracter_longitud = 6;
	private String caracter_invalido = " ";
	private PreparedStatement pst;
	private ResultSet rst;
	
	@Listen("onClick=#Registrar")
	public void Registrar(){
		
		Connection cnn = con.getConexion();
		ValidarPass(cnn,PASSWORD_U,PASSWORD_U_C);
		
	}
	
	public void ValidarPass(Connection con, Textbox a,Textbox b){
			
		
		if (a.getValue().length() < caracter_longitud) {
			Clients.showNotification("Tu contraseña debe contar más de "
					+ caracter_longitud + " caracteres.");
			Executions.sendRedirect("#");
		} else if (a.getValue().contains(caracter_invalido)) {
			Clients.showNotification("Tu contraseña no debe tener espacios en blanco.");
			Executions.sendRedirect("#");
		} else if (a.getValue().equals(b.getValue()) == false) {
			Clients.showNotification("Las contraseñas ingresadas no coinciden");
			// Clients.showNotification("Las contraseñas ingresadas no coinciden");
			Executions.sendRedirect("#");
		} else if(a.getValue().equals(b.getValue()) == true){
			
			if(OCUPACION.getSelectedItem().equals("EMPLEADO")){
				String user = USERNAME.getValue().toString();
				String rfc = RFC_USUARIO.getValue().toString();
				String nombre = NOMBRE.getValue().toString();
				String cel = TEL_CEL.getValue().toString();
				String tel_oficina = TEL_OFICINA.getValue().toString();
				String email = EMAIL_USER.getValue().toString();
				String pass = a.getValue().toString();
				String tipo = TIPO_USUARIO.getSelectedItem().toString();
				String perfil = ID_PERFIL.getSelectedItem().toString();
				String cliente = ID_CLIENTE.getValue().toString();
				String comp = ID_COMPANIA.getValue().toString();
				String emp = ID_OCUPACION.getValue().toString();
				Double limite_auto = ObtenerLimiteAuto(con,perfil);
				Date fechaAlta = new Date(); 
				String userAlta = (String) Sessions.getCurrent().getAttribute("username");
				
				try{
					String query = ""
							+ "INSERT INTO USUARIO "
							+ "(USERNAM,RFC_USUARIO,NOMBRE,TEL_CEL"
							+ ",TEL_OOFICINA,EMAIL_USER,PASSWORD_U"
							+ ",TIPO_USUARIO,ESTATUS,LIMITE_AUTORIZACION"
							+ ",ID_PERFIL,ID_CLIENTE,ID_COMPANIA"
							+ ",ID_PROVEEDOR,ID_EMPLEADO,FECHA_ALTA"
							+ ",USERNAME_ALTA,ESTATUS_BLOQ,FECHA_BLOQUEO"
							+ ",FECHA_MODF,USERNAME_MODF)"
							+ "VALUES("
							+ "'"+user+"'"
							+ ",'"+rfc+"'"
							+ ",'"+nombre+"'"
							+ ",'"+cel+"'"
							+ ",'"+tel_oficina+"'"
							+ ",'"+email+"'"
							+ ",'"+pass+"'"
							+ ",'"+tipo+"'"
							+ ",'A'"
							+ ","+limite_auto+""
							+ ",'"+perfil+"'"
							+ ",'"+cliente+"'"
							+ ",'"+comp+"'"
							+ ",''"
							+ ",'"+emp+"'"
							+ ",'"+fechaAlta+"'"
							+ ",'"+userAlta+"'"
							+ ",''"
							+ ",''"
							+ ",''"
							+ ",'')"
							+"GO";
			        pst = con.prepareStatement(query);
					rst = pst.executeQuery();
			        rst.close();
			        Clients.showNotification("El usuario se ha registrado exitosamente");
			        
			}catch(Exception e){
				System.out.println("Error: "+e.getMessage().toString());
				Clients.showNotification("El usuario no se ha registrado.");
			}
		}else if(OCUPACION.getSelectedItem().equals("PROVEEDOR")){
			String user = USERNAME.getValue().toString();
			String rfc = RFC_USUARIO.getValue().toString();
			String nombre = NOMBRE.getValue().toString();
			String cel = TEL_CEL.getValue().toString();
			String tel_oficina = TEL_OFICINA.getValue().toString();
			String email = EMAIL_USER.getValue().toString();
			String pass = a.getValue().toString();
			String tipo = TIPO_USUARIO.getSelectedItem().toString();
			String perfil = ID_PERFIL.getSelectedItem().toString();
			String cliente = ID_CLIENTE.getValue().toString();
			String comp = ID_COMPANIA.getValue().toString();
			String proveedor = ID_OCUPACION.getValue().toString(); 
			Double limite_auto = ObtenerLimiteAuto(con,perfil);
			Date fechaAlta = new Date();
			String userAlta = (String) Sessions.getCurrent().getAttribute("username");
			 
			try{
				String query = ""
						+ "INSERT INTO USUARIO "
						+ "(USERNAM,RFC_USUARIO,NOMBRE,TEL_CEL"
						+ ",TEL_OOFICINA,EMAIL_USER,PASSWORD_U"
						+ ",TIPO_USUARIO,ESTATUS,LIMITE_AUTORIZACION"
						+ ",ID_PERFIL,ID_CLIENTE,ID_COMPANIA"
						+ ",ID_PROVEEDOR,ID_EMPLEADO,FECHA_ALTA"
						+ ",USERNAME_ALTA,ESTATUS_BLOQ,FECHA_BLOQUEO"
						+ ",FECHA_MODF,USERNAME_MODF)"
						+ "VALUES("
						+ "'"+user+"'"
						+ ",'"+rfc+"'"
						+ ",'"+nombre+"'"
						+ ",'"+cel+"'"
						+ ",'"+tel_oficina+"'"
						+ ",'"+email+"'"
						+ ",'"+pass+"'"
						+ ",'"+tipo+"'"
						+ ",'A'"
						+ ","+limite_auto+""
						+ ",'"+perfil+"'"
						+ ",'"+cliente+"'"
						+ ",'"+comp+"'"
						+ ",'"+proveedor+"'"
						+ ",''"
						+ ",'"+fechaAlta+"'"
						+ ",'"+userAlta+"'"
						+ ",''"
						+ ",''"
						+ ",''"
						+ ",'')"
						+"GO";
		        pst = con.prepareStatement(query);
				rst = pst.executeQuery();
		        rst.close();
		        Clients.showNotification("El usuario se ha registrado exitosamente");
		}catch(Exception e){
			System.out.println("Error: "+e.getMessage().toString());
			Clients.showNotification("El usuario no se ha registrado.");
		}
		}
			
		}
	}
	
	public Double ObtenerLimiteAuto(Connection con,String limite){
		String perfil = ID_PERFIL.getSelectedItem().toString();
		String cliente = ID_CLIENTE.getValue().toString();
		String comp = ID_COMPANIA.getValue().toString();
		String query = "SELECT LIMITE_AUTO FROM PERFIL WHERE ID_PERFIL ='"+perfil+"' AND ID_CLIENTE='"+cliente+"' AND ID_COMPANIA='"+comp+"'";
		try {
			pst = con.prepareStatement(query);
			rst = pst.executeQuery();
			Double resultado = Double.parseDouble(rst.toString());
			System.out.println(resultado);
	        rst.close();
	        return resultado;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        return null;
	}
	
	
	
}
