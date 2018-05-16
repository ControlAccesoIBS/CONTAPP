package com.codigoMAPP;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import com.CodigoRP.DatosCombo;

public class ModeloVistaApps extends SelectorComposer<Component> {
	
	private String id_app,nombre_app,descrip_app,url_img,url_pag;

	private List<String> dUrl_PAG_A,dNombreA,dUrl_IMG_A;
	
	private List<DatosAplicaciones> datos;
	
	public int cantidad;
	
	@Wire 
	private Listbox carListBox;
	
	@Init
	public void GenerarMenuApps(){
		datos = new ConsultaAplicaciones().getConsultaAplicaciones();
		for(int i = 0;i<datos.size();i++){
			setNombre_app(datos.get(i).getNombre_app());
			setUrl_img(datos.get(i).getUrl_img());
			setUrl_pag(datos.get(i).getUrl_pag());
			setDescrip_app(datos.get(i).getDescripcion_app());
		}
		setCantidad(datos.size());
	}
	
	public List<DatosAplicaciones> getListaApps(){
		datos = new ConsultaAplicaciones().getConsultaAplicaciones();
		return datos;
	}
	

	public String getNombre_app() {
		return nombre_app;
	}

	public String getUrl_img() {
		return url_img;
	}

	public String getUrl_pag() {
		return url_pag;
	}

	public void setNombre_app(String nombre_app) {
		this.nombre_app = nombre_app;
	}

	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}

	public void setUrl_pag(String url_pag) {
		this.url_pag = url_pag;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public List<DatosAplicaciones> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosAplicaciones> datos) {
		this.datos = datos;
	}

	public Listbox getCarListBox() {
		return carListBox;
	}

	public void setCarListBox(Listbox carListBox) {
		this.carListBox = carListBox;
	}

	public String getDescrip_app() {
		return descrip_app;
	}

	public void setDescrip_app(String descrip_app) {
		this.descrip_app = descrip_app;
	}

	public void setdUrl_PAG_A(List<String> dUrl_PAG_A) {
		this.dUrl_PAG_A = dUrl_PAG_A;
	}

	public void setdNombreA(List<String> dNombreA) {
		this.dNombreA = dNombreA;
	}

	public void setdUrl_IMG_A(List<String> dUrl_IMG_A) {
		this.dUrl_IMG_A = dUrl_IMG_A;
	}

		
	
	
	
}
