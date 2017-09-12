var oldnameCom;
var activadoCom = 0;
var oldnameAl;
var activadoAl = 0;
var oldnamePr;
var activadoPr = 0;

var idBorrar = 0;
var idCambiar = 0;
//Carga de lista de personajes completa

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
					$("#modalModificaPersonaje1").modal();
					$("#personajeName" + activadoCom).attr("disabled","true");
					$("#savePersonaje" + activadoCom).hide();
					$("#cancelPersonaje" + activadoCom).hide();
					activadoCom = 0;
				}
				else{
					$("#personajeName" + json.id).val(json.oldname);
					if(json.message == "noGroup"){
						$("#modalModificaPersonaje2").modal();
					}
					else if(json.message == "noExist"){
						$("#modalModificaPersonaje3").modal();
					}
					else if(json.message == "nombreCorto"){
						$("#modalModificaPersonaje4").modal();
					}
					else if(json.message == "noPrivileges"){
						$("#modalModificaPersonaje5").modal();
					}
					else if(json.message == "nameExist"){
						$("#modalModificaPersonaje6").modal();
					}
				}
			});
		}
	});
	
	$(".deleteSPersonaje").click(function(){
		idBorrar = $(this).data('val');
		$("#modalBorrarPersonajePrev").modal();
	});
	
	$("#modalConfirmarBorrado").click(function(){
		$("#modalBorrarPersonajePrev").modal("hide");
		$.post("removeCharacterA.do",{
			idPersonaje : idBorrar
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraPersonaje1").modal();
				$("#personaje" + json.id).remove();
			}
			else if(json.message == "noExist"){
				$("#modalBorraPersonaje2").modal();
			}
			else if(json.message = "error"){
				$("#modalBorraPersonaje3").modal();
			}
			else if(json.message = "noGroup"){
				$("#modalBorraPersonaje4").modal();
			}
		})
	});
	
	/////////////////////////////////////////////////////////
	//Funciones concretas de acciones extra sobre personajes.
	/////////////////////////////////////////////////////////

	$(".oacdatos").click(function(){
		idCambiar =$(this).data('val');
		$("#modalDatosNombre").val($("#pnom" + idCambiar).val());
		$("#modalOtros").val($("#potros" + idCambiar).val());
		$("#modalDiaNac").val($("#pdnac" + idCambiar).val());
			$("#knowDN").prop("checked",!($("#pdnac" + idCambiar).val()==0));
			$("#modalDiaNac").attr("disabled",($("#pdnac" + idCambiar).val()==0));
		$("#modalMesNac").val($("#pmnac" + idCambiar).val());
			$("#knowMN").prop("checked",!($("#pmnac" + idCambiar).val()==0));
			$("#modalMesNac").attr("disabled",($("#pmnac" + idCambiar).val()==0));
		$("#modalAnioNac").val($("#panac" + idCambiar).val());
			$("#knowAN").prop("checked",!($("#panac" + idCambiar).val()==0));
			$("#modalAnioNac").attr("disabled",($("#panac" + idCambiar).val()==0));
		$("#modalDiaFal").val($("#pdfal" + idCambiar).val());
			$("#knowDF").prop("checked",!($("#pdfal" + idCambiar).val()==0));
			$("#modalDiaFal").attr("disabled",($("#pdfal" + idCambiar).val()==0));
		$("#modalMesFal").val($("#pmfal" + idCambiar).val());
			$("#knowMF").prop("checked",!($("#pmfal" + idCambiar).val()==0));
			$("#modalMesFal").attr("disabled",($("#pmfal" + idCambiar).val()==0));
		$("#modalAnioFal").val($("#pafal" + idCambiar).val());
			$("#knowAF").prop("checked",!($("#pafal" + idCambiar).val()==0));
			$("#modalAnioFal").attr("disabled",($("#pafal" + idCambiar).val()==0));
		$("#modalDatosNombre").html($("#pnom" + idCambiar).val());
		
		$("#modalCambiarDatos").modal();
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
		$("#modalCargos").modal();
		$.post("availableChargeList.do",{
			idPersonaje : id
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "ok"){
				$("#listaCargosActual").html(json.listaActual);
				$("#listaCargosDisponibles").html(json.listaDisponible);
			}
			else if(json.message == "error"){
				$("#modalCargos").modal("hide");
				$("#modalAsignaCargo1").modal();
				
			}
		})
	});
	
}

