var oldnameCom;
var oldnameComP;
var activadoCom = 0;
var activadoComP = 0;
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
var cambioAtrSimP = 0;
var firstC = 0;

var deleteObj = 0;

var idHijo = 0;
var tipoHijo = 0;
var idPadre = 0;

function cancelaEdicion(val,ol){
	$("#objetoName" + val).val(ol);
	activadoCom = 0;
	$("#cancelObjeto" + val).hide();
	$("#saveObjeto" + val).hide();
	$("#objetoName" + val).attr("disabled","true");
}

function cancelaEdicionP(val,ol){
	$("#objetoNameP" + val).val(ol);
	activadoCom = 0;
	$("#cancelObjetoP" + val).hide();
	$("#saveObjetoP" + val).hide();
	$("#objetoNameP" + val).attr("disabled","true");
}

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
		cancelaEdicion($(this).data('val'),oldnameCom);
	});
	
	$(".saveNewName").click(function(){
		if($("#objetoName" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaObjeto1").modal();
		}
		else{
			if($("#objetoName" + $(this).data('val')).val() == $("#objeto" + $(this).data('nom'))){
			cancelaEdicion($(this).data('val'),oldnameCom);
			}
			else{
				$.post("renameObjectA.do",{
					idInstancia : $(this).data('val'),
					newNombre : $("#objetoName" + $(this).data('val')).val()
				},
				function(data){
					var json = JSON.parse(data);
					if(json.message == "ok"){
						$("#modalModificaObjeto2").modal();
						$("#objetoName" + activadoCom).attr("disabled","true");
						$("#saveObjeto" + activadoCom).hide();
						$("#cancelObjeto" + activadoCom).hide();
						activadoCom = 0;
					}
					else{
						$("#objetoName" + json.id).val(json.oldname);
						if(json.message == "noObject"){
							$("#modalModificaObjeto3").modal();
						}
						else if(json.message == "nameRepeated"){
							$("#modalModificaObjeto5").modal();
						}
						else if(json.message == "sinSesion"){
							window.location.href="hello.do";
						}
						else{
							$("#modalModificaObjeto4").modal();
						}
					}
				});
			}
		}
	});
	
	$(".deleteSObjeto").click(function(){
		deleteObjeto = $(this).data('val');
		$("#modalBorraObjeto0").modal();
	});
	
	
	
	$(".modifySObjeto").click(function(){
		$("#contenidoDetalle").empty();
		$("#contenidoDetalleProfe").empty();
		$("#contenidoValidaProfe").empty();
		$("#contenidoModifyProfe").empty();
		oldModDetAct = 0;
		modDetAct = 0;
		name = $(this).data("name");
		$("#nombreObjetoDetalle2").html(name);
		$("#modalDetalleObjeto2").modal();
		idInstanciaModificar = $(this).data('val');
		$.post("objectDetail.do",{
			idInstancia : $(this).data('val'),
			modo : 2,
			val : 1
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
		$("#contenidoDetalleProfe").empty();
		$("#contenidoValidaProfe").empty();
		$("#contenidoModifyProfe").empty();
		oldModDetAct = 0;
		modDetAct = 0;
		id = $(this).data("val");
		name = $(this).data("name");
		$("#nombreObjetoDetalle").html(name);
		$("#modalDetalleObjeto").modal();
		$.post("objectDetail.do",{
			idInstancia : id,
			modo : 1,
			val : 1
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
		var relacion = json.relacion;
		$("#modDetAtributos" + pagina).append('<div class="row" id="rowPag'+pagina+'"></div>');
		$("#rowPag"+pagina).append('<div class="col-xs-6 form-group"><label>Actual</fmt:message></label><ul class="list-group" id="list'+pagina+'"></ul></div>');
		for(var d in obj){
			var nom = obj[d].instanciaHijo.nombre;
			var tipo = obj[d].instanciaHijo.tipo.tipoDOM;
			var idInst = obj[d].instanciaHijo.idInstancia;
			var val = obj[d].validado;
			if(val == 1){
				$("#list"+pagina).append('<li class="list-group-item" id="'+tipo+'-'+idInst+'"><button type="button" id="remAtC'+tipo+'-'+idInst+'" class="btn btn-xs btn-default remComplexAttribute" data-tipo="'+pagina+'" data-inst="'+idInst+'" data-pag="'+pagina+'" data-name="'+nom+'" data-tiporelacion="'+relacion+'"><span class="glyphicon glyphicon-arrow-right"></span></button> '+nom+'</li>');
			}
			else{
				$("#list"+pagina).append('<li class="list-group-item" id="'+tipo+'-'+idInst+'"><button type="button" class="btn btn-xs btn-warning pendienteVal"><span class="glyphicon glyphicon-ban-circle"></span></button> '+nom+'</li>');
			}
		}
		
		$("#rowPag"+pagina).append('<div class="col-xs-6 form-group"><label>Disponible</fmt:message></label><ul class="list-group" id="listD'+pagina+'"></ul></div>');
		for(var d in disp){
			var nom = disp[d].instanciaHijo.nombre;
			var tipo = disp[d].instanciaHijo.tipo.tipoDOM;
			var idInst = disp[d].instanciaHijo.idInstancia;
			var val = disp[d].validado;
			$("#listD"+pagina).append('<li class="list-group-item" id="'+tipo+'-'+idInst+'"><button type="button" id="addAtC'+tipo+'-'+idInst+'" class="btn btn-xs btn-default addComplexAttribute" data-tipo="'+pagina+'" data-inst="'+idInst+'" data-pag="'+pagina+'" data-name="'+nom+'" data-tiporelacion="'+relacion+'">&nbsp;<span class="glyphicon glyphicon-arrow-left"></span></button> '+nom+'</li>');
		}
//		if(recarga == 1){
			postCargaDetalle2();
//		}
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
					var form1 = $("#simpleAttributeForm").serialize();
					$.post("simpleAttributes.do",form1,
						function(data){
							var json = JSON.parse(data);
							if(json.message == "ok"){
								$("#modalModAtributoS1").modal();
							}
							else if(json.message == "errorAtributos"){
								$("#modalModAtributoS2").modal();
							}
							else if(json.message == "errorDB"){
								$("#modalModAtributoS3").modal();
							}
							else if(json.message == "sinPrivilegios"){
								$("#modalModAtributoS4").modal();
							}
							else if(json.message == "sinSesion"){
								window.location.href="hello.do";
							}
							else{
								$("#modalModAtributoS3").modal();
							}
					});
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
			cambioAtrSimP = 1;
		});
	}
}


//Esta función se llama cada vez que se ha de recargar el modal (sin cerrarlo) por adición o borrado de atributo complejo
function postCargaDetalle2(){
	$(".remComplexAttribute").unbind("click",handBotRem);
	$(".remComplexAttribute").click(handBotRem);
	$(".addComplexAttribute").unbind("click",handBotAdd); 
	$(".addComplexAttribute").click(handBotAdd);
	$(".pendienteVal").unbind("click",handPendienteVal);
	$(".pendienteVal").click(handPendienteVal);
}

var handPendienteVal = function botonPendienteVal(){
	$("#modalPendienteValidacionAtributo").modal();
}

var handBotAdd = function botonAddComplesAttr(){
	 inst = $(this).data('inst');
	 tipo = $(this).data('tipo');
	 pag = $(this).data('pag');
	 name = $(this).data('name');
	 tipoRel = $(this).data('tiporelacion');
	 $("#instHR" + tipoRel).val(inst);
	 $("#tipoHR" + tipoRel).val(tipo);
	 $("#idInstanciaModificar" + tipoRel).val(idInstanciaModificar);
	 $("#modalRelacion" + tipoRel).modal();
	 
 }

var handBotRem = function botonRemComplexAttr(){
	 inst = $(this).data('inst');
	 tipo = $(this).data('tipo');
	 pagina3 = $(this).data('pag');
	 name = $(this).data('name');
	 tipoRel = $(this).data('tiporelacion');
	 $.post("remComplexAttribute.do",
		 {
	 		idInstPadre : idInstanciaModificar,
	 		idTipoAttr : tipo,
	 		idInstHijo : inst,
	 		tipoRel : tipoRel
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
			$("#modalModificaLugar1").modal();
		}
		else{
			$.post("renameObjectA.do",{
				idInstancia : $(this).data('val'),
				newNombre : $("#objetoStudentName" + $(this).data('val')).val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "ok"){
					$("#modalModificaObjeto2").modal();
					$("#objetoStudentName" + activadoAl).attr("disabled","true");
					$("#saveStudentObjeto" + activadoAl).hide();
					$("#cancelStudentObjeto" + activadoAl).hide();
					activadoAl = 0;
				}
				else{
					$("#objetoName" + json.id).val(json.oldname);
					if(json.message == "noObject"){
						$("#modalModificaObjeto3").modal();
					}
					else if(json.message == "nameRepeated"){
						$("#modalModificaObjeto5").modal();
					}
					else if(json.message == "sinSesion"){
						window.location.href="hello.do";
					}
					else if(json.message == "noOwner"){
						$("#modalModificaObjeto6").modal();
					}
					else{
						$("#modalModificaObjeto4").modal();
					}
				}
			});
		}
	});
	
	$(".textoNoValidaOBA").click(function(){
		if($(this).data('leido') == 0){
			$("#marcarleidotextovalNV").show();
			$("#marcaleidonv").prop('checked',true);
		}
		else{
			$("#marcarleidotextovalNV").hide();
			$("#marcaleidonv").prop('checked',false);
		}
		$("#modalTextoNoValidacionOB").html($(this).data('textval'));
		$("#idObjetoTxtNVal").val($(this).data('idtextval'));
		$("#modalMuestraTextoNoOB").modal();
	});
	
	$(".textoValidaOBA").click(function(){
		if($(this).data('leido') == 0){
			$("#marcarleidotextoval").show();
			$("#marcaleido").prop('checked',true);
		}
		else{
			$("#marcarleidotextoval").hide();
			$("#marcaleidonv").prop('checked',false);
		}
		$("#modalTextoValidacionOB").html($(this).data('textval'));
		$("#idObjetoTxtVal").val($(this).data('idtextval'));
		$("#modalMuestraTextoOB").modal();
	});
	
	$(".deleteStudentSObjeto").click(function(){
		$.post("removePlaceA.do",{
			idLugar : $(this).data('val')
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraLugar1").modal();
				$("#lugarStudent" + json.id).remove();
			}
			else{
				$("#modalBorraLugar2").modal();
			}
		});
	});
	
	$(".detalleObjetoStudent").click(function(){
		$("#contenidoDetalle").empty();
		$("#contenidoDetalle2").empty();
		$("#contenidoValidaProfe").empty();
		$("#contenidoModifyProfe").empty();
		oldModDetAct = 0;
		modDetAct = 0;
		id = $(this).data("val");
		name = $(this).data("name");
		$("#nombreObjetoDetalleProfe").html(name);
		$("#modalDetalleObjetoProfe").modal();
		$.post("objectDetail.do",{
			idInstancia : id,
			modo : 1,
			val : 0
		},
		function(data){
			$("#contenidoDetalleProfe").html(data);
			validado = $("#objetodetallevalidado").val();
			if(validado == 1){
				$("#modalokValidaProfe").prop("disabled", true);
			}
			else{
				$("#modalokValidaProfe").prop("disabled", false);
			}
			postCargaDetalle(0);
			postCargaProfe();
		});
	});
}

