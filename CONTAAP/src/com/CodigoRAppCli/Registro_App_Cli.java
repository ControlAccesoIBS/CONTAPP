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
		String id_cliente = ID_CLIENTE.getValue().toString().replace(" ", "");
		String id_compania = ID_COMPANIA.getValue().toString();
		String id_app = ID_APP.getValue().toString();
		String estatus = ESTATUS.getValue().toString();
		String req_autorizacion = REQ_AUTORIZACION.getValue().toString();
		cnn = con.getConexion();
		
		
		if (ValidarAplicacionCliente2(id_cliente, id_compania) == true) {

			if (ValidarAplicacionCliente(id_cliente, id_compania, id_app) == false) {
				EjecutarComando(cnn);

			} else {
				Clients.showNotification("La aplicación ya esta vinculada al cliente",
						Clients.NOTIFICATION_TYPE_WARNING, null,
						"middle_center", 2000);

			}
		} else {
			Clients.showNotification(
					"No existe la relacion entre Cliente y Compañia.",
					Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center",
					2000);
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
					+ "ID_APP,"
					+ "ESTATUS,"
					+ "REQ_AUTORIZACION,"
					+ "FECHA_ALTA,"
					+ "USERNAME_ALTA,"
					+ "ESTATUS_BLOQUEO,"
					+ "FECHA_BLOQUEO,"
					+ "FECHA_MODF,"
					+ "USERNAME_MODF)"
					
					+"VALUES"
					+"(? "
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
			pst.setString(1, id_cliente.replace(" ",""));
			pst.setString(2, id_compania.replace(" ", ""));
			pst.setString(3, id_app.replace(" ", ""));
			pst.setString(4, estatus.replace(" ", ""));
			pst.setString(5, req_autorizacion.replace(" ",""));
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
			Clients.showNotification("No se puede hacer el registro, la relación ya existe.",
					Clients.NOTIFICATION_TYPE_WARNING, null,
					"middle_center", 2000);
			System.out.println(e.getMessage().toString());	
		}
	}
	private boolean ValidarAplicacionCliente2(String id_cliente2, String id_compania2) {
		boolean validado2=false;
		List<DatosCompania> r2 = new ConsultaCli_App().ConsultaCompC(id_compania2.replace(" ", ""), id_cliente2.replace(" ", ""));		
			if(r2.isEmpty()){				
				return validado2;
			}else {
				validado2 = true;
				return validado2;
			}
			
	}
	
	private boolean ValidarAplicacionCliente(String id_cliente,
			String id_compania, String id_app) {
				boolean validado=false;
				List<DatosCompania> r=new ConsultaCli_App().ConsultaCompCc(id_compania.replace(" ", ""), id_cliente.replace(" ", ""), id_app.replace(" ", ""));
				
					if(r.isEmpty()){
						return validado;
					}else{
						validado = true;
						return validado;
					}
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
	



















