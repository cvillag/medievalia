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
import com.cvilla.medievalia.service.CosaManager;
import com.cvilla.medievalia.service.ILoginManager;
import com.cvilla.medievalia.utils.Header;

@Controller
public class InicioController {

	@Autowired
	private CosaManager cosaManager;
	
	@Autowired
	private ILoginManager userManager;

	@RequestMapping(value = "inicio.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String mensaje;
		User user = null;
		HttpSession sesion = request.getSession();
		if(userManager.login(nombre, pass)){
			user = userManager.getCurrentUser();
			mensaje = "test.sesion";
			sesion.setAttribute("login", nombre);
			model = new ModelAndView("inicio");
			model.addObject("headers", getHeaders());
			model.addObject("usuario", user);
			model.addObject("mensaje", mensaje);
			model.addObject("user",nombre);
		}
		else{
			model = new ModelAndView("bienvenida");
			String mensaje2 = "test.noSesion";
			model.addObject("mensaje2", mensaje2);
		}
		return model;
	}
	
	private List<Header> getHeaders(){
		ArrayList<Header> lista = new ArrayList<Header>();
		lista.add(new Header("admin","Administración","",new ArrayList<Header>()));
		lista.get(0).getSons().add(new Header("users","Usuarios","users.do",null));
		lista.get(0).getSons().add(new Header("groups", "Grupos", "groups.do",null));
		lista.add(new Header("actions", "Acciones", "actions.do", null));
		return lista;
	}

}
