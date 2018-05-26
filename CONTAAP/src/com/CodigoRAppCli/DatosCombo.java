package com.CodigoRAppCli;

import java.util.ArrayList;
import java.util.List;

import com.CodigoCIA.DatosCliente;
import com.CodigoRAppCli.*;

import org.zkoss.zk.ui.Sessions;

public class DatosCombo {
private static List<String> dCliente = new ArrayList<String>();
private static List<String> dCompania = new ArrayList<String>();
private static List<String> dAplicacion = new ArrayList<String>();

static{
	List<DatosCliente> dc = new ConsultaCli_App().ConsultaCliente();
	for(int i = 0; i<dc.size(); i++){
		dCliente.add(dc.get(i).getId_cliente());			
	}
	
	
	List<DatosCompania> dcomp = new ConsultaCli_App().ConsultaComp();
		for(int i = 0; i<dcomp.size(); i++){
			dCompania.add(dcomp.get(i).getId_compania());			
		}
		
		
		List<DatosAplicacion> dapp = new ConsultaCli_App().ConsultaAplicacion();
			for(int i = 0; i<dapp.size(); i++){
				dAplicacion.add(dapp.get(i).getId_aplicacion());			
			}
}

public static final List<String> getdCliente() {
	return new ArrayList<String>(dCliente);
}

public static final List<String> getdCompania() {
	return new ArrayList<String>(dCompania);
}
public static final List<String> getdAplicacion() {
	return new ArrayList<String>(dAplicacion);
}
}
