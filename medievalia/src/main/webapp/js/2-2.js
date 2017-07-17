function postCarga(){
	//alert("Acciones de botones");
}

function cargaListaCompleta(){
	$.post("completeChargeList.do",{
		type : "table"},
		function(data){
			$("#listaCompleta").html(data);
			postCarga();
		});
}

var btncreate = 0;

$(document).ready(function(){
	
	$("#group-block1").hide();
	
	cargaListaCompleta();
	
	$("#displayCreate").click(function(){
		if(btncreate == 0){
			$("#group-block1").slideUp(500);
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
				cargaListaCompleta();
				$("#modalCreaCargo2").modal();
			});
		}
	});
});