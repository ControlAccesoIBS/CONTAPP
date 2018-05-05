package com.codigoMAPP;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;

import com.CodigoRP.DatosCombo;

public class ModeloVistaApps extends SelectorComposer<Component> {
	
	private String id_app,nombre_app,descrip_app,url_img,url_pag;

	private List<String> dUrl_PAG_A,dNombreA,dUrl_IMG_A;
	
	
	@Init
	public void GenerarMenuApps(){
		setNombre_app("Nombre App");
		url_img="height:30vh; "+
				"background-image:url(img/launcher.png); "+
			    "background-position:center; "+
				"background-repeat:no-repeat; "+
			    "background-size:cover; ";
		setUrl_pag("#");
		dUrl_PAG_A = ObtenerDatosApps.getdUrl_PAG_Apps();
		dNombreA = ObtenerDatosApps.getdNombreApps();
		dUrl_IMG_A = ObtenerDatosApps.getdUrl_IMG_Apps();
	}
	
	public List<String> getdApps(){
		return ObtenerDatosApps.getdApps();
	}
	public List<String> getdDescripA(){
		return ObtenerDatosApps.getdDescripApps();
	}
	public List<String> getdNombreA(){
		return dNombreA;
	}
	public List<String> getdUrl_IMG_A(){
		return dUrl_IMG_A;
	}
	public List<String> getdUrl_PAG_A(){
		return dUrl_PAG_A;
	}

	public String getId_app() {	
		return id_app;
	}

	public String getNombre_app() {
		return nombre_app;
	}

	public String getDescrip_app() {
		return descrip_app;
	}


	public String getUrl_img() {
		return url_img;
	}

	public String getUrl_pag() {
		return url_pag;
	}

	public void setId_app(String id_app) {
		this.id_app = id_app;
	}

	public void setNombre_app(String nombre_app) {
		this.nombre_app = nombre_app;
	}

	public void setDescrip_app(String descrip_app) {
		this.descrip_app = descrip_app;
	}


	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}

	public void setUrl_pag(String url_pag) {
		this.url_pag = url_pag;
	}

	
	
	
	
}
