package consulta.compania;

import java.util.ArrayList;
import java.util.List;

import com.CodigoRP.DatosCliente;
import com.CodigoRP.DatosPerfil;

public class DatosCombos {
	private static List<String> dCliente = new ArrayList<String>();
	private static List<String> dCompania = new ArrayList<String>();
	private static List<String> dPerfil = new ArrayList<String>();
	
	static{
		List<DatosCliente> datosCli = new ConsultaCompCli().ConsultaCliente();
		for(int i = 0; i<datosCli.size(); i++){
			dCliente.add(datosCli.get(i).getId_cliente());
		}
		List<DatosComp> datosComp = new ConsultaCompCli().ConsultaComp();
		for(int i = 0; i<datosComp.size(); i++){
			dCompania.add(datosComp.get(i).getId_compania());
		}
		List<DatosPerfil> datosPerfil = new ConsultaCompCli().ConsultaPerfil();
		for(int i = 0; i<datosPerfil.size(); i++){
			dPerfil.add(datosPerfil.get(i).getId_perfil());
		}
		
	}
	
	public static final List<String> getdPerfil() {
		return new ArrayList<String>(dPerfil);
	}
	
	public static final List<String> getdCliente() {
		return new ArrayList<String>(dCliente);
	}
	
	public static final List<String> getdCompania() {
		return new ArrayList<String>(dCompania);
	}
	
	
}
