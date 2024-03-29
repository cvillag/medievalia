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
import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class GroupParticipants {

	private int actionInt = Constants.P_PARTICIPANT_LIST;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "groupParticipants.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		if(authManager.isAutorized(actionInt, user)){
			if(groupA.getDirector() == user.getId() || groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
				logManager.log(user.getId(), actionInt, "Ventana de lista de participantes", Constants.P_OK);
				model = new ModelAndView("4-1.listaParticipantes");
				model.addObject("group",groupA);
				model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/4-1.js");
				model.addObject("scripts",scripts);
			}
			else{
				model = htmlManager.noPrivileges(user,logManager,actionInt,"Intento de ver lista de participantes de un grupo ajeno",request);
			}
		}
		else{
			model = htmlManager.noPrivileges(user,logManager,actionInt,"mensaje",request);
		}			
		return model;
	}
}
