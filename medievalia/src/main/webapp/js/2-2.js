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
			$("#cargoName" + activadoCom).val(oldnameCom);
			$("#cargoName" + activadoCom).attr("disabled","true");
			$("#saveCargo" + activadoCom).hide();
			$("#cancelCargo" + activadoCom).hide();
			activadoCom = 0;
		}
		oldnameCom = $("#cargoName" + $(this).data('val')).val();
		activadoCom = $(this).data('val');
		$("#cargoName" + $(this).data('val')).removeAttr("disabled");
		$("#saveCargo" + $(this).data('val')).show();
		$("#cancelCargo" + $(this).data('val')).show();
	});
	
	$(".cancelNewName").click(function(){
		$("#cargoName" + $(this).data('val')).val(oldnameCom);
		activadoCom = 0;
		$("#cancelCargo" + $(this).data('val')).hide();
		$("#saveCargo" + $(this).data('val')).hide();
		$("#cargoName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveNewName").click(function(){
		if($("#cargoName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaCargo1").modal();
		}
		else{
			$.post("renameChargeA.do",{
				idCargo : $(this).data('val'),
				newNombre : $("#cargoName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaCargo2").modal();
					$("#cargoName" + activadoCom).attr("disabled","true");
					$("#saveCargo" + activadoCom).hide();
					$("#cancelCargo" + activadoCom).hide();
					activadoCom = 0;
				}
				else{
					$("#cargoName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaCargo3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaCargo5").modal();
					}
					else{
						$("#modalModificaCargo4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteSCargo").click(function(){
		$.post("removeChargeA.do",{
			idCargo : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraCargo1").modal();
				$("#cargo" + json.id).remove();
			}
			else{
				$("#modalBorraCargo2").modal();
			}
		})
	});
}

function postCarga2(){
	$(".saveStudentNewName").hide();
	$(".cancelStudentNewName").hide();
	
	$(".activarStudentSNombre").click(function(){
		if(activadoAl != 0){
			$("#cargoStudentName" + activadoAl).val(oldnameAl);
			$("#cargoStudentName" + activadoAl).attr("disabled","true");
			$("#saveStudentCargo" + activadoAl).hide();
			$("#cancelStudentCargo" + activadoAl).hide();
			activadoAl = 0;
		}
		oldnameAl = $("#cargoStudentName" + $(this).data('val')).val();
		activadoAl = $(this).data('val');
		$("#cargoStudentName" + $(this).data('val')).removeAttr("disabled");
		$("#saveStudentCargo" + $(this).data('val')).show();
		$("#cancelStudentCargo" + $(this).data('val')).show();
	});
	
	$(".cancelStudentNewName").click(function(){
		$("#cargoStudentName" + $(this).data('val')).val(oldnameAl);
		activadoAl = 0;
		$("#cancelStudentCargo" + $(this).data('val')).hide();
		$("#saveStudentCargo" + $(this).data('val')).hide();
		$("#cargoStudentName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveStudentNewName").click(function(){
		if($("#cargoStudentName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaCargo1").modal();
		}
		else{
			$.post("renameChargeA.do",{
				idCargo : $(this).data('val'),
				newNombre : $("#cargoStudentName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaCargo2").modal();
				}
				else{
					$("#cargoStudentName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaCargo3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaCargo5").modal();
					}
					else{
						$("#modalModificaCargo4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteStudentSCargo").click(function(){
		$.post("removeChargeA.do",{
			idCargo : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraCargo1").modal();
				$("#cargoStudent" + json.id).remove();
			}
			else{
				$("#modalBorraCargo2").modal();
			}
		});
	});
}

function cargaListaCompleta(){
	$.post("completeChargeList.do",{
		type : "table"},
		function(data){
			$("#listaCompleta").html(data);
			postCarga();
	});
}

function cargaListaAlumno(){
	$.post("studentChargeListA.do",{
		type : "table"},
			function(data){
				$("#listaalumno").html(data);
				postCarga2();
	});
}

function cargaListaProfe(){
	$.post("teacherChargeListA.do",{
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
			$("#newChargeName").val("");
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
			$("#newChargeName").val("");
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
	
	//Filtro de cargos por fila según se rellena el campo imput
	$("#filtroBusquedaCompleta").keyup(function(){
		if($("#filtroBusquedaCompleta").val().length > 0){
			$(".trcargo").each(function(){
				if($(this).data("nom").indexOf($("#filtroBusquedaCompleta").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trcargo").show();
		}
	});
	
	$("#createButton").click(function(){
		if($("#newChargeName").val().length < 1){
			$("#modalCreaCargo1").modal();
		}
		else{
			$.post("createChargeA.do",{
				nombre : $("#newChargeName").val()
			},function(data){
				var json = JSON.parse(data);
				if(json.message == "nameRepeated"){
					$("#modalCreaCargo3").modal();
				}
				else if(json.message == "creado"){
					cargaListaCompleta();
					if($("#listaalumno").length > 0){
						cargaListaAlumno();
					}
					else if($("#listaProfe").length > 0){
						cargaListaProfe();
					}
					$("#modalCreaCargo2").modal();
				}
				else if(json.message == "noCreado"){
					$("#modalCreaCargo4").modal();
				}
			});
		}
	});
});