package com.cvilla.medievalia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.service.CosaManager;

@Controller
public class AddCosaController {

	@Autowired
	private CosaManager cosaManager;

	@RequestMapping(value = "addCosa.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("cosas");
		String nombre = request.getParameter("nombre");
		cosaManager.addCosa(nombre);
		String nom = cosaManager.getCosaName(5);
		model.addObject("lcosas", cosaManager.listar());
		model.addObject("nom1",nom);
		return model;
	}

	public void setCosaManager(CosaManager cm) {
		this.cosaManager = cm;
	}

}
