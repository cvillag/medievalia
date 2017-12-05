package com.cvilla.medievalia.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.InstanciaAtributoComplejoDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class SetComplexAttributeTextReadedAjaxController {
	
	private int actionInt = Constants.P_SET_COMPLEX_ATTRIBUTE_TEXT_READED;
	
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
	
	@RequestMapping(value = "setComplexAttributeTextReaded.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjetoDOM tipo = (TipoObjetoDOM) sesion.getAttribute("tipoObjeto");
		JSONObject j = new JSONObject();
		
		if(authManager.isAutorized(actionInt, user)){
			if((errorParam(request) && tipo == null) || groupA == null){
				j.put("message","noType");
				logManager.log(user.getId(), actionInt, "Fallo en marcado de comentario de atributo comeplejo como leído. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				int idPadre = (new Integer(request.getParameter("idPadre"))).intValue();
				int idHijo = (new Integer(request.getParameter("idHijo"))).intValue();
				int tipoHijo = (new Integer(request.getParameter("tipoHijo"))).intValue();
				String message = objectManager.setComplexAttributeTextReaded(idPadre,idHijo,tipoHijo,user,tipo,groupA);
				j.put("message", message);
				j.put("pag", tipo.getTipoDOM());
			}
			model.addObject("json", j);
		}
		else{
			model = Constants.noPrivilegesJ(user,logManager,actionInt,"Marcado de comentario de atributo comeplejo como leído no permitido.");
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idPadre") == null  || !Constants.isNumeric(request.getParameter("idPadre")) ||
		request.getParameter("idHijo") == null  || !Constants.isNumeric(request.getParameter("idHijo")) ||
		request.getParameter("tipoHijo") == null || !Constants.isNumeric(request.getParameter("tipoHijo"));
	}
}
