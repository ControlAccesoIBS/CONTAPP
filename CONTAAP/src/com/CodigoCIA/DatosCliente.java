package com.CodigoCIA;

import java.util.List;

public class DatosCliente {
	private List<DatosCliente> datos;
	private String id_cliente;
	
	public DatosCliente(){
		
	}
	
	public DatosCliente(String idcliente){
		this.id_cliente = idcliente;
	}
	
	public List<DatosCliente> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosCliente> datos) {
		this.datos = datos;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}	
}