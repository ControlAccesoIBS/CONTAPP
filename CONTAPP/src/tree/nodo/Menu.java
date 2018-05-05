package tree.nodo;

import menu.tree.MenuOpciones;
import menu.tree.ServicioMenuImpl;

import java.util.*;

import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.*;

public class Menu {

	private List<MenuOpciones> datos;
	TreeModel _model;

	public TreeModel getModel() {
		if (_model == null) {
			_model = new FooModel(getFooRoot());
		}
		return _model;
	}

	// create a FooNodes tree structure and return the root
	
	private FooNode getFooRoot() {

		datos = new ServicioMenuImpl().menuOp("CONTE");
		
		for(MenuOpciones mO: datos){
			System.out.println("Papa "+mO.getNombre_menu());
			if(mO.getHijos()!=null || !mO.getHijos().isEmpty()){
				List<MenuOpciones> hijos = mO.getHijos();
				for(MenuOpciones hijo:hijos){
					System.out.println("Hijo "+hijo.getNombre_menu());
				}
			}
		}
		System.out.println(datos.toString());
		
		
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
