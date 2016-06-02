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
public class CreateUserController2 {

	@RequestMapping(value = "createUserAction.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = null;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		
		if(authManager.isAutorized(3, user)){
			model = new ModelAndView("1-3-listausuarios");
			
			String name = (String)request.getParameter("name");
			String longname = (String)request.getParameter("longname");
			String pass = (String)request.getParameter("pass");
			String role = (String)request.getParameter("role");
			
			if(name.length()>0){
				if(longname.length() > 0){
					if( pass.length() > 0 ){
						if(role.length() > 0 ){
							userManager.createUser(name, longname, pass, role);
						}
					}
				}
			}
			
			ArrayList<User> users = (ArrayList<User>) userManager.listar();
			ArrayList<Role> roles = (ArrayList<Role>) roleManager.getRoleList();
			model.addObject("users", users);
			model.addObject("roles", roles);
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));
			List<String> scripts = new ArrayList<String>();
			scripts.add("js/1-3.js");
			model.addObject("scripts",scripts);

		}
		else{
			model = Constants.noPrivileges();
		}
		return model;
	}
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IRoleManager roleManager;

}
