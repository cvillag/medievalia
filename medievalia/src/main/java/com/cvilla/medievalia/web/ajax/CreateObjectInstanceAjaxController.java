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
public class CreateObjectInstanceAjaxController {
	
	private int actionInt = Constants.P_CREATE_OBJECT_INSTANCE;
	
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
	
	@RequestMapping(value = "createObjectA.do")
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
				logManager.log(user.getId(), actionInt, "Fallo en creación de instancia de objeto. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				String nombre = request.getParameter("nombre");
				InstanciaObjetoDOM o = new InstanciaObjetoDOM();
				o.setNombre(nombre);
				String message = objectManager.addObjetoDOM(tipo, o, groupA, user);
				j.put("message", message);
				if(message.equals("creado")){
					logManager.log(user.getId(), actionInt, "Creación de objeto, de tipo " + tipo.getNombreDOM() + " (" + tipo.getTipoDOM() + ") y nombre " + nombre, Constants.P_OK);
				}
				else{
					logManager.log(user.getId(), actionInt, "Fallo en creación de objeto, de tipo " + tipo.getNombreDOM() + " (" + tipo.getTipoDOM() + ") y nombre " + nombre + " en grupo " + groupA.getName() + " (" + groupA.getIdGrupo() + ")", Constants.P_NOK);
				}
			}
			model.addObject("json", j);
		}
		else{
			model = Constants.noPrivilegesJ(user,logManager,actionInt,"Creación de objeto no permitida ");
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("type") == null || !Constants.isNumeric(request.getParameter("type"));
	}
}
