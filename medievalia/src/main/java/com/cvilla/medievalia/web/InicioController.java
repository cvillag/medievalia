package com.cvilla.medievalia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.IAutorizationManager;
import com.cvilla.medievalia.service.ILoginManager;
import com.cvilla.medievalia.utils.Constants;


@Controller
public class InicioController {

	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;

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
			sesion.setAttribute("user", user);
			if(user.getUser_role() == 1)
				model = new ModelAndView("1-1-inicio");
			else if(user.getUser_role() == 2)
				model = new ModelAndView("3-0-inicio");
			else if(user.getUser_role() == 3)
				model = new ModelAndView("4-0-inicio");
			else
				model = new ModelAndView("0-bienvenida");
			model.addObject("headers", Constants.getHeaders(user.getUser_role()));
			model.addObject("usuario", user);
			if(authManager.isAutorized(1, user)){
				model.addObject("mensaje", "autorizado");
			}
			else{
				model.addObject("mensaje", "noautorizado");
			}
			model.addObject("user",nombre);
		}
		else{
			// FIXME: Cambiar el mensaje de error de contraseña del de sin sesión
			model = new ModelAndView("0-bienvenida");
			String mensaje2 = "test.noSesion";
			model.addObject("mensaje2", mensaje2);
		}
		return model;
	}

}
