package com.CodigoRP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Textbox;

import Sesion.CrearSesion;
import Sesion.DatosLogin;
import Sesion.QueryUserLogin;
import conexionSQL.ConexionSQL;

public class RegistrarPerfil extends SelectorComposer<Component>{
	private String cliente, comp;
	@Wire
	Textbox ID_PERFIL,DESCRIPCION_PERFIL;
	@Wire
	Combobox ID_CLIENTE,ID_COMPANIA,ESTATUS;
	@Wire
	Button REGISTRAR;
	
	private Connection cnn;
	private PreparedStatement pst;
	private ResultSet rst;
	private ConexionSQL con = new ConexionSQL();

	public List<String> getdCliente(){
		return DatosCombo.getdCliente();		
	}
	public List<String> getdCompania(){
		return DatosCombo.getdCompania();		
	}
	
	@Init()
	 public void init() {
		setCliente("Seleccione el cliente");
		setComp("Seleccione la compañia");
    }
	
	@Listen("onClick=#REGISTRAR")//# Y la ID DEL PORTAL
	public void RegistrarPerfil(){
		String id_perfil = ID_PERFIL.getValue().toString();
		String descripcion = DESCRIPCION_PERFIL.getValue().toString();
		String id_cliente = ID_CLIENTE.getValue().toString();
		String id_compania = ID_COMPANIA.getValue().toString();
		String estatus = ESTATUS.getValue().toString();
		double limite_defecto = 10.5;
		String userAlta = (String) Sessions.getCurrent().getAttribute("username");
		java.util.Date d = new java.util.Date(); 
		Timestamp timestamp = new Timestamp(d.getTime());
		
		System.out.println(timestamp.toString());
		
		
		cnn = con.getConexion();
		if(ValidaPerfil(id_perfil,id_cliente.replace(" ",""),id_compania.replace(" ",""))== false){
			try{
				EjecutarComando(cnn,id_perfil,descripcion,estatus,limite_defecto,id_cliente.replace(" ",""),id_compania.replace(" ",""),userAlta.replace(" ",""),timestamp); //PARA QUITAR LOS ESPACIOS EN LOS COMBOS
				Clients.showNotification("Se registro exitosamente el perfil",Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 2000);
			}catch(Exception e){
				Clients.showNotification("No se pudo registrar el perfil",Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 2000);
				System.out.println(e.getMessage().toString());
			}
		}else{
			Clients.showNotification("El perfil ya existe",Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 2000);
		}
		
	
	}
	
	private void EjecutarComando(Connection con, String perfil, String desc, String estatus, Double limite, String id_cliente, String id_compania, String userAlta,Timestamp dt){
		String query = "INSERT INTO PERFIL "
           +"(ID_PERFIL,DESCRIPCION_PERFIL,ESTATUS,ID_CLIENTE,ID_COMPANIA"
           +",FECHA_ALTA,USERNAME_ALTA,FECHA_BLOQUEO,ESTATUS_BLOQUEO,FECHA_MODF,USERNAME_MODF) "
           +"VALUES "
           +"(?"
           +",?"
           +",?"
           +",?"
           +",?"
           +",?"
           +",?"
           +",?"
           +",?"
           +",?"
           +",?)";
			System.out.println(query);
		try {
			pst = con.prepareStatement(query);
			
			pst.setString(1, perfil);
			pst.setString(2, desc);
			pst.setString(3, estatus);
			pst.setString(4, id_cliente);
			pst.setString(5, id_compania);
			pst.setTimestamp(6, dt);
			pst.setString(7, userAlta);
			pst.setString(8, "");
			pst.setString(9, "1");
			pst.setString(10, "");
			pst.setString(11, "");	
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage().toString());
		}
	}
	
	
	 public boolean ValidaPerfil(String perfil,String cliente,String comp){
		boolean validado = false;
		List<DatosPerfil> r =  new ConsultaCliComp().ConsultaPerfil(perfil,cliente,comp);
		for(DatosPerfil datosP : r){
			if(r == null){
				validado = false;
				return validado;
			}else if(r != null){
				validado = true;
				return validado;
			}
		}
		return validado;
	}
	 


	public String getCliente() {
		return cliente;
	}

	public String getComp() {
		return comp;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}
	

}