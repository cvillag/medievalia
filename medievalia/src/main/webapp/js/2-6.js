$(document).ready(function(){
	
	var idTema = $("#idTema").val();
	
	$("#activarNombre").show();
	$("#desactivarNombre").hide();
	$("#cambiarNombre").hide();
	
	$("#firstForm").show();
	$("#secondForm").hide();
	
	$.post("subtopicListA.do",
			{idTema : idTema},
			function(data){
				$("#listasubtemas").html(data);
				//postCarga();
	});
	
	$("#showform").click(function(){
		$("#firstForm").hide(200);
		$("#secondForm").show(200);
	});
	
	$("#cancelButton").click(function(){
		$("#firstForm").show(200);
		$("#secondForm").hide(200);
	});
	
	$("#activarNombre").click(function(){
		$("#activarNombre").hide();
		$("#desactivarNombre").show();
		$("#cambiarNombre").show();
		$("#nombreTema").prop('disabled',false);
	});
	
	$("#cambiarNombre").click(function(){
		if($("#nombreTema").val() == $("#topicName").val()){
			cancelName();
		}
		else{
			if($("#nombreTema").val().length < 4){
				$("#modalCrearSTema").modal();
			}
			else{
				$.post("renameUserA.do",
					{topicId : $("#idTema").val(),
					nombreTema : $("#nombreTema").val()},
					function(data){
						var json = JSON.parse(data);
						alert(json.message);
						if(json.message == "noLength"){
							$("#modalCrearSTema").modal();
						}
						else if(json.message == "nameRepeat"){
							$("#modalCrearSubGrupo3").modal();
						}
						else if(json.message == "noPrivileges" || json.message == "noCambiado"){
							$("#modalCrearSubGrupo4").modal();
						}
						else if(json.message == "cambiado"){
							$("#modalCrearSTema2").modal();
						}
					});
			}
		}
	});
	
	$("#desactivarNombre").click(function(){
		cancelName();
	});
	
	function cancelName(){
		$("#activarNombre").show();
		$("#desactivarNombre").hide();
		$("#cambiarNombre").hide();
		var tit = $("#topicName").val();
		$("#nombreTema").val(tit);
		$("#nombreTema").prop('disabled',true);
	}
	
	function crearS(){
		if($("#nombreSubTema").val().length < 4){
			$("#modalCrearSubTema").modal();
		}
		else{
			$.post("createSTopicA.do",{
				nombreTema : $("#nombreSubTema").val(),
			}, function(data){  
				var json = JSON.parse(data);
				if(json.message == "creado"){
					$("#modalCrearSTema2").modal();
					$.post("subtopicListA.do",
						function(responseText){
						$("#listasubtemas").html(responseText);
						postCarga();
					}					);
					$("#nombreSTema").val("");
					$("#firstForm").show(200);
					$("#secondForm").hide(200);
					
				}
				else if(json.message == "repetido"){
					$("#modalCrearSubGrupo3").modal();
				}
				else{
					$("#modalCrearSubGrupo4").modal();
					$("#firstForm").show(200);
					$("#secondForm").hide(200);
				}
			});
		}
	}
	
	function postCarga(){
		$(".subtopicDetail").click(function(){
			var stopicId = $(this).data('val');
			$("#idSubTopic").val(topicId);
			alert("Detalle de " + $("#idsubTopic").val());
			$("#subtopicDetail").submit();
		});
	}
});