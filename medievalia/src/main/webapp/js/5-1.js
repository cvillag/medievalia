$(document).ready(function(){
	
	display1();
	display2();
	display3();
	
	$("#role").val($.trim($("#role").val()));
	
	$("#modificarUser2").hide();
	$("#modificarPass2").hide();
	$("#passForm1").hide();
	$("#passForm2").hide();
	$("#passForm3").hide();
	
	$.post("belongGroupA.do",{
	}, function(responseText){
		$("#group-block1").html(responseText);
	});
	
	$.post("teacherGroupA.do",{
	}, function(responseText){
		$("#group-block2").html(responseText);
	});
	
	$.post("studentGroupA.do",{
	}, function(responseText){
		$("#group-block3").html(responseText);
	});
	
	$("#btnU-modificar").click(function(){
		$("#modificarUser2").show();
		$("#modificarUser").hide();
		$("#nombre").prop('disabled',false);
		$("#nombreC").prop('disabled',false);
	});
	
	$("#btnP-modificar").click(function(){
		$("#passForm1").show();
		$("#passForm2").show();
		$("#passForm3").show();
		$("#pass1").prop('disabled',false);
		$("#pass2").prop('disabled',false);
		$("#pass3").prop('disabled',false);
		$("#modificarPass").hide();
		$("#modificarPass2").show();
	});
	
	$("#btnU-cancel").click(function(){
		$("#modificarUser2").hide();
		$("#modificarUser").show();
		$("#nombre").val($("#nameO").val());
		$("#nombreC").val($("#nameLO").val());
		$("#nombre").prop('disabled',true);
		$("#nombreC").prop('disabled',true);
	});
	
	$("#btnP-cancel").click(function(){
		$("#modificarPass2").hide();
		$("#passForm1").hide();
		$("#passForm2").hide();
		$("#passForm3").hide();
		$("#modificarPass").show();
		$("#pass1").prop('disabled',true);
		$("#pass1").val("");
		$("#pass2").prop('disabled',true);
		$("#pass2").val("");
		$("#pass3").prop('disabled',true);
		$("#pass3").val("");
	});
		
	$("#modifyForm").submit(function(e){
		e.preventDefault();
	});
	
	$("#btnU-guardar").click(function(){
		$.post("modifyUserA.do",
				{nombre : $("#nombre").val(),
				nombreC : $("#nombreC").val()},
				function(data){
					alert(data);
				}
		);
	});
	
});