function postCarga3(){
	$(".saveNewNameP").hide();
	$(".cancelNewNameP").hide();
	$("#marcarleidotextoval").hide();
	$("#marcarleidotextovalNV").hide();
	$("#marcaleido").attr("checked",false);
	
	$(".deleteObjetoP").click(function(){
		deleteObjeto = $(this).data('val');
		$("#modalBorraObjeto0").modal();
	});
	
	$(".activarNombreProfe").click(function(){
		if(activadoComP != 0){
			$("#objetoNameP" + activadoComP).val(oldnameComP);
			$("#objetoNameP" + activadoComP).attr("disabled","true");
			$("#saveObjetoP" + activadoComP).hide();
			$("#cancelObjetoP" + activadoComP).hide();
			activadoComP = 0;
		}
		oldnameComP = $("#objetoNameP" + $(this).data('val')).val();
		activadoComP = $(this).data('val');
		$("#objetoNameP" + $(this).data('val')).removeAttr("disabled");
		$("#saveObjetoP" + $(this).data('val')).show();
		$("#cancelObjetoP" + $(this).data('val')).show();
	});
	
	$(".cancelNewNameP").click(function(){
		cancelaEdicionP($(this).data('val'),oldnameComP);
	});
	
	$(".saveNewNameP").click(function(){
		if($("#objetoNameP" + $(this).data('val')).val().length < 1 ){
			$("#modalModificaObjeto1").modal();
		}
		else{
			if($("#objetoNameP" + $(this).data('val')).val() == $("#objetoProfe" + $(this).data('nom'))){
			cancelaEdicionP($(this).data('val'),oldnameCom);
			}
			else{
				$.post("renameObjectA.do",{
					idInstancia : $(this).data('val'),
					newNombre : $("#objetoNameP" + $(this).data('val')).val()
				},
				function(data){
					var json = JSON.parse(data);
					if(json.message == "ok"){
						$("#modalModificaObjeto2").modal();
						$("#objetoNameP" + activadoComP).attr("disabled","true");
						$("#saveObjetoP" + activadoComP).hide();
						$("#cancelObjetoP" + activadoComP).hide();
						activadoComP = 0;
					}
					else{
						$("#objetoNameP" + json.id).val(json.oldname);
						if(json.message == "noObject"){
							$("#modalModificaObjeto3").modal();
						}
						else if(json.message == "nameRepeated"){
							$("#modalModificaObjeto5").modal();
						}
						else if(json.message == "sinSesion"){
							window.location.href="hello.do";
						}
						else{
							$("#modalModificaObjeto4").modal();
						}
					}
				});
			}
		}
	});
	
	
	$(".textoNoValidaOB").click(function(){
		$("#modalTextoNoValidacionOB").html($(this).data('textval'));
		$("#modalMuestraTextoNoOB").modal();
	});
	
	$(".textoValidaOB").click(function(){
		$("#modalTextoValidacionOB").html($(this).data('textval'));
		$("#modalMuestraTextoOB").modal();
	});
	
	$(".detalleObjetoProfe").click(function(){
		$("#contenidoDetalle").empty();
		$("#contenidoDetalle2").empty();
		$("#contenidoValidaProfe").empty();
		$("#contenidoModifyProfe").empty();
		oldModDetAct = 0;
		modDetAct = 0;
		id = $(this).data("val");
		name = $(this).data("name");
		$("#nombreObjetoDetalleProfe").html(name);
		$("#modalDetalleObjetoProfe").modal();
		$.post("objectDetail.do",{
			idInstancia : id,
			modo : 1,
			val : 0
		},
		function(data){
			$("#contenidoDetalleProfe").html(data);
			validado = $("#objetodetallevalidado").val();
			if(validado == 1){
				$("#modalokValidaProfe").prop("disabled", true);
			}
			else{
				$("#modalokValidaProfe").prop("disabled", false);
			}
			postCargaDetalle(0);
			postCargaProfe();
		});
	});
	
	$(".validarObjeto").click(function(){
		$("#contenidoDetalle").empty();
		$("#contenidoDetalle2").empty();
		$("#contenidoDetalleProfe").empty();
		$("#contenidoModifyProfe").empty();
		oldModDetAct = 0;
		modDetAct = 0;
		id = $(this).data("val");
		name = $(this).data("name");
		$("#nombreObjetoValidaProfe").html(name);
		$("#modalValidaObjetoProfe").modal();
		$.post("objectDetail.do",{
			idInstancia : id,
			modo : 3,
			val : 0
		},
		function(data){
			$("#contenidoValidaProfe").html(data);	
			postCargaDetalle(0);
			buttonflag = $("#validateButtonFlag").val();
			if(buttonflag == 0){
				$("#modalokValidaProfe").prop("disabled", false);
			}
			postCargaProfe();
		});
	});
	
	$(".modifyObjetoP").click(function(){
		$("#contenidoDetalle").empty();
		$("#contenidoDetalle2").empty();
		$("#contenidoDetalleProfe").empty();
		$("#contenidoValidaProfe").empty();
		oldModDetAct = 0;
		modDetAct = 0;
		name = $(this).data("name");
		$("#nombreObjetoModifyProfe").html(name);
		$("#modalModifyObjetoProfe").modal();
		idInstanciaModificar = $(this).data('val');
		$.post("objectDetail.do",{
			idInstancia : $(this).data('val'),
			modo : 2,
			val : 0
		},
		function(data){
			$("#contenidoModifyProfe").html(data);
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
			$("#modalsavep").click(function(){
				if(cambioAtrSimP == 1){
					var form1 = $("#simpleAttributeForm").serialize();
					$.post("simpleAttributes.do",form1,
						function(data){
							var json = JSON.parse(data);
							if(json.message == "ok"){
								$("#modalModAtributoS1").modal();
							}
							else if(json.message == "errorAtributos"){
								$("#modalModAtributoS2").modal();
							}
							else if(json.message == "errorDB"){
								$("#modalModAtributoS3").modal();
							}
							else if(json.message == "sinPrivilegios"){
								$("#modalModAtributoS4").modal();
							}
							else if(json.message == "sinSesion"){
								window.location.href="hello.do";
							}
							else{
								$("#modalModAtributoS3").modal();
							}
					});
				}
				cambioAtrSimP = 0;
				$("#modalModifyObjetoProfe").modal("hide");
			});
			$("#modalcancp").click(function(){
				cambioAtrSimP = 0;
				$("#modalModifyObjetoProfe").modal("hide");
			});
		});
	});
	
//	
//	$(".activarProfeSNombre").click(function(){
//		if(activadoPr != 0){
//			$("#objetoProfeName" + activadoPr).val(oldnamePr);
//			$("#objetoProfeName" + activadoPr).attr("disabled","true");
//			$("#saveProfeObjeto" + activadoPr).hide();
//			$("#cancelProfeObjeto" + activadoPr).hide();
//			activadoPr = 0;
//		}
//		oldnamePr = $("#objetoProfeName" + $(this).data('val')).val();
//		activadoPr = $(this).data('val');
//		$("#objetoProfeName" + $(this).data('val')).removeAttr("disabled");
//		$("#saveProfeObjeto" + $(this).data('val')).show();
//		$("#cancelProfeObjeto" + $(this).data('val')).show();
//	});
//	
//	$(".cancelProfeNewName").click(function(){
//		$("#objetoProfeName" + $(this).data('val')).val(oldnamePr);
//		activadoPr = 0;
//		$("#cancelProfeObjeto" + $(this).data('val')).hide();
//		$("#saveProfeObjeto" + $(this).data('val')).hide();
//		$("#objetoProfeName" + $(this).data('val')).attr("disabled","true");
//	});
//	
//	$(".saveProfeNewName").click(function(){
//		if($("#objetoProfeName" + $(this).data('val')).val().length < 1 ){
//			$("#modalModificaObjeto1").modal();
//		}
//		else{
//			$.post("renameObjectA.do",{
//				idObjeto : $(this).data('val'),
//				newNombre : $("#objetoProfeName" + $(this).data('val')).val()
//			},
//			function(data){
//				var json = JSON.parse(data);
//				if(json.message == "cambiado"){
//					$("#modalModificaObjeto2").modal();
//					$("#objetoProfeName" + activadoPr).attr("disabled","true");
//					$("#saveProfeObjeto" + activadoPr).hide();
//					$("#cancelProfeObjeto" + activadoPr).hide();
//					activadoPr = 0;
//				}
//				else{
//					$("#objetoProfeName" + json.id).val(json.oldname);
//					if(json.message == "noExist"){
//						$("#modalModificaObjeto3").modal();
//					}
//					else if(json.message == "repeated"){
//						$("#modalModificaObjeto5").modal();
//					}
//					else{
//						$("#modalModificaObjeto4").modal();
//					}
//				}
//			});
//		}
//	});
//	
//	$(".deleteProfeSObjeto").click(function(){
//		$.post("removeObjectA.do",{
//			idObjeto : $(this).data('val')
//		},
//		function(data){
//			var json = JSON.parse(data);
//			if(json.message == "borrado"){
//				$("#modalBorraObjeto1").modal();
//				$("#objetoProfe" + json.id).remove();
//			}
//			else{
//				$("#modalBorraObjeto2").modal();
//			}
//		});
//	});
//	
//	$(".validarObjeto").click(function(){
//		$.post("validateObject.do",{
//			idObjeto : $(this).data('val')
//		},
//		function(data){
//			var json = JSON.parse(data);
//			if(json.message == "validado"){
//				$("#modalValidaObjeto1").modal();
//				$("#validado" + json.id).removeClass();
//				$("#validado" + json.id).addClass("label label-success");
//				$("#validado" + json.id).text($("#textVal").val())
//			}
//			else{
//				$("#modalValidaObjeto2").modal();
//			}
//		});
//	});
}

