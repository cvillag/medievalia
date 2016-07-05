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

<!-- 
<div class="table-responsive">
<table class="table table-striped table-hover table-condensed">
    <thead>
        <tr>
            <th width="25%">First Name</th>
            <th width="13%">Last Name</th>
            <th width="25%" class="text-center">Address</th>
            <th width="25%" class="text-center">City</th>
            <th width="4%" class="text-center">State</th>
            <th width="8%" class="text-center">Zip</th>
        </tr>
    </thead>
</table>
<div class="bodycontainer scrollable">
<table class="table table-hover table-striped table-condensed table-scrollable">
    <tbody>
    < add rows here, specifying same widths as in header, at least on one row >
    </tbody>
</table>
</div>
<table class="table table-hover table-striped table-condensed">
    <tfoot>
    < add your footer here... >
    </tfoot>
</table>
</div>
 -->

<div class="panel panel-default">
	<div class="table-responsive">
        <table id="myTable" class="table table-bordered table-striped table-hover table-condensed">
            <thead>
                <tr>
                    <th width="5%"><fmt:message key="p1-3.tabla1"/></th>
                    <th width="10%"><fmt:message key="p1-3.tabla2"/></th>
                    <th width="20%"><fmt:message key="p1-3.tabla3"/></th>
                    <th width="5%"><fmt:message key="p1-3.tabla4"/></th>
                    <th width="20%"><fmt:message key="p1-3.tabla5"/></th>
                </tr>
            </thead>
		</table>
	</div>
	<div style="height:220px; overflow-y:scroll;">
		<table class="table table-hover table-striped table-condensed table-scrollable">
            <tbody>
            <%
				for(User u : list){ %>
					 <tr>
						<td width="5%"><%=u.getId() %></td>
						<td width="10%"><%=u.getUser_name() %></td>
						<td width="20%"><%=u.getUser_long_name() %></td>
						<%if(u.getUser_role() == 1){ %>
						<td width="5%"><span class="glyphicon glyphicon-cog" title="<fmt:message key="general.administrador"/>"></span></td>
						<%} 
						else if(u.getUser_role() == 2){%>
						<td width="5%"><span class="glyphicon glyphicon-briefcase" title="<fmt:message key="general.profesor"/>"></span></td>
						<%} 
						else if(u.getUser_role() == 3){%>
						<td width="5%"><span class="glyphicon glyphicon-user" title="<fmt:message key="general.alumno"/>"></span></td>
						<%}
						else if(u.getUser_role() == 4){%>
						<td width="5%"><span class="glyphicon glyphicon-ban-circle" title="<fmt:message key="general.inactivo"/>"></span></td>
						<%} %>
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
	<%}else if(message.equals("borrado")){%>
	<div class="alert alert-success"><fmt:message key="p1-3.deleteok"/></div>
	<%}else if(message.equals("noBorrado")){%>
	<div class="alert alert-danger"><fmt:message key="p1-3.error.noDelete"/></div>
	<%}else if(message.equals("noAuto")){%>
	<div class="alert alert-danger"><fmt:message key="p1-3.error.noSelf"/></div>
	<%} %>
</div>
<%}%>
<form id="deletion" action="deleteUser.do" method="post">
	<input type="hidden" name="deleteId" id="deleteId">
</form>
</div>