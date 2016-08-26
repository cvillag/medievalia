$(document).ready(function(){
	
	$("#btnMatricularAlumnos").click(function(){
		$("#modalMatricularAlumno").modal();
		$.post("possibleUserListA.do",{
			filterName : ""
		}, function(data){
			$("#divListaUsers").html(data);
		});
	});
});