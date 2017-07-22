package com.cvilla.medievalia.web.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Study;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IStudyManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class StudentStudyListAjaxController {
	
	private int actionIntS = Constants.P_VIEW_OWN_STUDY;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private IStudyManager studyManager;
	
	@RequestMapping(value = "studentStudyListA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/2-3-listaAlumno");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		JSONObject j = new JSONObject();
		
		if(authManager.isAutorized(actionIntS, user)){
			if(errorParam(request)){
				j.put("message","noType");
				logManager.log(user.getId(), actionIntS, "Fallo en listado de estudios. Parámetros incorrectos. Esquivada seguridad javascript", Constants.P_NOK);
			}
			else{
				List<Study> listag = studyManager.getStudentStudyList(user);
				model.addObject("listaEstudios", listag);
				model.addObject("type",request.getParameter("type"));
				logManager.log(user.getId(), actionIntS, "Visualización lista de estudios por validar", Constants.P_OK);
				if(authManager.isAutorized(Constants.P_DELETE_OWN_STUDIES, user)){
					model.addObject("permisoborrado","ok");
				}
			}
		}
		else{
			model = Constants.noPrivilegesA(user,logManager,actionIntS,"Visualización de lista de estudios por validar no permitida (grupo: " + groupA.getName() + ")");
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("type") == null;
	}
}
