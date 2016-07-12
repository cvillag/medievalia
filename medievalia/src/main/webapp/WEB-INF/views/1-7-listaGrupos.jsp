<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.cvilla.medievalia.domain.Group"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%
@SuppressWarnings("unchecked")
ArrayList<Group> list = (ArrayList<Group>)request.getAttribute("listaGrupos");
@SuppressWarnings("unchecked")
ArrayList<User> directors = (ArrayList<User>)request.getAttribute("directors");
User user = (User)request.getAttribute("user");
//String message = (String)request.getAttribute("message");
%>
<div class="container">
<legend><fmt:message key="p1-7.bienvenida"/></legend>
	<%if(list != null){
		for (Group g : list){
			String director;
			if(g.getDirector() == user.getId()){
				director = user.getUser_long_name();
			}
			else{
				int i = 0;
				boolean a = false;
				while(directors.size() > i && !a ){
					a = directors.get(i++).getId() == g.getDirector();
				}
				director = directors.get(i-1).getUser_long_name();
			}
	%>
		<p><%=g.getName() %> (<%=director %>)</p>
	<%	} 
	}
	else{%>
		<div class="alert alert-danger"><fmt:message key="p1-7.error.nolist"/></div>
	<%} %>
	
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>