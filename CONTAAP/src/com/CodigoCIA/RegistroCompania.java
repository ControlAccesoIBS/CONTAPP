package com.CodigoCIA;
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
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import conexionSQL.ConexionSQL;
public class RegistroCompania extends SelectorComposer<Component>{
	private String cliente;
	@Wire
	Textbox ID_COMPANIA, RFC_COMPANIA, NOMBRE_COMP, TEL_OFICINA,
			EMAIL_COMPANIA, PASW_EMAIL_COMPANIA,PASW_EMAIL_COMPANIA_C, CALLE, NO_INT, NO_EXT,
			COLONIA, MUNICIPIO, ESTADO, COD_POSTAL, DESCRIPCION_COMP, AMBIENTE,
			NOMBRE_CONTACTO, EMAIL_CONTACTO, TEL_CONTACTO;
	@Wire
	Combobox ID_CLIENTE, REQ_AUTORIZACION, ESTATUS, ID_PROVE_GENERICO,
			ID_PROVE_VIATICOS, ID_CONCEPTO_CONT;
	@Wire
	Button REGISTRAR;

	private int caracter_long=4; //el password debe ser mayor a 6 caracteres
	private int caracter_longitud_max=50;
	private String caracter_invalido= " ";
	private int caracter_longitud_rfc =13;
	private Connection cnn;
	private PreparedStatement pst;
	private ResultSet rst;
	private ConexionSQL con = new ConexionSQL();

	public List<String> getdCliente() {
		return DatosCombo.getdCliente();// metodo get que llama a los datos del									// combo
	}

	@Init()
	public void init() {
		setCliente("Seleccione el cliente");
	}
	
	@Listen("onClick=#LIMPIAR")
	public void Limpiar(){
		ID_COMPANIA.setValue(" ");
		RFC_COMPANIA.setValue(" ");
		NOMBRE_COMP.setValue(" ");
		TEL_OFICINA.setValue(" ");
		EMAIL_COMPANIA.setValue(" ");
		CALLE.setValue(" ");
		NO_INT.setValue(" ");
		NO_EXT.setValue(" ");
		COLONIA.setValue(" ");
		MUNICIPIO.setValue(" ");
		ESTADO.setValue(" ");
		COD_POSTAL.setValue(" ");
		DESCRIPCION_COMP.setValue(" ");
		AMBIENTE.setValue(" ");
		PASW_EMAIL_COMPANIA.setValue(" ");
		ID_CLIENTE.setSelectedIndex(0);
		PASW_EMAIL_COMPANIA.setValue(" ");
		REQ_AUTORIZACION.setSelectedIndex(0);
		ESTATUS.setSelectedIndex(0);
		ID_PROVE_GENERICO.setSelectedIndex(0);
		ID_PROVE_VIATICOS.setSelectedIndex(0);
		ID_CONCEPTO_CONT.setSelectedIndex(0);
		NOMBRE_CONTACTO.setValue(" ");
		EMAIL_CONTACTO.setValue(" "); 
		TEL_CONTACTO.setValue(" ");
	
	}

