package com.cvilla.medievalia.web;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.service.CosaManager;
import com.mysql.jdbc.Connection;

@Controller
public class WelcomeController {
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CosaManager cosaManager;
	
	@RequestMapping(value="/hello.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String error = "-";
		java.sql.Connection conexion;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch (Exception e){
			error += "NO COGE EL DRIVER!!";
			e.printStackTrace();
		}
		try {
			String res = "";
			conexion = DriverManager.getConnection ("jdbc:mysql://192.168.1.16/pruebaspring","carloss", "carloss");
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("select nombre from cosas where id = 5");
			while (rs.next()){
				res = rs.getString("nombre");
			}
			error += "De la BD cogemos: " + res;
		} catch (SQLException e) {
			error+= " Y no se conecta!";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView result = new ModelAndView("bienvenida");
        logger.info("--->WelcomeController");
        String Mensaje = "Sistema de apoyo a la investigación y docencia en investigación";
        //hora = cosaManager.getCosaName(5);
        result.addObject("mje", error);
        return result;
        
    }
}
