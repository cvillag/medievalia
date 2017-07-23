package com.cvilla.medievalia.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Place;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IPlaceManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class RenamePlaceAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IPlaceManager placeManager;
	
	@RequestMapping(value = "renamePlaceA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		String message;
		int action;
		if(errorParam(request)){
			message = "noParam";
			action = Constants.P_RENAME_PLACE;
		}
		else{
			String nombre = request.getParameter("newNombre");
			int idLugar = (new Integer(request.getParameter("idLugar"))).intValue();			
			if(authManager.isAutorized(Constants.P_RENAME_PLACE, user)){
				message = placeManager.renamePlace(nombre,idLugar,user,groupA);
				action = Constants.P_RENAME_PLACE;
			}
			else{
				if(authManager.isAutorized(Constants.P_RENAME_PLACE_OWN, user)){
					message = placeManager.renamePlaceOwn(nombre,idLugar,user,groupA);
					action = Constants.P_RENAME_PLACE_OWN;
					j.put("message", message);
				}
				else{
					message = "noPrivileges";
					action = Constants.P_RENAME_PLACE;
				}
			}
			if(message.equals("cambiado")){
				logManager.log(user.getId(), action, "Renombrar lugar. Nuevo nombre: " + nombre + " en lugar id = " + idLugar, Constants.P_OK);
			}
			else{
				logManager.log(user.getId(), action, "Renombrar lugar. Nuevo nombre: " + nombre + " en lugar id = " + idLugar + ". Fallido: mensaje = " + message, Constants.P_NOK);
				Place old = placeManager.getPlace(idLugar);
				j.put("oldname", old.getNombre());
				j.put("id", old.getIdPlace());
			}
			j.put("message", message);
			model.addObject("json", j);
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idLugar") == null &&
				request.getParameter("newNombre") == null;
	}
}
