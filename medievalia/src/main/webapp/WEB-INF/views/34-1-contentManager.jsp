<%@page import="com.cvilla.medievalia.domain.TemaGrupo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<% 
	String message = (String)request.getAttribute("message");
	@SuppressWarnings(value="unchecked")
	List<TemaGrupo> listaTemas = (List<TemaGrupo>) request.getAttribute("listaTemas");
%>
<div class="container">
<%if(message.equals("p3.1.msg.ok")){ %>
	<p><fmt:message key="p3.1.msg.ok"></fmt:message> </p>
<%} else if (message.equals("p3.1.msg.grpNoExiste")){%>
	<p><fmt:message key="p3.1.msg.grpNoExiste"></fmt:message> </p>
<%} %>
</div>
<div class="container">
	<h2><fmt:message key="p34.listaTemas"></fmt:message></h2>
	<%if (listaTemas != null && listaTemas.size() > 0){
		for(TemaGrupo t : listaTemas){		
		%>
			<p><%=t.getNombreTema() %></p>
		<%
		}
	}
	%>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>