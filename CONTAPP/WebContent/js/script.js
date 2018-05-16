
 	var mostrar="si";
 	$("#items").slideUp(0);

 	function menu(){
 		if (mostrar=="no") {
 		$("#items").slideUp(1000);
 		mostrar="si";
 	}else if(mostrar=="si"){
 		$("#items").slideDown(1000);
 		mostrar="no";
 	}


 	}