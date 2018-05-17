package com.CodigoRU;

import java.util.List;

public class DatosUserRFC {
	private String RFC;
	private List<DatosUserRFC> datos;
	
	public DatosUserRFC(){
		
	}
	public DatosUserRFC(String rfc){
		this.RFC = rfc;
	}
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		RFC = rFC;
	}
	public List<DatosUserRFC> getDatos() {
		return datos;
	}
	public void setDatos(List<DatosUserRFC> datos) {
		this.datos = datos;
	}
	
	
}
