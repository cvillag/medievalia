	var grSel = $("#groupSelected").val();
	var btngr1 = 0;
	var btngr2 = 0;
	var btngr3 = 0;
	var userId = $("#idUser").val();

$(document).ready(function(){
	
	$.post("belongGroupA.do",{
		idDir : userId,
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
	
	$.post("teacherGroupA.do",{
		idTeach : userId,
	}, function(responseText){
		$("#group-block2").html(responseText);
		$(".selectgrp").click(function(){
			idGroup = $(this).data('val');
			grSel = idGroup;
			$("#idGroup").val(grSel);
			$("#activeGroupForm").submit();
		});
	}
	);
	$.post("studentGroupA.do",{
		idStud : userId,
	}, function(responseText){
		$("#group-block3").html(responseText);
		$(".selectgrp").click(function(){
			idGroup = $(this).data('val');
			grSel = idGroup;
			$("#idGroup").val(grSel);
			$("#activeGroupForm").submit();
		});
	}
	);
	
	$("#displayGroup1").click(function(){
		display1();
	});

	$("#displayGroup2").click(function(){
		display2();
	});

	$("#displayGroup3").click(function(){
		display3();
	});
	
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
			$("#nameGroupForm").addClass("has-error");
			$("#modalCrearGrupo2").modal();
		}
		else{
			$("#nameGroupForm").removeClass("has-error");
			$.post("createGroupA.do",{
				nombreGrupo : $("#nombreGrupo").val(),
				descripcionGrupo : $("#descripcionGrupo").val()
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
					$("#nameGroupForm").addClass("has-error");
					$("#modalCrearGrupo3").modal();
				}
			});
		}
	});
});

function display1(){
	if(btngr1 == 0){
		$("#group-block1").slideUp(500);
		$("#displayGroup1i").removeClass();
		$("#displayGroup1i").addClass("glyphicon glyphicon-chevron-down");
		btngr1 = 1;
	}
	else{
		$("#group-block1").slideDown(500);
		$("#displayGroup1i").removeClass();
		$("#displayGroup1i").addClass("glyphicon glyphicon-chevron-up");
		btngr1 = 0;
	}
}

function display2(){
	if(btngr2 == 0){
		$("#group-block2").slideUp(500);
		$("#displayGroup2i").removeClass();
		$("#displayGroup2i").addClass("glyphicon glyphicon-chevron-down");
		btngr2 = 1;
	}
	else{
		$("#group-block2").slideDown(500);
		$("#displayGroup2i").removeClass();
		$("#displayGroup2i").addClass("glyphicon glyphicon-chevron-up");
		btngr2 = 0;
	}
}

function display3(){	
	if(btngr3 == 0){
		$("#group-block3").slideUp(500);
		$("#displayGroup3i").removeClass();
		$("#displayGroup3i").addClass("glyphicon glyphicon-chevron-down");
		btngr3 = 1;
	}
	else{
		$("#group-block3").slideDown(500);
		$("#displayGroup3i").removeClass();
		$("#displayGroup3i").addClass("glyphicon glyphicon-chevron-up");
		btngr3 = 0;
	}
}