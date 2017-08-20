<%@page import="com.cvilla.medievalia.domain.Students"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
	@SuppressWarnings("unchecked")
	List<Students> ls = (List<Students>)request.getAttribute("listaS");  %>
	
	<div class="panel-heading">
				<fmt:message key="p4-1.tabStudents.title"></fmt:message>
			</div>
			<div class="panel-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>
								<fmt:message key="p4-1.tabStudents.t1"></fmt:message>
							</th>
							<th>
								<fmt:message key="p4-1.tabStudents.t2"></fmt:message>
							</th>
						</tr>
					</thead>
					<tbody>
						<%for(Students s : ls){ %>
						<tr>
							<td>
								<%=s.getStudentName() %>
							</td>
							<td>
								<button type="button" class="btn btn-sm unenrollS" data-val="<%=s.getIdStudent()%>"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
							</td>
						</tr>
						<%} %>
					</tbody>
				</table>
			</div>