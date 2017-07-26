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
public class RenameAuthorAjaxController {

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IAuthorManager authorManager;
	
	@RequestMapping(value = "renameAuthorA.do")
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
			action = Constants.P_RENAME_AUTHOR;
		}
		else{
			String nombre = request.getParameter("newNombre");
			int idAutor = (new Integer(request.getParameter("idAutor"))).intValue();			
			if(authManager.isAutorized(Constants.P_RENAME_AUTHOR, user)){
				message = authorManager.renameAuthor(nombre,idAutor,user,groupA);
				action = Constants.P_RENAME_AUTHOR;
			}
			else{
				if(authManager.isAutorized(Constants.P_RENAME_AUTHOR_OWN, user)){
					message = authorManager.renameAuthorOwn(nombre,idAutor,user,groupA);
					action = Constants.P_RENAME_AUTHOR_OWN;
					j.put("message", message);
				}
				else{
					message = "noPrivileges";
					action = Constants.P_RENAME_AUTHOR;
				}
			}
			if(message.equals("cambiado")){
				logManager.log(user.getId(), action, "Renombrar autor. Nuevo nombre: " + nombre + " en autor id = " + idAutor, Constants.P_OK);
			}
			else{
				logManager.log(user.getId(), action, "Renombrar autor. Nuevo nombre: " + nombre + " en autor id = " + idAutor + ". Fallido: mensaje = " + message, Constants.P_NOK);
				Author old = authorManager.getAuthor(idAutor);
				j.put("oldname", old.getNombre());
				j.put("id", old.getIdAuthor());
			}
			j.put("message", message);
			model.addObject("json", j);
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idAutor") == null &&
				request.getParameter("newNombre") == null;
	}
}
