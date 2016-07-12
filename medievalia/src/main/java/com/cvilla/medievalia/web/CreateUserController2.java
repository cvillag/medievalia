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
import com.cvilla.medievalia.service.ILogManager;
import com.cvilla.medievalia.service.ILoginManager;
import com.cvilla.medievalia.service.IRoleManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class CreateUserController2 {

	@Autowired
	private ILogManager logManager;
	
	//TODO: usar una sola clase, con dos métodos, usando http://chuwiki.chuidiang.org/index.php?title=Controladores_con_anotaciones_en_Spring_MVC_Framework
	@RequestMapping(value = "createUserAction.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = null;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			model = Constants.paramError(logManager,user.getId(),Constants.P_CREATE_USER);
			return model;
		}
		else{
			if(authManager.isAutorized(Constants.P_CREATE_USER, user)){
				model = new ModelAndView("1-3-listausuarios");
				
				String name = (String)request.getParameter("name");
				String longname = (String)request.getParameter("longname");
				String pass = (String)request.getParameter("pass");
				String role = (String)request.getParameter("role");
				String pass2 = (String)request.getParameter("pass2");
				
				String message = userManager.createUser(name, longname, pass,pass2, role);
				logManager.log(user.getId(), Constants.P_CREATE_USER, "Inicio crear usuario", Constants.P_OK);
				ArrayList<User> users = (ArrayList<User>) userManager.listar();
				ArrayList<Role> roles = (ArrayList<Role>) roleManager.getRoleList();
				model.addObject("users", users);
				model.addObject("roles", roles);
				model.addObject("message", message);
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/1-3.js");
				model.addObject("scripts",scripts);
	
			}
			else{
				model = Constants.noPrivileges(user,logManager,Constants.P_CREATE_USER,"Intento de crear usuario no permitido en segudo paso. Nombre: " + request.getParameter("user"));
			}
		model.addObject("headers",Constants.getHeaders(user.getUser_role()));
		return model;
		}
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("name") == null 
				|| request.getParameter("longname") == null
				|| request.getParameter("pass") == null
				|| request.getParameter("role") == null;
	}
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IRoleManager roleManager;

}