function postCargaProfe(){
	$(".validationText").click(function(){
		$("#modalTextoValidacion").html($(this).data('textvalidacion'));
		$("#modalMuestraTextoAC").modal();
	});
	
	$(".novalidationText").click(function(){
		$("#modalTextoNoValidacion").html($(this).data('textvalidacion'));
		$("#modalMuestraTextoACNoVal").modal();
	});
	
	$(".validarAtributoC").click(function(){
		idHijo = $(this).data('val');
		tipoHijo = $(this).data('thijo');
		idPadre = $(this).data('padre');
		textoRelacionAC = $(this).data('textrel')
		$("#textoValidaciónAC").val("");
		$("#textoRelacionAC").html(textoRelacionAC);
		$("#modalValidaAtributoC0").modal();
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
	
	if($("#listaalumno").length > 0){
		cargaListaAlumno();
	}
//	else
	if($("#listaProfe").length > 0){
		cargaListaProfe();
	}
	
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
			$(".trObjetoP").each(function(){
				if($(this).data("nom").indexOf($("#busquedaProfe").val()) == -1){
					$(this).hide();
				}
				else{
					$(this).show();
				}
			});
		}
		else{
			$(".trObjetoP").show();
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
	
	$("#validaAC").click(function(){
		$.post("validateComplexAttributeA.do",{
			idHijo : idHijo,
			tipoHijo : tipoHijo,
			idPadre : idPadre,
			val : 1,
			textoVal : $("#textoValidaciónAC").val()
		},
		function(data){
			modalesValidacion(data);
		});
	});
	
	$("#noValidaAC").click(function(){
		$.post("validateComplexAttributeA.do",{
			idHijo : idHijo,
			tipoHijo : tipoHijo,
			idPadre : idPadre,
			val : 0,
			textoVal : $("#textoValidaciónAC").val()
		},
		function(data){
			modalesValidacion(data);
		});
	});
	
	$("#modalokValidaProfe").click(function(){
		$("#modalValidaObjetoText").modal();
	});
	
	$("#validaOB").click(function(){
		$.post("validateObject.do",{
			idInstancia : $("#idObjetoValidar").val(),
			text : $("#textoValidaciónOB").val(),
			val : 1},
			function(data){
				modalesValidacionObj(data);
				cargaListaProfe();
				cargaListaCompleta();
		});
	});
	
	$("#noValidaOB").click(function(){
		$.post("validateObject.do",{
			idInstancia : $("#idObjetoValidar").val(),
			text : $("#textoValidaciónOB").val(),
			val : 0},
			function(data){
				modalesValidacionObj(data);
				cargaListaProfe();
				cargaListaCompleta();
		});
	});
	
	$("#modalDelete").click(function(){
		$.post("removeObjectA.do",{
			idInstancia : deleteObjeto
		},
		function(data){
			var json = JSON.parse(data);
			if(json.message == "borrado"){
				$("#modalBorraObjeto1").modal();
				$("#objeto" + json.id).remove();
				$("#objetoProfe" + json.id).remove();
			}
			else{
				$("#modalBorraObjeto2").modal();
			}
		})
	});
	
	$(".buttonmodalasignacionrelacion").click(function(){
		
		tipoR = $(this).data("tiporel");
		idInstMod = $("#idInstanciaModificar" + tipoR).val();
		tipo = $("#tipoHR" + tipoR).val();
		inst = $("#instHR" + tipoR).val();
		selRel = $("#selectRelacion" + tipoR).val();
		$.post("addComplexAttribute.do",
				 {
			 		idInstPadre : idInstMod,
			 		idTipoAttr : tipo,
			 		idInstHijo : inst,
			 		selRel : selRel
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
	});
	
	$("#botonmarcaleidonv").click(function(){
		if($("#marcaleidonv").is(':checked')){
			$.post("setObjectTextReaded.do",{
				idObjeto : $("#idObjetoTxtNVal").val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "ok"){
					cargaListaAlumno();
				}
				else if(json.message == "noType"){
					$("#marcaTextoLeido1").modal();
				}
				else if(json.message == "errorDB"){
					$("#marcaTextoLeido2").modal();
				}
			});
		}
	});
	
	$("#botonmarcaleido").click(function(){
		if($("#marcaleido").is(':checked')){
			$.post("setObjectTextReaded.do",{
				idObjeto : $("#idObjetoTxtVal").val()
			},
			function(data){
				var json = JSON.parse(data);
				if(json.message == "ok"){
					cargaListaAlumno();
				}
				else if(json.message == "noType"){
					$("#marcaTextoLeido1").modal();
				}
				else if(json.message == "errorDB"){
					$("#marcaTextoLeido2").modal();
				}
			});
		}
	});
	
});

function modalesValidacionObj(data){
	var json = JSON.parse(data);
	if(json.message == "sinSesion"){
		window.location.href="hello.do";
	}
	else if(json.message == "sinPrivilegios"){
		$("#modalErrorParam").modal();
	}
	else if(json.message == "noObject"){
		$("#modalNoObject").modal();
	}
	else if(json.message == "errorParam"){
		$("#modalErrorParam").modal();
	}
	else if(json.message == "validado"){
		$("#modalObjetoValidado").modal();
	}
	else if(json.message == "novalidado"){
		$("#modalObjetoNoValidado").modal();
	}
}

function modalesValidacion(data){
	var json = JSON.parse(data);
	if(json.message == "sinSesion"){
		window.location.href="hello.do";
	}
	else if(json.message == "sinPrivilegios"){
		$("#modalErrorParam").modal();
	}
	else if(json.message == "noType"){
		$("#modalValidaAtributoC1").modal();
	}
	else if(json.message == "errVal"){
		$("#modalValidaAtributoC1").modal();
	}
	else if(json.message == "alreadyValidated"){
		$("#modalValidaAtributoC2").modal();
	}
	else if(json.message == "noAtributoC"){
		$("#modalValidaAtributoC3").modal();
	}
	else if(json.message == "noObject"){
		$("#modalValidaAtributoC3").modal();
	}
	else if(json.message == "errorBD"){
		$("#modalValidaAtributoC4").modal();
	}
	else if(json.message == "validado"){
		$("#validado"+json.tipoHijo+"-"+json.idHijo).removeClass("label-warning");
		$("#validado"+json.tipoHijo+"-"+json.idHijo).addClass("label-success");
		$("#validado"+json.tipoHijo+"-"+json.idHijo).addClass("validationText");
		$("#validado"+json.tipoHijo+"-"+json.idHijo).empty();
		$("#validado"+json.tipoHijo+"-"+json.idHijo).html("Validado");
		$("#validarProfeAtributoC"+json.tipoHijo+"-"+json.idHijo).remove();
		$("#modalValidaAtributoC5").modal();
		if(json.texts.length > 0){
			$("#validado"+json.tipoHijo+"-"+json.idHijo).append('<span class="label label-info">1</span>');
			$("#validado"+json.tipoHijo+"-"+json.idHijo).data("textvalidacion",json.texts);
		}
	}
	else if(json.message == "novalidado"){
		$("#validado"+json.tipoHijo+"-"+json.idHijo).removeClass("label-success");
		$("#validado"+json.tipoHijo+"-"+json.idHijo).addClass("label-warning");
		$("#validado"+json.tipoHijo+"-"+json.idHijo).addClass("novalidationText");
		$("#validado"+json.tipoHijo+"-"+json.idHijo).empty();
		$("#validado"+json.tipoHijo+"-"+json.idHijo).html("No validado");
		$("#modalValidaAtributoC6").modal();
		if(json.texts.length > 0){
			$("#validado"+json.tipoHijo+"-"+json.idHijo).append('<span class="label label-info">1</span>');
			$("#validado"+json.tipoHijo+"-"+json.idHijo).data("textvalidacion",json.texts);
		}
	}
}