package Sesion;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

public class ValidarSesion extends SelectorComposer<Component> {
	@Wire
	Textbox USERNAME;
	
	@Wire
	Textbox ID_COMPANIA;
	
	@Wire
	Textbox PASSWORD_U;
	
	@Wire
	Button INICIO_SESION;
	
	
	List<DatosLogin> resultado;
	QueryUserLogin consultaUser;
	private String caracter_email = "@";

	
	@Listen("onClick=#INICIO_SESION")
	
	public void IniciarSesion(){
		//Executions.sendRedirect("#");
		String usuario = USERNAME.getValue().toString();
		String compania = ID_COMPANIA.getValue().toString();
		String password = PASSWORD_U.getValue().toString();
		
		if(ValidaUsuario(usuario,compania) == true){
			if(ValidarPasswordUser(usuario,compania,password) == true){							
				Executions.sendRedirect("registro_perfil.zul");
			}else{
				Clients.showNotification("La contraseña es invalida.");
				Executions.sendRedirect("#");
			}
		}else{
			Clients.showNotification("El usuario o la compañia no están vinculados.");
			Executions.sendRedirect("#");
		}
		
	}
	
	
	public boolean ValidaUsuario(String user,String comp){
		boolean validado = false;
		List<DatosLogin> r = new QueryUserLogin().ConsultaUser(user, comp);
		for(DatosLogin datosL : r){
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
	
	public boolean ValidarPasswordUser(String user,String comp,String pass){
		boolean validado = false;
		List<DatosLogin> r = new QueryUserLogin().ConsultaUser(user, comp);
		ArrayList<DatosLogin> dl = new ArrayList<DatosLogin>();
		String passEncrip = DigestUtils.md5Hex(pass);
		System.out.println(passEncrip);
		for(DatosLogin datosL : r){
				String datosbase = datosL.getPassword();
				if(passEncrip.equals(datosbase)){
					Clients.showNotification("Bienvenido "+datosL.getUsuario());
					//Se extraen los datos para hacer la sesion
					String userS = datosL.getUsuario();
					String clienteS = datosL.getCliente();
					String companiaS = datosL.getCompania();
					String perfilS = datosL.getPerfil();
					//Se instancia la clase para crear la sesion
					CrearSesion cs = new CrearSesion();
					//Se mandan los datos para guardarlos en la sesion
					cs.DoingSesion(userS, clienteS, companiaS, perfilS);
					validado = true;
					return validado;
				}else{
					validado = false;
					return validado;
				}
		}
								
		return false;
	}

}
