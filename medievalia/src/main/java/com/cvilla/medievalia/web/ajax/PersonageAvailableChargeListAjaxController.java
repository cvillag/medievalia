package com.cvilla.medievalia.web.ajax;

import java.util.List;

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
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IChargeManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IPersonageManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class PersonageAvailableChargeListAjaxController {
	
	private int actionId = Constants.P_VIEW_CHARGES_BY_CHARACTER; 
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private IPersonageManager personageManager;
	
	@RequestMapping(value = "availableChargeList.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		//FIXME: jsp vacío
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		JSONObject j = new JSONObject();
		
		if(authManager.isAutorized(actionId, user)){
			if(errorParam(request)){
				j.put("message","noType");
				logManager.log(user.getId(), actionId, "Fallo en listado de cargos de personaje. Parámetros incorrectos. Esquivada seguridad javascript", Constants.P_NOK);
			}
			else{
				int idPersonaje = (new Integer(request.getParameter("idPersonaje"))).intValue();
				List<Charge> listaDisponible = personageManager.getChargeListAvailable(idPersonaje);
				model.addObject("listaDisponible", listaDisponible);
				List<Charge> listaActual= personageManager.getChargeListOfPersonage(idPersonaje);
				model.addObject("listaActual", listaActual);
				model.addObject("message","ok");
				//FIXME: Meter las listas por json y con javascript crear los elementos. En caso contrario dividir en dos esta petición una para lista disponible y otra la actual
				if(authManager.isAutorized(Constants.P_VALIDATE_CHARGE_CHARACTER,user)){
					model.addObject("permisoAsignar","ok");
				}
				logManager.log(user.getId(), actionId, "Visualización lista de cargos de personaje y disponibles", Constants.P_OK);
			}
		}
		else{
			model = Constants.noPrivilegesA(user,logManager,actionId,"Visualización de cargos de personaje no permitida (grupo: " + groupA.getName() + ")");
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idPersonaje") == null;
	}
}
