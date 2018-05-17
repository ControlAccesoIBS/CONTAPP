package com.CodigoRU;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;




import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import com.CodigoRP.DatosCombo;
import com.CodigoRP.DatosPerfil;

import Sesion.CrearSesion;
import Sesion.DatosLogin;
import Sesion.QueryUserLogin;
import conexionSQL.ConexionSQL;
import consulta.compania.ConsultaCompCli;
import consulta.compania.DatosCombos;
import consulta.compania.DatosComp;

public class RegistroUser extends SelectorComposer<Component> {
	@Wire
	private Textbox USERNAME,RFC_USUARIO,NOMBRE,TEL_CEL,
	TEL_OFICINA,
	EMAIL_USER,PASSWORD_U,PASSWORD_U_C;
	
	@Wire
	private Combobox TIPO_USUARIO,ID_PERFIL,ID_CLIENTE,
	ID_COMPANIA,OCUPACION,ID_OCUPACION;
	
	private String cliente, comp, perfil;
	
	private ConexionSQL con = new ConexionSQL();
	private int caracter_longitud = 6;
	private int caracter_longitud_max =15;
	private String caracter_invalido = " ";
	private String caracter_valido_correo = "@";
	private int caracter_longitud_rfc =13;
	private PreparedStatement pst;
	private ResultSet rst;
	
	@Init
	public void Combos(){
		setCliente("Seleccione el cliente");
		setComp("Seleccione la compañia");
		setPerfil("Seleccione el Perfil");
	}
	
	@Listen("onClick=#LIMPIAR")
	public void Limpiar(){
		USERNAME.setValue(" ");
		RFC_USUARIO.setValue(" ");
		NOMBRE.setValue(" ");
		TEL_CEL.setValue(" ");
		TEL_OFICINA.setValue(" ");
		EMAIL_USER.setValue(" ");
		TIPO_USUARIO.setSelectedIndex(0);
		ID_PERFIL.setSelectedIndex(0);
		ID_CLIENTE.setSelectedIndex(0);
		ID_COMPANIA.setSelectedIndex(0);
		ID_OCUPACION.setSelectedIndex(0);
		PASSWORD_U.setValue(" ");
		PASSWORD_U_C.setValue(" ");
	}
	
	@Listen("onClick=#REGISTRAR")
	public void Registrar(){
		
		Connection cnn = con.getConexion();
		String user = USERNAME.getValue().toString();
		String rfc = RFC_USUARIO.getValue().toString();
		String email = EMAIL_USER.getValue().toString();
		String cliente = ID_CLIENTE.getValue().toString();
		String comp = ID_COMPANIA.getValue().toString();
		String perfil = ID_PERFIL.getValue().toString();
		
		if(ValidaUsuario(user,cliente,comp) == false){
			if(ValidaRFC(rfc.toUpperCase(),cliente,comp) == false){
				if(ValidaEmail(email,cliente,comp) == false){
					if(ValidarPerfil(perfil,cliente,comp) == true){
						if(ValidarCompaniaCliente() == true){
							ValidarPass(cnn,PASSWORD_U,PASSWORD_U_C);
						}else{
							Clients.showNotification("La compania no esta asociada a ese cliente..",Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 2000);
						}
					}else{
						Clients.showNotification("El perfil no se encuentra asociado a el cliente y a la compania.",Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 2000);
					}
				}else{
					Clients.showNotification("El e-mail ya esta asosiado con la compañia.",Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 2000);
				}
			}else{
				Clients.showNotification("El RFC ya esta asosiado con la compañia.",Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 2000);
			}
		}else{
			Clients.showNotification("El Usuario ya esta asosiado con la compañia.",Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 2000);
		}
		
	}
	
