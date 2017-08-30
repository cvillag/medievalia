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
public class ChangeDataCharacterAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IPersonageManager characterManager;
	
	@RequestMapping(value = "modifyCharacterA.do")
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
			action = Constants.P_MODIFY_CHARACTER;
		}
		else{
			int idPersonaje = (new Integer(request.getParameter("idPersonaje"))).intValue();
			if(authManager.isAutorized(Constants.P_MODIFY_CHARACTER, user) || authManager.isAutorized(Constants.P_MODIFY_DATA_CHARACTER_OWN, user)){
				
				Personage p = new Personage();
				p.setNombre(request.getParameter("modalDatosNombre"));
				p.setOtros(request.getParameter("modalOtros"));
				if(new Boolean(request.getParameter("knowAN"))){
					p.setAnacimiento(new Integer(request.getParameter("modalAnioNac")));
					if(new Boolean(request.getParameter("knowMN"))){
						p.setMnacimiento(new Integer(request.getParameter("modalMesNac")));
						if(new Boolean(request.getParameter("knowDN")))
							p.setDnacimiento(new Integer(request.getParameter("modalDiaNac")));
					}
				}
				
				if(new Boolean(request.getParameter("knowAF"))){
					p.setMnacimiento(new Integer(request.getParameter("modalAnioFal")));
					if(new Boolean(request.getParameter("knowMF"))){
						p.setMnacimiento(new Integer(request.getParameter("modalMesFal")));
						if(new Boolean(request.getParameter("knowDF")))
							p.setDnacimiento(new Integer(request.getParameter("modalDiaFal")));
					}
				}
				
				p.setCreador(user.getId());
				p.setIdGrupo(groupA.getIdGrupo());
				
				if(authManager.isAutorized(Constants.P_MODIFY_CHARACTER, user)){
					message = characterManager.modifyCharacter(p,idPersonaje,user,groupA);
					action = Constants.P_MODIFY_CHARACTER;
				}
				else{
					message = characterManager.modifyCharacterOwn(p,idPersonaje,user,groupA);
					action = Constants.P_MODIFY_DATA_CHARACTER_OWN;
				}
			}
			else{
				message = "noPrivileges";
				action = Constants.P_MODIFY_CHARACTER;
			}
			if(message.equals("cambiado")){
				logManager.log(user.getId(), action, "Cambiar datos de personaje id = " + idPersonaje, Constants.P_OK);
			}
			else{
				logManager.log(user.getId(), action, "Cambiar datos de personaje id = " + idPersonaje + ". Fallido: mensaje = " + message, Constants.P_NOK);
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
				request.getParameter("modalDatosNombre") == null &&
				request.getParameter("modalOtros") == null &&
				request.getParameter("knowDN") == null &&
				request.getParameter("modalDiaNac") == null &&
				request.getParameter("knowMN") == null &&
				request.getParameter("modalMesNac") == null &&
				request.getParameter("knowAN") == null &&
				request.getParameter("modalAnioNac") == null &&
				request.getParameter("knowDF") == null &&
				request.getParameter("modalDiaFal") == null &&
				request.getParameter("knowMF") == null &&
				request.getParameter("modalMesFal") == null &&
				request.getParameter("knowAF") == null &&
				request.getParameter("modalAnioFal") == null;
	}
}
