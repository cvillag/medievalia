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
				$(".saveNewName").hide();
				$(".cancelNewName").hide();
				$(".activarSNombre").click(function(){
					$("#st" + $(this).data('val')).removeAttr("disabled");
					$("#saveSt" + $(this).data('val')).show();
					$("#cancelSt" + $(this).data('val')).show();
				});
				//postCarga();
				
				$(".cancelNewName").click(function(){
					$("#cancelSt" + $(this).data('val')).hide();
					$("#saveSt" + $(this).data('val')).hide();
					$("#st" + $(this).data('val')).attr("disabled","true");
				});
				
				$(".saveNewName").click(function(){
					$.post("renameSubtopicA.do",
						{idTema : $("#idTema").val(),
						idSubtema : $(this).data('val'),
						newName : $("#st"+$(this).data('val')).val()},
						function(data){
							var json = JSON.parse(data);
							if(json.message == "cambiado"){
								$("#modalRenSTema1").modal();
								$("#cancelSt" + json.id).hide();
								$("#saveSt" + json.id).hide();
								$("#st" + json.id).attr("disabled","true");
							}
							else if(json.message == "noTopic"){
								$("#modalRenSTema2").modal();
							}
							else if(json.message == "noSubTopic"){
								$("#modalRenSTema3").modal();
							}
							else if(json.message == "topicMismatch"){
								$("#modalRenSTema4").modal();
							}
							else if(json.message == "noPrivileges"){
								$("#modalRenSTema5").modal();
							}
							
						}
					);
				});
				
				$(".deleteSTopic").click(function(){
					$.post("subtopicDeletion.do",
							{idTema : $("#idTema").val(),
							idSubtema : $(this).data('val')
							},
							function(data){
								var json = JSON.parse(data);
								if(json.message == "borrado"){
									$("#strow" + json.idSubtema).remove();
									$("#modalDelSTema1").modal();
								}
								else if(json.message == "noSubTopic"){
									$("#modalDelSTema2").modal();
								}
								else if(json.message == "noTopic"){
									$("#modalDelSTema3").modal();
								}
								else if(json.message == "topicMismatch"){
									$("#modalDelSTema4").modal();
								}
								else{
									$("#modalDelSTema5").modal();
								}
							}
					);
				});
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
				$.post("renameTopicA.do",
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