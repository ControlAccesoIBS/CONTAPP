package com.CodigoRP;

import java.util.Date;

import org.zkoss.zk.ui.Sessions;

public class DatosRP {
	private String id_perfil;
	private String descripcion;
	private String id_cliente;
	private String id_compania;
	private String estatus;
	private String userAlta; 
	private Date fechaAlta;
	private double limite;
	
	public DatosRP(){
		
	}
	public DatosRP(String perfil, String descripcion, String id_cliente, String compania, String estatus,double limite, String userAlta, Date fechaA){
		this.id_perfil = perfil;
		this.descripcion = descripcion;
		this.id_cliente = id_cliente;
		this.id_compania = compania;
		this.estatus = estatus;
		this.userAlta = userAlta;
		this.fechaAlta=fechaA;
		this.limite = limite;
	}
	
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
	public String getId_perfil() {
		return id_perfil;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public String getId_compania() {
		return id_compania;
	}
	public String getEstatus() {
		return estatus;
	}
	public String getUserAlta() {
		return userAlta;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setId_perfil(String id_perfil) {
		this.id_perfil = id_perfil;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public void setId_compania(String id_compania) {
		this.id_compania = id_compania;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public void setUserAlta(String userAlta) {
		this.userAlta = userAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	
}
