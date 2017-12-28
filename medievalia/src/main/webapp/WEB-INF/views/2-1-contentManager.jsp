<%@page import="java.util.Map"%>
<%@page import="com.cvilla.medievalia.domain.TipoObjeto"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
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
	List<TipoObjeto> listaTipos = (List<TipoObjeto>) request.getAttribute("listaObjetos");
	
	@SuppressWarnings("unchecked")
	Map<Integer,Integer> profe1 = (Map<Integer,Integer>) request.getAttribute("objectsToValidate"); 
	
	@SuppressWarnings("unchecked")
	Map<Integer,Integer> profe2 = (Map<Integer,Integer>) request.getAttribute("usersToValidate");
	
	@SuppressWarnings("unchecked")
	Map<Integer,Integer> alum1 = (Map<Integer,Integer>) request.getAttribute("ownObjectsToValidate");
	
	@SuppressWarnings("unchecked")
	Map<Integer,Integer> alum2 = (Map<Integer,Integer>) request.getAttribute("totalOwnObjects");
	
	@SuppressWarnings("unchecked")
	Map<Integer,Integer> alum3 = (Map<Integer,Integer>) request.getAttribute("ownObjectsToValidateAC");
	
	@SuppressWarnings("unchecked")
	Map<Integer,Integer> alum4 = (Map<Integer,Integer>) request.getAttribute("totalOwnObjectsAC");
	
	@SuppressWarnings("unchecked")
	Map<Integer,Integer> totalObjsVal = (Map<Integer,Integer>) request.getAttribute("totalObjsVal");
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
				<p><button type="button" class="btn btn-sm btn-info" id="btnMatricularAlumnos" title="<fmt:message key="acc.matricularusuarios"></fmt:message>"><fmt:message key="p2-1.listaUsuarios.btn"></fmt:message></button></p>
				<%} %>
			</div>
		</div>
	</div>
	
	
<%
	int i = 0;
	for(TipoObjeto tipo : listaTipos){ 
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
				<fmt:message key="p2-1.objeto-total"></fmt:message>&nbsp;
				<span class="label label-info"><%=totalObjsVal.get(tipo.getTipoDOM()).intValue() %></span>
				</p><p>
				<fmt:message key="p2-1.objeto02"></fmt:message>
					<% int numToVal = profe1.get(tipo.getTipoDOM()).intValue();
					if(numToVal == 0){ %>
					<span class="label label-success"><%=numToVal %></span>
					<%}else{%>
					<span class="label label-warning"><%=numToVal %></span>
					<%
  					}%>	
					<%
					int numStudents = profe2.get(tipo.getTipoDOM()).intValue();
					if(numStudents > 0){ %>
					&nbsp;<fmt:message key="p2-1.objeto03"></fmt:message>
					<span class="label label-warning"><%=numStudents %></span>
					<%}%>
				&nbsp;<fmt:message key="p2-1.objeto04"></fmt:message>
				<%}
 				else{%>
					<fmt:message key="p2-1.objetosPropios"></fmt:message>
					<span class="label label-info"><%=alum2.get(tipo.getTipoDOM()).intValue() %></span>
					
					<%
					int numStudentToVal = alum1.get(tipo.getTipoDOM()).intValue();
					if(numStudentToVal > 0){ %>
					.&nbsp;
					<fmt:message key="p2-1.objeto02"></fmt:message>
					<span class="label label-warning"><%=numStudentToVal %></span>
					<%}
 					%>
					</p><p>
					<fmt:message key="p2-1.atributosPropios"></fmt:message>
					<span class="label label-info"><%=alum4.get(tipo.getTipoDOM()).intValue() %></span>
					<%
					int numStudentToValAC = alum3.get(tipo.getTipoDOM()).intValue();
					if(numStudentToValAC > 0){ %>
					.&nbsp;
					<fmt:message key="p2-1.objeto02"></fmt:message>
					<span class="label label-warning"><%=numStudentToValAC %></span>
					<%
 					}%>
				<%} %>
				</p>
				<p><button type="button" class="btn btn-sm btn-info btnGestionObjetos" data-val="<%=tipo.getTipoDOM()%>" title="<fmt:message key="acc.gestobj"></fmt:message>"><fmt:message key="p2-1.objeto.btn"></fmt:message></button></p>
			</div>
		</div>
	<%if(i++ % 3 == 2){
	%>	
	</div>
	<%}
	}%>
</div>
<form id="formOC" method="post" action="objectController.do">
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