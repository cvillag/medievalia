$(document).ready(function(){
	var btngr1 = 0;
	var btngr2 = 0;
	var btngr3 = 0;
	var userId = $("#idUser").val();
	
	$.post("belongGroupA.do",{
		idDir : userId,
	}, function(responseText){
		$("#group-block1").html(responseText);
	}
	);
	
	$.post("teacherGroupA.do",{
		idTeach : userId,
	}, function(responseText){
		$("#group-block2").html(responseText);
	}
	);
	$.post("studentGroupA.do",{
		idStud : userId,
	}, function(responseText){
		$("#group-block3").html(responseText);
	}
	);
	
	$("#displayGroup1").click(function(){
		if(btngr1 == 0){
			$("#group-block1").slideUp(500);
			$("#displayGroup1i").removeClass();
			$("#displayGroup1i").addClass("glyphicon glyphicon-chevron-up");
			btngr1 = 1;
		}
		else{
			$("#group-block1").slideDown(500);
			$("#displayGroup1i").removeClass();
			$("#displayGroup1i").addClass("glyphicon glyphicon-chevron-down");
			btngr1 = 0;
		}
	});

	$("#displayGroup2").click(function(){
		if(btngr2 == 0){
			$("#group-block2").slideUp(500);
			$("#displayGroup2i").removeClass();
			$("#displayGroup2i").addClass("glyphicon glyphicon-chevron-up");
			btngr2 = 1;
		}
		else{
			$("#group-block2").slideDown(500);
			$("#displayGroup2i").removeClass();
			$("#displayGroup2i").addClass("glyphicon glyphicon-chevron-down");
			btngr2 = 0;
		}
	});

	$("#displayGroup3").click(function(){
		if(btngr3 == 0){
			$("#group-block3").slideUp(500);
			$("#displayGroup3i").removeClass();
			$("#displayGroup3i").addClass("glyphicon glyphicon-chevron-up");
			btngr3 = 1;
		}
		else{
			$("#group-block3").slideDown(500);
			$("#displayGroup3i").removeClass();
			$("#displayGroup3i").addClass("glyphicon glyphicon-chevron-down");
			btngr3 = 0;
		}
	});
});