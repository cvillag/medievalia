<%@page import="java.util.ArrayList"%>
<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%
ArrayList<User> list = (ArrayList<User>)request.getAttribute("users");
%>
<div class="container">
	<!-- div class="jumbotron">
		<h3></h3>
	</div>
	<form class="form-horizontal">
	<fieldset>
 -->
<!-- Form Name -->
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
						<td width="10%"><%=u.getUser_role() %></td>
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

</div>