$(document).ready(function(){
	
	var btnact = 0;
	var btngr1 = 0;
	var btngr2 = 0;
	var btngr3 = 0;
	
	//TODO: desactivar botones cuando no se pueda ir en una dirección u otra
	$("#siguiente").click(function(){
		var pag = $("#actual").val();
		pag++;
		$("#pag").val(pag);
//		alert("Página siguiente " + $("#pag").val());
		$("#pagForm").submit();
	});
	
	$("#anterior").click(function(){
		var pag = $("#actual").val();
		pag--;
		$("#pag").val(pag);
//		alert("Página siguiente " + $("#pag").val());
		$("#pagForm").submit();
	});
	
	$("#primero").click(function(){
		$("#pag").val(1);
//		alert("Página siguiente " + $("#pag").val());
		$("#pagForm").submit();
	});
	
	$("#ultimo").click(function(){
		$("#pag").val($("#maxPag").val());
//		alert("Página siguiente " + $("#pag").val());
		$("#pagForm").submit();
	});
	
	$("#volver").click(function(){
		document.location = "users.do";
	});
	
	$("#displayActivity").click(function(){
		if(btnact == 0){
			$("#activity-block").slideUp(500);
			$("#activity-block2").slideUp(500);
			$("#activity-block3").slideUp(500);
			$("#displayActivity").removeClass();
			$("#displayActivity").addClass("glyphicon glyphicon-chevron-up");
			btnact = 1;
		}
		else{
			$("#activity-block").slideDown(500);
			$("#activity-block2").slideDown(500);
			$("#activity-block3").slideDown(500);
			$("#displayActivity").removeClass();
			$("#displayActivity").addClass("glyphicon glyphicon-chevron-down");
			btnact = 0;
		}
	});
	
	$("#displayGroup1").click(function(){
		if(btnact == 0){
			$("#group-block1").slideUp(500);
			$("#displayGroup1").removeClass();
			$("#displayGroup1").addClass("glyphicon glyphicon-chevron-up");
			btnact = 1;
		}
		else{
			$("#group-block1").slideDown(500);
			$("#displayGroup1").removeClass();
			$("#displayGroup1").addClass("glyphicon glyphicon-chevron-down");
			btnact = 0;
		}
	});

	$("#displayGroup2").click(function(){
		if(btnact == 0){
			$("#group-block2").slideUp(500);
			$("#displayGroup2").removeClass();
			$("#displayGroup2").addClass("glyphicon glyphicon-chevron-up");
			btnact = 1;
		}
		else{
			$("#group-block2").slideDown(500);
			$("#displayGroup2").removeClass();
			$("#displayGroup2").addClass("glyphicon glyphicon-chevron-down");
			btnact = 0;
		}
	});

	$("#displayGroup3").click(function(){
		if(btnact == 0){
			$("#group-block3").slideUp(500);
			$("#displayGroup3").removeClass();
			$("#displayGroup3").addClass("glyphicon glyphicon-chevron-up");
			btnact = 1;
		}
		else{
			$("#group-block3").slideDown(500);
			$("#displayGroup3").removeClass();
			$("#displayGroup3").addClass("glyphicon glyphicon-chevron-down");
			btnact = 0;
		}
	});
	
	$("#pagA").click(function(){
		$("#pag").val($("#pagp2").val());
		$("#pagForm").submit();
	});
	
	$("#pagB").click(function(){
		$("#pag").val($("#pagp1").val());
		$("#pagForm").submit();
	});
	
	$("#pagC").click(function(){
		$("#pag").val($("#pagm1").val());
		$("#pagForm").submit();
	});
	
	$("#pagD").click(function(){
		$("#pag").val($("#pagm2").val());
		$("#pagForm").submit();
	});
});