	@Listen("onClick=#REGISTRAR")
	public void RegistrarCompania() {
		
		String id_compania = ID_COMPANIA.getValue().toString();
		String rfc_compania = RFC_COMPANIA.getValue().toString();
		String id_cliente = ID_CLIENTE.getValue().toString();
		cnn = con.getConexion();
		
		if(RFC_COMPANIA.getValue().replace(" ", "").length()< caracter_longitud_rfc){
			Clients.showNotification("El campo de RFC debe contener" +caracter_longitud_rfc+ "caracteres.");
			Executions.sendRedirect("#");	
		}
		
		else if(NOMBRE_COMP.getValue().length()<caracter_long){
			Clients.showNotification("El campo de NOMBRE debe contener más de " +caracter_long+ "caracteres.");
			Executions.sendRedirect("#");	
		}
		
		else if(TEL_OFICINA.getValue().length()<7){
			Clients.showNotification("El campo de TELEFONO debe contener más de " +7+ "caracteres.");
			Executions.sendRedirect("#");
		}
		
		else if(EMAIL_COMPANIA.getValue().length()<caracter_long){
			Clients.showNotification("El campo de NOMBRE debe contener más de " +caracter_long+ "caracteres.");
			Executions.sendRedirect("#");
		}
		
		else if(PASW_EMAIL_COMPANIA.getValue().length()<caracter_long){
			Clients.showNotification("El campo de PASSWORD debe contener más de " +caracter_long+ "caracteres.");
			Executions.sendRedirect("#");
		}
		else if (PASW_EMAIL_COMPANIA.getValue().equals(PASW_EMAIL_COMPANIA_C.getValue()) == false) {
			Clients.showNotification("Las contraseñas ingresadas no coinciden");
			// Clients.showNotification("Las contraseñas ingresadas no coinciden");
			Executions.sendRedirect("#");
		}
		else if(COLONIA.getValue().length()<caracter_long){
			Clients.showNotification("El campo de COLONIA debe contener más de " +caracter_long+ "caracteres.");
			Executions.sendRedirect("#");
		}
		else if(MUNICIPIO.getValue().length()<caracter_long){
			Clients.showNotification("El campo de MUNICIPIO debe contener más de " +caracter_long+ "caracteres.");
			Executions.sendRedirect("#");
		}
		else if(ESTADO.getValue().length()<caracter_long){
			Clients.showNotification("El campo de ESTADO debe contener más de " +caracter_long+ "caracteres.");
			Executions.sendRedirect("#");
		}
		else if(COD_POSTAL.getValue().length()<3){
			Clients.showNotification("El campo de CODIGO POSTAL debe contener más de " +3+ "caracteres.");
			Executions.sendRedirect("#");
		}
		else if(NOMBRE_CONTACTO.getValue().length()<caracter_long){
			Clients.showNotification("El campo de NOMBRE_CONTACTO debe contener más de " +caracter_long+ "caracteres.");
			Executions.sendRedirect("#");
		}
		else if(EMAIL_CONTACTO.getValue().length()<caracter_long){
			Clients.showNotification("El campo de NOMBRE debe contener más de " +caracter_long+ "caracteres.");
			Executions.sendRedirect("#");
		}
		else if(TEL_CONTACTO.getValue().length()<7){
			Clients.showNotification("El campo de TELEFONO debe contener más de " +7+ "caracteres.");
			Executions.sendRedirect("#");
		}
		else if (ValidarCompania(id_compania,rfc_compania,id_cliente.replace(" ", "")) == false) {		
				EjecutarComando(cnn);				
		} else {
			Clients.showNotification("La compañia ya existe",
					Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center",
					2000);
		}
		
	}

