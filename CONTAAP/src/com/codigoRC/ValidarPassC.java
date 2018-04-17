package com.codigoRC;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;

public class ValidarPassC extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox NUEVA_PASSW;

	@Wire
	private Textbox CONFIRMAR_PASSW;

	private int caracter_longitud = 6;
	private String caracter_invalido = " ";

	@Listen("onClick=#ENVIAR")
	public void validarPassw() {		
		Validar(NUEVA_PASSW,CONFIRMAR_PASSW);
	}
	
	public void Validar(Textbox a, Textbox b){
		
		if (a.getValue().length() < caracter_longitud) {
			Clients.showNotification("Tu contraseña debe contar más de "
					+ caracter_longitud + " caracteres.");
			Executions.sendRedirect("#");
		} else if (a.getValue().contains(caracter_invalido)) {
			Clients.showNotification("Tu contraseña no debe tener espacios en blanco.");
			Executions.sendRedirect("#");
		} else if (a.getValue().equals(b.getValue()) == false) {
			Clients.showNotification("Las contraseñas ingresadas no coinciden");
			// Clients.showNotification("Las contraseñas ingresadas no coinciden");
			Executions.sendRedirect("#");
		} else if(a.getValue().equals(b.getValue()) == true){
			Clients.showNotification("Las contraseña se cambio exitosamente");
			Executions.sendRedirect("#");
			/*
			 * aqui se pondra el codigo para actualizar el password en la base.
			 */
			
		}
	}

}
