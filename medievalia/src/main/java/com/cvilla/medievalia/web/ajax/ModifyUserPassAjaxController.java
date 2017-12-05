package com.cvilla.medievalia.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IRoleManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class ModifyUserPassAjaxController {

	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value = "modifyUserPassA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		if(authManager.isAutorized(Constants.P_MODIFY_USER_PASS_OWN, user)){
			if(errorParam(request)){
				model = Constants.paramError(logManager,user.getId(),Constants.P_MODIFY_USER_PASS_OWN);
				return model;
			}
			else{
				String pass1 = request.getParameter("pass1");
				String pass2 = request.getParameter("pass2");
				String pass3 = request.getParameter("pass3");
				String message = userManager.modifyUserPass(user.getUser_name(), user.getUser_long_name(), pass1, pass2, pass3, user.getUser_role(), user);
				j.put("message", message);
				if(message.equals("p1.3.modifyok")){
					logManager.log(user.getId(), Constants.P_MODIFY_USER_PASS_OWN, "Modificación de contraseña propia", Constants.P_OK);
				}
				else{
					logManager.log(user.getId(), Constants.P_MODIFY_USER_PASS_OWN, "Modificación de contraseña propia fallida. Mensaje: " + message, Constants.P_OK);
				}
			}
		}
		else{
			j.put("message", "noPrivileges");
			logManager.log(user.getId(), Constants.P_MODIFY_USER_PASS_OWN, "Intento de modificación contraseña sin permisos", Constants.P_NOK);
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("pass1") == null  || request.getParameter("pass1").length() < 1 ||
				request.getParameter("pass2") == null  || request.getParameter("pass2").length() < 1 ||
				request.getParameter("pass3") == null || request.getParameter("pass3").length() < 1;
	}
}
