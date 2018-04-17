function validar_contrasena() {
	var caract_invalido = " ";
	var caract_longitud = 6;
	var cla1 = document.getElementById('NUEVA_PASSW').value;
	var cla2 = document.getElementById('CONFIRMAR_PASSW').value;
		
		if (clal.length < caract_longitud) {
			alert('Tu clave debe constar de ' + caract_longitud + ' caracteres.');
			return false;
		}
		if (cal.indexOf(caract_invalido) > -1) {
			alert("Las claves no pueden contener espacios");
			return false;
		}
		else {
			if (cla1 != cla2) {
				alert ("Las claves introducidas no son iguales");
				return false;
			}
			else {
				alert('Contraeña correcta');
				return true;
      		}
   		
	}
}