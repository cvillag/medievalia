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
public class CreateUserController2 {

	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "createUserAction.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			model = htmlManager.paramError(logManager,user.getId(),Constants.P_CREATE_USER);
			model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));
			return model;
		}
		else{
			if(authManager.isAutorized(Constants.P_CREATE_USER, user)){
				model = new ModelAndView("1-3-listausuarios");
				
				String name = request.getParameter("name");
				String longname = request.getParameter("longname");
				String pass = request.getParameter("pass");
				String role = request.getParameter("role");
				String pass2 = request.getParameter("pass2");
				
				String message = userManager.createUser(name, longname, pass,pass2, role);
				logManager.log(user.getId(), Constants.P_CREATE_USER, "Creación de usuario con resultado: " + message, Constants.P_OK);
				ArrayList<User> users = (ArrayList<User>) userManager.listar();
				ArrayList<Role> roles = (ArrayList<Role>) roleManager.getRoleList();
				model.addObject("users", users);
				model.addObject("roles", roles);
				model.addObject("message", message);
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/1-3.js");
				model.addObject("scripts",scripts);
				model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));
			}
			else{
				model = htmlManager.noPrivileges(user,logManager,Constants.P_CREATE_USER,"Intento de crear usuario no permitido en segudo paso. Nombre: " + request.getParameter("user"),request);
			}
		return model;
		}
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("name") == null 
				|| request.getParameter("longname") == null
				|| request.getParameter("pass") == null
				|| request.getParameter("role") == null || !htmlManager.isNumeric(request.getParameter("role"));
	}
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IRoleManager roleManager;

}
