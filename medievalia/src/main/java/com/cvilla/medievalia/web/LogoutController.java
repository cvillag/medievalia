package com.cvilla.medievalia.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	
	@RequestMapping(value = "logout.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView model = new ModelAndView("0-bienvenida");
		
		HttpSession sesion = request.getSession();
		sesion.invalidate();
		
		return model;
	}
}
