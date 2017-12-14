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


//import com.cvilla.medievalia.domain.Log;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class UserDetailController {
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "detailUser.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			model = htmlManager.paramError(logManager,Constants.P_DETAIL_OTHER_USER,user.getId());
			model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));

		}
		else{
			if(authManager.isAutorized(Constants.P_DETAIL_OTHER_USER, user)){
				int pags = 0;
				String id = request.getParameter("detailId");
				User u = userManager.getUser((new Integer(id).intValue()));
				if(u == null){
					logManager.log(user.getId(), Constants.P_DETAIL_OTHER_USER, "Detalle de actividad de otro usuario " + id + " fallida en acceso a BD", Constants.P_NOK);
					model = htmlManager.processError("Error: el usuario seleccionado no existe");
				}
				else{
					model = new ModelAndView("1-3.2-detalleUsuario");
						pags = logManager.getNumPag(u.getId(), 10);
						model.addObject("numPags",pags);
				}
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/1-3.2.js");
				scripts.add("js/common/groups-list.js");
				model.addObject("tamPag",10);
				model.addObject("scripts",scripts);
				model.addObject("detailId", id);
				model.addObject("usuario",u);
				model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));
			}
			else{
				model = htmlManager.noPrivileges(user,logManager, Constants.P_DETAIL_OTHER_USER,"mensaje",request);
			}			
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("detailId") == null || !htmlManager.isNumeric(request.getParameter("detailId"));
	}
}
