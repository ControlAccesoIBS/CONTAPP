package menu.tree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionSQL.ConexionSQL;;

public class ServicioMenuImpl implements ServicioMenu {
	
	private PreparedStatement pst;
	private ResultSet rst;
	private ConexionSQL con = new ConexionSQL();

	@Override
	public List<MenuOpciones> menuOp(String app) {
		List<MenuOpciones> lista = menuOrder(app,0);
		return ordenaNiveles(app,lista);
			
	}
	
	private List<MenuOpciones> ordenaNiveles(String app,List<MenuOpciones> list){
		for(MenuOpciones m: list){
			List<MenuOpciones> listHijo = menuOrder(app,m.getId_menu());	
			if(listHijo!=null)
				m.setHijos(ordenaNiveles(app,listHijo));
		}
		return list;
	}
	
	public List<MenuOpciones> menuOrder(String app,int nivel) {
		Connection conn = con.getConexion();
		try{
			String query = "SELECT ID_OPCION_MENU,NIVEL,ORDEN,NOMBRE_MENU,URL_IMG,URL_PAG FROM MENU WHERE ID_APP ='"+app+"' and NIVEL ='"+nivel+"' ";
	        ArrayList<MenuOpciones> almacenes = new ArrayList<MenuOpciones>();
	        pst = conn.prepareStatement(query);
			rst = pst.executeQuery();
			while (rst.next()) {
				MenuOpciones almacenesCon = cursorToContact(rst);
	            almacenes.add(almacenesCon);
			}
	        rst.close();
	        return almacenes;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	 private MenuOpciones cursorToContact(ResultSet cursor) throws SQLException {
		 MenuOpciones alma1 = new MenuOpciones(
				    cursor.getInt(1),    
				 	cursor.getInt(2),
	                cursor.getInt(3),
	                cursor.getString(4),
	                cursor.getString(5),
	                cursor.getString(6)
	                );
	        //alma1.getHijo((cursor.getInt(1));

	        return alma1;
	    }

}
