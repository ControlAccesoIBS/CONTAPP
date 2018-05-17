package com.CodigoRAppCli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import conexionSQL.ConexionSQL;

public class Registro_App_Cli extends SelectorComposer<Component> {
	private String cliente, comp, app;
	
	@Wire
	Textbox ESTATUS, REQ_AUTORIZACION;
	
	@Wire
	Combobox ID_CLIENTE,ID_COMPANIA,ID_APP;
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
	
	public List<String> getdApplicacion(){
		return DatosCombo.getdAplicacion();		
	}
	@Init()
	 public void init() {
		setCliente("Seleccione el cliente");
		setComp("Seleccione la compañia");
		setApp("Seleccione la aplicación");
   }
	
	@Listen("onClick=#REGISTRAR")
	public void RegistrarAplicacionCliente(){
		String id_cliente = ID_CLIENTE.getValue().toString();
		String id_compania = ID_COMPANIA.getValue().toString();
		String id_app = ID_APP.getValue().toString();
		String estatus = ESTATUS.getValue().toString();
		String req_autorizacion = REQ_AUTORIZACION.getValue().toString();
		
		if(ValidarAplicacionCliente(id_cliente, id_compania, id_app)==false){
			
				EjecutarComando(cnn); 
			}else{
				Clients.showNotification("No se pudo registrar, la relación ya existe.",
				Clients.NOTIFICATION_TYPE_WARNING,null,"middle_center", 2000);
			}	
		}
	
	private void EjecutarComando(Connection con) {
		// TODO Auto-generated method stub
		String id_cliente = ID_CLIENTE.getValue().toString().toUpperCase();
		String id_compania = ID_COMPANIA.getValue().toString().toUpperCase();
		String id_app= ID_APP.getValue().toString().toUpperCase();
		String estatus = ESTATUS.getValue().toString().toUpperCase();
		String req_autorizacion = REQ_AUTORIZACION.getValue().toString().toUpperCase();
		String userAlta = (String) Sessions.getCurrent().getAttribute("username");
		java.util.Date d = new java.util.Date(); 
		Timestamp fecha_alta = new Timestamp(d.getTime());
		
		try{
			String query = "INSERT INTO REL_CIA_APP"
					+ "(ID_CLIENTE,"
					+ "ID_COMPANIA,"
					+ "ID_APP, "
					+ "ESTATUS,"
					+ "REQ_AUTORIZACION,"
					+ "FECHA_ALTA,"
					+ "USERNAME_ALTA,"
					+ "ESTATUS_BLOQUEO,"
					+ "FECHA_BLOQUEO,"
					+ "FECHA_MODF, "
					+ "USERNAME_MODF)"
					
					+"VALUES"
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
			pst = con.prepareStatement(query);
			pst.setString(1, id_cliente);
			pst.setString(2, id_compania);
			pst.setString(3, id_app);
			pst.setString(4, estatus);
			pst.setString(5, req_autorizacion);
			pst.setTimestamp(6, fecha_alta);
			pst.setString(7, userAlta.replace(" ", ""));
			pst.setString(8, "");
			pst.setString(9, "");
			pst.setString(10, "");
			pst.setString(11, "");
			pst.executeUpdate();
			pst.close();
			con.close();
			
			Clients.showNotification("Se registro exitosamente el cliente.",
					Clients.NOTIFICATION_TYPE_INFO, null, "middle_center",
					2000);
		}catch (SQLException e){
			Clients.showNotification("No la relación ya existe.",
					Clients.NOTIFICATION_TYPE_WARNING, null,
					"middle_center", 2000);
			System.out.println(e.getMessage().toString());	
		}
	}
	
	
	private boolean ValidarAplicacionCliente(String id_cliente,
			String id_compania, String id_app) {
				boolean validado=false;
				List<DatosCliente> r=new ConsultaCli_App().ConsultaCliente();
				for(DatosCliente datosC : r){
					if(r == null){
						validado = false;
						return validado;
					}else if(r!=null){
						return validado;
					}
				}
		return validado;
		// TODO Auto-generated method stub	
	}
		
	
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getComp() {
		return comp;
	}
	public void setComp(String comp) {
		this.comp = comp;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	
	
}
	



















