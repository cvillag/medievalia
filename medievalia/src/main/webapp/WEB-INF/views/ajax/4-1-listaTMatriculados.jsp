	<%@page import="com.cvilla.medievalia.domain.Teachers"%>
<%@page import="com.cvilla.medievalia.domain.Students"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	@SuppressWarnings("unchecked")
	List<Teachers> lt = (List<Teachers>)request.getAttribute("listaT");  %>
	
	<div class="panel-heading">
				<fmt:message key="p4-1.tabTeachers.title"></fmt:message>
			</div>
			<div class="panel-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>
								<fmt:message key="p4-1.tabTeachers.t1"></fmt:message>
							</th>
							<th>
								<fmt:message key="p4-1.tabTeachers.t2"></fmt:message>
							</th>
						</tr>
					</thead>
					<tbody>
						<%for(Teachers s : lt){ %>
						<tr>
							<td>
								<%=s.getTeacherName() %>
							</td>
							<td>
								<button type="button" class="button" data-val="<%=s.getIdTeacher()%>"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
							</td>
						</tr>
						<%} %>
					</tbody>
				</table>
			</div>