$(document).ready(function(){
	$("#secondForm").hide();
	
	$.post("topicListA.do",
		function(data){
			$("#listatemas").html(data);
			postCarga();
	});
	
	$("#showform").click(function(){
		$("#firstForm").hide(200);
		$("#secondForm").show(200);
	});
	
	$("#cancelButton").click(function(){
		$("#firstForm").show(200);
		$("#secondForm").hide(200);
	});
	
	$(document).keypress(function(e){
		if(e.which == 13){
			if($("#nombreTema").val().length > 4){
				crear();
			}
		}
	});
	
	$("#createButton").click(crear);
	
	function crear(){
		if($("#nombreTema").val().length < 4){
			$("#modalCrearTema").modal();
		}
		else{
			$.post("createTopicA.do",{
				nombreTema : $("#nombreTema").val(),
			}, function(data){  
				var json = JSON.parse(data);
				if(json.message == "creado"){
					$("#modalCrearTema2").modal();
					$.post("topicListA.do",
						function(responseText){
						$("#listatemas").html(responseText);
						postCarga();
					}					);
					$("#nombreTema").val("");
					$("#firstForm").show(200);
					$("#secondForm").hide(200);
					
				}
				else if(json.message == "repetido"){
					$("#modalCrearGrupo3").modal();
				}
				else{
					$("#modalCrearGrupo4").modal();
					$("#firstForm").show(200);
					$("#secondForm").hide(200);
				}
			});
		}
	}
	
	function postCarga(){
		$(".topicDetail").click(function(){
			var topicId = $(this).data('val');
			$("#idTopic").val(topicId);
			$("#topicDetail").submit();
		});
	}
});