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
		if($("#nombreGrupo").val().length < 3){
			alert("No");
		}
		else{
			alert("Nombre grupo : " + $("#nombreGrupo").val());
			$.post("createGroupA.do",{
				nombreGrupo : $("#nombreGrupo").val(),
			}, function(data){  
				var json = JSON.parse(data);
				if(json.message == "creado"){
					alert("ok");
					$.post("belongGroupA.do",{
						idDir : $("#directorId").val(),
					}, function(responseText){
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
				else{
					alert("json.message" + json.message);
				}
			});
		}
	});
});