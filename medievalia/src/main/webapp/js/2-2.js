function postCarga(){
	//alert("Acciones de botones");
	
	$(".saveNewName").hide();
	$(".cancelNewName").hide();
	
	$(".activarSNombre").click(function(){
		$("#cargoName" + $(this).data('val')).removeAttr("disabled");
		$("#saveCargo" + $(this).data('val')).show();
		$("#cancelCargo" + $(this).data('val')).show();
	});
	
	$(".cancelNewName").click(function(){
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
	$.post("studentChargeListA.do",
			function(data){
				$("#listaalumno").html(data);
				postCarga2();
	});
}

function cargaListaProfe(){
	$.post("teacherChargeListA.do",
			function(data){
				$("#listaProfe").html(data);
				postCarga2();
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
					$("#modalCreaCargo2").modal();
				}
				else if(json.message == "noCreado"){
					$("#modalCreaCargo4").modal();
				}
			});
		}
	});
});