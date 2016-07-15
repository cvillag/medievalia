$(document).ready(function(){
	
	$("#siguiente").click(function(){
		var pag = $("#actual").val();
		pag++;
		$("#pag").val(pag);
		alert("Página siguiente " + $("#pag").val());
		$("#pagForm").submit();
	});
	
	$("#anterior").click(function(){
		var pag = $("#actual").val();
		pag--;
		$("#pag").val(pag);
		alert("Página siguiente " + $("#pag").val());
		$("#pagForm").submit();
	});
	
	$("#primero").click(function(){
		$("#pag").val(1);
		alert("Página siguiente " + $("#pag").val());
		$("#pagForm").submit();
	});
	
	$("#ultimo").click(function(){
		$("#pag").val(50);
		alert("Página siguiente " + $("#pag").val());
		$("#pagForm").submit();
	});
});