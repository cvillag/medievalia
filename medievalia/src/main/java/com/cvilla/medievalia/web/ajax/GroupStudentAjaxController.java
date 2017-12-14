package com.cvilla.medievalia.web.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class GroupStudentAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "studentGroupA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			if(authManager.isAutorized(Constants.P_DETAIL_STUDENT_GROUPS_OWN, user) ){
				List<Students> lista = groupManager.getListByStudent(user,user);
				model = new ModelAndView("ajax/a-1-3.2-grupos-s");
				model.addObject("message", "ok");
				model.addObject("gruposStud",lista);
				model.addObject("play",true);
				logManager.log(user.getId(), Constants.P_DETAIL_STUDENT_GROUPS_OWN, "Listado de grupos propios como alumno", Constants.P_OK);
			}
			else{
				model = htmlManager.noPrivilegesA(user,logManager,Constants.P_DETAIL_STUDENT_GROUPS_OWN,"Sin permiso para ver listado de grupos propios como alumno");
			}
		}else{
			if(authManager.isAutorized(Constants.P_DETAIL_STUDENT_GROUPS_OTHER, user) || authManager.isAutorized(Constants.P_DETAIL_STUDENT_GROUPS_OWN, user) ){
				int idStud = (new Integer(request.getParameter("idStud"))).intValue();
				User student = loginManager.getUser(idStud);
				List<Students> lista = groupManager.getListByStudent(user,student);
				model = new ModelAndView("ajax/a-1-3.2-grupos-s");
				model.addObject("student",student);
				model.addObject("message", "ok");
				model.addObject("gruposStud",lista);
				model.addObject("play",false);
				logManager.log(user.getId(), Constants.P_DETAIL_STUDENT_GROUPS_OTHER, "Listado de grupos ajenos como alumno", Constants.P_OK);
			}
			else{
				model = htmlManager.noPrivilegesA(user,logManager,Constants.P_DETAIL_STUDENT_GROUPS_OTHER,"Sin permiso para ver listado de grupos de otros usuarios como alumno");
			}	
		}
		return model;
	}
	boolean errorParam(HttpServletRequest request){
		return request.getParameter("idStud") == null || !htmlManager.isNumeric(request.getParameter("idStud"));
	}
	
}
