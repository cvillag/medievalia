$(document).ready(function(){
	
	$("#btnMatricularAlumnos").click(function(){
		$("#modalMatricularAlumno").modal();
		cargaLista();
	});
});

function cargaLista(){
	$.post("possibleUserListA.do",{
		filterName : ""
	}, function(data){
		$("#divListaUsers").html(data);
		$("#resultOk").hide();
		$("#resultNok").hide();
		$(".enroll").click(function(){
			//alert("Matricular a " + $(this).data('val') + " como rol " + $("#roleSelected").val());
			$.post("enrollUserA.do",{
				role : $("#roleSelected").val(),
				userId : $(this).data('val')
			}, function(data){
				var json = JSON.parse(data);
				alert(json.error);
				if(json.error == "no"){
					$("#resultOk").slideDown(200).delay(2000).slideUp(500);
				}
				else{
					$("#resultNok").slideDown(200).delay(2000).slideUp(500);
				}
				cargaLista();
			});
		});
		
		$("#roleSelected").change(function(){
			if($(this).val() == 2){
				$(".alumno").hide();
			}
			else{
				$(".alumno").show();
			}
		});
		
	});
}