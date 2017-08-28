package com.cvilla.medievalia.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Personage;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IPersonageManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.IPersonageManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class RenameCharacterAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IPersonageManager characterManager;
	
	@RequestMapping(value = "renameCharacterA.do")
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
			action = Constants.P_RENAME_CHARACTER;
		}
		else{
			String nombre = request.getParameter("newNombre");
			int idPersonaje = (new Integer(request.getParameter("idPersonaje"))).intValue();			
			if(authManager.isAutorized(Constants.P_RENAME_CHARACTER, user)){
				message = characterManager.renameCharacter(nombre,idPersonaje,user,groupA);
				action = Constants.P_RENAME_CHARACTER;
			}
			else{
				if(authManager.isAutorized(Constants.P_RENAME_CHARACTER_OWN, user)){
					message = characterManager.renameCharacterOwn(nombre,idPersonaje,user,groupA);
					action = Constants.P_RENAME_CHARACTER_OWN;
					j.put("message", message);
				}
				else{
					message = "noPrivileges";
					action = Constants.P_RENAME_CHARGE;
				}
			}
			if(message.equals("cambiado")){
				logManager.log(user.getId(), action, "Renombrar personaje. Nuevo nombre: " + nombre + " en personaje id = " + idPersonaje, Constants.P_OK);
			}
			else{
				logManager.log(user.getId(), action, "Renombrar personaje. Nuevo nombre: " + nombre + " en personaje id = " + idPersonaje + ". Fallido: mensaje = " + message, Constants.P_NOK);
				Personage old = characterManager.getPersonage(idPersonaje);
				j.put("oldname", old.getNombre());
				j.put("id", old.getIdPersonaje());
			}
			j.put("message", message);
			model.addObject("json", j);
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idPersonaje") == null &&
				request.getParameter("newNombre") == null;
	}
}
