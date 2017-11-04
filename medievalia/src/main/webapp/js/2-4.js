var oldnameCom;
var activadoCom = 0;
var oldnameAl;
var activadoAl = 0;
var oldnamePr;
var activadoPr = 0;

function postCarga(){
	//alert("Acciones de botones");
	
	$(".saveNewName").hide();
	$(".cancelNewName").hide();
	
	$(".activarSNombre").click(function(){
		if(activadoCom != 0){
			$("#lugarName" + activadoCom).val(oldnameCom);
			$("#lugarName" + activadoCom).attr("disabled","true");
			$("#saveLugar" + activadoCom).hide();
			$("#cancelLugar" + activadoCom).hide();
			activadoCom = 0;
		}
		oldnameCom = $("#lugarName" + $(this).data('val')).val();
		activadoCom = $(this).data('val');
		$("#lugarName" + $(this).data('val')).removeAttr("disabled");
		$("#saveLugar" + $(this).data('val')).show();
		$("#cancelLugar" + $(this).data('val')).show();
	});
	
	$(".cancelNewName").click(function(){
		$("#lugarName" + $(this).data('val')).val(oldnameCom);
		activadoCom = 0;
		$("#cancelLugar" + $(this).data('val')).hide();
		$("#saveLugar" + $(this).data('val')).hide();
		$("#lugarName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveNewName").click(function(){
		if($("#lugarName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaLugar1").modal();
		}
		else{
			$.post("renamePlaceA.do",{
				idLugar : $(this).data('val'),
				newNombre : $("#lugarName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaLugar2").modal();
					$("#lugarName" + activadoCom).attr("disabled","true");
					$("#saveLugar" + activadoCom).hide();
					$("#cancelLugar" + activadoCom).hide();
					activadoCom = 0;
				}
				else{
					$("#lugarName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaLugar3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaLugar5").modal();
					}
					else{
						$("#modalModificaLugar4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteSLugar").click(function(){
		$.post("removePlaceA.do",{
			idLugar : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraLugar1").modal();
				$("#lugar" + json.id).remove();
			}
			else{
				$("#modalBorraLugar2").modal();
			}
		})
	});
}

function postCarga2(){
	$(".saveStudentNewName").hide();
	$(".cancelStudentNewName").hide();
	
	$(".activarStudentSNombre").click(function(){
		if(activadoAl != 0){
			$("#objetoStudentName" + activadoAl).val(oldnameAl);
			$("#objetoStudentName" + activadoAl).attr("disabled","true");
			$("#saveStudentObjeto" + activadoAl).hide();
			$("#cancelStudentObjeto" + activadoAl).hide();
			activadoAl = 0;
		}
		oldnameAl = $("#lugarStudentName" + $(this).data('val')).val();
		activadoAl = $(this).data('val');
		$("#objetoStudentName" + $(this).data('val')).removeAttr("disabled");
		$("#saveStudentObjeto" + $(this).data('val')).show();
		$("#cancelStudentObjeto" + $(this).data('val')).show();
	});
	
	$(".cancelStudentNewName").click(function(){
		$("#objetoStudentName" + $(this).data('val')).val(oldnameAl);
		activadoAl = 0;
		$("#cancelStudentObjeto" + $(this).data('val')).hide();
		$("#saveStudentObjeto" + $(this).data('val')).hide();
		$("#objetoStudentName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveStudentNewName").click(function(){
		if($("#objetoStudentName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaLugar1").modal();
		}
		else{
			$.post("renamePlaceA.do",{
				idLugar : $(this).data('val'),
				newNombre : $("#objetoStudentName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaLugar2").modal();
					$("#objetoStudentName" + activadoAl).attr("disabled","true");
					$("#saveStudentObjeto" + activadoAl).hide();
					$("#cancelStudentObjeto" + activadoAl).hide();
					activadoAl = 0;
				}
				else{
					$("#objetoStudentName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaLugar3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaLugar5").modal();
					}
					else{
						$("#modalModificaLugar4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteStudentSLugar").click(function(){
		$.post("removePlaceA.do",{
			idLugar : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraLugar1").modal();
				$("#lugarStudent" + json.id).remove();
			}
			else{
				$("#modalBorraLugar2").modal();
			}
		});
	});
}

function postCarga3(){
	$(".saveProfeNewName").hide();
	$(".cancelProfeNewName").hide();
	
	$(".activarProfeSNombre").click(function(){
		if(activadoPr != 0){
			$("#lugarProfeName" + activadoPr).val(oldnamePr);
			$("#lugarProfeName" + activadoPr).attr("disabled","true");
			$("#saveProfeLugar" + activadoPr).hide();
			$("#cancelProfeLugar" + activadoPr).hide();
			activadoPr = 0;
		}
		oldnamePr = $("#lugarProfeName" + $(this).data('val')).val();
		activadoPr = $(this).data('val');
		$("#lugarProfeName" + $(this).data('val')).removeAttr("disabled");
		$("#saveProfeLugar" + $(this).data('val')).show();
		$("#cancelProfeLugar" + $(this).data('val')).show();
	});
	
	$(".cancelProfeNewName").click(function(){
		$("#lugarProfeName" + $(this).data('val')).val(oldnamePr);
		activadoPr = 0;
		$("#cancelProfeLugar" + $(this).data('val')).hide();
		$("#saveProfeLugar" + $(this).data('val')).hide();
		$("#lugarProfeName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveProfeNewName").click(function(){
		if($("#lugarProfeName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaLugar1").modal();
		}
		else{
			$.post("renamePlaceA.do",{
				idLugar : $(this).data('val'),
				newNombre : $("#lugarProfeName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaLugar2").modal();
					$("#lugarProfeName" + activadoPr).attr("disabled","true");
					$("#saveProfeLugar" + activadoPr).hide();
					$("#cancelProfeLugar" + activadoPr).hide();
					activadoPr = 0;
				}
				else{
					$("#lugarProfeName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaLugar3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaLugar5").modal();
					}
					else{
						$("#modalModificaLugar4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteProfeSLugar").click(function(){
		$.post("removePlaceA.do",{
			idLugar : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraLugar1").modal();
				$("#lugarProfe" + json.id).remove();
			}
			else{
				$("#modalBorraLugar2").modal();
			}
		});
	});
	
	$(".validarLugar").click(function(){
		$.post("validatePlace.do",{
			idLugar : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "validado"){
				$("#modalValidaLugar1").modal();
				$("#validado" + json.id).removeClass();
				$("#validado" + json.id).addClass("label label-success");
				$("#validado" + json.id).text($("#textVal").val())
			}
			else{
				$("#modalValidaLugar2").modal();
			}
		});
	});
}

function cargaListaCompleta(){
	$.post("completePlaceList.do",{
		type : "table"},
		function(data){
			$("#listaCompleta").html(data);
			postCarga();
	});
}

function cargaListaAlumno(){
	$.post("studentPlaceListA.do",{
		type : "table"},
			function(data){
				$("#listaalumno").html(data);
				postCarga2();
	});
}

function cargaListaProfe(){
	$.post("teacherPlaceListA.do",{
		type : "table"},
			function(data){
				$("#listaProfe").html(data);
				postCarga3();
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
			$("#newPlaceName").val("");
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
			$("#newPlaceName").val("");
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
	
	//Filtro de lugares por fila según se rellena el campo imput
	$("#filtroBusquedaCompleta").keyup(function(){
		if($("#filtroBusquedaCompleta").val().length > 0){
			$(".trlugar").each(function(){
				if($(this).data("nom").indexOf($("#filtroBusquedaCompleta").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trlugar").show();
		}
	});
	
	$("#busquedaProfe").keyup(function(){
		if($("#busquedaProfe").val().length > 0){
			$(".trlugarP").each(function(){
				if($(this).data("nom").indexOf($("#busquedaProfe").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trlugarP").show();
		}
	});
	
	$("#busquedaUsuario").keyup(function(){
		if($("#busquedaUsuario").val().length > 0){
			$(".trlugarU").each(function(){
				if($(this).data("nom").indexOf($("#busquedaUsuario").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trlugarU").show();
		}
	});
	
	$("#createButton").click(function(){
		if($("#newPlaceName").val().length < 1){
			$("#modalCreaLugar1").modal();
		}
		else{
			$.post("createPlaceA.do",{
				nombre : $("#newPlaceName").val()
			},function(data){
				var json = JSON.parse(data);
				if(json.message == "nameRepeated"){
					$("#modalCreaLugar3").modal();
				}
				else if(json.message == "creado"){
					cargaListaCompleta();
					if($("#listaalumno").length > 0){
						if(btncreate == 0){
							$("#group-block1").slideUp(500);
							$("#newPlaceName").val("");
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
					$("#modalCreaLugar2").modal();
				}
				else if(json.message == "noCreado"){
					$("#modalCreaLugar4").modal();
				}
			});
		}
	});
});