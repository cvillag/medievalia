package com.cvilla.medievalia.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class LogoutController {

	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value = "logout.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView model = new ModelAndView("0-bienvenida");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		if(user != null){
			logManager.log(user.getId(), Constants.P_LOGOUT, "Logout desde " + request.getRemoteAddr(), Constants.P_OK);
		}
		else{
			logManager.log(Constants.P_NOUSER, Constants.P_LOGOUT, "Intento de Logout con sesión expirada desde " + request.getRemoteAddr(), Constants.P_NOK);
		}
		sesion.invalidate();

		return model;
	}
}
