<%@page import="com.cvilla.medievalia.domain.Charge"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
@SuppressWarnings("unchecked")
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<Charge> cargos = (List<Charge>) request.getAttribute("listaCargos");
if (cargos != null && cargos.size() > 0){
	for(Charge c : cargos){
		if(type.equals("table")){%>
			<tr class="trcargo" id="cargo<%=c.getIdCharge()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<%=c.getNombre() %>
				</td>
				<td>
					<%=c.getValidado() %>
				</td>
			</tr>
<%		
		}
		else if(type.equals("select")){
			%>
			<option value="<%=c.getIdCharge()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>