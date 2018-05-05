package com.codigoMAPP;

import java.util.List;

public class DatosAplicaciones {
	private String id_app;
	private String nombre_app;
	private String descripcion_app;
	private String req_autorizacion;
	private String estatus;
	private String url_img;
	private String url_pag;
	private List<DatosAplicaciones> datos;
	
	public DatosAplicaciones(){
		
	}
	
	public DatosAplicaciones(String app,String nombre,String descrip,String req_auto,String estatus,String url_img,String url_pag){
		this.id_app = app;
		this.nombre_app = nombre;
		this.descripcion_app = descrip;
		this.req_autorizacion = req_auto;
		this.estatus = estatus;
		this.url_img = url_img;
		this.url_pag = url_pag;
	}
	
	public String getId_app() {
		return id_app;
	}
	public String getNombre_app() {
		return nombre_app;
	}
	public String getDescripcion_app() {
		return descripcion_app;
	}
	public String getReq_autorizacion() {
		return req_autorizacion;
	}
	public String getEstatus() {
		return estatus;
	}
	public String getUrl_img() {
		return url_img;
	}
	public List<DatosAplicaciones> getDatos() {
		return datos;
	}
	public void setId_app(String id_app) {
		this.id_app = id_app;
	}
	public void setNombre_app(String nombre_app) {
		this.nombre_app = nombre_app;
	}
	public void setDescripcion_app(String descripcion_app) {
		this.descripcion_app = descripcion_app;
	}
	public void setReq_autorizacion(String req_autorizacion) {
		this.req_autorizacion = req_autorizacion;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}
	public void setDatos(List<DatosAplicaciones> datos) {
		this.datos = datos;
	}

	public String getUrl_pag() {
		return url_pag;
	}

	public void setUrl_pag(String url_pag) {
		this.url_pag = url_pag;
	}
	
	
}
