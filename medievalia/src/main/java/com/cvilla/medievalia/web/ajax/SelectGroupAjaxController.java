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
import com.cvilla.medievalia.utils.Constants;

@Controller
public class SelectGroupAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@RequestMapping(value = "selectActiveGroup.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			
		}
		if(authManager.isAutorized(Constants.P_SELECT_ACTIVE_GROUP, user)){
			int idGroup = (new Integer(request.getParameter("idGroup"))).intValue();
			Group g = groupManager.getGroupById(idGroup);
			if(g != null){
				logManager.log(user.getId(), Constants.P_SELECT_ACTIVE_GROUP, "Selección de grupo " + g.getName(), Constants.P_OK);
				sesion.setAttribute("grupoActual", g);
				j.put("message", "ok");
			}
			else{
				logManager.log(user.getId(), Constants.P_SELECT_ACTIVE_GROUP, "Intento de selección de grupo con id " + idGroup, Constants.P_NOK);
				j.put("message", "nok");
			}
		}
		model.addObject("json", j);
		return model;
	}
	
	boolean errorParam(HttpServletRequest request){
		return request.getParameter("idGroup") == null;
	}
}
