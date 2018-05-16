package com.tree;


import java.util.*;

import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

public class Menu extends SelectorComposer<Component>{

	private List<MenuOpciones> datos;
	TreeModel _model;

	public TreeModel getModel() {
		if (_model == null) {
			_model = new FooModel(getFooRoot());
		}
		return _model;
	}
	@Wire
	private Div items;
	@Listen("onClick=#BotonMenu")
	public void MostarMenu(){
		items.setVisible(true);
	}
	

	// create a FooNodes tree structure and return the root
	
	private FooNode getFooRoot() {

		datos = new ConsultaMenuOpciones().menuOp("CONTE");
		
		
			int posicion = 0;
			int[] cabecera = new int[datos.size()];
			int cont = 0;
			FooNode primerNivelMenu;
			FooNode segundoNivelMenu;
			FooNode tercerNivelMenu;
			FooNode cuartoNivelMenu;
			FooNode quintoNivelMenu;
			FooNode root = new FooNode(null, 0, "");
			
				for(int i = 0; i<datos.size(); i++){
					
						for(MenuOpciones op : datos){
							primerNivelMenu = new FooNode(root,i,op.getNombre_menu());
							if(op.getHijos()!=null || !op.getHijos().isEmpty()){
								List<MenuOpciones> hijos = op.getHijos();
								for(MenuOpciones hijo:hijos){
									segundoNivelMenu = new FooNode(root,i,hijo.getNombre_menu());
									primerNivelMenu.appendChild(segundoNivelMenu);
									if(hijo.getHijos()!=null || !hijo.getHijos().isEmpty()){
										List<MenuOpciones> nieto = op.getHijos();
										for(MenuOpciones nietos : nieto){
											tercerNivelMenu = new FooNode(root,i,nietos.getNombre_menu());
											segundoNivelMenu.appendChild(tercerNivelMenu);
										}
									}
								}
							}
							root.appendChild(primerNivelMenu);					
					}
					return root;
				}		
			return null;	

	}
}
