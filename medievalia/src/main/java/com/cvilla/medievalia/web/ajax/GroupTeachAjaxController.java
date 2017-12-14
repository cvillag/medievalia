package com.cvilla.medievalia.web.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class GroupTeachAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@RequestMapping(value = "teacherGroupA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			if(authManager.isAutorized(Constants.P_DETAIL_DIRECTOR_GROUPS_OWN, user) ){
				List<Teachers> lista = groupManager.getListByTeacher(user,user);
				model = new ModelAndView("ajax/a-1-3.2-grupos-t");
				model.addObject("message", "ok");
				model.addObject("gruposTeach",lista);
				model.addObject("play",true);
				logManager.log(user.getId(), Constants.P_DETAIL_DIRECTOR_GROUPS_OWN, "Visualización de lista de grupos propios", Constants.P_OK);
			}
			else{
				model = Constants.noPrivilegesA(user,logManager,Constants.P_DETAIL_TEACHER_GROUPS_OWN,"Sin permisos para ver lista de grupos propio");
			}		
		}
		else{
			if(authManager.isAutorized(Constants.P_DETAIL_DIRECTOR_GROUPS_OTHER, user) || authManager.isAutorized(Constants.P_DETAIL_DIRECTOR_GROUPS_OWN, user) ){
				int idTeach = (new Integer(request.getParameter("idTeach"))).intValue();
				User teacher = loginManager.getUser(idTeach);
				List<Teachers> lista = groupManager.getListByTeacher(user,teacher);
				model = new ModelAndView("ajax/a-1-3.2-grupos-t");
				model.addObject("teacher",teacher);
				model.addObject("message", "ok");
				model.addObject("gruposTeach",lista);
				model.addObject("play",false);
				logManager.log(user.getId(), Constants.P_DETAIL_DIRECTOR_GROUPS_OTHER, "Visualización de lista de grupos de otros usuarios o propios", Constants.P_OK);

			}
			else{
				model = Constants.noPrivilegesA(user,logManager,Constants.P_DETAIL_TEACHER_GROUPS_OTHER,"mensaje");
			}			
		}
		return model;
	}
	boolean errorParam(HttpServletRequest request){
		return request.getParameter("idTeach") == null || !Constants.isNumeric(request.getParameter("idTeach"));
	}
	
}
