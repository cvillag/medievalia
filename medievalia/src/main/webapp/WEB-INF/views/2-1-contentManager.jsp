<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Tema"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<% 
	String message = (String)request.getAttribute("message");
	HttpSession s = request.getSession();
	Group g = (Group) ses.getAttribute("grupoActual");
	@SuppressWarnings(value="unchecked")
	List<Tema> listaTemas = (List<Tema>) request.getAttribute("listaTemas");
	User director = (User)request.getAttribute("director");
	User user = (User)request.getAttribute("user");
	String profe = (String) request.getAttribute("profe");
	int numChar = ((Integer)request.getAttribute("numChargesToValidate")).intValue();
	int numStud = ((Integer)request.getAttribute("numStudentsToValidate")).intValue();
	int numChSt = ((Integer)request.getAttribute("numChargesToValidateS")).intValue();
	int numS = ((Integer)request.getAttribute("numStudents")).intValue();
	int numT = ((Integer)request.getAttribute("numTeachers")).intValue();
	int numChStTot = ((Integer)request.getAttribute("numChargesByStudent")).intValue();
	
	int numStudy = ((Integer)request.getAttribute("numStudiesToValidate")).intValue();
	int numStudStud = ((Integer)request.getAttribute("numStudentsSToValidate")).intValue();
	int numSS = ((Integer)request.getAttribute("numStudiesToValidateS")).intValue();
	int numStStTot = ((Integer)request.getAttribute("numStudiesByStudent")).intValue();
	
	int numLugar = ((Integer)request.getAttribute("numPlaceToValidate")).intValue();
	int numLugarStud = ((Integer)request.getAttribute("numPlaceSToValidate")).intValue();
	int numLS = ((Integer)request.getAttribute("numPlacesToValidateS")).intValue();
	int numStLgTot = ((Integer)request.getAttribute("numPlacesByStudent")).intValue();
	
	int numAuthor = ((Integer)request.getAttribute("numAuthorsToValidate")).intValue();
	int numAuthorStud = ((Integer)request.getAttribute("numAuthorsSToValidate")).intValue();
	int numAS = ((Integer)request.getAttribute("numAuthorsToValidateS")).intValue();
	int numStAsTot = ((Integer)request.getAttribute("numAuthorsByStudent")).intValue();
