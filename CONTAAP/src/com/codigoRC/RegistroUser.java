package com.codigoRC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import menu.tree.MenuOpciones;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import conexionSQL.ConexionSQL;

public class RegistroUser extends SelectorComposer<Component> {
	@Wire
	private Textbox USERNAME;
	
	@Wire
	private Textbox RFC_USUARIO;
	
	@Wire
	private Textbox NOMBRE;
	
	@Wire
	private Textbox TEL_CEL;
	
	@Wire
	private Textbox TEL_OFICINA;
	
	@Wire
	private Textbox EMAIL_USER;
	
	@Wire
	private Textbox PASSWORD_U;
	
	@Wire
	private Textbox PASSWORD_U_C;
	
	@Wire
	private Combobox TIPO_USUARIO;
	
	@Wire
	private Combobox ID_PERFIL;
	
	@Wire
	private Combobox ID_CLIENTE;
	
	@Wire
	private Combobox ID_COMPANIA;
	
	@Wire
	private Combobox OCUPACION;
	
	@Wire
	private Combobox ID_OCUPACION;
	
	private ConexionSQL con = new ConexionSQL();
	private int caracter_longitud = 6;
	private String caracter_invalido = " ";
	private PreparedStatement pst;
	private ResultSet rst;
	public void Registrar(){
		
		Connection cnn = con.getConexion();
		
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
			Clients.showNotification("Las contraseña se cambio exitosamente");
			Executions.sendRedirect("#");
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
							+ ",'"++"'"
							+ ",''"
							+ ",''"
							+ ",''"
							+ ",'')"
							+"GO";
			        pst = con.prepareStatement(query);
					rst = pst.executeQuery();
			        rst.close();
		        
			}catch(Exception e){
				e.printStackTrace();
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
			Date fechaAlta = new Date();; 
			 
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
						+ ",'"++"'"
						+ ",''"
						+ ",''"
						+ ",''"
						+ ",'')"
						+"GO";
		        pst = con.prepareStatement(query);
				rst = pst.executeQuery();
		        rst.close();
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		}
			
		}
	}
	
	public Double ObtenerLimiteAuto(Connection con,String limite){
		String perfil = ID_PERFIL.getSelectedItem().toString();
		String cliente = ID_CLIENTE.getValue().toString();
		String comp = ID_COMPANIA.getValue().toString();
		String query = "SELECT LIMITE_AUTO FROM PERFIL WHERE ID_PERFIL ='"+perfil+"' AND ID_CLIENTE='"+cliente+"' AND ID_COMPANIA='"+comp+"'";
		pst = con.prepareStatement(query);
		rst = pst.executeQuery();
		Double resultado = Double.parseDouble(rst.toString());
		System.out.println(resultado);
        rst.close();
        return resultado;
	}
	
	
	
}
