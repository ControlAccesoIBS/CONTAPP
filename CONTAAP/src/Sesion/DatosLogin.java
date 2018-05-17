package Sesion;

import java.util.List;

public class DatosLogin {
	private String usuario;
	private String password;
	private String cliente;
	private String compania;
	private String perfil;
	private List<DatosLogin> datos;
	
	public DatosLogin(){
		
	}
	
	public List<DatosLogin> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosLogin> datos) {
		this.datos = datos;
	}

	public DatosLogin(String user,String pass,String cliente, String compania, String perfil){
		this.usuario = user;
		this.password = pass;
		this.cliente = cliente;
		this.compania = compania;
		this.perfil = perfil;
	}


	public String getUsuario() {
		return usuario;
	}
	public String getPassword() {
		return password;
	}
	public String getCliente() {
		return cliente;
	}
	public String getCompania() {
		return compania;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "DatosLogin [usuario=" + usuario + ", password=" + password
				+ ", cliente=" + cliente + ", compania=" + compania
				+ ", perfil=" + perfil + ", datos=" + datos + "]";
	}
	
}
