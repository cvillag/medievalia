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
import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class GetEnrolledStudensListAjaxController {
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@RequestMapping(value = "getStudentsEnrolled.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		if(groupA == null){
			model = new ModelAndView("5-2-errorAjax");
			model.addObject("mensaje2", "p5-2.errorGroup");
		}
		else{
			if(authManager.isAutorized(Constants.P_PARTICIPANT_LIST, user)){
				model = new ModelAndView("ajax/4-1-listaSMatriculados");
				List<Students> listaS = groupManager.getStudentParticipantList(groupA);
				model.addObject("listaS", listaS);
			}
			else{
				model = Constants.noPrivilegesA(user, logManager, Constants.P_PARTICIPANT_LIST, "Sin permisos para ver la lista de usuarios matriculados en grupo (" + groupA.getName() + ")"  + groupA.getIdGrupo());
			}
		}
		return model;
	}

}
