package com.cvilla.medievalia.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class ContentManagerController {

	private int actionInt = Constants.P_SELECT_ACTIVE_GROUP;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IObjectManager objectManager;
	
	@RequestMapping(value = "contentManager.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group activeGroup  = (Group) sesion.getAttribute("grupoActual");
		sesion.removeAttribute("tipoObjeto");
		
		if(authManager.isAutorized(actionInt, user)){
			if(errorParam(request) && activeGroup==null){
				return Constants.paramError(logManager, actionInt, user.getId());
			}
			else{
				String message;
				model = new ModelAndView("2-1-contentManager");
				User director;
				if(activeGroup != null && request.getParameter("change") == null){
					director = userManager.getUser(activeGroup.getDirector());
					message = "p3.1.msg.ok";
					logManager.log(user.getId(), actionInt, "Visualización de la página de gestión de contenido con grupo activo previamente", Constants.P_OK);
				}
				else{
					int idGroup = (new Integer(request.getParameter("idGroup"))).intValue();
					Group g = groupManager.getGroupById(idGroup);
					director = userManager.getUser(g.getDirector());
					
					if(groupManager.setActiveGroup(user, g, logManager)){
						sesion.setAttribute("grupoActual", g);
						message = "p3.1.msg.ok";
						activeGroup = new Group(g.getIdGrupo(),g.getDirector(),g.getName(),g.getDescription());
					}
					else{
						message = "p3.1.msg.grpNoExiste";
					}
				}
				
				
				//Resumen de participantes
				if(activeGroup != null){
					int numSt = groupManager.getStudentParticipantList(activeGroup).size();
					int numTe = groupManager.getTeacherParticipantList(activeGroup).size();
					model.addObject("numStudents",numSt);
					model.addObject("numTeachers",numTe);
				}
				else{
					model.addObject("numStudents",0);
					model.addObject("numTeachers",0);
				}
				
				if(authManager.isAutorized(Constants.P_OBJECT_TYPE_LIST, user)){
					List<TipoObjetoDOM> lo = objectManager.getTiposObjetosDOM();
					if(lo != null){
						logManager.log(user.getId(), Constants.P_OBJECT_TYPE_LIST, "Visualización de gestor de contenido", Constants.P_OK);
					}
					else{
						logManager.log(user.getId(), Constants.P_OBJECT_TYPE_LIST, "Visualización de gestor de contenido. Lista vacía.", Constants.P_NOK);
					}
					model.addObject("listaObjetos", lo);
				}
				
				if(authManager.isAutorized(Constants.P_VIEW_AUTHORS_STATISTICS_PER_TYPE, user)){
					model.addObject("profe", "ok");
					model.addObject("objectsToValidate",objectManager.getStatisticsToValidate(activeGroup));
					model.addObject("usersToValidate", objectManager.getStatisticsUsersToValidate(activeGroup));
					model.addObject("totalObjsVal", objectManager.getStatisticsTotalInstancesPerType());
					
				}
				else{
					if(authManager.isAutorized(Constants.P_VIEW_OWN_AUTHORS_STATISTICS_PER_TYPE, user) && activeGroup != null){
						model.addObject("profe", "nok");
						model.addObject("ownObjectsToValidate", objectManager.getUserStatisticsObjetsToVal(user, activeGroup));
						model.addObject("totalOwnObjects", objectManager.getUserStatisticsObjetsTotal(user,activeGroup));
						model.addObject("ownObjectsToValidateAC", objectManager.getUserStatisticsObjetsToValAC(user, activeGroup));
						model.addObject("totalOwnObjectsAC", objectManager.getUserStatisticsObjetsTotalAC(user,activeGroup));
					}
				}
				//Resumen de autores
//				if(authManager.isAutorized(Constants.P_VIEW_AUTHORS_STATISTICS, user) && activeGroup != null){
//					int numVal = authorManager.getNumUsersToValidateByGroup(user, activeGroup);
//					int numStud = authorManager.getUsersToValidateAuthorByGroup(user, activeGroup).size();
//					model.addObject("profe", "ok");
//					model.addObject("numAuthorsToValidate",numVal);
//					model.addObject("numAuthorsSToValidate", numStud);
//					model.addObject("numAuthorsToValidateS", 0);
//					model.addObject("numAuthorsByStudent",0);
//				}
//				else{
//					if(authManager.isAutorized(Constants.P_VIEW_OWN_AUTHORS_STATISTICS, user) && activeGroup != null){
//						int numCharTotal = authorManager.getStudentAuthorList(user).size();
//						model.addObject("profe", "nok");
//						model.addObject("numAuthorsToValidate",0);
//						model.addObject("numAuthorsSToValidate", 0);
//						model.addObject("numAuthorsToValidateS", authorManager.getNumAuthorsToValidateByUser(activeGroup, user));
//						model.addObject("numAuthorsByStudent",numCharTotal);
//					}
//				}
				
				
				//Fin resúmenes
				
				model.addObject("message", message);
				model.addObject("headers",Constants.getHeaders(user.getUser_role(),request));
				model.addObject("director",director);
				model.addObject("user",user);
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/2-1.js");
				model.addObject("scripts",scripts);
			}		
		}
		else{
			model = Constants.noPrivileges(user,logManager,actionInt,"mensaje",request);
		}	
		return model;
	}
	
	//true si hay error
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idGroup") == null || !Constants.isNumeric(request.getParameter("idGroup"));
	}
}