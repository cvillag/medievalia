package com.cvilla.medievalia.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

//import com.cvilla.medievalia.domain.User;
//import com.cvilla.medievalia.service.LoginManager;

@Controller
public class WelcomeController {
	protected final Log logger = LogFactory.getLog(getClass());
	//@Autowired
	//private LoginManager loginManager;
	@Autowired
	private ILogManager logManager;
	
	@RequestMapping(value="/hello.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView result = new ModelAndView("0-bienvenida");
		logManager.log(Constants.P_NOUSER, Constants.P_ACCESO_PORTAL, "Acceso a login desde " + request.getRemoteAddr(), Constants.P_OK);
        logger.info("--->WelcomeController");
        return result;
        		
    }
}
