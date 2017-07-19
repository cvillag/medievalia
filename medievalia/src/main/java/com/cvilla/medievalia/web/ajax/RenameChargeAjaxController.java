package com.cvilla.medievalia.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IChargeManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class RenameChargeAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IChargeManager chargeManager;
	
	@RequestMapping(value = "renameChargeA.do")
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
			action = Constants.P_RENAME_CHARGE;
		}
		else{
			String nombre = request.getParameter("newNombre");
			int idCargo = (new Integer(request.getParameter("idCargo"))).intValue();			
			if(authManager.isAutorized(Constants.P_RENAME_CHARGE, user)){
				message = chargeManager.renameCharge(nombre,idCargo,user,groupA);
				action = Constants.P_RENAME_CHARGE;
			}
			else{
				if(authManager.isAutorized(Constants.P_RENAME_CHARGE_OWN, user)){
					message = chargeManager.renameChargeOwn(nombre,idCargo,user,groupA);
					action = Constants.P_RENAME_CHARGE_OWN;
					j.put("message", message);
				}
				else{
					message = "noPrivileges";
					action = Constants.P_RENAME_CHARGE;
				}
			}
			if(message.equals("cambiado")){
				logManager.log(user.getId(), action, "Renombrar cargo. Nuevo nombre: " + nombre + " en cargo id = " + idCargo, Constants.P_OK);
			}
			else{
				logManager.log(user.getId(), action, "Renombrar cargo. Nuevo nombre: " + nombre + " en cargo id = " + idCargo + ". Fallido: mensaje = " + message, Constants.P_NOK);
				Charge old = chargeManager.getCharge(idCargo);
				j.put("oldname", old.getNombre());
				j.put("id", old.getIdCharge());
			}
			j.put("message", message);
			model.addObject("json", j);
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idCargo") == null &&
				request.getParameter("newNombre") == null;
	}
}
