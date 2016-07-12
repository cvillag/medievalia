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
import com.cvilla.medievalia.service.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class InicioController2 {

	@Autowired
	private IAutorizationManager authManager;

	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value = "main.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(authManager.isAutorized(Constants.P_LOGIN, user)){
			logManager.log(user.getId(), Constants.P_LOGIN, "Visualización de página principal"	, Constants.P_OK);
			if(user.getUser_role() == 1)
				model = new ModelAndView("1-1-inicio");
			else if(user.getUser_role() == 2)
				model = new ModelAndView("3-0-inicio");
			else if(user.getUser_role() == 3)
				model = new ModelAndView("4-0-inicio");
			else
				model = new ModelAndView("0-bienvenida");
			model.addObject("usuario", user);
			if(authManager.isAutorized(Constants.P_LOGIN, user)){
				model.addObject("mensaje", "autorizado");
			}
			else{
				model.addObject("mensaje", "noautorizado");
			}
			model.addObject("user",user.getUser_name());
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));
		}
		else{
			model = Constants.noPrivileges(user,logManager,Constants.P_LOGIN,"Visualización de página principal no permitida");
		}
		if(user != null){
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));
		}
		return model;
	}
}
