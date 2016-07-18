/**
 * 
 */
$(document).ready(function(){
	
	//FIXME: Limpiar el código
	$("#createUser").click(function(){
		alert($("#name").val().length + " " + $("#longname").val().length + " " + $("#pass").val().length + " " );
	
		if($("#name").val().length < 3 || $("#longname").val().length < 5 ||  $("#pass").val().length < 1){
			alert("nok");
		}
		else{
			alert("creando " + $("#name").val() + " " + $("#longname").val() + " " + $("input:radio[name=role]:checked").val() + " " + $("#pass").val() );
			$("#formusuario").submit();

		}
	});
});