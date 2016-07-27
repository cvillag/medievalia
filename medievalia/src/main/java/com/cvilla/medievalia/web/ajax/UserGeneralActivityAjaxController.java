package com.cvilla.medievalia.web.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Log;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.PaginaException;

@Controller
public class UserGeneralActivityAjaxController {
	
	int activityid = Constants.P_GENERAL_LOG;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@RequestMapping(value = "activityLogA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		
		if(errorParam(request)){
			model = new ModelAndView("ajax/a-1-3.2-actividad");
			model.addObject("message", "nok");
		}
		else{
			if(authManager.isAutorized(activityid, user)){
				model = new ModelAndView("ajax/a-1-3.2-actividad");
				int pag = Constants.nullParameterInt(request, "pag", 1);
				int tamPag = Constants.nullParameterInt(request, "tamPag", 10);
				int pags = 0;
				try{
					pags = logManager.getNumPag(tamPag);
					model.addObject("numPags",pags);
					List<Log> activity = logManager.getActivity(pag, tamPag,Constants.ORDER_ASC);
					logManager.log(user.getId(), activityid, "Detalle de actividad de todos los usuarios", Constants.P_OK);
					model.addObject("activity", activity);
				}
				catch(PaginaException e){
					logManager.log(user.getId(), activityid, "Detalle de actividad de todos los usuarios " + e.getMessage(), Constants.P_NOK);
					model.addObject("message", "p1-3.2.error.paginaNoExiste");
				}
				if(pag == 1){
					model.addObject("first",true);
				}
				else{
					model.addObject("first",false);
				}
				if(pag == pags){
					model.addObject("last",true);
				}
				else{
					model.addObject("last",false);
				}
				model.addObject("actual",pag);
			}
			else{
				model = Constants.noPrivilegesA(user,logManager,activityid,"Intento de visionado de actividad de todos los usuarios ");
			}
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return  request.getParameter("pag") == null 
				|| request.getParameter("tamPag") == null;
	}
}
