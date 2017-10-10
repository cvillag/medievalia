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
public class RenameObjectAjaxController {
	
	private int actionInt = Constants.P_RENAME_OBJECT_INSTANCE;
	private int actionInt2 = Constants.P_RENAME_OBJECT_INSTANCE_OWN;
	
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
	
	@RequestMapping(value = "renameObjectA.do")
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
				logManager.log(user.getId(), actionInt, "Fallo en modificación de instancia de objeto. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				int id = (new Integer(request.getParameter("idInstancia"))).intValue();
				InstanciaObjetoDOM obj = objectManager.getObjetoDOM(tipo, id);
				String message = "";
				if(obj != null){
					j.put("oldname", obj.getNombre());
					String newName = request.getParameter("newNombre");
					if(authManager.isAutorized(actionInt, user)){
						message = objectManager.renameObjetoDOM(tipo, newName, id, user, groupA);
					}
					else if(authManager.isAutorized(actionInt2, user)){
						message = objectManager.renameObjetoDOMOwn(tipo, newName, id, user, groupA);
					}			
				}
				else{
					j.put("message", "noObject");
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
		return request.getParameter("idInstancia") == null &&
				request.getParameter("newNombre") == null;
	}
}
