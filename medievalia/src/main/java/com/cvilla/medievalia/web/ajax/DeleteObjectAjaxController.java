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
public class DeleteObjectAjaxController {
	
	private int actionInt = Constants.P_DELETE_OBJECT_INSTANCE;
	private int actionInt2 = Constants.P_DELETE_OBJECT_INSTANCE_OWN;
	
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
	
	@RequestMapping(value = "removeObjectA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjetoDOM tipo = (TipoObjetoDOM) sesion.getAttribute("tipoObjeto");
		JSONObject j = new JSONObject();
		
		if(authManager.isAutorized(actionInt, user) || authManager.isAutorized(actionInt2, user)){
			if((errorParam(request) && tipo == null) || groupA == null){
				j.put("message","noType");
				logManager.log(user.getId(), actionInt, "Fallo en borrado de instancia de objeto. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				int id = (new Integer(request.getParameter("idInstancia"))).intValue();
				String message = "";
				InstanciaObjetoDOM obj = objectManager.getObjetoDOM(tipo, id);
				int act = 0;
				if(obj != null){
					j.put("id", obj.getIdInstancia());
					if(authManager.isAutorized(actionInt, user)){
						message = objectManager.deleteObjetoDOM(obj, user, groupA);
						act = actionInt;
					}
					else{
						message = objectManager.deleteObjetoDOMOwn(obj, user, groupA);
						act = actionInt2;
					}
				}
				else{
					message = "noObject";
				}
				if(message.equals("borrado")){
					logManager.log(user.getId(), act, "Borrado con éxito de objeto " + obj.getNombre() + " del tipo " + obj.getTipo().getNombreDOM(), Constants.P_OK);
				}
				else{
					logManager.log(user.getId(), act, "Intento de borrado de objeto con ID " + id + " del tipo " + tipo.getNombreDOM() + ". Mensaje: " + message, Constants.P_NOK);
				}
				j.put("message", message);
			}
			model.addObject("json", j);
		}
		else{
			model = Constants.noPrivilegesJ(user,logManager,actionInt,"Modificación de objeto no permitida ");
		}
		return model;
	}

	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idInstancia") == null;
	}
}
