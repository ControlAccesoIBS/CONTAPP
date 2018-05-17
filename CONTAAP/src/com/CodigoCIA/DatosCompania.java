package com.CodigoCIA;

import java.util.List;

public class DatosCompania {
	private String id_compania; //variable para almacenar los datos que se traigan de la base de datos
	private String id_cliente;
	private List<DatosCompania> datos;

	public DatosCompania(String compania, String cliente){
		this.id_compania = compania;
		this.id_cliente = cliente;
	}

	public String getId_compania() {
		return id_compania;
	}


	public void setId_compania(String id_compania) {
		this.id_compania = id_compania;
	}


	public String getId_cliente() {
		return id_cliente;
	}


	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}


	public List<DatosCompania> getDatos() {
		return datos;
	}
	}
