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
public class SetObjectTextReadedAjaxController {
	
	private int actionInt = Constants.P_SET_OBJECT_TEXT_READED;
	
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
	
	@RequestMapping(value = "setObjectTextReaded.do")
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
				logManager.log(user.getId(), actionInt, "Fallo en marcado de comentario de objeto como leído. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				int idObjeto = (new Integer(request.getParameter("idObjeto"))).intValue();
				String message = objectManager.setObjectTextReaded(idObjeto,user,tipo,groupA);
				logManager.log(user.getId(), actionInt, "Comentario de validación de objeto marcado como leído", Constants.P_OK);
				j.put("message", message);
			}
			model.addObject("json", j);
		}
		else{
			model = htmlManager.noPrivilegesJ(user,logManager,actionInt,"Marcado de comentario de objeto como leído no permitido.");
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idObjeto") == null  || !htmlManager.isNumeric(request.getParameter("idObjeto"));
	}
}
