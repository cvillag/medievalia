<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<div class="container theme-showcase" role="main">
		<%	if(request.getAttribute("mensaje2") != null){%>
		<div class="alert alert-danger"><fmt:message key="${mensaje2}"/></div>
		<%} %>
		<input type="button" onclick="location.href='main.do'" value="Aceptar"/>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>