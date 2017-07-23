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
public class ValidatePlaceAjaxController {

	private int actionS = Constants.P_VALIDATE_PLACE;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IPlaceManager placeManager;
	
	@RequestMapping(value = "validatePlace.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		String message;
		if(errorParam(request)){
			message = "noParam";
		}
		else{
			int idLugar = (new Integer(request.getParameter("idLugar"))).intValue();
			Place c = placeManager.getPlace(idLugar);
			if(authManager.isAutorized(actionS, user)){
				message = placeManager.validatePlace(c,user,groupA);
			}
			else{
				message = "noPrivileges";
			}
			if(message.equals("validado")){
				logManager.log(user.getId(), actionS, "Validar lugar con nombre: " + c.getNombre() + " e id = " + idLugar, Constants.P_OK);
			}
			else{
				if(c != null){
					logManager.log(user.getId(), actionS, "No se pudo validar el lugar con nombre " + c.getNombre() + " e id = " + idLugar + ". Fallido: mensaje = " + message, Constants.P_NOK);
				}
				else{
					logManager.log(user.getId(), actionS, "Intento de validar lugar con id = " + idLugar + ". Fallido: mensaje = " + message, Constants.P_NOK);
				}
			}
			j.put("id", idLugar);
			j.put("message", message);
			model.addObject("json", j);
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idLugar") == null;
	}
}
