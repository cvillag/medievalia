package com.cvilla.medievalia.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Role;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.IAutorizationManager;
import com.cvilla.medievalia.service.ILogManager;
import com.cvilla.medievalia.service.ILoginManager;
import com.cvilla.medievalia.service.IRoleManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class ModifyUserController2 {

	@Autowired
	private ILoginManager userManager;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value = "modifyUserAction.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(errorParam(request)){
			model = Constants.paramError(logManager,user.getId(),Constants.P_MODIFY_USER);
			model.addObject("headers",Constants.getHeaders(user.getUser_role()));
			return model;
		}
		else{
			if(!request.getParameter("id").equals(sesion.getAttribute("modifyUserId"))){
				model = Constants.processError("p5-2.errorPeticion");
				model.addObject("headers",Constants.getHeaders(user.getUser_role()));
			}
			else{
				if(authManager.isAutorized(Constants.P_MODIFY_USER, user)){
					String name = request.getParameter("name");
					String lname = request.getParameter("longname");
					String role = request.getParameter("role");
					String pass = request.getParameter("pass");
					String pass2 = request.getParameter("pass2");
					int iden = (new Integer(request.getParameter("id"))).intValue();
					User u = userManager.getUser(iden);
					
					String message = userManager.modifyUser(name, lname,  pass, pass2, role, u);
					if(message.equals("p1.3.modifyok")){
						logManager.log(user.getId(), Constants.P_MODIFY_USER, "Modificación de usuario con éxito. ID: "
								+ u.getId()
								+ ". Nombre: "
								+ u.getUser_name()
								, Constants.P_OK);
						model = new ModelAndView("1-3-listausuarios");
						ArrayList<User> users = (ArrayList<User>) userManager.listar();
						ArrayList<Role> roles = (ArrayList<Role>) roleManager.getRoleList();
						model.addObject("message", message);
						model.addObject("users", users);
						model.addObject("roles", roles);
						List<String> scripts = new ArrayList<String>();
						scripts.add("js/1-3.js");
						model.addObject("scripts",scripts);
					}
					else{
						logManager.log(user.getId(), Constants.P_MODIFY_USER, "Modificación de usuario con fallida por error en datos. ID: "
								+ u.getId()
								+ ". Nombre: "
								+ u.getUser_name()
								, Constants.P_NOK);
						
						model = new ModelAndView("1-3.1-modificaUsuarios");
						model.addObject("targetUser", u);
						List<Role> roles = roleManager.getRoleList();
						model.addObject("roles",roles);
						model.addObject("message", message);
					}
					model.addObject("headers",Constants.getHeaders(user.getUser_role()));

				}
				else{
					String id = request.getParameter("id");
					if(id == null)
						id = "nulo";
					model = Constants.noPrivileges(user,logManager,Constants.P_MODIFY_USER,"Intento de modificación de usuario no permitida. ID: "
							+ id
							+ ". Nombre: "
							+ request.getParameter("name"));
				}
			}
			return model;
		}
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("name") == null 
				|| request.getParameter("longname") == null
				|| request.getParameter("role") == null
				|| request.getParameter("pass") == null
				|| request.getParameter("pass2") == null
				|| request.getParameter("id") == null;
	}
}
