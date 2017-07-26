package com.cvilla.medievalia.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Author;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IAuthorManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class ValidateAuthorAjaxController {

	private int actionS = Constants.P_VALIDATE_AUTHOR;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IAuthorManager authorManager;
	
	@RequestMapping(value = "validateAuthor.do")
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
			int idAutor = (new Integer(request.getParameter("idAutor"))).intValue();
			Author c = authorManager.getAuthor(idAutor);
			if(authManager.isAutorized(actionS, user)){
				message = authorManager.validateAuthor(c,user,groupA);
			}
			else{
				message = "noPrivileges";
			}
			if(message.equals("validado")){
				logManager.log(user.getId(), actionS, "Validar autor con nombre: " + c.getNombre() + " e id = " + idAutor, Constants.P_OK);
			}
			else{
				if(c != null){
					logManager.log(user.getId(), actionS, "No se pudo validar el autor con nombre " + c.getNombre() + " e id = " + idAutor + ". Fallido: mensaje = " + message, Constants.P_NOK);
				}
				else{
					logManager.log(user.getId(), actionS, "Intento de validar autor con id = " + idAutor + ". Fallido: mensaje = " + message, Constants.P_NOK);
				}
			}
			j.put("id", idAutor);
			j.put("message", message);
			model.addObject("json", j);
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idAutor") == null;
	}
}
