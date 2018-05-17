package com.CodigoCLI;

import java.util.List;

public class DatosCliente {
private String id_cliente;
private List<DatosCliente> datos;

public DatosCliente(){
	
}
public DatosCliente(String cliente){
	this.id_cliente = cliente;
}
public String getId_cliente() {
	return id_cliente;
}
public void setId_cliente(String id_cliente) {
	this.id_cliente = id_cliente;
}
public List<DatosCliente> getDatos() {
	return datos;
}
public void setDatos(List<DatosCliente> datos) {
	this.datos = datos;
}

}
