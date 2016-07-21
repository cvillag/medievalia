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
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;
//import com.cvilla.medievalia.utils.PaginaException;

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
//				String pa = request.getParameter("pag");
//				int pag = Constants.nullParameterInt(request, "pag", 1);
//				int tamPag = Constants.nullParameterInt(request, "tamPag", 10);
				int pags = 0;
				String id = request.getParameter("detailId");
				User u = userManager.getUser((new Integer(id).intValue()));
				if(u == null){
					logManager.log(user.getId(), Constants.P_DETAIL_OTHER_USER, "Detalle de actividad de otro usuario " + id + " fallida en acceso a BD", Constants.P_NOK);
					model = Constants.processError("Error: el usuario seleccionado no existe");
				}
				else{
					model = new ModelAndView("1-3.2-detalleUsuario");
//					try{
						pags = logManager.getNumPag(u.getId(), 10);
						model.addObject("numPags",pags);
//						List<Log> activity = logManager.getActivity(u.getId(), pag, tamPag,Constants.ORDER_ASC);
//						logManager.log(user.getId(), Constants.P_DETAIL_OTHER_USER, "Detalle de actividad de otro usuario " + id, Constants.P_OK);
//						model.addObject("activity", activity);
//					}
//					catch(PaginaException e){
//						logManager.log(user.getId(), Constants.P_DETAIL_OTHER_USER, "Detalle de actividad de otro usuario " + id + " " + e.getMessage(), Constants.P_NOK);
//						model.addObject("message", "p1-3.2.error.paginaNoExiste");
//					}
//					if(pag == 1){
//						model.addObject("first",true);
//					}
//					else{
//						model.addObject("first",false);
//					}
//					if(pag == pags){
//						model.addObject("last",true);
//					}
//					else{
//						model.addObject("last",false);
//					}
//					model.addObject("user", u);
				}
				List<String> scripts = new ArrayList<String>();
				scripts.add("js/1-3.2.js");
//				model.addObject("actual",pag);
				model.addObject("tamPag",10);
				model.addObject("scripts",scripts);
				model.addObject("detailId", id);
				model.addObject("user",u);
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
