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
public class DeleteUserAjaxController {

	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value = "deleteUserA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		if(errorParam(request)){
			model = Constants.paramError(logManager,user.getId(),Constants.P_DELETE_USER);
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));
			return model;
		}
		else{
			if(authManager.isAutorized(Constants.P_DELETE_USER, user)){
				int idUs = (new Integer(request.getParameter("deleteId"))).intValue();
				String message = userManager.deleteUser(idUs, user);
				logManager.log(user.getId(), Constants.P_DELETE_USER , "Borrado de usuario. Id: " 
				+ (new Integer(idUs)).toString() 
				+ ". Nombre: " + user.getUser_name() 
				+ ". Nombre completo: " + user.getUser_long_name() , Constants.P_OK);
				j.put("message", message);
			}
			else{
				model = Constants.noPrivilegesA(user,logManager,Constants.P_DELETE_USER,"Intento de borrado de usuario con ID: " + request.getParameter("deleteId"));
			}
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("deleteId") == null;
	}
}
