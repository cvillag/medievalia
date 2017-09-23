<%@page import="com.cvilla.medievalia.domain.TipoAtributoComplejoDOM"%>
<%@page import="com.cvilla.medievalia.domain.AtributoComplejoDOM"%>
<%@page import="com.cvilla.medievalia.utils.SpecialDate"%>
<%@page import="com.cvilla.medievalia.domain.AtributoSencilloDOM"%>
<%@page import="com.cvilla.medievalia.domain.ObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
User user = (User) session.getAttribute("user");
String type= (String) request.getAttribute("message");
ObjetoDOM objeto = (ObjetoDOM) request.getAttribute("object");
@SuppressWarnings("unchecked")
List<TipoAtributoComplejoDOM> listaTipos = (List<TipoAtributoComplejoDOM>) request.getAttribute("tatributoc");
List<AtributoComplejoDOM> acl = objeto.getAtributosComplejos();
@SuppressWarnings("unchecked")
List<AtributoComplejoDOM> disp = (List<AtributoComplejoDOM>) request.getAttribute("disponibles");
if(objeto != null){
	%>
	<ul class="nav nav-tabs">
		<li class="active listaA" data-val="0"><a href="#"><fmt:message key="p2.2.detalle.atributos"></fmt:message></a></li>
		<%
		if(listaTipos != null && listaTipos.size() > 0){
			for(TipoAtributoComplejoDOM ac : listaTipos){
			%>
		<li class="listaA" data-val="<%=ac.getIdTipoHijo()%>"><a href="#"><%=ac.getNombreAtributo() %></a></li>
		<%	}
		}%>
	</ul>
	<div id="modDetAtributos0">
	<%
	
	List<AtributoSencilloDOM> atrS = objeto.getAtributosSencillos();
	if(atrS != null && atrS.size() > 0){
		%>
		
			<form>
			<%
			for(AtributoSencilloDOM as : atrS){
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
							<input type="number" id="dia<%=as.getIdAtributo() %>" value="<%=as.getValor()!=null?((SpecialDate)as.getValor()).getDia():""%>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="mes"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" id="mes<%=as.getIdAtributo() %>" value="<%=as.getValor()!=null?((SpecialDate) as.getValor()).getMes():""%>" size="2" min="1" max="12" class="inputMonths form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="anio"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" id="anio<%=as.getIdAtributo() %>" value="<%=as.getValor()!=null?((SpecialDate) as.getValor()).getAnio():""%>"  size="2" min="1" max="12" class="inputYears form-control"	>
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_DOUBLE){
					double d;
					boolean nulo;
					nulo = as.getValor() == null;
					if(!nulo){
						d = ((Double)as.getValor()).doubleValue();
					}
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="double"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" id="double<%=as.getIdAtributo() %>" value="<%if(!nulo){ %><fmt:formatNumber type="number" maxFractionDigits="3" value="${d}"></fmt:formatNumber><%} %>" class="form-control">
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_INT){
					int i = 0;
					boolean nulo;
					nulo = as.getValor() == null;
					if(!nulo){
						i= ((Integer)as.getValor()).intValue();
					}
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="int"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" id="int<%=as.getIdAtributo() %>" value="<%=nulo?i:""%>" class="form-control">
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
							<input type="text" id="string<%=as.getIdAtributo() %>" value="<%=s==null?s:""%>" class="form-control">
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
							<textarea id="text<%=as.getIdAtributo() %>" class="form-control"><%=s==null?s:""%></textarea>
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
		for(TipoAtributoComplejoDOM act : listaTipos){
			idPag++;	
			%>
	<div id="modDetAtributos<%=idPag %>" class="modDetAtributosC">
		<div class="row">
			<div class="col-xs-6 form-group">
				<label><fmt:message key="p2.2.detalle.actual"></fmt:message></label>
				<ul class="list-group" id="list<%=idPag%>">
				<%	for(AtributoComplejoDOM ac : acl){
						int tipo = ac.getTipoHijo().getTipoDOM();
						int idAC = ac.getInstanciaHijo().getIdInstancia();
						if(tipo == act.getIdTipoHijo()){
					%>
					<li class="list-group-item" id="ulI<%=tipo%>-<%=idAC %>">
						<button type="button" id="remAtC<%=tipo%>-<%=idAC %>" class="btn btn-sm btn-default remComplexAttribute" data-tipo="<%=tipo%>" data-inst="<%=idAC%>" data-pag="<%=idPag %>" data-name="<%=ac.getInstanciaHijo().getNombre() %>">
							<span class="glyphicon glyphicon-arrow-right"></span>
						</button>
						<%=ac.getInstanciaHijo().getNombre() %>
					</li>
				<%
						}
					} %>
				</ul>
			</div>
			<div class="col-xs-6">
				<label><fmt:message key="p2.2.detalle.disponible"></fmt:message></label>
				<ul class="list-group" id="listD<%=idPag%>">
				<%	for(AtributoComplejoDOM ac2 : disp){
						if(ac2.getTipoHijo().getTipoDOM() == act.getIdTipoHijo()){
							int tipo = ac2.getTipoHijo().getTipoDOM();
							int idAC = ac2.getInstanciaHijo().getIdInstancia();
					%>
					<li class="list-group-item" id="ulD<%=tipo%>-<%=idAC %>">
						<button type="button" id="addAtC<%=tipo%>-<%=idAC %>" class="btn btn-sm btn-default addComplexAttribute" data-tipo="<%=tipo%>" data-inst="<%=idAC%>" data-pag="<%=idPag %>" data-name="<%=ac2.getInstanciaHijo().getNombre() %>">
							<span class="glyphicon glyphicon-arrow-left"></span>
						</button>
						<%=ac2.getInstanciaHijo().getNombre() %>
					</li>
				<%
						}
					} %>
				</ul>
			</div>
		</div>
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