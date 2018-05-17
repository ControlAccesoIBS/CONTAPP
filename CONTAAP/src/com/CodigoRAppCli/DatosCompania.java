package com.CodigoRAppCli;

import java.util.List;


public class DatosCompania {
	private String id_compania; //variable para almacenar los datos que se traigan de la base de datos
	
	private List<DatosCompania> datos;

	public DatosCompania(String compania){
		this.id_compania = compania;
	}

	public String getId_compania() {
		return id_compania;
	}


	public void setId_compania(String id_compania) {
		this.id_compania = id_compania;
	}
	public List<DatosCompania> getDatos() {
		return datos;
	}
	}
