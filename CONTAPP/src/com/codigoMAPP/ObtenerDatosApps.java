package com.codigoMAPP;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Sessions;

public class ObtenerDatosApps {
	
	 	private static List<String> dApps = new ArrayList<String>();
		private static List<String> dNombreApps = new ArrayList<String>();
		private static List<String> dDescripApps = new ArrayList<String>();
		private static List<String> dUrl_IMG_Apps = new ArrayList<String>();
		private static List<String> dUrl_PAG_Apps = new ArrayList<String>();
	
		static{
				List<DatosAplicaciones> dA = new ConsultaAplicaciones().getConsultaAplicaciones();			
				for(int i = 0; i<dA.size(); i++){
					dApps.add(dA.get(i).getId_app());							
				}
				
				List<DatosAplicaciones> dA1 = new ConsultaAplicaciones().getConsultaAplicaciones();
				for(int i=0;i<dA1.size();i++){
					dNombreApps.add(dA1.get(i).getNombre_app());
				}
				List<DatosAplicaciones> dA2 = new ConsultaAplicaciones().getConsultaAplicaciones();
				for(int i=0;i<dA2.size();i++){
					dDescripApps.add(dA2.get(i).getDescripcion_app());
				}
				List<DatosAplicaciones> dA3 = new ConsultaAplicaciones().getConsultaAplicaciones();
				for(int i=0;i<dA3.size();i++){
					dUrl_IMG_Apps.add(dA3.get(i).getUrl_img());
				}
				List<DatosAplicaciones> dA4 = new ConsultaAplicaciones().getConsultaAplicaciones();
				for(int i=0;i<dA4.size();i++){
					dUrl_PAG_Apps.add(dA4.get(i).getUrl_pag());
				}
		}

		public static final List<String> getdApps() {
			return new ArrayList<String>(dApps);
		}

		public static final List<String> getdNombreApps() {
			return new ArrayList<String>(dNombreApps);
		}

		public static final List<String> getdDescripApps() {
			return new ArrayList<String>(dDescripApps);
		}

		public static final List<String> getdUrl_IMG_Apps() {
			return new ArrayList<String>(dUrl_IMG_Apps);
		}

		public static final List<String> getdUrl_PAG_Apps() {
			return new ArrayList<String>(dUrl_PAG_Apps);
		}

		
}
