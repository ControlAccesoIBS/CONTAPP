package consulta.compania;

import java.util.List;

public class DatosComp {
	private String id_compania;
	private String id_cliente;
	private List<DatosComp> datos;
	
	public DatosComp(){
		
	}
	
	public DatosComp(String id_comp, String id_clien){
		this.id_compania = id_comp;
		this.id_cliente = id_clien;
	}

	public String getId_compania() {
		return id_compania;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public List<DatosComp> getDatos() {
		return datos;
	}

	public void setId_compania(String id_compania) {
		this.id_compania = id_compania;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public void setDatos(List<DatosComp> datos) {
		this.datos = datos;
	}
	
	
}
