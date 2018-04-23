package com.CodigoRP;

import java.util.List;

public class DatosPerfil {
	private String id_perfil;
	private String id_cliente;
	private String id_compania;
	private List<DatosPerfil> datos;
	
	public DatosPerfil(){
		
	}
	
	public DatosPerfil(String perfil,String cliente,String compania){
		this.id_perfil = perfil;
		this.id_cliente = cliente;
		this.id_compania = compania;
	}

	public String getId_perfil() {
		return id_perfil;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public String getId_compania() {
		return id_compania;
	}

	public void setId_perfil(String id_perfil) {
		this.id_perfil = id_perfil;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public void setId_compania(String id_compania) {
		this.id_compania = id_compania;
	}

	public List<DatosPerfil> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosPerfil> datos) {
		this.datos = datos;
	}
	
	
}
