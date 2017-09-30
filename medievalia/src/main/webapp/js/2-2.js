var oldnameCom;
var activadoCom = 0;
var oldnameAl;
var activadoAl = 0;
var oldnamePr;
var activadoPr = 0;

var modDetAct = 0;
var oldModDetAct = 0;

var idInstanciaModificar = 0;
var pag = 0;
var pagCarga = 0;
var cambioAtrSim = 0;
var firstC = 0;

function postCarga(){
	//alert("Acciones de botones");
	
	$(".saveNewName").hide();
	$(".cancelNewName").hide();
	
	$(".activarSNombre").click(function(){
		if(activadoCom != 0){
			$("#objetoName" + activadoCom).val(oldnameCom);
			$("#objetoName" + activadoCom).attr("disabled","true");
			$("#saveObjeto" + activadoCom).hide();
			$("#cancelObjeto" + activadoCom).hide();
			activadoCom = 0;
		}
		oldnameCom = $("#objetoName" + $(this).data('val')).val();
		activadoCom = $(this).data('val');
		$("#objetoName" + $(this).data('val')).removeAttr("disabled");
		$("#saveObjeto" + $(this).data('val')).show();
		$("#cancelObjeto" + $(this).data('val')).show();
	});
	
	$(".cancelNewName").click(function(){
		$("#objetoName" + $(this).data('val')).val(oldnameCom);
		activadoCom = 0;
		$("#cancelObjeto" + $(this).data('val')).hide();
		$("#saveObjeto" + $(this).data('val')).hide();
		$("#objetoName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveNewName").click(function(){
		if($("#objetoName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaObjeto1").modal();
		}
		else{
			$.post("renameObjectA.do",{
				idInstancia : $(this).data('val'),
				newNombre : $("#objetoName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaObjeto2").modal();
					$("#objetoName" + activadoCom).attr("disabled","true");
					$("#saveObjeto" + activadoCom).hide();
					$("#cancelObjeto" + activadoCom).hide();
					activadoCom = 0;
				}
				else{
					$("#objetoName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaObjeto3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaObjeto5").modal();
					}
					else{
						$("#modalModificaObjeto4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteSObjeto").click(function(){
		$.post("removeObjectA.do",{
			idInstancia : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraObjeto1").modal();
				$("#objeto" + json.id).remove();
			}
			else{
				$("#modalBorraObjeto2").modal();
			}
		})
	});
	
	$(".modifySObjeto").click(function(){
		$("#contenidoDetalle").empty();
		oldModDetAct = 0;
		modDetAct = 0;
		name = $(this).data("name");
		$("#nombreObjetoDetalle2").html(name);
		$("#modalDetalleObjeto2").modal();
		idInstanciaModificar = $(this).data('val');
		$.post("objectDetail.do",{
			idInstancia : $(this).data('val'),
			modo : 2
		},
		function(data){
			$("#contenidoDetalle2").html(data);
			var i = 0;
			var s = $(".modDetAtributosC").size();
			$(".modDetAtributosC").each(function(){
				//La variable pagCarga no puede ser javascript, debe venir en data. Pasar data a JSON con la página y los objetos. Construir botones y luego postCargaDetalle()
				pagCarga = $(this).data('num');
				if(++i == s){
					cargaAtributosComplejosPorPagina(pagCarga,1);
				}
				else{
					cargaAtributosComplejosPorPagina(pagCarga,0);
				}
			});
			//postCargaDetalle2();
			postCargaDetalle(1);
		});
	});
	
	$(".detalleObjeto").click(function(){
		$("#contenidoDetalle2").empty();
		oldModDetAct = 0;
		modDetAct = 0;
		id = $(this).data("val");
		name = $(this).data("name");
		$("#nombreObjetoDetalle").html(name);
		$("#modalDetalleObjeto").modal();
		$.post("objectDetail.do",{
			idInstancia : id,
			modo : 1
		},
		function(data){
			$("#contenidoDetalle").html(data);
			postCargaDetalle(0);
		});
	});
}

function cargaAtributosComplejosPorPagina(pag2,recarga){
	$("#modDetAtributos" + pag2).empty();
	$.post("objectDetailComplexAttributes.do",{
		idInstancia : idInstanciaModificar,
		modo : 2,
		pag : pag2,
		recarga: recarga
	},
	function(data){
		var json = JSON.parse(data);
		var pagina = json.pag;
		var obj = json.actual;
		var disp = json.disponibles;
		var recarga = json.recarga;
		$("#modDetAtributos" + pagina).append('<div class="row" id="rowPag'+pagina+'"></div>');
		$("#rowPag"+pagina).append('<div class="col-xs-6 form-group"><label>Actual</fmt:message></label><ul class="list-group" id="list'+pagina+'"></ul></div>');
		for(var d in obj){
			var nom = obj[d].instanciaHijo.nombre;
			var tipo = obj[d].instanciaHijo.tipo.tipoDOM;
			var idInst = obj[d].instanciaHijo.idInstancia;
			$("#list"+pagina).append('<li class="list-group-item" id="'+tipo+'-'+idInst+'"><button type="button" id="remAtC'+tipo+'-'+idInst+'" class="btn btn-xs btn-default remComplexAttribute" data-tipo="'+pagina+'" data-inst="'+idInst+'" data-pag="'+pagina+'" data-name="'+nom+'">&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></button>'+nom+'</li>');
		}
		
		$("#rowPag"+pagina).append('<div class="col-xs-6 form-group"><label>Disponible</fmt:message></label><ul class="list-group" id="listD'+pagina+'"></ul></div>');
		for(var d in disp){
			var nom = disp[d].instanciaHijo.nombre;
			var tipo = disp[d].instanciaHijo.tipo.tipoDOM;
			var idInst = disp[d].instanciaHijo.idInstancia;
			$("#listD"+pagina).append('<li class="list-group-item" id="'+tipo+'-'+idInst+'"><button type="button" id="addAtC'+tipo+'-'+idInst+'" class="btn btn-xs btn-default addComplexAttribute" data-tipo="'+pagina+'" data-inst="'+idInst+'" data-pag="'+pagina+'" data-name="'+nom+'">&nbsp;<span class="glyphicon glyphicon-arrow-left"></span></button>'+nom+'</li>');
		}
		if(recarga == 1){
			postCargaDetalle2();
		}
	}
);
}

//Esta función únicamente se llama la primera vez que se construye el modal
function postCargaDetalle(mod){
	$("#modDetAtributos0").show();
	$(".modDetAtributosC").hide();
	$(".listaA").click(function(){
		oldModDetAct = modDetAct;
		modDetAct = $(this).data("val");
		$(".listaA").removeClass("active");
		$(this).addClass("active");
		$("#modDetAtributos" + oldModDetAct).hide();
		$("#modDetAtributos" + modDetAct).show();
	});
	//postCargaDetalle2();
	if(mod == 1){
		if(firstC == 0){
			$("#modalsave").click(function(){
				if(cambioAtrSim == 1){
					alert("guardamos");
				}
				cambioAtrSim = 0;
				$("#modalDetalleObjeto2").modal("hide");
			});
			$("#modalcanc").click(function(){
				cambioAtrSim = 0;
				$("#modalDetalleObjeto2").modal("hide");
			});
			firstC = 1;
		}
		
		$(".atrSim").change(function(){
			cambioAtrSim = 1;
		});
	}
}


//Esta función se llama cada vez que se ha de recargar el modal (sin cerrarlo) por adición o borrado de atributo complejo
function postCargaDetalle2(){
	$(".remComplexAttribute").unbind("click",handBotRem);
	$(".remComplexAttribute").click(handBotRem);
	$(".addComplexAttribute").unbind("click",handBotAdd); 
	$(".addComplexAttribute").click(handBotAdd);
}

var handBotAdd = function botonAddComplesAttr(){
	 inst = $(this).data('inst');
	 tipo = $(this).data('tipo');
	 pag = $(this).data('pag');
	 name = $(this).data('name');
	 $.post("addComplexAttribute.do",
			 {
		 		idInstPadre : idInstanciaModificar,
		 		idTipoAttr : tipo,
		 		idInstHijo : inst
			 },
			 function(data){
				 var json = JSON.parse(data);
					if(json.message == "añadido"){
						$("#modalAddAtributoC1").modal();
						cargaAtributosComplejosPorPagina(json.pag,1);
					}
					else if(json.message == "noType"){
						$("#modalAddAtributoC3").modal();
					}
					else if(json.message == "sinPrivilegios"){
						$("#modalAddAtributoC4").modal();
					}
					else if(json.message == "sinSesion"){
						window.location.href="hello.do";
					}
					else if(json.message == "errorDB"){
						$("#modalAddAtributoC5").modal();
					}
					else{
						$("#modalAddAtributoC2").modal();
					}
			 });
 }

var handBotRem = function botonRemComplexAttr(){
	 inst = $(this).data('inst');
	 tipo = $(this).data('tipo');
	 pagina3 = $(this).data('pag');
	 name = $(this).data('name');
	 $.post("remComplexAttribute.do",
		 {
	 		idInstPadre : idInstanciaModificar,
	 		idTipoAttr : tipo,
	 		idInstHijo : inst
		 },
		 function(data){
			 var json = JSON.parse(data);
				if(json.message == "borrado"){
					cargaAtributosComplejosPorPagina(json.pag,1);
					$("#modalRemAtributoC1").modal();
				}
				else if(json.message == "noType"){
					$("#modalRemAtributoC3").modal();
				}
				else if(json.message == "sinPrivilegios"){
					$("#modalRemAtributoC4").modal();
				}
				else if(json.message == "sinSesion"){
					window.location.href="hello.do";
				}
				else if(json.message == "errorDB"){
					$("#modalRemAtributoC5").modal();
				}
				else{
					$("#modalRemAtributoC2").modal();
				}
		 });
 }

function postCarga2(){
	$(".saveStudentNewName").hide();
	$(".cancelStudentNewName").hide();
	
	$(".activarStudentSNombre").click(function(){
		if(activadoAl != 0){
			$("#objetoStudentName" + activadoAl).val(oldnameAl);
			$("#objetoStudentName" + activadoAl).attr("disabled","true");
			$("#saveStudentObjeto" + activadoAl).hide();
			$("#cancelStudentObjeto" + activadoAl).hide();
			activadoAl = 0;
		}
		oldnameAl = $("#objetoStudentName" + $(this).data('val')).val();
		activadoAl = $(this).data('val');
		$("#objetoStudentName" + $(this).data('val')).removeAttr("disabled");
		$("#saveStudentObjeto" + $(this).data('val')).show();
		$("#cancelStudentObjeto" + $(this).data('val')).show();
	});
	
	$(".cancelStudentNewName").click(function(){
		$("#objetoStudentName" + $(this).data('val')).val(oldnameAl);
		activadoAl = 0;
		$("#cancelStudentObjeto" + $(this).data('val')).hide();
		$("#saveStudentObjeto" + $(this).data('val')).hide();
		$("#objetoStudentName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveStudentNewName").click(function(){
		if($("#objetoStudentName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaObjeto1").modal();
		}
		else{
			$.post("renameObjectA.do",{
				idObjeto : $(this).data('val'),
				newNombre : $("#objetoStudentName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaObjeto2").modal();
					$("#objetoStudentName" + activadoAl).attr("disabled","true");
					$("#saveStudentObjeto" + activadoAl).hide();
					$("#cancelStudentObjeto" + activadoAl).hide();
					activadoAl = 0;
				}
				else{
					$("#objetoStudentName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaObjeto3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaObjeto5").modal();
					}
					else{
						$("#modalModificaObjeto4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteStudentSObjeto").click(function(){
		$.post("removeObjectA.do",{
			idObjeto : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraObjeto1").modal();
				$("#objetoStudent" + json.id).remove();
			}
			else{
				$("#modalBorraObjeto2").modal();
			}
		});
	});
}

function postCarga3(){
	$(".saveProfeNewName").hide();
	$(".cancelProfeNewName").hide();
	
	$(".activarProfeSNombre").click(function(){
		if(activadoPr != 0){
			$("#objetoProfeName" + activadoPr).val(oldnamePr);
			$("#objetoProfeName" + activadoPr).attr("disabled","true");
			$("#saveProfeObjeto" + activadoPr).hide();
			$("#cancelProfeObjeto" + activadoPr).hide();
			activadoPr = 0;
		}
		oldnamePr = $("#objetoProfeName" + $(this).data('val')).val();
		activadoPr = $(this).data('val');
		$("#objetoProfeName" + $(this).data('val')).removeAttr("disabled");
		$("#saveProfeObjeto" + $(this).data('val')).show();
		$("#cancelProfeObjeto" + $(this).data('val')).show();
	});
	
	$(".cancelProfeNewName").click(function(){
		$("#objetoProfeName" + $(this).data('val')).val(oldnamePr);
		activadoPr = 0;
		$("#cancelProfeObjeto" + $(this).data('val')).hide();
		$("#saveProfeObjeto" + $(this).data('val')).hide();
		$("#objetoProfeName" + $(this).data('val')).attr("disabled","true");
	});
	
	$(".saveProfeNewName").click(function(){
		if($("#objetoProfeName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaObjeto1").modal();
		}
		else{
			$.post("renameObjectA.do",{
				idObjeto : $(this).data('val'),
				newNombre : $("#objetoProfeName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "cambiado"){
					$("#modalModificaObjeto2").modal();
					$("#objetoProfeName" + activadoPr).attr("disabled","true");
					$("#saveProfeObjeto" + activadoPr).hide();
					$("#cancelProfeObjeto" + activadoPr).hide();
					activadoPr = 0;
				}
				else{
					$("#objetoProfeName" + json.id).val(json.oldname);
					if(json.message == "noExist"){
						$("#modalModificaObjeto3").modal();
					}
					else if(json.message == "repeated"){
						$("#modalModificaObjeto5").modal();
					}
					else{
						$("#modalModificaObjeto4").modal();
					}
				}
			});
		}
	});
	
	$(".deleteProfeSObjeto").click(function(){
		$.post("removeObjectA.do",{
			idObjeto : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraObjeto1").modal();
				$("#objetoProfe" + json.id).remove();
			}
			else{
				$("#modalBorraObjeto2").modal();
			}
		});
	});
	
	$(".validarObjeto").click(function(){
		$.post("validateObject.do",{
			idObjeto : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "validado"){
				$("#modalValidaObjeto1").modal();
				$("#validado" + json.id).removeClass();
				$("#validado" + json.id).addClass("label label-success");
				$("#validado" + json.id).text($("#textVal").val())
			}
			else{
				$("#modalValidaObjeto2").modal();
			}
		});
	});
}

function cargaListaCompleta(){
	$.post("completeObjectList.do",{
		type : "table"},
		function(data){
			$("#listaCompleta").html(data);
			postCarga();
	});
}

function cargaListaAlumno(){
	$.post("studentObjectListA.do",{
		type : "table"},
			function(data){
				$("#listaalumno").html(data);
				postCarga2();
	});
}

function cargaListaProfe(){
	$.post("teacherObjectListA.do",{
		type : "table"},
			function(data){
				$("#listaProfe").html(data);
				postCarga3();
	});
}

var btncreate = 1;

$(document).ready(function(){
	
	$("#group-block1").hide();
	
	cargaListaCompleta();
	
//	if($("#listaalumno").length > 0){
//		cargaListaAlumno();
//	}
//	else if($("#listaProfe").length > 0){
//		cargaListaProfe();
//	}
	
	function swipBotonCrear(){
		if(btncreate == 0){
			$("#group-block1").slideUp(500);
			$("#newObjectName").val("");
			$("#displayCreatei").removeClass();
			$("#displayCreatei").addClass("glyphicon glyphicon-chevron-down");
			btncreate = 1;
		}
		else{
			$("#group-block1").slideDown(500);
			$("#displayCreatei").removeClass();
			$("#displayCreatei").addClass("glyphicon glyphicon-chevron-up");
			btncreate = 0;
		}
	}
	
	$("#displayCreate").click(function(){
		swipBotonCrear();
	});
	
	$("#cancelButton").click(function(){
		swipBotonCrear();
	});
	
	//Filtro de objetos por fila según se rellena el campo imput
	$("#filtroBusquedaCompleta").keyup(function(){
		if($("#filtroBusquedaCompleta").val().length > 0){
			$(".trobjeto").each(function(){
				if($(this).data("nom").indexOf($("#filtroBusquedaCompleta").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trobjeto").show();
		}
	});
	
	$("#busquedaProfe").keyup(function(){
		if($("#busquedaProfe").val().length > 0){
			$(".trobjetoP").each(function(){
				if($(this).data("nom").indexOf($("#busquedaProfe").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trobjetoP").show();
		}
	});
	
	$("#busquedaUsuario").keyup(function(){
		if($("#busquedaUsuario").val().length > 0){
			$(".trobjetoU").each(function(){
				if($(this).data("nom").indexOf($("#busquedaUsuario").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trobjetoU").show();
		}
	});
	
	$("#createButton").click(function(){
		if($("#newObjectName").val().length < 1){
			$("#modalCreaObjeto1").modal();
		}
		else{
			$.post("createObjectA.do",{
				nombre : $("#newObjectName").val()
			},function(data){
				var json = JSON.parse(data);
				if(json.message == "nombreRepetido"){
					$("#modalCreaObjeto3").modal();
				}
				else if(json.message == "creado"){
					cargaListaCompleta();
					if($("#listaalumno").length > 0){
						if(btncreate == 0){
							$("#group-block1").slideUp(500);
							$("#newObjectName").val("");
							$("#displayCreatei").removeClass();
							$("#displayCreatei").addClass("glyphicon glyphicon-chevron-down");
							btncreate = 1;
						}
						else{
							$("#group-block1").slideDown(500);
							$("#displayCreatei").removeClass();
							$("#displayCreatei").addClass("glyphicon glyphicon-chevron-up");
							btncreate = 0;
						}
						cargaListaAlumno();
					}
					else if($("#listaProfe").length > 0){
						cargaListaProfe();
					}
					$("#modalCreaObjeto2").modal();
					swipBotonCrear();
				}
				else if(json.message == "errorDB"){
					$("#modalCreaObjeto4").modal();
				}
				else if(json.message == "sinPrivilegios"){
					$("#modalNoPrivilegios").modal();
				}
				else if(json.message == "sinSesion"){
					window.location.href="hello.do";
				}
			});
		}
	});
});