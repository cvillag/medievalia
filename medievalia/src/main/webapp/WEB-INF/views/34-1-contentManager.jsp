<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<% String message = (String)request.getAttribute("message"); %>
<div class="container">
<%if(message.equals("p3.1.msg.ok")){ %>
	<p><fmt:message key="p3.1.msg.ok"></fmt:message> </p>
<%} else if (message.equals("p3.1.msg.grpNoExiste")){%>
	<p><fmt:message key="p3.1.msg.grpNoExiste"></fmt:message> </p>
<%} %>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>