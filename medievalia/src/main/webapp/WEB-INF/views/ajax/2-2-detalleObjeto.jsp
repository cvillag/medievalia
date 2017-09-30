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
boolean disabled = ((Integer) request.getAttribute("modo")).intValue() == 1;
@SuppressWarnings("unchecked")
List<TipoAtributoComplejoDOM> listaTipos = (List<TipoAtributoComplejoDOM>) request.getAttribute("tatributoc");
List<AtributoComplejoDOM> acl = objeto.getAtributosComplejos();
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
							<input type="number" <%=disabled?"disabled":"" %> id="dia<%=as.getIdAtributo() %>" value="<%=((SpecialDate)as.getValor()).getDia()%>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="mes"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="mes<%=as.getIdAtributo() %>" value="<%=((SpecialDate) as.getValor()).getMes()%>" size="2" min="1" max="12" class="inputMonths form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="anio"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="anio<%=as.getIdAtributo() %>" value="<%=((SpecialDate) as.getValor()).getAnio()%>"  size="2" min="1" max="12" class="inputYears form-control"	>
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_DOUBLE){
					double d = ((Double)as.getValor()).doubleValue();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="double"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" <%=disabled?"disabled":"" %> id="double<%=as.getIdAtributo() %>" value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${d}"></fmt:formatNumber>" class="form-control">
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_INT){
					int i = ((Integer)as.getValor()).intValue();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="int"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" <%=disabled?"disabled":"" %> id="int<%=as.getIdAtributo() %>" value="<%=i%>" class="form-control">
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
							<input type="text" <%=disabled?"disabled":"" %> id="string<%=as.getIdAtributo() %>" value="<%=s%>" class="form-control">
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
							<textarea <%=disabled?"disabled":"" %> id="text<%=as.getIdAtributo() %>" class="form-control"><%=s%></textarea>
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
		<ul class="list-group">
		<%	for(AtributoComplejoDOM ac : acl){
				if(ac.getTipoHijo().getTipoDOM() == act.getIdTipoHijo()){
			%>
			<li class="list-group-item"><%=ac.getInstanciaHijo().getNombre() %></li>
		<%
				}
			} %>
		</ul>
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