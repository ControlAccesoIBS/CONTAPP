package com.CodigoRU;

import java.util.List;

public class DatosUser {
	private List<DatosUser> datos;
	private String UserName;
	
	public DatosUser(){
		
	}
	public DatosUser(String user){
		this.UserName = user;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public List<DatosUser> getDatos() {
		return datos;
	}
	public void setDatos(List<DatosUser> datos) {
		this.datos = datos;
	}
	
	
}
