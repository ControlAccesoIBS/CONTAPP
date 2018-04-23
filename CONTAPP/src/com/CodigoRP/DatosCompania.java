package com.CodigoRP;

import java.util.List;

public class DatosCompania {
	
	private List<DatosCompania> datos;
	private String id_compania;
	
	public DatosCompania(){
		
	}
	
	public DatosCompania(String idcompania){
		this.id_compania = idcompania;
	}

	public List<DatosCompania> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosCompania> datos) {
		this.datos = datos;
	}

	public String getId_compania() {
		return id_compania;
	}

	public void setId_compania(String id_compania) {
		this.id_compania = id_compania;
	}
	
	
}
