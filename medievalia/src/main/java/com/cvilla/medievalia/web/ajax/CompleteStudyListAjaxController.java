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
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IStudyManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class CompleteStudyListAjaxController {
	
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
	
	@RequestMapping(value = "completeStudyList.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/2-3-listaCompleta");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		JSONObject j = new JSONObject();
		
		if(authManager.isAutorized(Constants.P_VIEW_STUDIES, user)){
			if(errorParam(request)){
				j.put("message","noType");
				logManager.log(user.getId(), Constants.P_VIEW_STUDIES, "Fallo en listado de estudios. Parámetros incorrectos. Esquivada seguridad javascript", Constants.P_NOK);
			}
			else{
				List<Study> listag = studyManager.getStudyList();
				model.addObject("listaEstudios", listag);
				model.addObject("type",request.getParameter("type"));
				logManager.log(user.getId(), Constants.P_VIEW_STUDIES, "Visualización lista de estudios", Constants.P_OK);
				if(authManager.isAutorized(Constants.P_DELETE_STUDY, user)){
					model.addObject("permisoborrado","ok");
				}
				if(authManager.isAutorized(Constants.P_RENAME_STUDY, user)){
					model.addObject("permisoRenombrado", "ok");
				}
			}
		}
		else{
			model = Constants.noPrivilegesA(user,logManager,Constants.P_VIEW_STUDIES,"Visualización de estudios no permitida (grupo: " + groupA.getName() + ")");
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("type") == null;
	}
}
