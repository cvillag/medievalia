/**
 * 
 */

$(document).ready(function(){
	
	
	$("#adduser").click(function(){
		document.location.href='createUser.do';
	});
	
	
	//TODO: acciones de los botones con jquery y ajax
	$(".deleteuser").click(function(){
		$("#deleteId").val($(this).data('val'));
		$("#deletion").submit();
	});
	
	$(".modifyuser").click(function(){
		$("#modifyId").val($(this).data('val'));
		$("#modify").submit();
		//  document.location.href='modifyUser.do';
	});
	
	$(".detailsuser").click(function(){
		alert("Detalle de id: " + $(this).data('val'));
		$("#detailId").val($(this).data('val'));
		$("#detail").submit();
		//  document.location.href='userDetails.do';
	});
	
	$( "#deletion" ).submit(function( event ) {
		  alert( "Handler for .submit() called." );
	});
});