$(document).ready(function(){
	
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
});