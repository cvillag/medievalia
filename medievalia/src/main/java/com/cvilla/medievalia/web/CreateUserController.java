package com.cvilla.medievalia.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.IAutorizationManager;
import com.cvilla.medievalia.service.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class CreateUserController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@RequestMapping(value = "createUser.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = null;
		
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		
		if(authManager.isAutorized(3, user)){
			model = new ModelAndView("1-2-creaUsuarios");
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));
		}
		else{
			model = Constants.noPrivileges();
		}
		return model;
	}
}
