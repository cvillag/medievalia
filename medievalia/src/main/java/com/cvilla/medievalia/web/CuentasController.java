package com.cvilla.medievalia.web;

import java.util.ArrayList;

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
public class CuentasController {

	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@RequestMapping(value = "users.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = null;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		
		if(authManager.isAutorized(3, user)){
			model = new ModelAndView("1-3-listausuarios");
			
			ArrayList<User> users = (ArrayList<User>) userManager.listar();
			
			model.addObject("users", users);
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));

		}
		else{
			//FIXME Mensaje temporal. La página ha de ser otra
			model = new ModelAndView("0-bienvenida");
			String mensaje2 = "test.noSesion";
			model.addObject("mensaje2", mensaje2);
		}
		return model;
	}
	
}
