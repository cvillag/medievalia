var oldnameCom;
var activadoCom = 0;
var oldnameAl;
var activadoAl = 0;
var oldnamePr;
var activadoPr = 0;

function postAutor(){
	//alert("Acciones de botones");
	
	$(".saveNewName").hide();
	$(".cancelNewName").hide();
	
	$(".activarSNombre").click(function(){
		if(activadoCom != 0){
			$("#autorName" + activadoCom).val(oldnameCom);
			$("#autorName" + activadoCom).attr("disabled","true");
			$("#saveAutor" + activadoCom).hide();
			$("#cancelAutor" + activadoCom).hide();
			activadoCom = 0;
		}
		oldnameCom = $("#autorName" + $(this).data('val')).val();
		activadoCom = $(this).data('val');
		$("#autorName" + $(this).data('val')).removeAttr("disabled");
		$("#saveAutor" + $(this).data('val')).show();
		$("#cancelAutor" + $(this).data('val')).show();
	});
	
	$(".cancelNewName").click(function(){
		$("#autorName" + $(this).data('val')).val(oldnameCom);
		activadoCom = 0;
		$("#cancelAutor" + $(this).data('val')).hide();
		$("#saveAutor" + $(this).data('val')).hide();
		$("#autorName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveNewName").click(function(){
		if($("#autorName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaAutor1").modal();
		}
		else{
			$.post("renameAuthorA.do",{
				idAutor : $(this).data('val'),
				newNombre : $("#autorName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaAutor2").modal();
					$("#autorName" + activadoCom).attr("disabled","true");
					$("#saveAutor" + activadoCom).hide();
					$("#cancelAutor" + activadoCom).hide();
					activadoCom = 0;
				}
				else{
					$("#autorName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaAutor3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaAutor5").modal();
					}
					else{
						$("#modalModificaAutor4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteSAutor").click(function(){
		$.post("removeAuthorA.do",{
			idAutor : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraAutor1").modal();
				$("#autor" + json.id).remove();
			}
			else{
				$("#modalBorraAutor2").modal();
			}
		})
	});
}

function postAutor2(){
	$(".saveStudentNewName").hide();
	$(".cancelStudentNewName").hide();
	
	$(".activarStudentSNombre").click(function(){
		if(activadoAl != 0){
			$("#autorStudentName" + activadoAl).val(oldnameAl);
			$("#autorStudentName" + activadoAl).attr("disabled","true");
			$("#saveStudentAutor" + activadoAl).hide();
			$("#cancelStudentAutor" + activadoAl).hide();
			activadoAl = 0;
		}
		oldnameAl = $("#autorStudentName" + $(this).data('val')).val();
		activadoAl = $(this).data('val');
		$("#autorStudentName" + $(this).data('val')).removeAttr("disabled");
		$("#saveStudentAutor" + $(this).data('val')).show();
		$("#cancelStudentAutor" + $(this).data('val')).show();
	});
	
	$(".cancelStudentNewName").click(function(){
		$("#autorStudentName" + $(this).data('val')).val(oldnameAl);
		activadoAl = 0;
		$("#cancelStudentAutor" + $(this).data('val')).hide();
		$("#saveStudentAutor" + $(this).data('val')).hide();
		$("#autorStudentName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveStudentNewName").click(function(){
		if($("#autorStudentName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaAutor1").modal();
		}
		else{
			$.post("renameAuthorA.do",{
				idAutor : $(this).data('val'),
				newNombre : $("#autorStudentName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaAutor2").modal();
					$("#autorStudentName" + activadoAl).attr("disabled","true");
					$("#saveStudentAutor" + activadoAl).hide();
					$("#cancelStudentAutor" + activadoAl).hide();
					activadoAl = 0;
				}
				else{
					$("#autorStudentName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaAutor3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaAutor5").modal();
					}
					else{
						$("#modalModificaAutor4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteStudentSAutor").click(function(){
		$.post("removeAuthorA.do",{
			idAutor : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraAutor1").modal();
				$("#autorStudent" + json.id).remove();
			}
			else{
				$("#modalBorraAutor2").modal();
			}
		});
	});
}

function postAutor3(){
	$(".saveProfeNewName").hide();
	$(".cancelProfeNewName").hide();
	
	$(".activarProfeSNombre").click(function(){
		if(activadoPr != 0){
			$("#autorProfeName" + activadoPr).val(oldnamePr);
			$("#autorProfeName" + activadoPr).attr("disabled","true");
			$("#saveProfeAutor" + activadoPr).hide();
			$("#cancelProfeAutor" + activadoPr).hide();
			activadoPr = 0;
		}
		oldnamePr = $("#autorProfeName" + $(this).data('val')).val();
		activadoPr = $(this).data('val');
		$("#autorProfeName" + $(this).data('val')).removeAttr("disabled");
		$("#saveProfeAutor" + $(this).data('val')).show();
		$("#cancelProfeAutor" + $(this).data('val')).show();
	});
	
	$(".cancelProfeNewName").click(function(){
		$("#autorProfeName" + $(this).data('val')).val(oldnamePr);
		activadoPr = 0;
		$("#cancelProfeAutor" + $(this).data('val')).hide();
		$("#saveProfeAutor" + $(this).data('val')).hide();
		$("#autorProfeName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveProfeNewName").click(function(){
		if($("#autorProfeName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaAutor1").modal();
		}
		else{
			$.post("renameAuthorA.do",{
				idAutor : $(this).data('val'),
				newNombre : $("#autorProfeName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaAutor2").modal();
					$("#autorProfeName" + activadoPr).attr("disabled","true");
					$("#saveProfeAutor" + activadoPr).hide();
					$("#cancelProfeAutor" + activadoPr).hide();
					activadoPr = 0;
				}
				else{
					$("#autorProfeName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaAutor3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaAutor5").modal();
					}
					else{
						$("#modalModificaAutor4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteProfeSAutor").click(function(){
		$.post("removeAuthorA.do",{
			idAutor : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraAutor1").modal();
				$("#autorProfe" + json.id).remove();
			}
			else{
				$("#modalBorraAutor2").modal();
			}
		});
	});
	
	$(".validarAutor").click(function(){
		$.post("validateAuthor.do",{
			idAutor : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "validado"){
				$("#modalValidaAutor1").modal();
				$("#validado" + json.id).removeClass();
				$("#validado" + json.id).addClass("label label-success");
				$("#validado" + json.id).text($("#textVal").val())
			}
			else{
				$("#modalValidaAutor2").modal();
			}
		});
	});
}

function cargaListaCompleta(){
	$.post("completeAuthorList.do",{
		type : "table"},
		function(data){
			$("#listaCompleta").html(data);
			postAutor();
	});
}

function cargaListaAlumno(){
	$.post("studentAuthorListA.do",{
		type : "table"},
			function(data){
				$("#listaalumno").html(data);
				postAutor2();
	});
}

function cargaListaProfe(){
	$.post("teacherAuthorListA.do",{
		type : "table"},
			function(data){
				$("#listaProfe").html(data);
				postAutor3();
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
			$("#newAuthorName").val("");
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
			$("#newAuthorName").val("");
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
	
	//Filtro de autores por fila según se rellena el campo imput
	$("#filtroBusquedaCompleta").keyup(function(){
		if($("#filtroBusquedaCompleta").val().length > 0){
			$(".trautor").each(function(){
				if($(this).data("nom").indexOf($("#filtroBusquedaCompleta").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trautor").show();
		}
	});
	
	$("#busquedaProfe").keyup(function(){
		if($("#busquedaProfe").val().length > 0){
			$(".trautorP").each(function(){
				if($(this).data("nom").indexOf($("#busquedaProfe").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trautorP").show();
		}
	});
	
	$("#busquedaUsuario").keyup(function(){
		if($("#busquedaUsuario").val().length > 0){
			$(".trautorU").each(function(){
				if($(this).data("nom").indexOf($("#busquedaUsuario").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trautorU").show();
		}
	});
	
	$("#createButton").click(function(){
		if($("#newAuthorName").val().length < 1){
			$("#modalCreaAutor1").modal();
		}
		else{
			$.post("createAuthorA.do",{
				nombre : $("#newAuthorName").val()
			},function(data){
				var json = JSON.parse(data);
				if(json.message == "nameRepeated"){
					$("#modalCreaAutor3").modal();
				}
				else if(json.message == "creado"){
					cargaListaCompleta();
					if($("#listaalumno").length > 0){
						if(btncreate == 0){
							$("#group-block1").slideUp(500);
							$("#newAuthorName").val("");
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
					$("#modalCreaAutor2").modal();
				}
				else if(json.message == "noCreado"){
					$("#modalCreaAutor4").modal();
				}
			});
		}
	});
});