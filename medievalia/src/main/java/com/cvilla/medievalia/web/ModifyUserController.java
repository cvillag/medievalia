package com.cvilla.medievalia.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Role;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IRoleManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class ModifyUserController {

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
	
	@RequestMapping(value = "modifyUser.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView();
		
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		
		if(errorParam(request)){
			model = htmlManager.paramError(logManager,user.getId(),Constants.P_MODIFY_USER);
			model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));
			return model;
		}
		else{
		
			if(authManager.isAutorized(Constants.P_MODIFY_USER, user)){
				String idUs = request.getParameter("modifyId");
				sesion.setAttribute("modifyUserId", idUs);
				int id = (new Integer(idUs)).intValue();
				model = new ModelAndView("1-3.1-modificaUsuarios");
				
				User u = userManager.getUser(id);
				logManager.log(user.getId(), Constants.P_MODIFY_USER, "Inicio de modificación de usuario. ID: "
						+ (new Integer(u.getId()).toString())
						+ ". Nombre: "
						+ u.getUser_name(), Constants.P_OK);
				model.addObject("targetUser", u);
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/1-3.1.js");
				model.addObject("scripts",scripts);
				List<Role> roles = roleManager.getRoleList();
				model.addObject("roles",roles);
				model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));
			}
			else{
				model = htmlManager.noPrivileges(user,logManager,Constants.P_MODIFY_USER,"Intento de modificación de usuario. ID: " + request.getParameter("modifyId"),request);
			}
			return model;
		}
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("modifyId") == null || !htmlManager.isNumeric(request.getParameter("modifyId"));
	}
}
