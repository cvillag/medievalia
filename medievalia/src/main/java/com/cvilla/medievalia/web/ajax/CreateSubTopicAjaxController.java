package com.cvilla.medievalia.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class CreateSubTopicAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private ITemaManager temaManager;
	
	@RequestMapping(value = "createSTopicA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		
		if(authManager.isAutorized(Constants.P_CREATE_SUB_TOPIC, user)){
			if(errorParam(request)){
				j.put("message","noParam");
			}
			else{
				String nombre = request.getParameter("nombreSTema");
				int idTema = (new Integer(request.getParameter("idTema"))).intValue();
				String message = temaManager.addSubTema(nombre, groupA, idTema);
				j.put("message", message);
				if(message.equals("creado")){
					logManager.log(user.getId(), Constants.P_CREATE_SUB_TOPIC, "Creación de subtema. Nombre: " + nombre + " en tema id = " + idTema, Constants.P_OK);
				}
				else{
					logManager.log(user.getId(), Constants.P_CREATE_SUB_TOPIC, "Intento de creación de subtema. Nombre: " + nombre + " en tema id = " + idTema + ". Fallido: mensaje = " + message, Constants.P_OK);
				}
			}
		}
		else{
			j.put("message", "noPrivileges");
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("nombreSTema") == null
				&& request.getParameter("idTema") == null;
	}
	
}
