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
				$("#modalRenTema").modal();
			}
			else{
				$.post("renameUserA.do",
					{topicId : $("#idTema").val(),
					nombreTema : $("#nombreTema").val()},
					function(data){
						var json = JSON.parse(data);
						if(json.message == "noLength"){
							$("#modalRenTema").modal();
						}
						else if(json.message == "nameRepeat"){
							$("#modalRenTema3").modal();
						}
						else if(json.message == "noPrivileges" || json.message == "noCambiado"){
							$("#modalRenTema4").modal();
						}
						else if(json.message == "cambiado"){
							$("#modalRenTema2").modal();
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
	
	$("#createButton").click(function(){
		crearS();
		$.post("subtopicListA.do",
				{idTema : idTema},
				function(data){
					$("#listasubtemas").html(data);
		});
		postCarga();
	});
	
	function crearS(){
		if($("#nombreSTema").val().length < 4){
			$("#modalCreaSTema2").modal();
		}
		else{
			$.post("createSTopicA.do",{
				nombreSTema : $("#nombreSTema").val(),
				idTema : $("#idTema").val()
			}, function(data){  
				var json = JSON.parse(data);
				if(json.message == "creado"){
					$("#modalCreaSTema").modal();
					$.post("subtopicListA.do",
							{idTema : idTema},
							function(data){
								$("#listasubtemas").html(data);
								//postCarga();
					});
					$("#nombreSTema").val("");
					$("#firstForm").show(200);
					$("#secondForm").hide(200);
					
				}
				else{ 
					if(json.message == "nameRepeat"){
						$("#modalCreaSTema3").modal();
					}
					else{ 
						if(json.message == "noTema" || json.message == "noGroup" || json.message == "noCreado"){
							$("#modalCreaSTema4").modal();
							$("#firstForm").show(200);
							$("#secondForm").hide(200);
						}
						else{
							if(json.message = "noLength"){
								$("#modalCreaSTema2").modal();
							}
						}
					}
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