package com.cvilla.medievalia.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//import com.cvilla.medievalia.domain.User;
//import com.cvilla.medievalia.service.LoginManager;

@Controller
public class WelcomeController {
	protected final Log logger = LogFactory.getLog(getClass());
	//@Autowired
	//private LoginManager loginManager;
	
	@RequestMapping(value="/hello.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView result = new ModelAndView("bienvenida");
        logger.info("--->WelcomeController");
        String mensaje = "Sistema de apoyo a la investigación y docencia en investigación";
        //List<User> userList = loginManager.listar();
        result.addObject("mje", mensaje);
        //result.addObject("users", userList);
        return result;
        		
    }
}
