package com.tree;

import java.util.List;

public class MenuOpciones {
	private int id_menu;
	private int id_padre;
	private int orden;
	private String nombre_menu;
	private String url_img;
	private String url_pag;
	private List<MenuOpciones> hijos;
	
	public MenuOpciones(){
		
	}
	
	public MenuOpciones(int id_menu,int id_padre,int orden,String nombre_menu,String url_img,String url_pag){
		this.id_menu = id_menu;
		this.id_padre = id_padre;
		this.orden = orden;
		this.nombre_menu = nombre_menu;
		this.url_img = url_img;
		this.url_pag = url_pag;
	}
	
	public int getId_menu() {
		return id_menu;
	}
	public int getId_padre() {
		return id_padre;
	}
	public int getOrden() {
		return orden;
	}
	public String getNombre_menu() {
		return nombre_menu;
	}
	public String getUrl_img() {
		return url_img;
	}
	public String getUrl_pag() {
		return url_pag;
	}
	public List<MenuOpciones> getHijos() {
		return hijos;
	}
	public void setId_menu(int id_menu) {
		this.id_menu = id_menu;
	}
	public void setId_padre(int id_padre) {
		this.id_padre = id_padre;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public void setNombre_menu(String nombre_menu) {
		this.nombre_menu = nombre_menu;
	}
	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}
	public void setUrl_pag(String url_pag) {
		this.url_pag = url_pag;
	}
	public void setHijos(List<MenuOpciones> hijos) {
		this.hijos = hijos;
	}
	
	
}
