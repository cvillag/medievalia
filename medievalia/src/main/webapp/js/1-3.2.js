$(document).ready(function(){
	
	var userId = $("#detailId").val();
	var tamPag = $("#tamPag").val();
	var maxPag = $("#numPags").val();
	var pagAct = 1;
	var btnact = 0;
	var btngr1 = 0;
	var btngr2 = 0;
	var btngr3 = 0;
	
	$("#primero").hide();
	$("#anterior").hide();
	$("#primeros").show();
	$("#anteriores").show();
	$("#pagA").hide();
	$("#pagB").hide();
	$("#pagC").show();
	$("#pagD").show();
	$("#siguiente").show();
	$("#ultimo").show();
	$("#siguientes").hide();
	$("#ultimos").hide();
	
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
	
	
	$.post("activityUserA.do",{
		detailId : userId,
		pag : 1,
		tamPag : 10
	}, function(responseText){
		$("#activity-block").html(responseText);
	}
	);
		
	$("#siguiente").click(function(){
		pagAct++;
		alert("Página siguiente " + pagAct + " detailID " + userId + " tamPag " + tamPag );
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#anterior").click(function(){
		pagAct--;
		alert("Página siguiente " + pagAct + " detailID " + userId + " tamPag " + tamPag );
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#primero").click(function(){
		pagAct=1;
		alert("Página siguiente " + pagAct + " detailID " + userId + " tamPag " + tamPag );
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#ultimo").click(function(){
		pagAct = maxPag;
		alert("Página siguiente " + pagAct + " detailID " + userId + " tamPag " + tamPag );
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#pagA").click(function(){
		pagAct = pagAct - 2;
		alert("Página siguiente " + pagAct + " detailID " + userId + " tamPag " + tamPag );
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#pagB").click(function(){
		pagAct--;
		alert("Página siguiente " + pagAct + " detailID " + userId + " tamPag " + tamPag );
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#pagC").click(function(){
		pagAct++;
		alert("Página siguiente " + pagAct + " detailID " + userId + " tamPag " + tamPag );
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#pagD").click(function(){
		pagAct = pagAct + 2;
		alert("Página siguiente " + pagAct + " detailID " + userId + " tamPag " + tamPag );
		cargaPagina(pagAct,userId,tamPag);
	});

		

	
	$("#volver").click(function(){
		document.location = "users.do";
	});
	
	$("#displayActivity").click(function(){
		if(btnact == 0){
			$("#activity-block").slideUp(500);
			$("#displayActivity").removeClass();
			$("#displayActivity").addClass("glyphicon glyphicon-chevron-up");
			btnact = 1;
		}
		else{
			$("#activity-block").slideDown(500);
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
	
	function cargaPagina(pag,uid,tpag){
		$.post("activityUserA.do",{
			detailId : uid,
			pag: pagAct,
			tamPag : tpag
		}, function(responseText){
			$("#activity-block").html(responseText);
		});
		actualizaBotones(pag);
	}
	
	function actualizaBotones(pag){
		if(pag < 2){		
			$("#primero").hide();
			$("#primeros").show();
			$("#anterior").hide();
			$("#anteriores").show();
			$("#pagA").hide();
			$("#pagB").hide();
		}
		else{
			$("#primero").show();
			$("#primeros").hide();
			$("#anterior").show();
			$("#anteriores").hide();
			$("#pagB").show();
			if(pag < 3){
				$("#pagA").hide();
			}
			else{
				$("#pagA").show();
			}
		}
		if(pag < maxPag){
			$("#pagC").show();
			$("#siguiente").show();
			$("#siguientes").hide();
			$("#ultimo").show();
			$("#ultimos").hide();
			if(pag < (maxPag -1)){
				$("#pagD").show();
			}
			else{
				$("#pagD").hide();
			}
		}
		else{
			$("#pagC").hide();
			$("#pagD").hide();
			$("#siguiente").hide();
			$("#siguientes").show();
			$("#ultimo").hide();
			$("#ultimos").show();
		}
		$("#pagA").html(pag-2);
		$("#pagB").html(pag-1);
		$("#paginaActual").html("<u><strong>" + pag + "</strong></u></span>");
		$("#pagC").html(pag+1);
		$("#pagD").html(pag+2);
	}
});