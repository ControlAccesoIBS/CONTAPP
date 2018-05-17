package com.CodigoCIA;

import java.util.ArrayList;
import java.util.List;

public class DatosCombo {
	private static List<String> dCliente = new ArrayList<String>();
	
	static{
		
		List<DatosCliente> dc = new ConsultaIDCliente().ConsultaCliente();
		
			for(int i = 0; i<dc.size(); i++){
				dCliente.add(dc.get(i).getId_cliente());			
			}
		}
	public static final List<String> getdCliente() {
		return new ArrayList<String>(dCliente);
	}
	
}