%>
<%if(message.equals("p3.1.msg.ok") && g != null){ %>
<div class="container">
	<div class="row">
		<div class="panel panel-default col-sm-4">
			
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
				<h3 class="panel-title"><fmt:message key="p2-1.temas"></fmt:message></h3>
			</div>
			<div class="panel-body">
				<p><fmt:message key="p2-1.numTemas"></fmt:message>&nbsp;<span class="label label-info"><%=listaTemas.size() %></span></p>
				<p><a href="topicManager.do"><button type="button" class="btn btn-sm btn-default" id="btnListaTemas"><fmt:message key="p2-1.btnListaTemas"></fmt:message></button></a></p>
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
	<div class="row">
		<div class="panel panel-default col-sm-4">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="p2-1.cargo"></fmt:message></h3>
			</div>
			<div class="panel-body">
				<p>
				<%if(profe.equals("ok")){ %>
				<fmt:message key="p2-1.cargo02"></fmt:message>
					<%if(numChar == 0){ %>
					<span class="label label-success"><%=numChar %></span>
					<%}else{%>
					<span class="label label-warning"><%=numChar %></span>
					<%
					}%>
				&nbsp;<fmt:message key="p2-1.cargo03"></fmt:message>
					<%if(numStud == 0){ %>
					<span class="label label-success"><%=numStud %></span>
					<%}else{%>
					<span class="label label-warning"><%=numStud %></span>
					<%
					}%>
				&nbsp;<fmt:message key="p2-1.cargo04"></fmt:message>
				<%}
				else{%>
					<fmt:message key="p2-1.cargosPropios"></fmt:message>
					<span class="label label-info"><%=numChStTot %></span>
					</p><p>
					<fmt:message key="p2-1.cargo02"></fmt:message>
					<%if(numChSt == 0){ %>
					<span class="label label-success"><%=numChSt %></span>
					<%}else{%>
					<span class="label label-warning"><%=numChSt %></span>
					<%
					}
				} %>
				</p>
				<p><button type="button" class="btn btn-sm btn-default" id="btnGestionCargos"><fmt:message key="p2-1.cargo.btn"></fmt:message></button></p>
			</div>
		</div>
		<div class="panel panel-default col-sm-4">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="p2-1.estudio"></fmt:message></h3>
			</div>
			<div class="panel-body">
				<p>
				<%if(profe.equals("ok")){ %>
				<fmt:message key="p2-1.estudio02"></fmt:message>
					<%if(numStudy == 0){ %>
				<span class="label label-success"><%=numStudy %></span>
					<%}else{ %>
				<span class="label label-warning"><%=numStudy %></span>
					<%} %>
				&nbsp;<fmt:message key="p2-1.estudio03"></fmt:message>
					<%if(numStudStud == 0){ %>
				<span class="label label-success"><%=numStudStud %></span>
					<%}else{ %>
				<span class="label label-warning"><%=numStudStud %></span>
					<%} %>
				&nbsp;<fmt:message key="p2-1.estudio04"></fmt:message>
				<%}
				else{%>
				<fmt:message key="p2-1.estudiosPropios"></fmt:message>
				<span class="label label-info"><%=numSS %></span>
				</p><p>
				<fmt:message key="p2-1.estudio02"></fmt:message>
					<%if(numStStTot == 0){ %>
					<span class="label label-success"><%=numStStTot %></span>	
					<%}else{ %>
					<span class="label label-warning"><%=numStStTot %></span>
					<%} %>
				<%} %>
				</p>
				<p><button type="button" class="btn btn-sm btn-default" id="btnGestionEstudios"><fmt:message key="p2-1.estudio.btn"></fmt:message></button></p>
			</div>
		</div>
		<div class="panel panel-default col-sm-4">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="p2-1.lugar"></fmt:message></h3>
			</div>
			<div class="panel-body">
				<p>
				<%if(profe.equals("ok")){ %>
				<fmt:message key="p2-1.lugar02"></fmt:message>
					<%if(numLugar == 0){ %>
				<span class="label label-success"><%=numLugar %></span>
					<%}else{ %>
				<span class="label label-warning"><%=numLugar %></span>
					<%} %>
				&nbsp;<fmt:message key="p2-1.lugar03"></fmt:message>
					<%if(numAuthorStud == 0){ %>
				<span class="label label-success"><%=numAuthorStud %></span>
					<%}else{ %>
				<span class="label label-warning"><%=numAuthorStud %></span>
					<%} %>
				&nbsp;<fmt:message key="p2-1.lugar04"></fmt:message>
				<%}
				else{%>
				<fmt:message key="p2-1.lugaresPropios"></fmt:message>
				<span class="label label-info"><%=numLS %></span>
				</p><p>
				<fmt:message key="p2-1.lugar02"></fmt:message>
					<%if(numStLgTot == 0){ %>
					<span class="label label-success"><%=numStLgTot %></span>	
					<%}else{ %>
					<span class="label label-warning"><%=numStLgTot %></span>
					<%} %>
				<%} %>
				</p>
				<p><button type="button" class="btn btn-sm btn-default" id="btnGestionLugares"><fmt:message key="p2-1.lugar.btn"></fmt:message></button></p>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="panel panel-default col-sm-4">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="p2-1.autor"></fmt:message></h3>
			</div>
			<div class="panel-body">
				<p>
				<%if(profe.equals("ok")){ %>
				<fmt:message key="p2-1.autor02"></fmt:message>
					<%if(numAuthor == 0){ %>
				<span class="label label-success"><%=numAuthor %></span>
					<%}else{ %>
				<span class="label label-warning"><%=numAuthor %></span>
					<%} %>
				&nbsp;<fmt:message key="p2-1.autor03"></fmt:message>
					<%if(numAuthorStud == 0){ %>
				<span class="label label-success"><%=numAuthorStud %></span>
					<%}else{ %>
				<span class="label label-warning"><%=numAuthorStud %></span>
					<%} %>
				&nbsp;<fmt:message key="p2-1.autor04"></fmt:message>
				<%}
				else{%>
				<fmt:message key="p2-1.autoresPropios"></fmt:message>
				<span class="label label-info"><%=numAS %></span>
				</p><p>
				<fmt:message key="p2-1.autor02"></fmt:message>
					<%if(numStAsTot == 0){ %>
					<span class="label label-success"><%=numStAsTot %></span>	
					<%}else{ %>
					<span class="label label-warning"><%=numStAsTot %></span>
					<%} %>
				<%} %>
				</p>
				<p><button type="button" class="btn btn-sm btn-default" id="btnGestionAutores"><fmt:message key="p2-1.autor.btn"></fmt:message></button></p>
			</div>
		</div>
		
		<div class="panel panel-default col-sm-4">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="p2-1.personaje"></fmt:message></h3>
			</div>
			<div class="panel-body">
				<p>
				<%if(profe.equals("ok")){ %>
				<fmt:message key="p2-1.personaje02"></fmt:message>
					<%if(numAuthor == 0){ %>
				<span class="label label-success">23</span>
					<%}else{ %>
				<span class="label label-warning">23</span>
					<%} %>
				&nbsp;<fmt:message key="p2-1.personaje03"></fmt:message>
					<%if(numAuthorStud == 0){ %>
				<span class="label label-success">23</span>
					<%}else{ %>
				<span class="label label-warning">23</span>
					<%} %>
				&nbsp;<fmt:message key="p2-1.personaje04"></fmt:message>
				<%}
				else{%>
				<fmt:message key="p2-1.autoresPropios"></fmt:message>
				<span class="label label-info">23</span>
				</p><p>
				<fmt:message key="p2-1.personaje02"></fmt:message>
					<%if(numStAsTot == 0){ %>
					<span class="label label-success">23</span>	
					<%}else{ %>
					<span class="label label-warning">23</span>
					<%} %>
				<%} %>
				</p>
				<p><button type="button" class="btn btn-sm btn-default" id="btnGestionPersonajes"><fmt:message key="p2-1.personaje.btn"></fmt:message></button></p>
			</div>
		</div>
	</div>
</div>
<%} else if (message.equals("p3.1.msg.grpNoExiste")){%>
	<div class="panel panel-danger">
		<div class="panel-heading">
			<p><fmt:message key="p3.1.msg.grpNoExiste"></fmt:message></p>
		</div>
	</div>	
<%} %>


<%@ include file="/WEB-INF/views/footer.jsp"%>