$(document).ready(function(){
	$("#secondForm").hide();
	
	$("#showform").click(function(){
		$("#firstForm").hide(200);
		$("#secondForm").show(200);
	});
	
	$("#cancelButton").click(function(){
		$("#firstForm").show(200);
		$("#secondForm").hide(200);
	});
	
	$("#createButton").click(function(){
		if($("#nombreGrupo").val().length < 4){
			$("#modalCrearGrupo2").modal();
		}
		else{
			$.post("createGroupA.do",{
				nombreGrupo : $("#nombreGrupo").val(),
			}, function(data){  
				var json = JSON.parse(data);
				if(json.message == "creado"){
					$("#modalCrearGrupo").modal();
					$.post("belongGroupA.do",
						function(responseText){
						$("#group-block1").html(responseText);
						$(".selectgrp").click(function(){
							idGroup = $(this).data('val');
							grSel = idGroup;
							$("#idGroup").val(grSel);
							$("#activeGroupForm").submit();
						});
					}
					);
					$("#nombreGrupo").val("");
					$("#firstForm").show(200);
					$("#secondForm").hide(200);
					
				}
				else if(json.message == "nameRepeat"){
					$("#modalCrearGrupo3").modal();
				}
			});
		}
	});
});