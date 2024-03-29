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
public class CuentasController {

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
	
	@RequestMapping(value = "users.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = null;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		
		if(authManager.isAutorized(Constants.P_USER_LIST, user)){
			model = new ModelAndView("1-3-listausuarios");
			logManager.log(user.getId(), Constants.P_USER_LIST, "Visualización de listado de usuarios", Constants.P_OK);
			
			ArrayList<User> users = (ArrayList<User>) userManager.listar();
			ArrayList<Role> roles = (ArrayList<Role>) roleManager.getRoleList();
			model.addObject("users", users);
			model.addObject("roles", roles);
			List<String> scripts = new ArrayList<String>();
			scripts.add("js/1-3.js");
			model.addObject("scripts",scripts);
			model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));
		}
		else{
			model = htmlManager.noPrivileges(user,logManager,Constants.P_USER_LIST,"Intento de listado de usuarios no permitido",request);
		}
		return model;
	}	
}
