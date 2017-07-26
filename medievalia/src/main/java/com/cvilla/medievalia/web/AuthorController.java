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

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IAuthorManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class AuthorController {

	private int actionInt = Constants.P_VIEW_AUTHORS;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IAuthorManager authorManager;
	
	@RequestMapping(value = "authorController.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		
		if(authManager.isAutorized(actionInt, user)){
			logManager.log(user.getId(), actionInt, "mensaje", Constants.P_OK);
			model = new ModelAndView("2-7.listaAutores");
			model.addObject("headers",Constants.getHeaders(user.getUser_role(),request));
			List<String> scripts = new ArrayList<String>();
			scripts.add("js/2-7.js");
			model.addObject("scripts",scripts);
			if(authManager.isAutorized(Constants.P_VALIDATE_AUTHOR, user)){
				model.addObject("validar", "ok");
			}
			//model.addObject("listaAutores",authorManager.getAuthorList());
		}
		else{
			model = Constants.noPrivileges(user,logManager,actionInt,"mensaje",request);
		}			
		
		return model;
	}
	
	//true si hay error
//	private boolean errorParam(HttpServletRequest request){
//		return false;
//	}
}