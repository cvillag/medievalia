<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
boolean disabled = ((Integer) request.getAttribute("modo")).intValue() == 1;
@SuppressWarnings("unchecked")
List<TipoAtributoComplejo> listaTipos = (List<TipoAtributoComplejo>) request.getAttribute("tatributoc");
@SuppressWarnings("unchecked")
Map<Integer, Integer> badges = (Map<Integer, Integer>) request.getAttribute("badges");
if(objeto != null){
	List<InstanciaAtributoComplejo> acl = objeto.getAtributosComplejos();
	%>
	<ul class="nav nav-tabs">
		<li class="active listaA" data-val="0"><a href="#"><fmt:message key="p2.2.detalle.atributos"></fmt:message></a></li>
		<%
		if(listaTipos != null && listaTipos.size() > 0){
			for(TipoAtributoComplejo ac : listaTipos){
				int badge = 0;
				if(badges != null){
					if(badges.containsKey(ac.getIdTipoHijo())){
						badge = badges.get(ac.getIdTipoHijo());
					}
				}
			%>
		<li class="listaA" data-val="<%=ac.getIdTipoHijo()%>"><a href="#"><%=ac.getNombreAtributo() %>&nbsp;<span class="label <%=badge==0?"label-success":"label-warning"%>"><%=badge %></span></a></li>
		<%	}
		}%>
	</ul>
	<div id="modDetAtributos0">
	<%
	
	List<InstanciaAtributoSencillo> atrS = objeto.getAtributosSencillos();
	if(atrS != null && atrS.size() > 0){
		%>
		
			<form>
			<%
			for(InstanciaAtributoSencillo as : atrS){
				if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_FECHA){
					boolean empty = as.getValor() == null;
					int d =0,m=0,a=0;
					if(!empty){
						d = ((SpecialDate)as.getValor()).getDia();
						m = ((SpecialDate)as.getValor()).getMes();
						a = ((SpecialDate)as.getValor()).getAnio();
					}
					%>
					<div class="row">
						<div class="col-xs-12">
							<label><%=as.getNombreTipoAtributo() %></label>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-3 form-group">
							<label for="dia"><fmt:message key="p2.2.detalle.dia"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="dia<%=as.getIdAtributo() %>" value="<%=empty?"":d%>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="mes"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="mes<%=as.getIdAtributo() %>" value="<%=empty?"":m%>" size="2" min="1" max="12" class="inputMonths form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="anio"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="anio<%=as.getIdAtributo() %>" value="<%=empty?"":a%>"  size="2" min="1" max="12" class="inputYears form-control"	>
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_DOUBLE){
					boolean nulo = as.getValor() == null;
					String d = "";
					if(!nulo)
						d = ((Double)as.getValor()).toString();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="double"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" <%=disabled?"disabled":"" %> id="double<%=as.getIdAtributo() %>" value="<%=d%>"
							 	class="form-control">
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_INT){
					boolean nulo = as.getValor() == null;
					int i=0;
					if(!nulo)
						i = ((Integer)as.getValor()).intValue();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="int"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" <%=disabled?"disabled":"" %> id="int<%=as.getIdAtributo() %>" value="<%if(!nulo){ %><%=i%><%} %>" class="form-control">
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
		for(TipoAtributoComplejo act : listaTipos){
			idPag++;	
			%>
	<div id="modDetAtributos<%=act.getIdTipoHijo() %>" class="modDetAtributosC">
		<ul class="list-group">
		<%	for(InstanciaAtributoComplejo ac : acl){
				if(ac.getTipoHijo().getTipoDOM() == act.getIdTipoHijo()){
			%>
			<li class="list-group-item clearfix">
				<%=ac.getInstanciaHijo().getNombre() %>
				<span class="pull-right">
				<%if(ac.getValidado() == Constants.OBJETO_VALIDADO){ %>
				<span id="validado<%=ac.getInstanciaHijo().getIdInstancia() %>" class="label label-success validationText" data-textvalidacion="<%=ac.getTextoValidacion()%>"><fmt:message key="general.validado"></fmt:message><%if(!ac.isTextoLeido()){ %><span class="label label-info">1</span> <%} %></span>
				<%}
					else{
						if(ac.getValidado() == Constants.OBJETO_NO_VALIDADO){ %>				
				<span id="validado<%=ac.getInstanciaHijo().getIdInstancia() %>" class="label label-warning novalidationText" data-textvalidacion="<%=ac.getTextoValidacion()%>"><fmt:message key="general.novalidado"></fmt:message><%if(!ac.isTextoLeido()){ %><span class="label label-info">1</span> <%} %></span>
				
					<%}else{ %>
				<span id="validado<%=ac.getInstanciaHijo().getIdInstancia() %>" class="label label-danger denegationText" data-textvalidacion="<%=ac.getTextoValidacion()%>"><fmt:message key="general.denegado"></fmt:message></span>
				<%	}
				}%>
				</span>
			<%} %>
			</li>
		<%
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