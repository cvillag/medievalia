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
import com.cvilla.medievalia.service.IAutorizationManager;
import com.cvilla.medievalia.service.ILoginManager;
import com.cvilla.medievalia.service.IRoleManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class ModifyUserController {

	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@RequestMapping(value = "modifyUser.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView();
		
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		
		if(authManager.isAutorized(Constants.P_MODIFY_USER, user)){
			String idUs = request.getParameter("modifyId");
			int id = (new Integer(idUs)).intValue();
			model = new ModelAndView("1-3.1-modificaUsuarios");
			
			User u = userManager.getUser(id);
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));
			model.addObject("targetUser", u);
			List<String> scripts = new ArrayList<String>();
			scripts.add("js/1-3.1.js");
			model.addObject("scripts",scripts);
			List<Role> roles = roleManager.getRoleList();
			model.addObject("roles",roles);
		}
		else{
			model = Constants.noPrivileges();
		}
		
		return model;
	}
}
