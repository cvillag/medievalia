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
import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class ViewObjectInstanceDetailAjaxController {
	
	final static int actionId = Constants.P_VIEW_OBJECT_INSTANCE_DETAIL; 

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IObjectManager objectManager;
	
	@RequestMapping(value = "objectDetail.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjetoDOM tipo = (TipoObjetoDOM) sesion.getAttribute("tipoObjeto");
		ModelAndView model = new ModelAndView("ajax/2-2-detalleObjeto");
//		JSONObject j = new JSONObject();
		String message;
		if(errorParam(request) || groupA == null || tipo == null){
			return Constants.paramError(logManager, actionId, user.getId());
		}
		else{
			int idInstancia = (new Integer(request.getParameter("idInstancia"))).intValue();
			if(authManager.isAutorized(actionId, user)){
				message = "ok";
				ObjetoDOM obj = objectManager.getObjetoDOM(tipo, idInstancia);
				model.addObject("object", obj);
				logManager.log(user.getId(), actionId, "Visualización de detalle de objeto ", Constants.P_OK);
			}
			else{
				message =  "noPrivileges";
				logManager.log(user.getId(), actionId, "Visualización de detalle de objeto no permitida", Constants.P_NOK);
			}
		}
		model.addObject("message", message);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idInstancia") == null;
	}
}
