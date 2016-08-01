<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%
User u = (User)request.getAttribute("usuario");
%>
<%@ include file="/WEB-INF/views/common/inicio-comun.jsp"%>
<%@ include file="/WEB-INF/views/common/groups-list.jsp"%>
<%@ include file="/WEB-INF/views/footer.jsp"%>