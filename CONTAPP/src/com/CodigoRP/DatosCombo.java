package com.CodigoRP;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Sessions;


public class DatosCombo{

	private static List<String> dCliente = new ArrayList<String>();
	private static List<String> dCompania = new ArrayList<String>();
	
	
	
	static{
		String cli = (String) Sessions.getCurrent().getAttribute("id_cliente");
		List<DatosCliente> dc = new ConsultaCliComp().ConsultaCliente(cli);
		
			for(int i = 0; i<dc.size(); i++){
				dCliente.add(dc.get(i).getId_cliente());			
			}
		String comp = (String) Sessions.getCurrent().getAttribute("id_compania");
		List<DatosCliente> dcia = new ConsultaCliComp().ConsultaCliente(comp);
			for(int i = 0; i<dc.size(); i++){
				dCompania.add(dc.get(i).getId_cliente());			
			}
		}
		
	
	
	public static final List<String> getdCliente() {
		return new ArrayList<String>(dCliente);
	}
	
	public static final List<String> getdCompania() {
		return new ArrayList<String>(dCompania);
	}
	
}
