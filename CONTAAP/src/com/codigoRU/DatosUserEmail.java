package com.CodigoRU;

import java.util.List;

public class DatosUserEmail {
	private String Email;
	private List<DatosUserEmail> datos;
	
	public DatosUserEmail(){
		
	}
	public DatosUserEmail(String email){
		this.Email = email;
	}
	public String getEmail() {
		return Email;
	}
	public List<DatosUserEmail> getDatos() {
		return datos;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public void setDatos(List<DatosUserEmail> datos) {
		this.datos = datos;
	}
	
	
}
