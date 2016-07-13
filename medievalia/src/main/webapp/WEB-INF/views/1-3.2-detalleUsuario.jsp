<%@page import="com.cvilla.medievalia.domain.Role"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%
@SuppressWarnings("unchecked")
User user = (User)request.getAttribute("user");
//String message = (String)request.getAttribute("message");
%>
<div class="container">
<legend><fmt:message key="p1-3.2.bienvenida"/> <%=user.getUser_long_name() %></legend>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>