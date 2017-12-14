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
import com.cvilla.medievalia.domain.Role;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.IRoleManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class PossibleUserListToGroupAjaxController {
	
	private int actionInt = Constants.P_LIST_USERS_TO_ADD_GROUP;

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "possibleUserListA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		ModelAndView model;;
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		if(groupA == null){
			model = new ModelAndView("5-2-errorAjax");
			model.addObject("mensaje2", "p5-2.errorGroup");
		}
		else{
			if(authManager.isAutorized(actionInt, user)){
				model = new ModelAndView("ajax/2-1-listaUsuariosMatricula");
				List<User> lu = groupManager.getUsersToGroup(groupA, request.getParameter("filterName"));
				List<Role> lr = roleManager.getRoleList();
				model.addObject("list", lu);
				model.addObject("roles",lr);
				if(lu == null){
					logManager.log(user.getId(), actionInt, "Error en listado de usuarios para matriculación. Grupo " + groupA.getName() + "( " + groupA.getIdGrupo() + ")", Constants.P_NOK);
				}
				else{
					logManager.log(user.getId(), actionInt, "Listado de usuarios para matriculación. Grupo " + groupA.getName() + "( " + groupA.getIdGrupo() + ")", Constants.P_OK);
				}
			}
			else{
				model = htmlManager.noPrivilegesA(user,logManager,actionInt,"Intento de listar usuarios para matriculación, sin  privilegios");
			}
		}
		return model;
	}
}
