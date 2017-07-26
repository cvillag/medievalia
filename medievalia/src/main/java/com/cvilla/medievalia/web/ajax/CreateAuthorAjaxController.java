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
import com.cvilla.medievalia.service.intf.IAuthorManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class CreateAuthorAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private IAuthorManager authorManager;
	
	@RequestMapping(value = "createAuthorA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		
		if(authManager.isAutorized(Constants.P_CREATE_AUTHOR, user)){
			if(errorParam(request)){
				j.put("message","noName");
				logManager.log(user.getId(), Constants.P_CREATE_AUTHOR, "Fallo en creación de autor. Parámetros incorrectos. Esquivada seguridad javascript", Constants.P_NOK);
			}
			else{
				String nombre = request.getParameter("nombre");
				String message ="";
				message =  authorManager.addAuthor(nombre,groupA,user);
				j.put("message", message);
				if(message.equals("creado")){
					logManager.log(user.getId(), Constants.P_CREATE_AUTHOR, "Creado autor " + nombre + " en grupo " + groupA.getName() + "(id:" + groupA.getIdGrupo() + ")", Constants.P_OK);
				}
				else{
					logManager.log(user.getId(), Constants.P_CREATE_AUTHOR, "Intento de creación de autor " + nombre + " en grupo " + groupA.getName() + "(id:" + groupA.getIdGrupo() + "). Fallo: " + message, Constants.P_NOK);
				}
			}
		}
		else{
			j.put("message", "noPrivileges");
			logManager.log(user.getId(), Constants.P_CREATE_AUTHOR, "Fallo en creación de autor. Sin privilegios", Constants.P_NOK);
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("nombre") == null;
	}	
}
