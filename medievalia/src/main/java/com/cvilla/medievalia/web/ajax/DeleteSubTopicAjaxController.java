package com.cvilla.medievalia.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class DeleteSubTopicAjaxController {
	
	private int actionIntS = Constants.P_DELETE_STOPIC;

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private ITemaManager temaManager;
	
	@RequestMapping(value = "subtopicDeletion.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		
		if(authManager.isAutorized(actionIntS, user)){
			if(errorParam(request)){
				j.put("message","noId");
				logManager.log(user.getId(), actionIntS, "Fallo en creación de tema. Parámetros incorrectos. Esquivada seguridad javascript", Constants.P_NOK);
			}
			else{
				int idTema = (new Integer(request.getParameter("idTema"))).intValue();
				int idSubtema = (new Integer(request.getParameter("idSubtema"))).intValue();
				String message = temaManager.deleteSubTema(idTema, idSubtema);
				j.put("message", message);
				j.put("idSubtema", idSubtema);
				logManager.log(user.getId(), actionIntS, "Borrado subtema con id" + idSubtema + ", en grupo " + groupA.getName() + "(id:" + groupA.getIdGrupo() + ")", Constants.P_OK);
			}
		}
		else{
			j.put("message", "noPrivileges");
			logManager.log(user.getId(), actionIntS, "Fallo en borrado de tema. Sin privilegios", Constants.P_NOK);
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idTema") == null &&
				request.getParameter("idSubtema") == null;
	}	
}
