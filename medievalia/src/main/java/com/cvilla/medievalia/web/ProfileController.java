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

import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.IAutorizationManager;
import com.cvilla.medievalia.service.ILogManager;
import com.cvilla.medievalia.service.ILoginManager;
import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.Header;

@Controller
public class ProfileController {
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value="profile.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView model = null;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(user==null){
			model = new ModelAndView("bienvenida");
			String mensaje2 = "test.noSesion";
			model.addObject("mensaje2", mensaje2);
			logManager.log(user.getId(), Constants.P_EDIT_PROFILE, "Intento de visualización de perfil propio sin sesión", Constants.P_NOK);
		}
		else{
			if(authManager.isAutorized(Constants.P_LOGIN, user)){
				model = new ModelAndView("5-1-profile");
				model.addObject("usuario", user);
				logManager.log(user.getId(), Constants.P_EDIT_PROFILE, "Visualización de perfil propio", Constants.P_OK);
				//model.addObject("headers", getHeaders());
			}
		}
		model.addObject("headers",Constants.getHeaders(user.getUser_role()));
		return model;
	}
	
//	private List<Header> getHeaders(){
//		ArrayList<Header> lista = new ArrayList<Header>();
//		lista.add(new Header("admin","Administración","",new ArrayList<Header>()));
//		lista.get(0).getSons().add(new Header("users","Usuarios","users.do",null));
//		lista.get(0).getSons().add(new Header("groups", "Grupos", "groups.do",null));
//		lista.add(new Header("actions", "Acciones", "actions.do", null));
//		return lista;
//	}

}
