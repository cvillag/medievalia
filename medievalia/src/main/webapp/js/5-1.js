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
		if($("#nombre").val().length < 4){
			$("#modalModUser4").modal();
			$("#formNombre").addClass("has-error");
		}
		else if($("#nombreC").val().length < 4){
			$("#formNombre").removeClass("has-error");
			$("#formNombreC").addClass("has-error");
			$("#modalModUser4").modal();
		}
		else{
			$("#formNombre").removeClass("has-error");
			$("#formNombreC").removeClass("has-error");
			$.post("modifyUserA.do",
					{nombre : $("#nombre").val(),
					nombreC : $("#nombreC").val()},
					function(data){
						var json = JSON.parse(data);
						if(json.message == "nameRepeat"){
							$("#nombre").val($("#nameO").val());
							$("#nombreC").val($("#nameLO").val());
							$("#formNombre").addClass("has-error");
							$("#modalModUser").modal();
						}
						else if(json.message == "p1.3.modifyok"){
							$("#modalModUser2").modal();
							$("#formNombre").removeClass("has-error");
							$("#formNombreC").removeClass("has-error");
						}
						else if(json.message == "p1-3.1.error.nok"){
							$("#nombre").val($("#nameO").val());
							$("#nombreC").val($("#nameLO").val());
							$("#modalModUser3").modal();
						}
						else if(json.message == "noLength"){
							$("#nombre").val($("#nameO").val());
							$("#nombreC").val($("#nameLO").val());
							$("#formNombre").addClass("has-error");
							$("#formNombreC").addClass("has-error");
							$("#modalModUser4").modal();
						}
					}
			);
		}
	});
	
	$("#btnP-guardar").click(function(){
		$("#passForm1").removeClass("has-error");
		if($("#pass2").val().length < 8){
				$("#modalModPass4").modal();
				$("#passForm2").addClass("has-error");
			}
			else{
				$("#passForm2").removeClass("has-error");
				if($("#pass3").val().length < 8){
					$("#modalModPass4").modal();
					$("#passForm3").addClass("has-error");
				}
				else{
					$("#passForm3").removeClass("has-error");
					if($("#pass2").val() != $("#pass3").val()){
						$("#modalModPass6").modal();
						$("#passForm2").addClass("has-error");
						$("#passForm3").addClass("has-error");
					}
					else{
						$("#passForm2").removeClass("has-error");
						$("#passForm3").removeClass("has-error");
						$.post("modifyUserPassA.do",
								{pass1 : $("#pass1").val(),
								pass2 : $("#pass2").val(),
								pass3 : $("#pass3").val()},
								function(data){
									var json = JSON.parse(data);
									if(json.message == "p1.3.modifyok"){
										$("#modalModPass2").modal();
									}
									else if(json.message == "p1-3.1.error.nok"){
										vaciaPass();
										$("#modalModPass3").modal();
									}
									else if(json.message == "noLength"){
										vaciaPass();
										$("#modalModPass4").modal();
										$("#passForm1").addClass("has-error");
										$("#passForm2").addClass("has-error");
										$("#passForm3").addClass("has-error");
									}
									else if(json.message == "mismatchPass1"){
										vaciaPass();
										$("#modalModPass5").modal();
										$("#passForm1").addClass("has-error");
										$("#passForm2").removeClass("has-error");
										$("#passForm3").removeClass("has-error");
									}
									else if(json.message == "mismatchPass2"){
										vaciaPass();
										$("#modalModPass6").modal();
										$("#passForm1").removeClass("has-error");
										$("#passForm2").addClass("has-error");
										$("#passForm3").addClass("has-error");
									}
								}
						);
					}
				}
			}
	});
});

function vaciaPass(){
	$("#pass1").val("");
	$("#pass2").val("");
	$("#pass3").val("");
}