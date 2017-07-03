$(document).ready(function(){
	
	cargaDobleLista();
	
	cargaLista();
	
	$("#modalok").click(function(){
		cargaDobleLista();
	});
});

function cargaDobleLista(){
	$.post("getStudentsEnrolled.do",{
		par : "ok"
	},function(data){
		$("#listaAlumnos").html(data);
	});
	
	$.post("getTeachersEnrolled.do",{
		par : "ok"
	},function(data){
		$("#listaProfesores").html(data);
	});
}

function cargaLista(){
	$.post("possibleUserListA.do",{
		filterName : ""
	}, function(data){
		$("#divListaPossibleUsers").html(data);
		$("#resultOk").hide();
		$("#resultNok").hide();
		$(".enroll").click(function(){
			//alert("Matricular a " + $(this).data('val') + " como rol " + $("#roleSelected").val());
			var idus = $(this).data('val');
			$.post("enrollUserA.do",{
				role : $("#roleSelected").val(),
				userId : $(this).data('val')
			}, function(data){
				var json = JSON.parse(data);
				if(json.error == "no"){
					$("#resultOk").slideDown(200).delay(2000).slideUp(500);
					$("#enroll"+idus).remove();
				}
				else{
					$("#resultNok").slideDown(200).delay(2000).slideUp(500);
				}
				//setTimeout(cargaLista(),3000);
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