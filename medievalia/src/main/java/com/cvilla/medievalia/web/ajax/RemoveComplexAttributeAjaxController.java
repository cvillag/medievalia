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
public class RemoveComplexAttributeAjaxController {
	
	private int actionInt = Constants.P_MODIFY_OBJECT_INSTANCE;
	private int actionInt2 = Constants.P_VALIDATE_COMPLEX_ATTRIBUTE;
	
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
	
	@RequestMapping(value = "remComplexAttribute.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjeto tipo = (TipoObjeto) sesion.getAttribute("tipoObjeto");
		JSONObject j = new JSONObject();
		
		if(authManager.isAutorized(actionInt, user)){
			if((errorParam(request) && tipo == null) || groupA == null){
				j.put("message","noType");
				logManager.log(user.getId(), actionInt, "Fallo en borrado de atributos complejos. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				int idInstPadre = (new Integer(request.getParameter("idInstPadre"))).intValue();
				int idTipoAttr = (new Integer(request.getParameter("idTipoAttr"))).intValue();
				int idInstHijo = (new Integer(request.getParameter("idInstHijo"))).intValue();
				int val;
				if(authManager.isAutorized(actionInt2, user)){
					val = Constants.OBJETO_VALIDADO;
				}
				else{
					val = Constants.OBJETO_NO_VALIDADO;
				}
				String message = objectManager.deleteObjetoDOMAttributeByType(idInstPadre, idInstHijo, tipo, idTipoAttr, val, user, groupA);
				logManager.log(user.getId(), actionInt, "Eliminación  de atributo complejo, con resultado: " + message, Constants.P_OK);
				j.put("message", message);
				j.put("pag", idTipoAttr);
			}
			model.addObject("json", j);
		}
		else{
			model = htmlManager.noPrivilegesJ(user,logManager,actionInt,"Eliminación de atributo complejo no permitida ");
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idInstPadre") == null || !htmlManager.isNumeric(request.getParameter("idInstPadre")) ||
				request.getParameter("idTipoAttr") == null  || !htmlManager.isNumeric(request.getParameter("idTipoAttr")) ||
				request.getParameter("idInstHijo") == null  || !htmlManager.isNumeric(request.getParameter("idInstHijo"));
	}
}
