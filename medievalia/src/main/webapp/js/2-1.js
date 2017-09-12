$(document).ready(function(){
	
	$("#btnMatricularAlumnos").click(function(){
		window.location.href="groupParticipants.do";
	});
	
	$(".btnGestionObjetos").click(function(){
		tipo = $(this).data('val');
		alert(tipo);
		$("#idTipo").val(tipo);
		$("#formOC").submit();
	});
	
	
	
//	$("#btnGestionCargos").click(function(){
//		window.location.href="chargeController.do";
//	});
//	
//	$("#btnGestionEstudios").click(function(){
//		window.location.href="studyController.do";
//	});
//	
//	$("#btnGestionLugares").click(function(){
//		window.location.href="placeController.do";
//	});
//	
//	$("#btnGestionAutores").click(function(){
//		window.location.href="authorController.do";
//	});
//	
//	$("#btnGestionPersonajes").click(function(){
//		window.location.href="characterController.do";
//	});
});