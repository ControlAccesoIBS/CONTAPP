package com.RA;

import java.util.List;

public class DatosAplicacion {
	private String id_aplicacion;
	private List<DatosAplicacion> datos;
	
	public DatosAplicacion(String aplicacion){
		this.id_aplicacion = aplicacion;
	}

	public String getId_aplicacion() {
		return id_aplicacion;
	}

	public void setId_aplicacion(String id_aplicacion) {
		this.id_aplicacion = id_aplicacion;
	}

	public List<DatosAplicacion> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosAplicacion> datos) {
		this.datos = datos;
	}
}


