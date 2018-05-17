package com.CodigoCLI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import conexionSQL.ConexionSQL;

public class RegistrarCliente extends SelectorComposer<Component>{
private String cliente;
@Wire
Textbox ID_CLIENTE, NOMBRE_CLI,TEL_CEL, TEL_OFICINA, EMAIL;
 
@Wire
Combobox ESTATUS;

@Wire
Button REGISTRAR;
 private Connection cnn;
 private PreparedStatement pst;
 private ResultSet rst;
 private ConexionSQL con = new ConexionSQL();
 private int caracter_long_tel = 15;
 
 @Init()
 public void init() {
	setCliente("Seleccione el cliente");
}
 
@Listen("onClick=#REGISTRAR")
public void RegistrarCliente(){
	String id_cliente = ID_CLIENTE.getValue().toString();
	String nombre_cli = NOMBRE_CLI.getValue().toString();
	String tel_cel = TEL_CEL.getValue().toString();
	String tel_oficina = TEL_OFICINA.getValue().toString();
	String email = EMAIL.getValue().toString();
	String estatus = ESTATUS.getValue().toString();
	String userAlta = (String) Sessions.getCurrent().getAttribute("username");
	java.util.Date d = new java.util.Date(); 
	Timestamp fecha_alta = new Timestamp(d.getTime());

	cnn = con.getConexion();
	
	if(NOMBRE_CLI.getValue().toString().length()<3){
		Clients.showNotification("El campo NOMBRE debe contener más caracteres.");
		Executions.sendRedirect("#");	
	}
	else if(TEL_CEL.getValue().toString().length()<caracter_long_tel){
		Clients.showNotification("El campo TEL. CÉLULAR debe contener" +caracter_long_tel+ ".");
	}
	else if(TEL_OFICINA.getValue().toString().length()<caracter_long_tel){
		Clients.showNotification("El campo TEL. OFICINA debe contener" +caracter_long_tel+ ".");
	}
	else if(ValidarCliente(id_cliente)==false){
		EjecutarComando(cnn);
	} else{
		Clients.showNotification("El Cliente ya existe",
				Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 2000);
	}
	}


private void EjecutarComando(Connection con ) {
	// TODO Auto-generated method stub
	String id_cliente = ID_CLIENTE.getValue().toString().toUpperCase();
	String nombre_cli = NOMBRE_CLI.getValue().toString().toUpperCase();
	String tel_cel = TEL_CEL.getValue().toString().toUpperCase();
	String tel_oficina = TEL_OFICINA.getValue().toString().toUpperCase();
	String email = EMAIL.getValue().toString().toUpperCase();
	String estatus= ESTATUS.getValue().toString().toUpperCase();
	String userAlta = (String) Sessions.getCurrent().getAttribute("username");
	java.util.Date dt = new java.util.Date();
	Timestamp fecha_alta = new Timestamp(dt.getTime());
	
	try{
		String query = "INSERT INTO CLIENTE"
				+ "(ID_CLIENTE,"
				+ "NOMBRE_CLI,"
				+ "TEL_CEL, "
				+ "TEL_OFI,"
				+ "EMAIL,"
				+ "ESTATUS,"
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
			     +",?"
			     +",?)";
		pst = con.prepareStatement(query);
		pst.setString(1, id_cliente);
		pst.setString(2, nombre_cli);
		pst.setString(3, tel_cel);
		pst.setString(4, tel_oficina);
		pst.setString(5, email);
		pst.setString(6, estatus);
		pst.setTimestamp(7, fecha_alta);
		pst.setString(8, userAlta.replace(" ", ""));
		pst.setString(9, "");
		pst.setString(10, "");
		pst.setString(11, "");
		pst.setString(12, "");
		pst.executeUpdate();
		pst.close();
		con.close();
		Clients.showNotification("Se registro exitosamente el cliente.",
				Clients.NOTIFICATION_TYPE_INFO, null, "middle_center",
				2000);
	}catch (SQLException e){
		Clients.showNotification("No se pudo registrar la compañia",
				Clients.NOTIFICATION_TYPE_WARNING, null,
				"middle_center", 2000);
		System.out.println(e.getMessage().toString());	
	}
}

private boolean ValidarCliente(String cliente) {
	boolean validado=false;
	List<DatosCliente> r = new ConsultaCli().ConsultaCliente(cliente);
	for(DatosCliente datosC : r){
		if(r == null){
			validado = false;
			return validado;
		}else if(r!=null){
			return validado;
		}
	}
	// TODO Auto-generated method stub
	return validado;
}

public String getCliente() {
	return cliente;
}

public void setCliente(String cliente) {
	this.cliente = cliente;
}

}










