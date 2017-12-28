<%@page import="com.cvilla.medievalia.domain.TipoAtributoComplejo"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
User user = (User) session.getAttribute("user");
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<TipoAtributoComplejo> objetos = (List<TipoAtributoComplejo>) request.getAttribute("tipos");
int i = 0;
if (objetos != null && objetos.size() > 0){
	for(TipoAtributoComplejo c : objetos){

			if(i++ == 0){
				%>
				<option value="0" selected></option>
				<%
			}
			%>
			<option value="<%=c.getIdTipoHijo()%>"><%=c.getNombreAtributo() %></option>
			<%
		}
	} %>