var rol;
var usid;


$(document).ready(function(){
	$("#desresultOk").hide();
	$("#desresultNok").hide();
	
	cargaDobleLista();
	
	cargaLista(0);
	
	$("#modalUnenroll").click(function(){
		$.post("unenrollA.do",{
			role : rol,
			userId : usid
		},function(data){ 
			desmat(data);
		});
	});
	
});

function cargaDobleLista(){
	$.post("getStudentsEnrolled.do",{
		par : "ok"
	},function(data){
		$("#listaAlumnos").html(data);
		$(".unenrollS").click(function(){
			rol = 3;
			usid = $(this).data('val');
			$("#modalDesmatricula0").modal();
//			$.post("unenrollA.do",{
//				role : "3",
//				userId : $(this).data('val')
//			}, modalesDesmat);
		});//TODO: Activar la de profesor, eliminar fila y mostrar modales de error
	});
	
	$.post("getTeachersEnrolled.do",{
		par : "ok"
	},function(data){
		$("#listaProfesores").html(data);
		$(".unenrollT").click(function(){
			rol = 2;
			usid = $(this).data('val');
			$("#modalDesmatricula0").modal();
//			$.post("unenrollA.do",{
//				role : "2",
//				userId : $(this).data('val')
//			}, modalesDesmat);
		});//TODO: Activar la de profesor, eliminar fila y mostrar modales de error
	});
	
}

function desmat(data){
	var json = JSON.parse(data);
	if(json.message == "ok"){
		$("#modalDesmatricula1").modal();
		cargaDobleLista();
	}
	else if(json.message == "noTeacherOrDirector"){
		$("#modalDesmatricula2").modal();
		cargaDobleLista();
	}
	else if(json.message == "errorBD"){
		$("#modalDesmatricula3").modal();
		cargaDobleLista();
	}
	else if(json.message == "sinSesion"){
		window.location.href="hello.do";
	}
	else{
		$("#modalDesmatricula4").modal();
		cargaDobleLista();
	}
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