$(document).ready(function(){
	$("#desresultOk").hide();
	$("#desresultNok").hide();
	
	cargaDobleLista();
	
	cargaLista(0);
	
});

function cargaDobleLista(){
	$.post("getStudentsEnrolled.do",{
		par : "ok"
	},function(data){
		$("#listaAlumnos").html(data);
		$(".unenrollS").click(function(){
			$.post("unenrollA.do",{
				role : "3",
				userId : $(this).data('val')
			}, function(data){
				var json = JSON.parse(data);
				if(json.error == "no"){
					$("#desresultOk").slideDown(200).delay(2000).slideUp(500);
					//$("#enroll"+idus).remove();
					setTimeout(cargaDobleLista(),3000);
					setTimeout(cargaLista(0),3000);
				}
				else{
					$("#desresultNok").slideDown(200).delay(2000).slideUp(500);
				}
			});
		});//TODO: Activar la de profesor, eliminar fila y mostrar modales de error
	});
	
	$.post("getTeachersEnrolled.do",{
		par : "ok"
	},function(data){
		$("#listaProfesores").html(data);
		$(".unenrollT").click(function(){
			$.post("unenrollA.do",{
				role : "2",
				userId : $(this).data('val')
			}, function(data){
				var json = JSON.parse(data);
				if(json.error == "no"){
					$("#desresultOk").slideDown(200).delay(2000).slideUp(500);
					//$("#enroll"+idus).remove();
					setTimeout(cargaDobleLista(),3000);
					setTimeout(cargaLista(0),3000);
				}
				else{
					$("#desresultNok").slideDown(200).delay(2000).slideUp(500);
				}
			});
		});//TODO: Activar la de profesor, eliminar fila y mostrar modales de error
	});
	
}

function cargaLista(h){
	$.post("possibleUserListA.do",{
		filterName : ""
	}, function(data){
		$("#divListaPossibleUsers").html(data);
		if(h == 0){
			$("#resultOk").hide();
			$("#resultNok").hide();
		}
		$(".enroll").click(function(){
			var idus = $(this).data('val');
			$.post("enrollUserA.do",{
				role : $("#roleSelected").val(),
				userId : $(this).data('val')
			}, function(data){
				var json = JSON.parse(data);
				if(json.error == "no"){
					$("#resultOk").slideDown(200).delay(2000).slideUp(500);
					//$("#enroll"+idus).remove();
					cargaDobleLista();
					cargaLista(1);
				}
				else{
					$("#resultNok").slideDown(200).delay(2000).slideUp(500);
				}
				// TODO: Modales de error.
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