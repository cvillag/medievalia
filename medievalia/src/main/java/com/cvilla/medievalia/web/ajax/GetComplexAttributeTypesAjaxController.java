package com.cvilla.medievalia.web.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.TipoAtributoComplejo;
import com.cvilla.medievalia.domain.TipoObjeto;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class GetComplexAttributeTypesAjaxController {
	
	private int actionInt = Constants.P_VIEW_OBJECT_INSTANCE_DETAIL;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private IObjectManager objectManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "getComplexAttributeTypes.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/2-2-listaTipos");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjeto tipo = (TipoObjeto) sesion.getAttribute("tipoObjeto");
		JSONObject j = new JSONObject();
		
		if(authManager.isAutorized(actionInt, user)){
			if((errorParam(request) && tipo == null) || groupA == null){
				j.put("message","noType");
				logManager.log(user.getId(), actionInt, "Fallo al consultar tipos de atributos complejos. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				List<TipoAtributoComplejo> tipos = objectManager.getTiposAtributosCompleos(tipo);
				model.addObject("tipos", tipos);
			}
			model.addObject("json", j);
		}
		else{
			model = htmlManager.noPrivilegesJ(user,logManager,actionInt,"Consulta de tipos de atributos complejos no permitida.");
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("instp") == null  || !htmlManager.isNumeric(request.getParameter("instp")) ||
				request.getParameter("insth") == null  || !htmlManager.isNumeric(request.getParameter("insth")) ||
				request.getParameter("tipoh") == null  || !htmlManager.isNumeric(request.getParameter("tipoh"));
	}
}
