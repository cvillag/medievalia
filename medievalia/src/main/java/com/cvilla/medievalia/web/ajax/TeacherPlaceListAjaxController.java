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

import com.cvilla.medievalia.domain.Place;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IPlaceManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class TeacherPlaceListAjaxController {
	
	private int actionIntS = Constants.P_VIEW_PLACES;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private IPlaceManager placeManager;
	
	@RequestMapping(value = "teacherPlaceListA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/2-4-listaProfe");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		JSONObject j = new JSONObject();
		
		if(authManager.isAutorized(actionIntS, user)){
			if(errorParam(request)){
				j.put("message","noType");
				logManager.log(user.getId(), actionIntS, "Fallo en listado de lugares para validación. Parámetros incorrectos. Esquivada seguridad javascript", Constants.P_NOK);
			}
			else{
				List<Place> listag = placeManager.getTeacherPlaceList(user,groupA);
				model.addObject("listaLugares", listag);
				model.addObject("type",request.getParameter("type"));
				logManager.log(user.getId(), actionIntS, "Visualización lista de lugares por validar", Constants.P_OK);
				if(authManager.isAutorized(Constants.P_DELETE_PLACE, user)){
					model.addObject("permisoborrado","ok");
				}
				if(authManager.isAutorized(Constants.P_VALIDATE_PLACE, user)){
					model.addObject("permisovalidar","ok");
				}
			}
		}
		else{
			model = Constants.noPrivilegesA(user,logManager,actionIntS,"Visualización de lista de lugares por validar no permitida (grupo: " + groupA.getName() + ")");
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("type") == null;
	}
}
