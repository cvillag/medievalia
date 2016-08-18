/**
 * 
 */
$(document).ready(function(){
	
	//FIXME: Limpiar el código. Crear modal
	$("#createUser").click(function(){
	
		if($("#name").val().length < 3 || $("#longname").val().length < 5 ||  $("#pass").val().length < 1){
			alert("nok");
		}
		else{
			$("#formusuario").submit();
			alert("creando " + $("#name").val() + " " + $("#longname").val() + " " + $("input:radio[name=role]:checked").val() + " " + $("#pass").val() );

		}
	});
});