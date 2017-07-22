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
			$("#estudioName" + activadoCom).val(oldnameCom);
			$("#estudioName" + activadoCom).attr("disabled","true");
			$("#saveEstudio" + activadoCom).hide();
			$("#cancelEstudio" + activadoCom).hide();
			activadoCom = 0;
		}
		oldnameCom = $("#estudioName" + $(this).data('val')).val();
		activadoCom = $(this).data('val');
		$("#estudioName" + $(this).data('val')).removeAttr("disabled");
		$("#saveEstudio" + $(this).data('val')).show();
		$("#cancelEstudio" + $(this).data('val')).show();
	});
	
	$(".cancelNewName").click(function(){
		$("#estudioName" + $(this).data('val')).val(oldnameCom);
		activadoCom = 0;
		$("#cancelEstudio" + $(this).data('val')).hide();
		$("#saveEstudio" + $(this).data('val')).hide();
		$("#estudioName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveNewName").click(function(){
		if($("#estudioName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaEstudio1").modal();
		}
		else{
			$.post("renameStudyA.do",{
				idEstudio : $(this).data('val'),
				newNombre : $("#estudioName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaEstudio2").modal();
					$("#estudioName" + activadoCom).attr("disabled","true");
					$("#saveEstudio" + activadoCom).hide();
					$("#cancelEstudio" + activadoCom).hide();
					activadoCom = 0;
				}
				else{
					$("#estudioName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaEstudio3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaEstudio5").modal();
					}
					else{
						$("#modalModificaEstudio4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteSEstudio").click(function(){
		$.post("removeStudyA.do",{
			idEstudio : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraEstudio1").modal();
				$("#estudio" + json.id).remove();
			}
			else{
				$("#modalBorraEstudio2").modal();
			}
		})
	});
}

function postCarga2(){
	$(".saveStudentNewName").hide();
	$(".cancelStudentNewName").hide();
	
	$(".activarStudentSNombre").click(function(){
		if(activadoAl != 0){
			$("#estudioStudentName" + activadoAl).val(oldnameAl);
			$("#estudioStudentName" + activadoAl).attr("disabled","true");
			$("#saveStudentEstudio" + activadoAl).hide();
			$("#cancelStudentEstudio" + activadoAl).hide();
			activadoAl = 0;
		}
		oldnameAl = $("#estudioStudentName" + $(this).data('val')).val();
		activadoAl = $(this).data('val');
		$("#estudioStudentName" + $(this).data('val')).removeAttr("disabled");
		$("#saveStudentEstudio" + $(this).data('val')).show();
		$("#cancelStudentEstudio" + $(this).data('val')).show();
	});
	
	$(".cancelStudentNewName").click(function(){
		$("#estudioStudentName" + $(this).data('val')).val(oldnameAl);
		activadoAl = 0;
		$("#cancelStudentEstudio" + $(this).data('val')).hide();
		$("#saveStudentEstudio" + $(this).data('val')).hide();
		$("#estudioStudentName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveStudentNewName").click(function(){
		if($("#estudioStudentName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaEstudio1").modal();
		}
		else{
			$.post("renameStudyA.do",{
				idEstudio : $(this).data('val'),
				newNombre : $("#estudioStudentName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaEstudio2").modal();
					$("#estudioStudentName" + activadoAl).attr("disabled","true");
					$("#saveStudentEstudio" + activadoAl).hide();
					$("#cancelStudentEstudio" + activadoAl).hide();
					activadoAl = 0;
				}
				else{
					$("#estudioStudentName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaEstudio3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaEstudio5").modal();
					}
					else{
						$("#modalModificaEstudio4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteStudentSEstudio").click(function(){
		$.post("removeStudyA.do",{
			idEstudio : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraEstudio1").modal();
				$("#estudioStudent" + json.id).remove();
			}
			else{
				$("#modalBorraEstudio2").modal();
			}
		});
	});
}

function postCarga3(){
	$(".saveProfeNewName").hide();
	$(".cancelProfeNewName").hide();
	
	$(".activarProfeSNombre").click(function(){
		if(activadoPr != 0){
			$("#estudioProfeName" + activadoPr).val(oldnamePr);
			$("#estudioProfeName" + activadoPr).attr("disabled","true");
			$("#saveProfeEstudio" + activadoPr).hide();
			$("#cancelProfeEstudio" + activadoPr).hide();
			activadoPr = 0;
		}
		oldnamePr = $("#estudioProfeName" + $(this).data('val')).val();
		activadoPr = $(this).data('val');
		$("#estudioProfeName" + $(this).data('val')).removeAttr("disabled");
		$("#saveProfeEstudio" + $(this).data('val')).show();
		$("#cancelProfeEstudio" + $(this).data('val')).show();
	});
	
	$(".cancelProfeNewName").click(function(){
		$("#estudioProfeName" + $(this).data('val')).val(oldnamePr);
		activadoPr = 0;
		$("#cancelProfeEstudio" + $(this).data('val')).hide();
		$("#saveProfeEstudio" + $(this).data('val')).hide();
		$("#estudioProfeName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveProfeNewName").click(function(){
		if($("#estudioProfeName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaEstudio1").modal();
		}
		else{
			$.post("renameStudyA.do",{
				idEstudio : $(this).data('val'),
				newNombre : $("#estudioProfeName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaEstudio2").modal();
					$("#estudioProfeName" + activadoPr).attr("disabled","true");
					$("#saveProfeEstudio" + activadoPr).hide();
					$("#cancelProfeEstudio" + activadoPr).hide();
					activadoPr = 0;
				}
				else{
					$("#estudioProfeName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaEstudio3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaEstudio5").modal();
					}
					else{
						$("#modalModificaEstudio4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteProfeSEstudio").click(function(){
		$.post("removeStudyA.do",{
			idEstudio : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraEstudio1").modal();
				$("#estudioProfe" + json.id).remove();
			}
			else{
				$("#modalBorraEstudio2").modal();
			}
		});
	});
	
	$(".validarEstudio").click(function(){
		$.post("validateStudy.do",{
			idEstudio : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "validado"){
				$("#modalValidaEstudio1").modal();
				$("#validado" + json.id).removeClass();
				$("#validado" + json.id).addClass("label label-success");
				$("#validado" + json.id).text($("#textVal").val())
			}
			else{
				$("#modalValidaEstudio2").modal();
			}
		});
	});
}

function cargaListaCompleta(){
	$.post("completeStudyList.do",{
		type : "table"},
		function(data){
			$("#listaCompleta").html(data);
			postCarga();
	});
}

function cargaListaAlumno(){
	$.post("studentStudyListA.do",{
		type : "table"},
			function(data){
				$("#listaalumno").html(data);
				postCarga2();
	});
}

function cargaListaProfe(){
	$.post("teacherStudyListA.do",{
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
			$("#newStudyName").val("");
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
			$("#newStudyName").val("");
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
	
	//Filtro de Estudios por fila según se rellena el campo imput
	$("#filtroBusquedaCompleta").keyup(function(){
		if($("#filtroBusquedaCompleta").val().length > 0){
			$(".trestudio").each(function(){
				if($(this).data("nom").indexOf($("#filtroBusquedaCompleta").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trestudio").show();
		}
	});
	
	$("#busquedaProfe").keyup(function(){
		if($("#busquedaProfe").val().length > 0){
			$(".trestudioP").each(function(){
				if($(this).data("nom").indexOf($("#busquedaProfe").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trestudioP").show();
		}
	});
	
	$("#busquedaUsuario").keyup(function(){
		if($("#busquedaUsuario").val().length > 0){
			$(".trestudioU").each(function(){
				if($(this).data("nom").indexOf($("#busquedaUsuario").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trestudioU").show();
		}
	});
	
	$("#createButton").click(function(){
		if($("#newStudyName").val().length < 1){
			$("#modalCreaEstudio1").modal();
		}
		else{
			$.post("createStudyA.do",{
				nombre : $("#newStudyName").val()
			},function(data){
				var json = JSON.parse(data);
				if(json.message == "nameRepeated"){
					$("#modalCreaEstudio3").modal();
				}
				else if(json.message == "creado"){
					cargaListaCompleta();
					if($("#listaalumno").length > 0){
						if(btncreate == 0){
							$("#group-block1").slideUp(500);
							$("#newStudyName").val("");
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
					$("#modalCreaEstudio2").modal();
				}
				else if(json.message == "noCreado"){
					$("#modalCreaEstudio4").modal();
				}
			});
		}
	});
});