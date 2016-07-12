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
import com.cvilla.medievalia.service.IAutorizationManager;
import com.cvilla.medievalia.service.IGroupManager;
import com.cvilla.medievalia.service.ILogManager;
import com.cvilla.medievalia.service.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class GroupController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@RequestMapping(value = "groups.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(authManager.isAutorized(Constants.P_GROUP_LIST, user) || authManager.isAutorized(Constants.P_GROUP_OWN, user)){
			//logManager.log(idUser, idAction, desc, succ);
			List<Group> list = groupManager.getList(user);
			List<User> directors = new ArrayList<User>();
			for(Group g : list){
				if(g.getDirector() != user.getId()){
					User u = loginManager.getUser(g.getDirector());
					directors.add(u);
				}
			}
			model = new ModelAndView("1-7-listaGrupos");
			model.addObject("listaGrupos", list);
			model.addObject("user", user);
			model.addObject("directors", directors);
		}
		else{
			//logManager.log(idUser, idAction, desc, succ);
			model = Constants.noPrivileges();
		}			
		model.addObject("headers",Constants.getHeaders(user.getUser_role()));
		return model;
	}
}
