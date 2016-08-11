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
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class GeneralLogController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value = "generalLog.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(authManager.isAutorized(Constants.P_GENERAL_LOG, user)){
			logManager.log(user.getId(), Constants.P_GENERAL_LOG, "Acceso a página de log general", Constants.P_OK);
			model = new ModelAndView("1-8-logGeneral");
			model.addObject("headers",Constants.getHeaders(user.getUser_role(),request));
			int numLog = logManager.getNumPag(20);
			model.addObject("numPag", numLog);
			model.addObject("tamPag",10);
			List<String> scripts = new ArrayList<String>();
			scripts.add("js/1-8.js");
			model.addObject("scripts",scripts);
		}
		else{
			model = Constants.noPrivileges(user,logManager,Constants.P_GENERAL_LOG,"mensaje",request);
		}			
		return model;
	}
}