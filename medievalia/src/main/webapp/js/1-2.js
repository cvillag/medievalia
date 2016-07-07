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
//			var dataString = 'name=' + $("#name").val() + 'longname=' + $("#longname").val() + 'role=' + $("input:radio[name=role]:checked").val() + 'pass' + $("#pass").val();
//			$.ajax(({
//				//FIXME: Añandir JSON
//				type: "POST",
//				url: "createuserAjax.do",
//				data: dataString,
//				success: function(data){
//						alert("bien " + data);
//						$("#suceso").html("<p>Creación con éxito</p>");
//				},
//				beforeSend: function(){
//					$("#suceso").html("<p>Creando...</p>");
//				},
//				error: function (xhr, ajaxOptions, thrownError) {
//			        alert(xhr.status);
//			        alert(thrownError);
//			        alert(ajaxOptions);
//			      }
//				
//			}));
		}
	});
});