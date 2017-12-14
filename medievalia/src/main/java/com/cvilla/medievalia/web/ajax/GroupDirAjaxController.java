package com.cvilla.medievalia.web.ajax;

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
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class GroupDirAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "belongGroupA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			if(authManager.isAutorized(Constants.P_DETAIL_DIRECTOR_GROUPS_OWN, user) ){
				List<Group> lista = groupManager.getListByDirector(user,user);
				model = new ModelAndView("ajax/a-1-3.2-grupos");
				model.addObject("message", "ok");
				model.addObject("gruposDir",lista);
				model.addObject("play",true);
				logManager.log(user.getId(), Constants.P_DETAIL_DIRECTOR_GROUPS_OWN, "Listado de grupos propios", Constants.P_OK);
			}
			else{
				model = htmlManager.noPrivilegesA(user,logManager,Constants.P_DETAIL_DIRECTOR_GROUPS_OWN,"Sin permiso de ver listado de grupos propios");
			}			
		}
		else{
			if(authManager.isAutorized(Constants.P_DETAIL_DIRECTOR_GROUPS_OTHER, user) || authManager.isAutorized(Constants.P_DETAIL_DIRECTOR_GROUPS_OWN, user) ){
				int idDir = (new Integer(request.getParameter("idDir"))).intValue();
				User dir = loginManager.getUser(idDir);
				List<Group> lista = groupManager.getListByDirector(user,dir);
				model = new ModelAndView("ajax/a-1-3.2-grupos");
				model.addObject("director",dir);
				model.addObject("message", "ok");
				model.addObject("gruposDir",lista);
				model.addObject("play",false);
				logManager.log(user.getId(), Constants.P_DETAIL_DIRECTOR_GROUPS_OTHER, "Listado de grupos de otros usuarios o propios", Constants.P_OK);
			}
			else{
				model = htmlManager.noPrivilegesA(user,logManager,Constants.P_DETAIL_DIRECTOR_GROUPS_OTHER,"Sin permiso de ver listado de grupos de otros usuarios");
			}			
		}
		return model;
	}
	boolean errorParam(HttpServletRequest request){
		return request.getParameter("idDir") == null || !htmlManager.isNumeric(request.getParameter("idDir"));
	}
}
