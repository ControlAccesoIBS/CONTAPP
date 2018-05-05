package menu.tree;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;

public class MenuControlador extends SelectorComposer<Component> {
	/**
	 *
	 */
	private static final long serialVersionUID = -8987638549407320294L;


	ListModelList<MenuOpciones> todoListModel;
	MenuOpciones mp;

	@Wire
	Menubar menu;

	@Wire
	Menu menus;

	@Wire
	Menuitem submenu;

	@Wire
	private List<MenuOpciones> lol;

	@Wire
    private Listbox mainListbox;

	

	private ServicioMenu menuService = new ServicioMenuImpl();

	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        lol = menuService.menuOp("CONTAPP");
        mainListbox.setModel(new ListModelList<MenuOpciones>(lol));
    }
	public void init(String app){
		//Se obtienen los datos de la lista
		lol = menuService.menuOp(app);

	}
	public void ObtenerMenu(){

	}
	public void ObtenerSubMenu(){

	}
	public Menubar getMenu() {
		return menu;
	}
	public void setMenu(Menubar menu) {
		this.menu = menu;
	}
	public List<MenuOpciones> getLol() {
		return lol;
	}
	public void setLol(List<MenuOpciones> lol) {
		this.lol = lol;
	}
	
	public Listbox getMainListbox() {
		return mainListbox;
	}
	public void setMainListbox(Listbox mainListbox) {
		this.mainListbox = mainListbox;
	}




}
