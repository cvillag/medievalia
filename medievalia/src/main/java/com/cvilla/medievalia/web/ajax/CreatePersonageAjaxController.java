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
import com.cvilla.medievalia.domain.Personage;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IPersonageManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class CreatePersonageAjaxController {

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
	
	@RequestMapping(value = "createPersonageA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		
		if(authManager.isAutorized(Constants.P_CREATE_CHARACTER, user)){
			if(errorParam(request)){
				j.put("message","noName");
				logManager.log(user.getId(), Constants.P_CREATE_CHARACTER, "Fallo en creación de personaje. Parámetros incorrectos. Esquivada seguridad javascript", Constants.P_NOK);
			}
			else{
				Personage p = new Personage();
				p.setNombre(request.getParameter("crearNombre"));
				p.setOtros(request.getParameter("modalOtros2"));
				if(new Boolean(request.getParameter("knowAN2"))){
					p.setAnacimiento(new Integer(request.getParameter("modalAnioNac2")));
					if(new Boolean(request.getParameter("knowMN2"))){
						p.setMnacimiento(new Integer(request.getParameter("modalMesNac2")));
						if(new Boolean(request.getParameter("knowDN2")))
							p.setDnacimiento(new Integer(request.getParameter("modalDiaNac2")));
					}
				}
				
				if(new Boolean(request.getParameter("knowAF2"))){
					p.setMnacimiento(new Integer(request.getParameter("modalAnioFal2")));
					if(new Boolean(request.getParameter("knowMF2"))){
						p.setMnacimiento(new Integer(request.getParameter("modalMesFal2")));
						if(new Boolean(request.getParameter("knowDF2")))
							p.setDnacimiento(new Integer(request.getParameter("modalDiaFal2")));
					}
				}
				
				p.setCreador(user.getId());
				p.setIdGrupo(groupA.getIdGrupo());
				
				String message ="";
				message =  personageManager.addPersonage(p,groupA,user);
				j.put("message", message);
				if(message.equals("creado")){
					logManager.log(user.getId(), Constants.P_CREATE_CHARACTER, "Creado personaje " + p.getNombre() + " en grupo " + groupA.getName() + "(id:" + groupA.getIdGrupo() + ")", Constants.P_OK);
				}
				else{
					logManager.log(user.getId(), Constants.P_CREATE_CHARACTER, "Intento de creación de personaje " + p.getNombre() + " en grupo " + groupA.getName() + "(id:" + groupA.getIdGrupo() + "). Fallo: " + message, Constants.P_NOK);
				}
			}
		}
		else{
			j.put("message", "noPrivileges");
			logManager.log(user.getId(), Constants.P_CREATE_CHARACTER, "Fallo en creación de personaje. Sin privilegios", Constants.P_NOK);
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("crearNombre") == null &&
				request.getParameter("modalOtros2") == null &&
//				request.getParameter("knowDN2") == null &&
				request.getParameter("modalDiaNac2") == null &&
//				request.getParameter("knowMN2") == null &&
				request.getParameter("modalMesNac2") == null &&
//				request.getParameter("knowAN2") == null &&
				request.getParameter("modalAnioNac2") == null &&
//				request.getParameter("knowDF2") == null &&
				request.getParameter("modalDiaFal2") == null &&
//				request.getParameter("knowMF2") == null &&
				request.getParameter("modalMesFal2") == null &&
//				request.getParameter("knowAF2") == null &&
				request.getParameter("modalAnioFal2") == null;
	}	
}
