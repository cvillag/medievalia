var oldnameCom;
var activadoCom = 0;
var oldnameAl;
var activadoAl = 0;
var oldnamePr;
var activadoPr = 0;

function postPersonaje(){
	//alert("Acciones de botones");
	
	$(".saveNewName").hide();
	$(".cancelNewName").hide();
	
	$(".activarSNombre").click(function(){
		if(activadoCom != 0){
			$("#personajeName" + activadoCom).val(oldnameCom);
			$("#personajeName" + activadoCom).attr("disabled","true");
			$("#savePersonaje" + activadoCom).hide();
			$("#cancelPersonaje" + activadoCom).hide();
			activadoCom = 0;
		}
		oldnameCom = $("#personajeName" + $(this).data('val')).val();
		activadoCom = $(this).data('val');
		$("#personajeName" + $(this).data('val')).removeAttr("disabled");
		$("#savePersonaje" + $(this).data('val')).show();
		$("#cancelPersonaje" + $(this).data('val')).show();
	});
	
	$(".cancelNewName").click(function(){
		$("#personajeName" + $(this).data('val')).val(oldnameCom);
		activadoCom = 0;
		$("#cancelPersonaje" + $(this).data('val')).hide();
		$("#savePersonaje" + $(this).data('val')).hide();
		$("#personajeName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveNewName").click(function(){
		if($("#personajeName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaPersonaje1").modal();
		}
		else{
			$.post("renameCharacterA.do",{
				idPersonaje : $(this).data('val'),
				newNombre : $("#personajeName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaPersonaje2").modal();
					$("#personajeName" + activadoCom).attr("disabled","true");
					$("#savePersonaje" + activadoCom).hide();
					$("#cancelPersonaje" + activadoCom).hide();
					activadoCom = 0;
				}
				else{
					$("#personajeName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaPersonaje3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaPersonaje5").modal();
					}
					else{
						$("#modalModificaPersonaje4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteSPersonaje").click(function(){
		$.post("removeCharacterA.do",{
			idPersonaje : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraPersonaje1").modal();
				$("#personaje" + json.id).remove();
			}
			else{
				$("#modalBorraPersonaje2").modal();
			}
		})
	});
	
	/////////////////////////////////////////////////////////
	//Funciones concretas de acciones extra sobre personajes.
	/////////////////////////////////////////////////////////

	$(".oacdatos").click(function(){
		id=$(this).data('val');
		alert("Cambiar datos de " + id);
	});
	
	$(".oaclnac").click(function(){
		id=$(this).data('val');
		alert("Cambiar fecha de nacimiento de " + id);
	});
	
	$(".oaclfal").click(function(){
		id=$(this).data('val');
		alert("Cambiar fecha de fallecimiento de " + id);
	});
	$(".oacest").click(function(){
		id=$(this).data('val');
		alert("Cambiar estudios de " + id);
	});
	$(".oaccarg").click(function(){
		id=$(this).data('val');
		alert("Cambiar cargos de " + id);
	});
	
}

function postPersonaje2(){
	$(".saveStudentNewName").hide();
	$(".cancelStudentNewName").hide();
	
	$(".activarStudentSNombre").click(function(){
		if(activadoAl != 0){
			$("#personajeStudentName" + activadoAl).val(oldnameAl);
			$("#personajeStudentName" + activadoAl).attr("disabled","true");
			$("#saveStudentPersonaje" + activadoAl).hide();
			$("#cancelStudentPersonaje" + activadoAl).hide();
			activadoAl = 0;
		}
		oldnameAl = $("#personajeStudentName" + $(this).data('val')).val();
		activadoAl = $(this).data('val');
		$("#personajeStudentName" + $(this).data('val')).removeAttr("disabled");
		$("#saveStudentPersonaje" + $(this).data('val')).show();
		$("#cancelStudentPersonaje" + $(this).data('val')).show();
	});
	
	$(".cancelStudentNewName").click(function(){
		$("#personajeStudentName" + $(this).data('val')).val(oldnameAl);
		activadoAl = 0;
		$("#cancelStudentPersonaje" + $(this).data('val')).hide();
		$("#saveStudentPersonaje" + $(this).data('val')).hide();
		$("#personajeStudentName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveStudentNewName").click(function(){
		if($("#personajeStudentName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaPersonaje1").modal();
		}
		else{
			$.post("renameCharacterA.do",{
				idPersonaje : $(this).data('val'),
				newNombre : $("#personajeStudentName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaPersonaje2").modal();
					$("#personajeStudentName" + activadoAl).attr("disabled","true");
					$("#saveStudentPersonaje" + activadoAl).hide();
					$("#cancelStudentPersonaje" + activadoAl).hide();
					activadoAl = 0;
				}
				else{
					$("#personajeStudentName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaPersonaje3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaPersonaje5").modal();
					}
					else{
						$("#modalModificaPersonaje4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteStudentSPersonaje").click(function(){
		$.post("removeCharacterA.do",{
			idPersonaje : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraPersonaje1").modal();
				$("#personajeStudent" + json.id).remove();
			}
			else{
				$("#modalBorraPersonaje2").modal();
			}
		});
	});
}

function postPersonaje3(){
	$(".saveProfeNewName").hide();
	$(".cancelProfeNewName").hide();
	
	$(".activarProfeSNombre").click(function(){
		if(activadoPr != 0){
			$("#personajeProfeName" + activadoPr).val(oldnamePr);
			$("#personajeProfeName" + activadoPr).attr("disabled","true");
			$("#saveProfePersonaje" + activadoPr).hide();
			$("#cancelProfePersonaje" + activadoPr).hide();
			activadoPr = 0;
		}
		oldnamePr = $("#personajeProfeName" + $(this).data('val')).val();
		activadoPr = $(this).data('val');
		$("#personajeProfeName" + $(this).data('val')).removeAttr("disabled");
		$("#saveProfePersonaje" + $(this).data('val')).show();
		$("#cancelProfePersonaje" + $(this).data('val')).show();
	});
	
	$(".cancelProfeNewName").click(function(){
		$("#personajeProfeName" + $(this).data('val')).val(oldnamePr);
		activadoPr = 0;
		$("#cancelProfePersonaje" + $(this).data('val')).hide();
		$("#saveProfePersonaje" + $(this).data('val')).hide();
		$("#personajeProfeName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveProfeNewName").click(function(){
		if($("#personajeProfeName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaPersonaje1").modal();
		}
		else{
			$.post("renameCharacterA.do",{
				idPersonaje : $(this).data('val'),
				newNombre : $("#personajeProfeName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaPersonaje2").modal();
					$("#personajeProfeName" + activadoPr).attr("disabled","true");
					$("#saveProfePersonaje" + activadoPr).hide();
					$("#cancelProfePersonaje" + activadoPr).hide();
					activadoPr = 0;
				}
				else{
					$("#personajeProfeName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaPersonaje3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaPersonaje5").modal();
					}
					else{
						$("#modalModificaPersonaje4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteProfeSPersonaje").click(function(){
		$.post("removeCharacterA.do",{
			idPersonaje : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraPersonaje1").modal();
				$("#personajeProfe" + json.id).remove();
			}
			else{
				$("#modalBorraPersonaje2").modal();
			}
		});
	});
	
	$(".validarPersonaje").click(function(){
		$.post("validateCharacter.do",{
			idPersonaje : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "validado"){
				$("#modalValidaPersonaje1").modal();
				$("#validado" + json.id).removeClass();
				$("#validado" + json.id).addClass("label label-success");
				$("#validado" + json.id).text($("#textVal").val())
			}
			else{
				$("#modalValidaPersonaje2").modal();
			}
		});
	});
}

function cargaListaCompleta(){
	$.post("completePersonageList.do",{
		type : "table"},
		function(data){
			$("#listaCompleta").html(data);
			postPersonaje();
	});
}

function cargaListaAlumno(){
	$.post("studentCharacterListA.do",{
		type : "table"},
			function(data){
				$("#listaalumno").html(data);
				postPersonaje2();
	});
}

function cargaListaProfe(){
	$.post("teacherCharacterListA.do",{
		type : "table"},
			function(data){
				$("#listaProfe").html(data);
				postPersonaje3();
	});
}

var btncreate = 1;

$(document).ready(function(){
	
	$("#group-block1").hide();
	
	cargaListaCompleta();
	
	if($("#listaalumno").length > 0){
		cargaListaAlumno();
	}
	else if($("#listaProfe").length > 0){
		cargaListaProfe();
	}
	
	$("#displayCreate").click(function(){
		if(btncreate == 0){
			$("#group-block1").slideUp(500);
			$("#newCharacterName").val("");
			$("#displayCreatei").removeClass();
			$("#displayCreatei").addClass("glyphicon glyphicon-chevron-down");
			btncreate = 1;
		}
		else{
			$("#group-block1").slideDown(500);
			$("#displayCreatei").removeClass();
			$("#displayCreatei").addClass("glyphicon glyphicon-chevron-up");
			btncreate = 0;
		}
	});
	
	$("#cancelButton").click(function(){
		if(btncreate == 0){
			$("#group-block1").slideUp(500);
			$("#newCharacterName").val("");
			$("#displayCreatei").removeClass();
			$("#displayCreatei").addClass("glyphicon glyphicon-chevron-down");
			btncreate = 1;
		}
		else{
			$("#group-block1").slideDown(500);
			$("#displayCreatei").removeClass();
			$("#displayCreatei").addClass("glyphicon glyphicon-chevron-up");
			btncreate = 0;
		}
	});
	
	//Filtro de personajees por fila según se rellena el campo imput
	$("#filtroBusquedaCompleta").keyup(function(){
		if($("#filtroBusquedaCompleta").val().length > 0){
			$(".trpersonaje").each(function(){
				if($(this).data("nom").indexOf($("#filtroBusquedaCompleta").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trpersonaje").show();
		}
	});
	
	$("#busquedaProfe").keyup(function(){
		if($("#busquedaProfe").val().length > 0){
			$(".trpersonajeP").each(function(){
				if($(this).data("nom").indexOf($("#busquedaProfe").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trpersonajeP").show();
		}
	});
	
	$("#busquedaUsuario").keyup(function(){
		if($("#busquedaUsuario").val().length > 0){
			$(".trpersonajeU").each(function(){
				if($(this).data("nom").indexOf($("#busquedaUsuario").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trpersonajeU").show();
		}
	});
	
	$("#createButton").click(function(){
		if($("#newCharacterName").val().length < 1){
			$("#modalCreaPersonaje1").modal();
		}
		else{
			$.post("createCharacterA.do",{
				nombre : $("#newCharacterName").val()
			},function(data){
				var json = JSON.parse(data);
				if(json.message == "nameRepeated"){
					$("#modalCreaPersonaje3").modal();
				}
				else if(json.message == "creado"){
					cargaListaCompleta();
					if($("#listaalumno").length > 0){
						if(btncreate == 0){
							$("#group-block1").slideUp(500);
							$("#newCharacterName").val("");
							$("#displayCreatei").removeClass();
							$("#displayCreatei").addClass("glyphicon glyphicon-chevron-down");
							btncreate = 1;
						}
						else{
							$("#group-block1").slideDown(500);
							$("#displayCreatei").removeClass();
							$("#displayCreatei").addClass("glyphicon glyphicon-chevron-up");
							btncreate = 0;
						}
						cargaListaAlumno();
					}
					else if($("#listaProfe").length > 0){
						cargaListaProfe();
					}
					$("#modalCreaPersonaje2").modal();
				}
				else if(json.message == "noCreado"){
					$("#modalCreaPersonaje4").modal();
				}
			});
		}
	});
});
