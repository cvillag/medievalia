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
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAuthorManager;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IChargeManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IPlaceManager;
import com.cvilla.medievalia.service.intf.IStudyManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
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
	private ITemaManager temaManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IChargeManager chargeManager;
	
	@Autowired
	private IStudyManager studyManager;
	
	@Autowired
	private IPlaceManager placeManager;
	
	@Autowired
	private IAuthorManager authorManager;
	
	@RequestMapping(value = "contentManager.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group activeGroup  = (Group) sesion.getAttribute("grupoActual");
		
		if(authManager.isAutorized(actionInt, user)){
			if(errorParam(request) && activeGroup==null){
				model = Constants.paramError(logManager,Constants.P_LOGIN,user.getId());
				model.addObject("headers",Constants.getHeaders(user.getUser_role(),request));
			}
			else{
				String message;
				model = new ModelAndView("2-1-contentManager");
				User director;
				if(activeGroup != null && request.getParameter("change") == null){
					director = userManager.getUser(activeGroup.getDirector());
					message = "p3.1.msg.ok";
					List<Tema> listaTemas = temaManager.getTemaGrupoByGroup(activeGroup);
					model.addObject("listaTemas", listaTemas);
				}
				else{
					int idGroup = (new Integer(request.getParameter("idGroup"))).intValue();
					Group g = groupManager.getGroupById(idGroup);
					director = userManager.getUser(g.getDirector());
					
					if(groupManager.setActiveGroup(user, g, logManager)){
						sesion.setAttribute("grupoActual", g);
						message = "p3.1.msg.ok";
						List<Tema> listaTemas = temaManager.getTemaGrupoByGroup(g);
						model.addObject("listaTemas", listaTemas);
						activeGroup = new Group(g.getIdGrupo(),g.getDirector(),g.getName(),g.getDescription());
					}
					else{
						message = "p3.1.msg.grpNoExiste";
					}
				}
				
				//Resumen de cargos
				if(authManager.isAutorized(Constants.P_VIEW_CHARGE_STATISTICS, user) && activeGroup != null){
					int numVal = chargeManager.getNumUsersToValidateByGroup(user, activeGroup);
					int numStud = chargeManager.getUsersToValidateChargeByGroup(user, activeGroup).size();
					model.addObject("profe", "ok");
					model.addObject("numChargesToValidate",numVal);
					model.addObject("numStudentsToValidate", numStud);
					model.addObject("numChargesToValidateS", 0);
					model.addObject("numChargesByStudent",0);
				}
				else{
					if(authManager.isAutorized(Constants.P_VIEW_OWN_CHARGE_STATISTICS, user) && activeGroup != null){
						int numCharTotal = chargeManager.getStudentChargeList(user).size();
						model.addObject("profe", "nok");
						model.addObject("numChargesToValidate",0);
						model.addObject("numStudentsToValidate", 0);
						model.addObject("numChargesToValidateS", chargeManager.getNumChargesToValidateByUser(activeGroup, user));
						model.addObject("numChargesByStudent",numCharTotal);
					}
				}
				
				//Resumen de estudios
				if(authManager.isAutorized(Constants.P_VIEW_STUDIES_STATISTICS, user) && activeGroup != null){
					int numVal = studyManager.getNumUsersToValidateByGroup(user, activeGroup);
					int numStud = studyManager.getUsersToValidateStudyByGroup(user, activeGroup).size();
					model.addObject("profe", "ok");
					model.addObject("numStudiesToValidate",numVal);
					model.addObject("numStudentsSToValidate", numStud);
					model.addObject("numStudiesToValidateS", 0);
					model.addObject("numStudiesByStudent",0);
				}
				else{
					if(authManager.isAutorized(Constants.P_VIEW_OWN_STUDIES_STATISTICS, user) && activeGroup != null){
						int numCharTotal = studyManager.getStudentStudyList(user).size();
						model.addObject("profe", "nok");
						model.addObject("numStudiesToValidate",0);
						model.addObject("numStudentsSToValidate", 0);
						model.addObject("numStudiesToValidateS", studyManager.getNumStudysToValidateByUser(activeGroup, user));
						model.addObject("numStudiesByStudent",numCharTotal);
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
				
				//Resumen de lugares
				if(authManager.isAutorized(Constants.P_VIEW_PLACES_STATISTICS, user) && activeGroup != null){
					int numVal = placeManager.getNumUsersToValidateByGroup(user, activeGroup);
					int numStud = placeManager.getUsersToValidatePlaceByGroup(user, activeGroup).size();
					model.addObject("profe", "ok");
					model.addObject("numPlaceToValidate",numVal);
					model.addObject("numPlaceSToValidate", numStud);
					model.addObject("numPlacesToValidateS", 0);
					model.addObject("numPlacesByStudent",0);
				}
				else{
					if(authManager.isAutorized(Constants.P_VIEW_OWN_AUTHORS_STATISTICS, user) && activeGroup != null){
						int numCharTotal = authorManager.getStudentAuthorList(user).size();
						model.addObject("profe", "nok");
						model.addObject("numPlaceToValidate",0);
						model.addObject("numPlaceSToValidate", 0);
						model.addObject("numPlacesToValidateS", authorManager.getNumAuthorsToValidateByUser(activeGroup, user));
						model.addObject("numPlacesByStudent",numCharTotal);
					}
				}
				
				//Resumen de autores
				if(authManager.isAutorized(Constants.P_VIEW_AUTHORS_STATISTICS, user) && activeGroup != null){
					int numVal = authorManager.getNumUsersToValidateByGroup(user, activeGroup);
					int numStud = authorManager.getUsersToValidateAuthorByGroup(user, activeGroup).size();
					model.addObject("profe", "ok");
					model.addObject("numAuthorsToValidate",numVal);
					model.addObject("numAuthorsSToValidate", numStud);
					model.addObject("numAuthorsToValidateS", 0);
					model.addObject("numAuthorsByStudent",0);
				}
				else{
					if(authManager.isAutorized(Constants.P_VIEW_OWN_AUTHORS_STATISTICS, user) && activeGroup != null){
						int numCharTotal = authorManager.getStudentAuthorList(user).size();
						model.addObject("profe", "nok");
						model.addObject("numAuthorsToValidate",0);
						model.addObject("numAuthorsSToValidate", 0);
						model.addObject("numAuthorsToValidateS", authorManager.getNumAuthorsToValidateByUser(activeGroup, user));
						model.addObject("numAuthorsByStudent",numCharTotal);
					}
				}
				
				
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
		return request.getParameter("idGroup") == null;
	}
}