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
%>
<div class="container">
	<%if(message.equals("p3.1.msg.ok") && g != null){ %>
	<h2>
		<%= g.getName()%> 
	</h2>
	<%} else if (message.equals("p3.1.msg.grpNoExiste")){%>
	<p>
		<fmt:message key="p3.1.msg.grpNoExiste"></fmt:message>
	</p>
	<%} %>
</div>
<div class="container">
	<h3>
		<fmt:message key="p34.listaTemas"></fmt:message>
	</h3>
	<%if (listaTemas != null && listaTemas.size() > 0){
		for(Tema t : listaTemas){		
		%>
	<p><%=t.getNombre() %></p>
	<%
		}
	}
	%>
</div>
<div class="container">
	
</div>
<%@ include file="/WEB-INF/views/footer.jsp"%>