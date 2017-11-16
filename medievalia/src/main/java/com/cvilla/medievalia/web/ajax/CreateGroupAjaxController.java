package com.cvilla.medievalia.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IRoleManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class CreateGroupAjaxController {
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@RequestMapping(value = "createGroupA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		if(authManager.isAutorized(Constants.P_CREATE_GROUP, user)){
			if(errorParam(request)){
				model = Constants.paramError(logManager,user.getId(),Constants.P_CREATE_GROUP);
				logManager.log(user.getId(), Constants.P_CREATE_GROUP, "Fallo en creación de grupo. Parámetros incorrectos.", Constants.P_NOK);
				return model;
			}
			else{
				String name = request.getParameter("nombreGrupo");
				String desc = request.getParameter("descripcionGrupo");
				String res = groupManager.addGroup(user.getId(), name,desc);
				if(res.equals("creado")){
					logManager.log(user.getId(), Constants.P_CREATE_GROUP, "Creación de nuevo grupo", Constants.P_OK);
				}
				else{
					logManager.log(user.getId(), Constants.P_CREATE_GROUP, "Creación de nuevo grupo fallida: " + res, Constants.P_NOK);
				}
				j.put("message",res);
			}
		}
		else{
			model = Constants.noPrivilegesJ(user,logManager,Constants.P_DELETE_USER,"Intento de borrado de usuario con ID: " + request.getParameter("deleteId"));
			logManager.log(user.getId(), Constants.P_CREATE_GROUP, "Creación de nuevo grupo no permitida", Constants.P_NOK);
			j.put("message", "noPrivileges");
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("nombreGrupo") == null &&
				request.getParameter("descripcionGrupo") == null;
	}
}
