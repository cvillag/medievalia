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
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IRoleManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class UnEnrollUserAjaxController {

	private int actionIntS = Constants.P_REMOVE_STUDENT_TO_GROUP;
	private int actionIntT = Constants.P_REMOVE_TEACHER_TO_GROUP;

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "unenrollA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		ModelAndView model = new ModelAndView("ajax/empty");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		JSONObject j = new JSONObject();
		if(groupA == null){
			j.put("error", "noGroup");
			logManager.log(user.getId(), actionIntS, "Intento de desmatriculación en grupo sin grupo activo", Constants.P_OK);
		}
		else{
			if(paramError(request)){
				j.put("error", "noParam");
				logManager.log(user.getId(), actionIntS, "Intento de desmatriculación en grupo "+ groupA.getIdGrupo()+ " sin parámetros.", Constants.P_OK);
			}
			else{
				int role;
				int idUser;
				try{
					role = (new Integer(request.getParameter("role"))).intValue();
					idUser = (new Integer(request.getParameter("userId"))).intValue();
				}
				catch(Exception e){
					j.put("error", "paramError");
					logManager.log(user.getId(), actionIntS, "Intento de desmatriculación parámetros erróneos ", Constants.P_NOK);
					model.addObject("json", j);
					return model;
				}
				if(role == Constants.ROLE_ALUMNO){
					if(authManager.isAutorized(actionIntS, user)){
						String message = groupManager.removeStudent(groupA, idUser, user);
						if(message.equals("desmatriculado")){
							j.put("error", "no");
							logManager.log(user.getId(), actionIntS, "Desmatriculación del usuario " + idUser + " en grupo" + groupA.getIdGrupo() + " como alumno", Constants.P_OK);
						}
						else{
							j.put("error", message);
							logManager.log(user.getId(), actionIntS, "Intento de matriculación del usuario " + idUser + " en grupo" + groupA.getIdGrupo() + " como alumno. Error: "+ message, Constants.P_NOK);
						}
					}
					else{
						logManager.log(user.getId(), actionIntS, "Intento de matriculación del usuario " + idUser + " en grupo" + groupA.getIdGrupo() + " como alumno. Sin privilegios ", Constants.P_NOK);
						j.put("error", "noPrivileges");
					}
				}
				else if(role == Constants.ROLE_PROFESOR){
					if(authManager.isAutorized(actionIntT, user)){
						String message = groupManager.removeTeacher(groupA, idUser, user);
						if(message.equals("desmatriculado")){
							j.put("error", "no");
							logManager.log(user.getId(), actionIntT, "Desmatriculación del usuario " + idUser + " en grupo" + groupA.getIdGrupo() + " como profesor", Constants.P_OK);
						}
						else{
							j.put("error", message);
							logManager.log(user.getId(), actionIntT, "Intento de desmatriculación del usuario " + idUser + " en grupo" + groupA.getIdGrupo() + " como profesor. Error: "+ message, Constants.P_NOK);
						}
					}
					else{
						logManager.log(user.getId(), actionIntS, "Intento de desmatriculación del usuario " + idUser + " en grupo" + groupA.getIdGrupo() + " como profesor. Sin privilegios ", Constants.P_NOK);
						j.put("error", "noPrivileges");
					}
				}
				else{
					j.put("error", "roleError");
					logManager.log(user.getId(), actionIntS, "Intento de desmatriculación del usuario " + idUser + " en grupo" + groupA.getIdGrupo() + " con rol erróneo ", Constants.P_NOK);
				}	
			}
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean paramError(HttpServletRequest request){
		return request.getParameter("role") == null  || !htmlManager.isNumeric(request.getParameter("role")) ||
				request.getParameter("userId") == null || !htmlManager.isNumeric(request.getParameter("userId"));
	}
}
