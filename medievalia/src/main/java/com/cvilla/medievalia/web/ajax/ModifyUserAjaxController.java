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
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IRoleManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class ModifyUserAjaxController {

	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "modifyUserA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		if(authManager.isAutorized(Constants.P_MODIFY_USER_OWN, user)){
			if(errorParam(request)){
				model = htmlManager.paramError(logManager,user.getId(),Constants.P_MODIFY_USER_OWN);
				return model;
			}
			else{
				String name = request.getParameter("nombre");
				String lname = request.getParameter("nombreC");
				String message = userManager.modifyUser(name, lname, user);
				j.put("message", message);
				user.setUser_long_name(lname);
				user.setUser_name(name);
				if(message.equals("p1.3.modifyok")){
					sesion.setAttribute("user", user);
					logManager.log(user.getId(), Constants.P_MODIFY_USER_OWN, "Modificación de datos personales", Constants.P_OK);
				}
				else{
					logManager.log(user.getId(), Constants.P_MODIFY_USER_OWN, "Modificación de datos personales fallida. Mensaje:" + message, Constants.P_OK);
				}
			}
		}
		else{
			return htmlManager.noPrivilegesJ(user, logManager, Constants.P_MODIFY_USER_OWN, "Intento de modificación de datos personales sin permisos");
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("nombre") == null || request.getParameter("nombre").length() < 1 ||
				request.getParameter("nombreC") == null  || request.getParameter("nombreC").length() < 1;
	}
}
