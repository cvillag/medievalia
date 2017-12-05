$(document).ready(function(){
	
	var userId = $("#detailId").val();
	var tamPag = $("#tamPag").val();
	var maxPag = $("#numPags").val();
	var pagAct = 1;
	var btnact = 0;
	
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
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#anterior").click(function(){
		pagAct--;
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#primero").click(function(){
		pagAct=1;
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#ultimo").click(function(){
		pagAct = maxPag;
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#pagA").click(function(){
		pagAct = pagAct - 2;
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#pagB").click(function(){
		pagAct--;
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#pagC").click(function(){
		pagAct++;
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#pagD").click(function(){
		pagAct = pagAct + 2;
		cargaPagina(pagAct,userId,tamPag);
	});
	
	$("#volver").click(function(){
		document.location = "users.do";
	});
	
	$("#displayActivity").click(function(){
		if(btnact == 0){
			$("#activity-block").slideUp(500);
			$("#displayActivityi").removeClass();
			$("#displayActivityi").addClass("glyphicon glyphicon-chevron-up");
			btnact = 1;
		}
		else{
			$("#activity-block").slideDown(500);
			$("#displayActivityi").removeClass();
			$("#displayActivityi").addClass("glyphicon glyphicon-chevron-down");
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