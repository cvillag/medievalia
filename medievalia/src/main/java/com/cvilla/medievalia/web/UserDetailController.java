package com.cvilla.medievalia.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.IAutorizationManager;
import com.cvilla.medievalia.service.ILogManager;
import com.cvilla.medievalia.service.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class UserDetailController {
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value = "detailUser.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			model = Constants.paramError(logManager,Constants.P_DETAIL_OTHER_USER,user.getId());
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));

		}
		else{
			if(authManager.isAutorized(Constants.P_DETAIL_OTHER_USER, user)){
				String id = request.getParameter("detailId");
				User u = userManager.getUser((new Integer(id).intValue()));
				if(u == null){
					logManager.log(user.getId(), Constants.P_DETAIL_OTHER_USER, "Detalle de actividad de usuario " + id + " fallida en acceso a BD", Constants.P_NOK);
					model = Constants.processError("Error: el usuario seleccionado no existe");
				}
				else{
					logManager.log(user.getId(), Constants.P_DETAIL_OTHER_USER, "Detalle de actividad de usuario " + id, Constants.P_OK);
					model = new ModelAndView("1-3.2-detalleUsuario");
					model.addObject("user", u);
				}
				model.addObject("headers",Constants.getHeaders(user.getUser_role()));
			}
			else{
				model = Constants.noPrivileges(user,logManager, Constants.P_DETAIL_OTHER_USER,"mensaje");
			}			
		}
		return model;
	}
	
	//true si hay error
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("detailId") == null;
	}
}