	public void ValidarPass(Connection con, Textbox a,Textbox b){
	
		if (a.getValue().length() < caracter_longitud) {
			Clients.showNotification("Tu contraseña debe contar más de "
					+ caracter_longitud + " caracteres.");
			Executions.sendRedirect("#");
		}else if (RFC_USUARIO.getValue().replace(" ","").length() < caracter_longitud_rfc) {
			Clients.showNotification("Tu RFC debe contener "
					+ caracter_longitud_rfc + " caracteres.");
			Executions.sendRedirect("#");
		}
		else if (USERNAME.getValue().length() > caracter_longitud_max) {
			Clients.showNotification("Tu usuario no debe contar más de "
					+ caracter_longitud_max + " caracteres.");
			Executions.sendRedirect("#");
		}		
		else if (a.getValue().length() > caracter_longitud_max) {
			Clients.showNotification("Tu contraseña debe contar menos de "
					+ caracter_longitud_max + " caracteres.");
			Executions.sendRedirect("#");
		}
		else if (a.getValue().contains(caracter_invalido)) {
			Clients.showNotification("Tu contraseña no debe tener espacios en blanco.");
			Executions.sendRedirect("#");
		}
		else if (a.getValue().equals(b.getValue()) == false) {
			Clients.showNotification("Las contraseñas ingresadas no coinciden");
			// Clients.showNotification("Las contraseñas ingresadas no coinciden");
			Executions.sendRedirect("#");
		} 
		else if(a.getValue().equals(b.getValue()) == true){
			String ocupacion = OCUPACION.getValue().toString();
			ocupacion.replace(" ", "");
			if(ocupacion.equals("EMPLEADO")){
				String user = USERNAME.getValue().toString();
				String rfc = RFC_USUARIO.getValue().toString().toUpperCase();
				String nombre = NOMBRE.getValue().toString().toUpperCase();
				String cel = TEL_CEL.getValue().toString();
				String tel_oficina = TEL_OFICINA.getValue().toString();
				String email = EMAIL_USER.getValue().toString();
				String pass = a.getValue().toString();
				String passEncrip = DigestUtils.md5Hex(pass);
				String tipo = TIPO_USUARIO.getValue().toString();
				String perfil = ID_PERFIL.getValue().toString();
				String cliente = ID_CLIENTE.getValue().toString();
				String comp = ID_COMPANIA.getValue().toString();
				String emp = ID_OCUPACION.getValue().toString();
				Double limite_auto = 10.5;
				java.util.Date d = new java.util.Date(); 
				Timestamp timestamp = new Timestamp(d.getTime());
				String userAlta = user;
				
				try{
				
					String query = "" + "INSERT INTO USUARIO "
							+ "(USERNAME,RFC_USUARIO,NOMBRE,TEL_CEL"
							+ ",TEL_OOFICINA,EMAIL_USER,PASSWORD_U"
							+ ",TIPO_USUARIO,ESTATUS,LIMITE_AUTORIZACION"
							+ ",ID_PERFIL,ID_CLIENTE,ID_COMPANIA"
							+ ",ID_PROVEEDOR,ID_EMPLEADO,FECHA_ALTA"
							+ ",USERNAME_ALTA,ESTATUS_BLOQ,FECHA_BLOQUEO"
							+ ",FECHA_MODF,USERNAME_MODF)" + "VALUES " + "(?"
							+ ",?" + ",?" + ",?" + ",?" + ",?" + ",?" + ",?"
							+ ",?" + ",?" + ",?" + ",?" + ",?" + ",?" + ",?"
							+ ",?" + ",?" + ",?" + ",?" + ",?" + ",?)";
					
					
				System.out.println(query);
				pst = con.prepareStatement(query);
				pst.setString(1, user.replace(" ",""));
				pst.setString(2, rfc.replace(" ",""));
				pst.setString(3, nombre);
				pst.setString(4, cel.replace(" ",""));
				pst.setString(5, tel_oficina.replace(" ",""));
				pst.setString(6,email.replace(" ",""));
				pst.setString(7, passEncrip);
				pst.setString(8, tipo.replace(" ", ""));
				pst.setString(9, "A");
				pst.setDouble(10, limite_auto);
				pst.setString(11,perfil.replace(" ",""));
				pst.setString(12, cliente.replace(" ", ""));
				pst.setString(13, comp.replace(" ", ""));
				pst.setString(14, "");
				pst.setString(15, emp.replace(" ", ""));
				pst.setTimestamp(16, timestamp);
				pst.setString(17, userAlta.replace(" ", ""));
				pst.setString(18, "");
				pst.setString(19, "");
				pst.setString(20, "");
				pst.setString(21, "");
				pst.executeUpdate();
				Clients.showNotification("El usuario se ha registrado exitosamente.");
		        pst.close();
			    con.close();   
			}catch(Exception e){
				System.out.println("Error: "+e.getMessage().toString());
				Clients.showNotification("El usuario no se ha registrado.");
			}
		}else if(ocupacion.equals("PROVEEDOR")){
			String user = USERNAME.getValue().toString();
			String rfc = RFC_USUARIO.getValue().toString().toUpperCase();
			String nombre = NOMBRE.getValue().toString().toUpperCase();
			String cel = TEL_CEL.getValue().toString();
			String tel_oficina = TEL_OFICINA.getValue().toString();
			String email = EMAIL_USER.getValue().toString();
			String pass = a.getValue().toString();
			String passEncrip = DigestUtils.md5Hex(pass);
			String tipo = TIPO_USUARIO.getValue().toString();
			String perfil = ID_PERFIL.getValue().toString();
			String cliente = ID_CLIENTE.getValue().toString();
			String comp = ID_COMPANIA.getValue().toString();
			String proveedor = ID_OCUPACION.getValue().toString(); 
			Double limite_auto = 10.5;
			java.util.Date d = new java.util.Date(); 
			Timestamp timestamp = new Timestamp(d.getTime());
			String userAlta = user;
			 
			try{
				String query = "" + "INSERT INTO USUARIO "
						+ "(USERNAME,RFC_USUARIO,NOMBRE,TEL_CEL"
						+ ",TEL_OOFICINA,EMAIL_USER,PASSWORD_U"
						+ ",TIPO_USUARIO,ESTATUS,LIMITE_AUTORIZACION"
						+ ",ID_PERFIL,ID_CLIENTE,ID_COMPANIA"
						+ ",ID_PROVEEDOR,ID_EMPLEADO,FECHA_ALTA"
						+ ",USERNAME_ALTA,ESTATUS_BLOQ,FECHA_BLOQUEO"
						+ ",FECHA_MODF,USERNAME_MODF)" + "VALUES " + "(?"
						+ ",?" + ",?" + ",?" + ",?" + ",?" + ",?" + ",?"
						+ ",?" + ",?" + ",?" + ",?" + ",?" + ",?" + ",?"
						+ ",?" + ",?" + ",?" + ",?" + ",?" + ",?)";
			System.out.println(query);
			pst = con.prepareStatement(query);
			pst.setString(1, user.replace(" ",""));
			pst.setString(2, rfc.replace(" ",""));
			pst.setString(3, nombre);
			pst.setString(4, cel.replace(" ",""));
			pst.setString(5, tel_oficina.replace(" ",""));
			pst.setString(6,email.replace(" ",""));
			pst.setString(7, passEncrip);
			pst.setString(8, tipo.replace(" ", ""));
			pst.setString(9, "A");
			pst.setDouble(10, limite_auto);
			pst.setString(11,perfil.replace(" ",""));
			pst.setString(12, cliente.replace(" ", ""));
			pst.setString(13, comp.replace(" ", ""));
			pst.setString(14, proveedor.replace(" ",""));
			pst.setString(15, "");
			pst.setTimestamp(16, timestamp);
			pst.setString(17, userAlta.replace(" ", ""));
			pst.setString(18, "");
			pst.setString(19, "");
			pst.setString(20, "");
			pst.setString(21, "");
			pst.executeUpdate();
			Clients.showNotification("El usuario se ha registrado exitosamente.");
	        pst.close();
		}catch(Exception e){
			System.out.println("Error: "+e.getMessage().toString());
			Clients.showNotification("El usuario no se ha registrado.");
		}
		}
			
		}
	}
	
