<%@page import="java.util.ArrayList"%>
<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%
ArrayList<User> list = (ArrayList<User>)request.getAttribute("users");
%>
<div class="container">
	<div class="jumbotron">
		<h2><fmt:message key="p1-3.bienvenida"/></h2>
		<h3><fmt:message key="p1-3.bienvenida"/></h3>
	</div>
	<%
		for(User u : list){%>
			<p><%=u.getUser_long_name() %></p>
	<%	}
	%>
</div>