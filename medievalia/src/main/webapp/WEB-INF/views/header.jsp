<%@page import="com.cvilla.medievalia.domain.TipoObjetoDOM"%>
<%@page import="com.cvilla.medievalia.domain.Group"%>
<%@ page session="false"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%@ page import="com.cvilla.medievalia.utils.Header"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Tema principal  
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
<!-- Tema opcional 
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
-->
<link rel="stylesheet" href="styles/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="styles/css/general.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Versión compilada y comprimida del JavaScript de Bootstrap -->
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<% 
	@SuppressWarnings(value="unchecked")
	List<String> scripts = (List<String>)request.getAttribute("scripts");
	if(scripts != null && scripts.size() > 0){
		for(String sc : scripts){
		%>
<script src="<%=sc%>"></script>
<%
		}
	}
%>
<script src="js/common/header.js"></script>
<title>Medievalia</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<%
	@SuppressWarnings("unchecked")
	List<Header> headers = (List<Header>) request.getAttribute("headers");
	HttpSession ses = request.getSession();
	Group grupoactual = (Group) ses.getAttribute("grupoActual");
	TipoObjetoDOM tipoAct = (TipoObjetoDOM) ses.getAttribute("tipoObjeto");
	%>
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Medievalia</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<%if(grupoactual == null){ %>
				<a class="navbar-brand" href="main.do">Medievalia</a>
				<%}
          	else{
          		if(tipoAct == null){
          	%>
				<a class="navbar-brand" href="main.do">Medievalia </a><a class="navbar-brand" href="contentManager.do">/&nbsp;<%=grupoactual.getName()%></a>
				<%}
          		else{
          			%>
          		<a class="navbar-brand" href="main.do">Medievalia </a>
          		<a class="navbar-brand" href="contentManager.do">/&nbsp;<%=grupoactual.getName()%></a>
          		<a class="navbar-brand">/&nbsp;<%=tipoAct.getNombreDOM() %></a>
<!--           		<form id="formHeader" method="post" action="objectController.do"> -->
<%-- 					<input type="hidden" name="idTipo" id="idTipo" value="<%=tipoAct.getTipoDOM()%>"> --%>
<!-- 				</form> -->
          			<%
          		}
			}%>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<%
				if(!(headers == null || headers.size() < 1)){
					for(Header h : headers){
						if(h.getSons() == null || h.getSons().size() < 1){
				%>
					<li><a class="dropdown-toggle" href="<%= h.getServiceUrl() %>"><%= h.getServiceName() %></a></li>
					<%
						}
						else{
							%>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"><%= h.getServiceName() %><span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<%
							for(Header h2 : h.getSons()){
								%>
							<li><a class="dropdown-toggle"
								href="<%= h2.getServiceUrl() %>"><%= h2.getServiceName() %></a></li>
							<%
							}
							%>
						</ul></li>
					<%
						}
						
					}
					%>
					<button type="button" class="btn btn-default navbar-btn"
						onclick="document.location.href='logout.do'">
						<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
					</button>
					<%
				}
					%>
				</ul>
			</div>
		</div>
	</nav>