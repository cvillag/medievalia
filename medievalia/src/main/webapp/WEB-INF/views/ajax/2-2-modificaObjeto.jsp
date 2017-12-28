<%@page import="com.cvilla.medievalia.utils.ListaAtributoSimple"%>
<%@page import="com.cvilla.medievalia.domain.TipoAtributoComplejo"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaAtributoComplejo"%>
<%@page import="com.cvilla.medievalia.utils.SpecialDate"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaAtributoSencillo"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaObjeto"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
User user = (User) session.getAttribute("user");
String type= (String) request.getAttribute("message");
InstanciaObjeto objeto = (InstanciaObjeto) request.getAttribute("object");
@SuppressWarnings("unchecked")
List<TipoAtributoComplejo> listaTipos = (List<TipoAtributoComplejo>) request.getAttribute("tatributoc");
@SuppressWarnings("unchecked")
List<InstanciaAtributoComplejo> disp = (List<InstanciaAtributoComplejo>) request.getAttribute("disponibles");
@SuppressWarnings("unchecked")
List<ListaAtributoSimple> atributosSimplesObjeto = (List<ListaAtributoSimple>) request.getAttribute("simplesDisponibles");
String editSimple = (String) request.getAttribute("editSimple");
boolean editS = editSimple != null;

if(objeto != null){
	List<InstanciaAtributoComplejo> acl = objeto.getAtributosComplejos();
	%>
	<ul class="nav nav-tabs">
		<li class="active listaA" data-val="0"><a href="#"><fmt:message key="p2.2.detalle.atributos"></fmt:message></a></li>
		<%
		if(listaTipos != null && listaTipos.size() > 0){
			for(TipoAtributoComplejo ac : listaTipos){
			%>
		<li class="listaA" data-val="<%=ac.getIdTipoHijo()%>"><a href="#"><%=ac.getNombreAtributo() %></a></li>
		<%	}
		}%>
	</ul>
	<div id="modDetAtributos0">
	<%
	
	List<InstanciaAtributoSencillo> atrS = objeto.getAtributosSencillos();
	if(atrS != null && atrS.size() > 0){
		%>
		
			<form id="simpleAttributeForm">
			<input type="hidden" name="idInstanciaObjeto" value="<%=objeto.getIdInstancia()%>">
			<%
			for(InstanciaAtributoSencillo as : atrS){
				if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_FECHA){
					%>
					<div class="row">
						<div class="col-xs-12">
							<label><%=as.getNombreTipoAtributo() %></label>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-3 form-group">
							<label for="dia"><fmt:message key="p2.2.detalle.dia"></fmt:message></label>
							<input type="number" name="dia<%=as.getIdAtributo() %>" <%=editS?"":"disabled" %> value="<%=as.getValor()!=null?((SpecialDate)as.getValor()).getDia():""%>" size="2" min="1" max="31" class="inputDays form-control atrSim">
						</div>
						<div class="col-xs-3 form-group">
							<label for="mes"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" name="mes<%=as.getIdAtributo() %>" <%=editS?"":"disabled" %> value="<%=as.getValor()!=null?((SpecialDate) as.getValor()).getMes():""%>" size="2" min="1" max="12" class="inputMonths form-control atrSim">
						</div>
						<div class="col-xs-3 form-group">
							<label for="anio"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" name="anio<%=as.getIdAtributo() %>" <%=editS?"":"disabled" %> value="<%=as.getValor()!=null?((SpecialDate) as.getValor()).getAnio():""%>"  size="2" class="inputYears form-control atrSim"	>
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_DOUBLE){
					String d = "";
					boolean nulo = as.getValor() == null;
					if(!nulo){
						d = ((Double)as.getValor()).toString();
					}
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="double"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" name="double<%=as.getIdAtributo() %>" <%=editS?"":"disabled" %> value="<%=d %>" class="form-control atrSim">
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_INT){
					int i=0;
					boolean nulo = as.getValor() == null;
					if(!nulo){
						i= ((Integer)as.getValor()).intValue();
					}
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="int"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" name="int<%=as.getIdAtributo() %>" <%=editS?"":"disabled" %> value="<%if(!nulo){%><%=i%><%} %>" class="form-control atrSim">
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_STRING){
					String s = (String)as.getValor();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="st"><%=as.getNombreTipoAtributo() %></label>
							<input type="text" name="string<%=as.getIdAtributo() %>" <%=editS?"":"disabled" %> value="<%=s!=null?s:""%>" class="form-control atrSim">
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_TEXT){
					String s = (String)as.getValor();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="st"><%=as.getNombreTipoAtributo() %></label>
							<textarea name="text<%=as.getIdAtributo() %>" <%=editS?"":"disabled" %> class="form-control atrSim"><%=s!=null?s:""%></textarea>
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_OBJECT){
					boolean enc = false;
					int i = 0;
					ListaAtributoSimple aso = null;
					while(!enc && i < atributosSimplesObjeto.size()){
						if(atributosSimplesObjeto.get(i).getAtributo().getIdAtributo() == as.getIdAtributo()){
							aso = atributosSimplesObjeto.get(i);
							enc = true;
						}
						i++;
					}
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="st"><%=as.getNombreTipoAtributo() %></label>
							<select class="form-control atrSim" name="object<%=as.getIdAtributo()%>" <%=editS?"":"disabled" %>>
								<option value="0"></option>
								<%
								InstanciaObjeto seleccionado = (InstanciaObjeto) as.getValor();
								if(aso != null){
									for(InstanciaObjeto objAtr : aso.getDisponibles()){
									%>
									<option value="<%=objAtr.getIdInstancia()%>" <%=seleccionado!=null && seleccionado.getIdInstancia()==objAtr.getIdInstancia()?"selected":"" %>>
										<%=objAtr.getNombre() %>
									</option>
								<%
									}
								}
								%>
							</select>
						</div>
					</div>
						
					<%
				}
			}
		%>
			</form>
	<%
	}
	else{
		%>
		<div class="row">
			<div class="col-xs-12">
				<fmt:message key="p2.2.detalle.vacio"></fmt:message>
			</div>
		</div>
		<%
	}
	%>
	</div>
	<%if(listaTipos != null && listaTipos.size() > 0){
		int idPag = 0;
		for(TipoAtributoComplejo act : listaTipos){
			idPag++;	
			%>
	<div id="modDetAtributos<%=act.getIdTipoHijo() %>" class="modDetAtributosC" data-num="<%=act.getIdTipoHijo()%>" data-tiporelacion="<%=act.getIdTipoRelacion()%>">
	</div>
	
	<%
		}
	}
}
else{
	%>
	<div class="alert alert-danger">
		<fmt:message key="p2.2.objetos.error.noExiste" ></fmt:message>
	</div>
	<%
}
%>