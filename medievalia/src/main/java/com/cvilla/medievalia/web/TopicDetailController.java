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
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class TopicDetailController {

	private int actionInt = Constants.P_TOPIC_DETAIL;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private ITemaManager temaManager;
	
	@RequestMapping(value = "topicDetail.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
//		Group groupA = (Group) sesion.getAttribute("grupoActual");
		if(errorParam(request)){
			model = Constants.paramError(logManager,Constants.P_LOGIN,user.getId());
			model.addObject("headers",Constants.getHeaders(user.getUser_role(),request));

		}
		else{
			if(authManager.isAutorized(actionInt, user)){
				int idT = (new Integer(request.getParameter("idTopic"))).intValue();
				Tema t = temaManager.getTema(idT);
				logManager.log(user.getId(), actionInt, "Visualización de detalle de tema", Constants.P_OK);
				model = new ModelAndView("2-6-detalleTema");
				model.addObject("tema",t);
				model.addObject("headers",Constants.getHeaders(user.getUser_role(),request));
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/2-6.js");
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
		return request.getParameter("idTopic") == null;
	}
}
