/**
 * 
 */

$(document).ready(function(){
	
	
	$("#adduser").click(function(){
		document.location.href='createUser.do';
	});
	
	
	//TODO: acciones de los botones con jquery y ajax
	$(".deleteuser").click(function(){
		alert("Borrar id: " + $(this).data('val'));
		// document.location.href='deleteUser.do';
	});
	
	$(".modifyuser").click(function(){
		alert("Modificar id: " + $(this).data('val'));
		//  document.location.href='modifyUser.do';
	});
	
	$(".detailsuser").click(function(){
		alert("Detalle de id: " + $(this).data('val'));
		//  document.location.href='userDetails.do';
	});
});