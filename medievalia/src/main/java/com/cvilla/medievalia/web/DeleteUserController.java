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
public class DeleteUserController {
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value = "deleteUser.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView();
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			model = Constants.paramError(logManager,user.getId(),Constants.P_DELETE_USER);
			return model;
		}
		else{
			if(authManager.isAutorized(Constants.P_DELETE_USER, user)){
				String idUs = request.getParameter("deleteId");
				int id = (new Integer(idUs)).intValue();
				model = new ModelAndView("1-3-listausuarios");
				
				String message = userManager.deleteUser(id, user);
				logManager.log(user.getId(), Constants.P_DELETE_USER , "Borrado de usuario. Id: " 
				+ (new Integer(id)).toString() 
				+ ". Nombre: " + user.getUser_name() 
				+ ". Nombre completo: " + user.getUser_long_name() , Constants.P_OK);
	
				ArrayList<User> users = (ArrayList<User>) userManager.listar();
				ArrayList<Role> roles = (ArrayList<Role>) roleManager.getRoleList();
				model.addObject("message",message);
				model.addObject("users", users);
				model.addObject("roles", roles);
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/1-3.js");
				model.addObject("scripts",scripts);
			}
			else{
				logManager.log(user.getId(), Constants.P_DELETE_USER, "Intento de borrado de usuario con ID: " + request.getParameter("deleteId"), Constants.P_OK);
				model = Constants.noPrivileges();
			}
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));
			return model;
		}
	}
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("deleteId") == null;
	}
}