//Carga de lista de personajes de estudiante

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
	
	$("#createButton").click(function(){
		$("#modalCrear").modal();
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
	
	$("#createButton2").click(function(){
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
	
	//////////////////////////////
	//Funciones de modal de fechas
	//////////////////////////////
	
	$("#knowDN").click(function(){
		if($("#knowDN").is(':checked')) {  
            $("#modalDiaNac").attr("disabled", false);
            validaDia("#modalDiaNac");
            $("#modalAnioNac").attr("disabled", false);
            validaAnio("#modalAnioNac");
            validaMes("#modalMesNac");
            $("#modalMesNac").attr("disabled", false);
            $("#knowMN").prop("checked",true);
            $("#knowAN").prop("checked",true);
        } else {  
        	$("#modalDiaNac").attr("disabled", true);
        }  
	});
	
	$("#knowMN").click(function(){
		if($("#knowMN").is(':checked')) {  
            $("#modalAnioNac").attr("disabled", false);
            validaAnio("#modalAnioNac");
            $("#modalMesNac").attr("disabled", false);
            validaMes("#modalMesNac");
            $("#knowAN").prop("checked",true);
        } else {  
        	$("#modalDiaNac").attr("disabled", true);
            $("#modalMesNac").attr("disabled", true);
            $("#knowDN").prop("checked",false);
        }  
	});
	
	$("#knowAN").click(function(){
		if($("#knowAN").is(':checked')) {  
            $("#modalAnioNac").attr("disabled", false);
            validaAnio("#modalAnioNac");
        } else {  
        	$("#modalDiaNac").attr("disabled", true);
            $("#modalMesNac").attr("disabled", true);
            $("#modalAnioNac").attr("disabled", true);
            $("#knowDN").prop("checked",false);
            $("#knowMN").prop("checked",false);
        }  
	});
	
	$("#knowDF").click(function(){
		if($("#knowDF").is(':checked')) {  
            $("#modalDiaFal").attr("disabled", false);
            validaDia("#modalDiaFal");
            $("#modalMesFal").attr("disabled", false);
            validaMes("#modalMesFal");
            $("#modalAnioFal").attr("disabled", false);
            validaAnio("#modalAnioFal");
            $("#knowAF").prop("checked",true);
            $("#knowMF").prop("checked",true);
        } else {  
        	$("#modalDiaFal").attr("disabled", true);
        }  
	});
	
	$("#knowMF").click(function(){
		if($("#knowMF").is(':checked')) {  
            $("#modalAnioFal").attr("disabled", false);
            validaAnio("#modalAnioFal");
            $("#modalMesFal").attr("disabled", false);
            validaMes("#modalMesFal");
            $("#knowAF").prop("checked",true);
        } else {  
        	$("#modalDiaFal").attr("disabled", true);
            $("#modalMesFal").attr("disabled", true);
            $("#knowDF").prop("checked",false);
        }  
	});
	
	$("#knowAF").click(function(){
		if($("#knowAF").is(':checked')) {  
            $("#modalAnioFal").attr("disabled", false);
            validaAnio("#modalAnioFal");
        } else {  
        	$("#modalDiaFal").attr("disabled", true);
            $("#modalMesFal").attr("disabled", true);
            $("#modalAnioFal").attr("disabled", true);
            $("#knowDF").prop("checked",false);
            $("#knowMF").prop("checked",false);
        }  
	});
	
	//////////////////////////////
	//Funciones de modal de creación
	//////////////////////////////
	
	$("#knowDN2").click(function(){
		if($("#knowDN2").is(':checked')) {  
            $("#modalDiaNac2").attr("disabled", false);
            validaDia("#modalDiaNac2");
            $("#modalAnioNac2").attr("disabled", false);
            validaAnio("#modalAnioNac2");
            $("#modalMesNac2").attr("disabled", false);
            validaMes("#modalMesNac2");
            $("#knowMN2").prop("checked",true);
            $("#knowAN2").prop("checked",true);
        } else {  
        	$("#modalDiaNac2").attr("disabled", true);
        	
        }  
	});
	
	$("#knowMN2").click(function(){
		if($("#knowMN2").is(':checked')) {  
            $("#modalAnioNac2").attr("disabled", false);
            validaAnio("#modalAnioNac2");
            $("#modalMesNac2").attr("disabled", false);
            validaMes("#modalMesNac2");
            $("#knowAN2").prop("checked",true);
        } else {  
        	$("#modalDiaNac2").attr("disabled", true);
            $("#modalMesNac2").attr("disabled", true);
            $("#knowDN2").prop("checked",false);
        }  
	});
	
	$("#knowAN2").click(function(){
		if($("#knowAN2").is(':checked')) {  
            $("#modalAnioNac2").attr("disabled", false);
            validaAnio("#modalAnioNac2");
        } else {  
        	$("#modalDiaNac2").attr("disabled", true);
            $("#modalMesNac2").attr("disabled", true);
            $("#modalAnioNac2").attr("disabled", true);
            $("#knowDN2").prop("checked",false);
            $("#knowMN2").prop("checked",false);
        }  
	});
	
	$("#knowDF2").click(function(){
		if($("#knowDF2").is(':checked')) {  
            $("#modalDiaFal2").attr("disabled", false);
            validaDia("#modalDiaFal2");
            $("#modalAnioFal2").attr("disabled", false);
            validaAnio("#modalAnioFal2");
            $("#modalMesFal2").attr("disabled", false);
            validaMes("#modalMesFal2");
            $("#knowAF2").prop("checked",true);
            $("#knowMF2").prop("checked",true);
        } else {  
        	$("#modalDiaFal2").attr("disabled", true);
        }  
	});
	
	$("#knowMF2").click(function(){
		if($("#knowMF2").is(':checked')) {  
            $("#modalAnioFal2").attr("disabled", false);
            validaAnio("#modalAnioFal2");
            $("#modalMesFal2").attr("disabled", false);
            validaMes("#modalMesFal2");
            $("#knowAF2").prop("checked",true);
        } else {  
        	$("#modalDiaFal2").attr("disabled", true);
            $("#modalMesFal2").attr("disabled", true);
            $("#knowDF2").prop("checked",false);
        }  
	});
	
	$("#knowAF2").click(function(){
		if($("#knowAF2").is(':checked')) {  
            $("#modalAnioFal2").attr("disabled", false);
            validaAnio("#modalAnioFal2");
        } else {  
        	$("#modalDiaFal2").attr("disabled", true);
            $("#modalMesFal2").attr("disabled", true);
            $("#modalAnioFal2").attr("disabled", true);
            $("#knowDF2").prop("checked",false);
            $("#knowMF2").prop("checked",false);
        }  
	});
	
	$(".inputDays").keyup(function(){
		validaDia(this);
	});
	
	$(".inputMonths").keyup(function(){
		validaMes(this);
	});
	
	$("#modalCrearOk").click(function(){
		$.post("createPersonageA.do",
			{
			crearNombre : $("#crearNombre").val(),
			modalOtros2 : $("#modalOtros2").val(),
			knowDN2 : $("#knowDN2").is(':checked'),
			modalDiaNac2 : $("#modalDiaNac2").val(),
			knowMN2 : $("#knowMN2").is(':checked'),
			modalMesNac2 : $("#modalMesNac2").val(),
			knowAN2 : $("#knowAN2").is(':checked'),
			modalAnioNac2 : $("#modalAnioNac2").val(),
			knowDF2 : $("#knowDF2").is(':checked'),
			modalDiaFal2 : $("#modalDiaFal2").val(),
			knowMF2 : $("#knowMF2").is(':checked'),
			modalMesFal2 : $("#modalMesFal2").val(),
			knowAF2 : $("#knowAF2").is(':checked'),
			modalAnioFal2 : $("#modalAnioFal2").val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "creado"){
					$("#modalCrearPersonaje1").modal();
					cargaListaCompleta();
				}
				else if(json.message == "fechaIncorrecta"){
					$("#modalCrearPersonaje2").modal();
				}
				else if(json.message == "nombreRepetido"){
					$("#modalCrearPersonaje3").modal();
				}
				else if(json.message == "nombreCorto"){
					$("#modalCrearPersonaje4").modal();
				}
			}
		);
	});
	
	$("#modalDatosOk").click(function(){
		$.post("modifyCharacterA.do",
				{
				idPersonaje : idCambiar,
				modalDatosNombre : $("#modalDatosNombre").val(),
				modalOtros : $("#modalOtros").val(),
				knowDN : $("#knowDN").is(':checked'),
				modalDiaNac : $("#modalDiaNac").val(),
				knowMN : $("#knowMN").is(':checked'),
				modalMesNac : $("#modalMesNac").val(),
				knowAN : $("#knowAN").is(':checked'),
				modalAnioNac : $("#modalAnioNac").val(),
				knowDF : $("#knowDF").is(':checked'),
				modalDiaFal : $("#modalDiaFal").val(),
				knowMF : $("#knowMF").is(':checked'),
				modalMesFal : $("#modalMesFal").val(),
				knowAF : $("#knowAF").is(':checked'),
				modalAnioFal : $("#modalAnioFal").val()
				},
				function(data){
					var json = JSON.parse(data);
					if(json.message == "cambiado"){
						$("#modalModPersonaje1").modal();
						cargaListaCompleta();
					}
					else if(json.message == "fechaIncorrecta"){
						$("#modalModPersonaje2").modal();
					}
					else if(json.message == "nombreRepetido"){
						$("#modalModPersonaje3").modal();
					}
					else if(json.message == "nombreCorto"){
						$("#modalModPersonaje4").modal();
					}
					else {
						$("#modalModPersonaje5").modal();
					}
				}
			);
	});
	
});

function validaDia(o){
	if($(o).val() > 31){
		$(o).val(31);
	}
	else if($(o).val() < 1){
		$(o).val(1);
	}
}

function validaMes(o){
	if($(o).val() > 12){
		$(o).val(12);
	}
	else if($(o).val() < 1){
		$(o).val(1);
	}
}

function validaAnio(o){
	if($(o).val() == 0){
		$(o).val(1);
	}
}