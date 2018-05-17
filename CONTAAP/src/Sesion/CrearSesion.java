package Sesion;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Initiator;

public class CrearSesion implements Initiator{
	public void DoingSesion(String user,String cliente, String compania, String perfil){
		Sessions.getCurrent().setAttribute("username", user); // save user-object in session
		Sessions.getCurrent().setAttribute("id_cliente", cliente); // save user-object in session
		Sessions.getCurrent().setAttribute("id_compania", compania); // save user-object in session
		Sessions.getCurrent().setAttribute("id_perfil", perfil); // save user-object in session
		
		Sessions.getCurrent().setMaxInactiveInterval(600);
	}
	 public void checkSession(){
	        boolean valid = Sessions.getCurrent().getAttribute("username") != null ? true : false;
	        if (!valid){
	        	Clients.showNotification("Debes de iniciar sesión antes de poder ingresar");
	            Executions.sendRedirect("login1.zul");
	       }
	  }
		@Override
		public void doInit(Page page, Map<String, Object> args) throws Exception {
			// TODO Auto-generated method stub
			boolean valid = Sessions.getCurrent().getAttribute("username") != null ? true : false;
	        if (!valid){
	        	Clients.showNotification("Debes de iniciar sesión antes de poder ingresar");
	            Executions.sendRedirect("login1.zul");
	            return;
	       }
		}

}
