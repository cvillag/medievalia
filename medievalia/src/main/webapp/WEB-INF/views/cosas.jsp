<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ page import="com.cvilla.medievalia.domain.Cosa" %>
<%@ page import="java.util.List" %>
<%
	List<Cosa> lc = (List<Cosa>)request.getAttribute("lcosas");%>
	<c:forEach items="{model.lcosas}" var="cosa">
		<p><c:out value="cosa.nombre"></c:out></p>
	</c:forEach>
	<hr>
<table>
	<tr>
		<th>Nombre</th>
		<th>Id</th>
	</tr>
	<%for(int i = 0; i < lc.size(); i++){ %>
	<tr>
		<td><%=lc.get(i).getNombre() %></td>
		<td><%= lc.get(i).getId() %></td>
	</tr>
	<%} %>
</table>
<p>De la BD: <%= request.getAttribute("nom1") %></p>
<input type="button" onclick="location.href='hello.do'" value="volver"/>

</body>
</html>