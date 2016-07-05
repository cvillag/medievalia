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
public class DeleteUserController {
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@RequestMapping(value = "deleteUser.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView();
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		
		if(authManager.isAutorized(Constants.P_DELETE_USER, user)){
			String idUs = request.getParameter("deleteId");
			int id = (new Integer(idUs)).intValue();
			model = new ModelAndView("1-3-listausuarios");
			
			String message = userManager.deleteUser(id, user);
			
			ArrayList<User> users = (ArrayList<User>) userManager.listar();
			ArrayList<Role> roles = (ArrayList<Role>) roleManager.getRoleList();
			model.addObject("message",message);
			model.addObject("users", users);
			model.addObject("roles", roles);
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));
			List<String> scripts = new ArrayList<String>();
			scripts.add("js/1-3.js");
			model.addObject("scripts",scripts);
		}
		return model;
	}
}