	public boolean ValidarPerfil(String perfil,String cliente,String comp){
		boolean validado = false;
		List<DatosPerfil> lista = new ConsultaCompCli().ConsultaPerfilV(perfil, cliente, comp);
		if(lista == null){
			return validado;
		}else{
			validado= true;
			return validado;
		}  
	}
	public boolean ValidarCompaniaCliente(){
		String cliente = ID_CLIENTE.getValue().toString();
		String comp = ID_COMPANIA.getValue().toString();
		boolean validado = false;
		List<DatosComp> lista = new ConsultaCompCli().ConsultaCompV(comp, cliente);
		if(lista == null){
			return validado;
		}else{
			validado = true;
			return validado;
		}
	}
	
	public boolean ValidaUsuario(String user,String cliente,String comp){
		boolean validado = false;
		List<DatosUser> r = new ConsultaUsuario().ConsultaUsuario(user.replace(" ", ""), cliente.replace(" ", ""), comp.replace(" ", ""));
			if(r.isEmpty() == true){
				validado = false;
				return validado;
			}else{
				validado = true;
				return validado;
			}
	}
	
	public boolean ValidaRFC(String rfc,String cliente,String comp){
		boolean validado = false;
		List<DatosUserRFC> r = new ConsultaUsuario().ConsultaRFC(rfc.replace(" ", ""), cliente.replace(" ", ""), comp.replace(" ", ""));
			if(r == null){
				validado = false;
				return validado;
			}else if(r != null){
				validado = true;
				return validado;
			}
		
		return validado;
	}
	
	public boolean ValidaEmail(String rfc,String cliente,String comp){
		boolean validado = false;
		List<DatosUserEmail> r = new ConsultaUsuario().ConsultaEmail(rfc, cliente.replace(" ", ""), comp.replace(" ", ""));
		
			if(r == null){
				validado = false;
				return validado;
			}else if(r != null){
				validado = true;
				return validado;
			}
	
		return validado;
	}
	
	public List<String> getdCliente(){
		return DatosCombos.getdCliente();		
	}
	public List<String> getdComp(){
		return DatosCombos.getdCompania();		
	}
	public List<String> getdCPerfil(){
		return DatosCombos.getdPerfil();		
	}


	public String getCliente() {
		return cliente;
	}


	public String getComp() {
		return comp;
	}


	public String getPerfil() {
		return perfil;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public void setComp(String comp) {
		this.comp = comp;
	}


	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	
	
}
