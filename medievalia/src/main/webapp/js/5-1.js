$(document).ready(function(){
	$.post("belongGroupA.do",{
	}, function(responseText){
		$("#group-block1").html(responseText);
	}
	);
	
	$.post("teacherGroupA.do",{
	}, function(responseText){
		$("#group-block2").html(responseText);
	}
	);
	
	$.post("studentGroupA.do",{
	}, function(responseText){
		$("#group-block3").html(responseText);
	}
	);
});