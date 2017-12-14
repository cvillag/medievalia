package com.cvilla.medievalia.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;


@Controller
public class InicioController {

	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;

	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "inicio.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		User user = null;
		HttpSession sesion = request.getSession();
		user = (User) sesion.getAttribute("user");
		if(user != null || userManager.login(nombre, pass)){
			user = userManager.getCurrentUser();
			logManager.log(user.getId(), Constants.P_LOGIN, "Login desde " + request.getRemoteAddr(), Constants.P_OK);
			sesion.setAttribute("user", user);
			if(user.getUser_role() == 1)
				model = new ModelAndView("1-1-inicio");
			else if(user.getUser_role() == 2)
				model = new ModelAndView("4-0-inicio");
			else if(user.getUser_role() == 3)
				model = new ModelAndView("3-0-inicio");
			else
				model = new ModelAndView("0-bienvenida");
			model.addObject("usuario", user);
			if(authManager.isAutorized(Constants.P_LOGIN, user)){
				model.addObject("mensaje", "autorizado");
			}
			else{
				model.addObject("mensaje", "noautorizado");
			}
			List<String> scripts = new ArrayList<String>();
			scripts.add("js/common/groups-list.js");
			scripts.add("js/1-1.js");
			if(user.getUser_role() == Constants.ROLE_PROFESOR){
				scripts.add("js/4-0.js");
			}
			if(user.getUser_role() == Constants.ROLE_ALUMNO){
				scripts.add("js/3-0.js");
			}
			model.addObject("scripts",scripts);
			model.addObject("user",nombre);
			model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));
		}
		else{
			if(errorParam(request)){
				model = htmlManager.paramError(logManager,Constants.P_NOUSER,Constants.P_LOGIN);
				return model;
			}
			else{
				logManager.log(Constants.P_NOUSER, Constants.P_LOGIN, "Login fallido desde " + request.getRemoteAddr() + " usuario: " + nombre, Constants.P_OK);
				model = new ModelAndView("0-bienvenida");
				String mensaje2 = "test.noSesion";
				model.addObject("mensaje2", mensaje2);
			}
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("nombre") == null 
				|| request.getParameter("pass") == null;
	}

}
