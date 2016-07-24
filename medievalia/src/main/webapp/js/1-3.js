/**
 * 
 */

$(document).ready(function(){
	
	var idUserAction;
	
	$("#adduser").click(function(){
		document.location.href='createUser.do';
	});
	
	
	$(".deleteuser").click(function(){
		idUserAction = $(this).data('val'); 
	});
	
	$("#modalok").click(function(){
		$.post("deleteUserA.do",{
			deleteId : idUserAction
		},function(data){  
				var json = JSON.parse(data);

				if(json.message == "borrado"){
					$("#fila" + idUserAction).remove();
				}
		});
	});
	
	$(".modifyuser").click(function(){
		$("#modifyId").val($(this).data('val'));
		$("#modify").submit();
		//  document.location.href='modifyUser.do';
	});
	
	$(".detailsuser").click(function(){
		$("#detailId").val($(this).data('val'));
		$("#detail").submit();
		//  document.location.href='userDetails.do';
	});
});