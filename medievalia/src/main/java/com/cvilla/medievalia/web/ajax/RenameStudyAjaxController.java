package com.cvilla.medievalia.web.ajax;

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
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class RenameStudyAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IStudyManager StudyManager;
	
	@RequestMapping(value = "renameStudyA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		String message;
		int action;
		if(errorParam(request)){
			message = "noParam";
			action = Constants.P_RENAME_STUDY;
		}
		else{
			String nombre = request.getParameter("newNombre");
			int idestudio = (new Integer(request.getParameter("idEstudio"))).intValue();			
			if(authManager.isAutorized(Constants.P_RENAME_STUDY, user)){
				message = StudyManager.renameStudy(nombre,idestudio,user,groupA);
				action = Constants.P_RENAME_STUDY;
			}
			else{
				if(authManager.isAutorized(Constants.P_RENAME_STUDY_OWN, user)){
					message = StudyManager.renameStudyOwn(nombre,idestudio,user,groupA);
					action = Constants.P_RENAME_STUDY_OWN;
					j.put("message", message);
				}
				else{
					message = "noPrivileges";
					action = Constants.P_RENAME_STUDY;
				}
			}
			if(message.equals("cambiado")){
				logManager.log(user.getId(), action, "Renombrar estudio. Nuevo nombre: " + nombre + " en estudio id = " + idestudio, Constants.P_OK);
			}
			else{
				logManager.log(user.getId(), action, "Renombrar estudio. Nuevo nombre: " + nombre + " en estudio id = " + idestudio + ". Fallido: mensaje = " + message, Constants.P_NOK);
				Study old = StudyManager.getStudy(idestudio);
				j.put("oldname", old.getNombre());
				j.put("id", old.getIdStudy());
			}
			j.put("message", message);
			model.addObject("json", j);
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idEstudio") == null &&
				request.getParameter("newNombre") == null;
	}
}
