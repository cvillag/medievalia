<%@page import="com.cvilla.medievalia.domain.Role"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%
@SuppressWarnings("unchecked")
ArrayList<User> list = (ArrayList<User>)request.getAttribute("users");
@SuppressWarnings("unchecked")
ArrayList<Role> roles = (ArrayList<Role>)request.getAttribute("roles");
String message = (String)request.getAttribute("message");
%>
<div class="container">
<legend><fmt:message key="p1-3.bienvenida"/></legend>

<div class="panel panel-default">
        <table id="myTable" class="table table-fixedheader table-bordered table-striped">
            <thead>
                <tr>
                    <th width="10%">Identificador</th>
                    <th width="20%">Login</th>
                    <th width="20%">Nombre</th>
                    <th width="10%">Rol</th>
                    <th width="20%">Acciones</th>
                </tr>
            </thead>
            <tbody style="height:110px">
            <%
				for(User u : list){ %>
					 <tr>
						<td width="10%"><%=u.getId() %></td>
						<td width="20%"><%=u.getUser_name() %></td>
						<td width="20%"><%=u.getUser_long_name() %></td>
						<td width="10%"><%=roles.get(u.getUser_role()).getNombreRol() %></td>
						<td width="20%">
							<button type="button" class="btn btn-default navbar-btn deleteuser" data-val="<%=u.getId() %>">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</button>
							<button type="button" class="btn btn-default navbar-btn modifyuser" data-val="<%=u.getId() %>">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</button>
							<button type="button" class="btn btn-default navbar-btn detailsuser" data-val="<%=u.getId() %>">
								<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>
							</button>
						</td>
                     </tr>
			<%	}
			%>
            </tbody>
        </table>
</div>
<div class="panel panel-default">
	<button type="button" class="btn btn-default navbar-btn" id="adduser" >
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
	</button>
</div>
<% if(message != null && message.length()>0 ){ %>
<div class="container">
	<% if(message.equals("p1-2.error.duplicateUser")){ %>
	<div class="alert alert-danger"><fmt:message key="p1-2.error.duplicateUser"/></div>
	<%}else if(message.equals("p1-2.error.norole")){%>
	<div class="alert alert-danger"><fmt:message key="p1-2.error.norole"/></div>
	<%}else if(message.equals("p1-2.error.nopass")){%>
	<div class="alert alert-danger"><fmt:message key="p1-2.error.nopass"/></div>
	<%}else if(message.equals("p1-2.error.nolname")){%>
	<div class="alert alert-danger"><fmt:message key="p1-2.error.nolname"/></div>
	<%}else if(message.equals("p1-2.error.noname")){%>
	<div class="alert alert-danger"><fmt:message key="p1-2.error.noname"/></div>
	<%}else if(message.equals("p1-2.createok")){%>
	<div class="alert alert-success"><fmt:message key="p1-2.createok"/></div>
	<%} %>
</div>
<%}%>

</div>