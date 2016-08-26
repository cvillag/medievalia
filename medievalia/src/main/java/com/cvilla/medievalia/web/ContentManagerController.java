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
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class ContentManagerController {

	private int actionInt = Constants.P_SELECT_ACTIVE_GROUP;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ITemaManager temaManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@RequestMapping(value = "contentManager.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			model = Constants.paramError(logManager,Constants.P_LOGIN,user.getId());
			model.addObject("headers",Constants.getHeaders(user.getUser_role(),request));

		}
		else{
			if(authManager.isAutorized(actionInt, user)){
				int idGroup = (new Integer(request.getParameter("idGroup"))).intValue();
				String message;
				Group g = groupManager.getGroupById(idGroup);
				User director = userManager.getUser(g.getDirector());
				model = new ModelAndView("2-1-contentManager");
				if(groupManager.setActiveGroup(user, g, logManager)){
					sesion.setAttribute("grupoActual", g);
					message = "p3.1.msg.ok";
					List<Tema> listaTemas = temaManager.getTemaGrupoByGroup(g);
					model.addObject("listaTemas", listaTemas);
					model.addObject("director",director);
				}
				else{
					message = "p3.1.msg.grpNoExiste";
				}
				model.addObject("message", message);
				model.addObject("headers",Constants.getHeaders(user.getUser_role(),request));
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/2-1.js");
				model.addObject("scripts",scripts);
			}
			else{
				model = Constants.noPrivileges(user,logManager,actionInt,"mensaje",request);
			}			
		}
		return model;
	}
	
	//true si hay error
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idGroup") == null;
	}
}