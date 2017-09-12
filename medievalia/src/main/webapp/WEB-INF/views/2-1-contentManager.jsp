<%@page import="com.cvilla.medievalia.domain.TipoObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Tema"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<% 
	int numS = ((Integer)request.getAttribute("numStudents")).intValue();
	int numT = ((Integer)request.getAttribute("numTeachers")).intValue();
	String message = (String)request.getAttribute("message");
	HttpSession s = request.getSession();
	Group g = (Group) ses.getAttribute("grupoActual");
	User director = (User)request.getAttribute("director");
	User user = (User)request.getAttribute("user");
	String profe = (String) request.getAttribute("profe");
	
	@SuppressWarnings("unchecked")
	List<TipoObjetoDOM> listaTipos = (List<TipoObjetoDOM>) request.getAttribute("listaObjetos");
%>
<%if(message.equals("p3.1.msg.ok") && g != null){ %>
<div class="container">
	<div class="row">
		<div class="panel panel-default col-sm-8">
			
			<div class="panel-heading">
				<h3 class="panel-title"><%= g.getName()%></h3>
			</div>
			<div class="panel-body">
				<p><fmt:message key="p2-1.director"></fmt:message>&nbsp;<%=director.getUser_long_name() %></p>
				<p><%=g.getDescription() %></p>
			</div>
		</div>
		<div class="panel panel-default col-sm-4">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="p2-1.participantes"></fmt:message></h3>
			</div>
			<div class="panel-body">
				<!-- TODO: Insertar lista de profesores y número de alumnos -->
				<p><fmt:message key="p2-1.numAlumnos"></fmt:message>&nbsp;<span class="label label-info"><%=numS %></span></p>
				<p><fmt:message key="p2-1.numTeachers"></fmt:message>&nbsp;<span class="label label-info"><%=numT %></span></p>
				<%if(user.getUser_role() == Constants.ROLE_PROFESOR){ %>
				<p><button type="button" class="btn btn-sm btn-default" id="btnMatricularAlumnos"><fmt:message key="p2-1.listaUsuarios.btn"></fmt:message></button></p>
				<%} %>
			</div>
		</div>
	</div>
	
	
<%
	int i = 0;
	for(TipoObjetoDOM tipo : listaTipos){ 
		if(i % 3 == 0){
	%>	
	<div class="row">
	<%	} %>
		<div class="panel panel-default col-sm-4">
			<div class="panel-heading">
				<h3 class="panel-title"><%=tipo.getNombreDOM() %></h3>
			</div>
			<div class="panel-body">
				<p>
				<%if(profe.equals("ok")){ %>
				<fmt:message key="p2-1.objeto02"></fmt:message>
<%-- 					<%if(numChar == 0){ %> --%>
<%-- 					<span class="label label-success"><%=numChar %></span> --%>
<%-- 					<%}else{%> --%>
					<span class="label label-warning"><%=0 %></span>
<%-- 					<% --%>
<%--  					}%> --%>
				&nbsp;<fmt:message key="p2-1.objeto03"></fmt:message>
<%-- 					<%if(numStud == 0){ %> --%>
<%-- 					<span class="label label-success"><%=numStud %></span> --%>
<%-- 					<%}else{%> --%>
					<span class="label label-warning"><%=0 %></span>
<%-- 					<% --%>
<%--  					}%> --%>
				&nbsp;<fmt:message key="p2-1.objeto04"></fmt:message>
				<%}
 				else{%>
					<fmt:message key="p2-1.objetosPropios"></fmt:message>
					<span class="label label-info"><%=0 %></span>
					</p><p>
					<fmt:message key="p2-1.objeto02"></fmt:message>
<%-- 					<%if(numChSt == 0){ %> --%>
<%-- 					<span class="label label-success"><%=numChSt %></span> --%>
<%-- 					<%}else{%> --%>
					<span class="label label-warning"><%=0 %></span>
					<%
 					//}
				} %>
				</p>
				<p><button type="button" class="btn btn-sm btn-default btnGestionObjetos" data-val="<%=tipo.getTipoDOM()%>"><fmt:message key="p2-1.objeto.btn"></fmt:message></button></p>
			</div>
		</div>
	<%if(i++ % 3 == 2){
	%>	
	</div>
	<%}
	}%>
</div>
<form id="formOC" action="objectController.do">
	<input type="hidden" name="idTipo" id="idTipo">
</form>
<%} else if (message.equals("p3.1.msg.grpNoExiste")){%>
	<div class="panel panel-danger">
		<div class="panel-heading">
			<p><fmt:message key="p3.1.msg.grpNoExiste"></fmt:message></p>
		</div>
	</div>	
<%} %>

<%@ include file="/WEB-INF/views/footer.jsp"%>