	private void EjecutarComando(Connection con) {
		
			
		String id_compania = ID_COMPANIA.getValue().toString().toUpperCase();
		String rfc_compania = RFC_COMPANIA.getValue().toString().toUpperCase();
		String nombre_comp = NOMBRE_COMP.getValue().toString().toUpperCase();
		String id_cliente = ID_CLIENTE.getValue().toString().toUpperCase();
		String tel_oficina = TEL_OFICINA.getValue().toString();
		String email_compania = EMAIL_COMPANIA.getValue().toString();
		String pasw_email_compania = PASW_EMAIL_COMPANIA.getValue().toString();
		String calle = CALLE.getValue().toString();
		String no_int = NO_INT.getValue().toString();
		String no_ext = NO_EXT.getValue().toString();
		String colonia = COLONIA.getValue().toString();
		String municipio = MUNICIPIO.getValue().toString();
		String estado = ESTADO.getValue().toString();
		String cod_postal = COD_POSTAL.getValue().toString();
		String descripcion_comp = DESCRIPCION_COMP.getValue().toString();
		String ambiente = AMBIENTE.getValue().toString();
		String req_autorizacion = REQ_AUTORIZACION.getValue().toString();
		String estatus = ESTATUS.getValue().toString();
		String id_prove_generico = ID_PROVE_GENERICO.getValue().toString();
		String id_prove_viaticos = ID_PROVE_VIATICOS.getValue().toString();
		String id_concepto_cont = ID_CONCEPTO_CONT.getValue().toString();
		String nombre_contacto = NOMBRE_CONTACTO.getValue().toString();
		String email_contacto = EMAIL_CONTACTO.getValue().toString();
		String tel_contacto = TEL_CONTACTO.getValue().toString();
		String userAlta = (String) Sessions.getCurrent().getAttribute("username");
		java.util.Date dt = new java.util.Date();
		Timestamp timestamp = new Timestamp(dt.getTime());
		
		// validación para que los campos sean minimo de 6 caracteres
		
		
		
		
		try{
		// TODO Auto-generated method stub
		String query = "INSERT INTO COMPANIA"
		           +" (ID_COMPANIA,RFC_COMPANIA,NOMBRE_COMP,ID_CLIENTE,TEL_OFICINA,"
		           +"EMAIL_COMPANIA,PASW_EMAIL_COMPANIA,CALLE,NO_INT,NO_EXT,COLONIA,"
		           +"MUNICIPIO,ESTADO,COD_POSTAL,DESCRIPCION_COMP,AMBIENTE,REQ_AUTORIZACION,"
		           +"ESTATUS,ID_PROV_GENERICO,ID_PROV_VIATICOS,ID_CONCEPTO_CONT,NOMBRE_CONTACTO,"
		           +"EMAIL_CONTATO,TEL_CONTACTO,FECHA_ALTA,USERNAME_ALTA,ESTATUS_BLOQUEO,"
		           +"FECHA_BLOQUEO,FECHA_MODF,USERNAME_MODF)"
		     +" VALUES "
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
	pst.setString(1, id_compania);
	pst.setString(2, rfc_compania);
	pst.setString(3, nombre_comp);
	pst.setString(4, id_cliente);
	pst.setString(5, tel_oficina);
	pst.setString(6, email_compania);
	pst.setString(7, pasw_email_compania);
	pst.setString(8, calle);
	pst.setString(9, no_int);
	pst.setString(10, no_ext);
	pst.setString(11, colonia);
	pst.setString(12, municipio);
	pst.setString(13, estado);
	pst.setString(14, cod_postal);
	pst.setString(15, descripcion_comp);
	pst.setString(16, ambiente);
	pst.setString(17, req_autorizacion);
	pst.setString(18, estatus);
	pst.setString(19, id_prove_generico);
	pst.setString(20, id_prove_viaticos);
	pst.setString(21, id_concepto_cont);
	pst.setString(22, nombre_contacto);
	pst.setString(23, email_contacto);
	pst.setString(24, tel_contacto);
	pst.setTimestamp(25, timestamp);
	pst.setString(26, userAlta.replace(" ", ""));
	pst.setString(27, "");
	pst.setString(28, "");
	pst.setString(29, "");
	pst.setString(30, "");
	pst.executeUpdate();
	pst.close();
	con.close();
	
Clients.showNotification("Se registro exitosamente la compañia",
			Clients.NOTIFICATION_TYPE_INFO, null, "middle_center",
			2000);
	
	
	}catch (SQLException e) {
		Clients.showNotification("No se pudo registrar la compañia",
				Clients.NOTIFICATION_TYPE_WARNING, null,
				"middle_center", 2000);
		System.out.println(e.getMessage().toString());
	}
		

}
	

	private boolean ValidarCompania(String id_compania2,String rfc, String cliente) {
		// TODO Auto-generated method stub
		boolean validado = false;
		List<DatosCompania> r = new ConsultaIDCliente().ConsultaCompania(id_compania2, rfc, cliente);
		
			if(r.isEmpty()){
				validado = false;
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
}
