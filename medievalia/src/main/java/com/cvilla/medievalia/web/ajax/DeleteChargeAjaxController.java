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
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class DeleteChargeAjaxController {
	
	private int actionIntS = Constants.P_DELETE_CHARGE;
	private int actionIntS2 = Constants.P_DELETE_OWN_CHARGES;

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private IChargeManager cargoManager;
	
	@RequestMapping(value = "removeChargeA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		
		if(authManager.isAutorized(actionIntS, user) || authManager.isAutorized(actionIntS2, user)){
			if(errorParam(request)){
				j.put("message","noId");
				logManager.log(user.getId(), actionIntS, "Fallo en creación de tema. Parámetros incorrectos. Esquivada seguridad javascript", Constants.P_NOK);
			}
			else{
				int idCargo = (new Integer(request.getParameter("idCargo"))).intValue();
				Charge c = cargoManager.getCharge(idCargo);
				String message;
				if(authManager.isAutorized(actionIntS, user)){
					message = cargoManager.deleteCharge(idCargo);
				}
				else {
					message = cargoManager.deleteOwnCharge(idCargo,user);
				}
				j.put("message", message);
				j.put("id", idCargo);
				if(message.equals("borrado")){
					logManager.log(user.getId(), actionIntS, "Borrado cargo " + c.getNombre()+ " (id:" + idCargo + ")", Constants.P_OK);
				}
				else{
					if(c != null){
						logManager.log(user.getId(), actionIntS, "Fallo borrado de cargo " + c.getNombre()+ " (id:" + idCargo + ")", Constants.P_NOK);
					}
				}
			}
		}
		else{
			j.put("message", "noPrivileges");
			logManager.log(user.getId(), actionIntS, "Fallo en borrado de tema. Sin privilegios", Constants.P_NOK);
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idCargo") == null;
	}	
}
