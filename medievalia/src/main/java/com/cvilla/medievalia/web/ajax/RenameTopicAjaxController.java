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
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class RenameTopicAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private ITemaManager temaManager;
	
	@RequestMapping(value = "renameUserA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		if(errorParam(request)){
			model = Constants.paramError(logManager,user.getId(),Constants.P_DELETE_USER);
		}
		else{
			String nombre = request.getParameter("nombreTema");
			int idTema = (new Integer(request.getParameter("topicId"))).intValue();
			if(authManager.isAutorized(Constants.P_RENAME_TOPIC, user)){
				String message = temaManager.renameTema(nombre,idTema,user,groupA);
				j.put("message", message);
				model.addObject("json", j);
			}
			else{
				model = Constants.noPrivilegesA(user,logManager,Constants.P_RENAME_TOPIC,"Intento cambio de nombre de tema con ID: " + idTema);
			}
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("topicId") == null &&
				request.getParameter("nombreTema") == null;
	}
}
