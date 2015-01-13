package com.cvilla.medievalia.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.service.CosaManager;
import com.cvilla.medievalia.utils.Header;

@Controller
public class InicioController {

	@Autowired
	private CosaManager cosaManager;

	@RequestMapping(value = "inicio.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("inicio");
		String nombre = request.getParameter("nombre");
		cosaManager.addCosa(nombre);
		String nom = cosaManager.getCosaName(5);
		model.addObject("headers", getHeaders());
		model.addObject("lcosas", cosaManager.listar());
		model.addObject("nom1",nom);
		return model;
	}

	public void setCosaManager(CosaManager cm) {
		this.cosaManager = cm;
	}
	
	private List<Header> getHeaders(){
		ArrayList<Header> lista = new ArrayList<Header>();
		lista.add(new Header("admin","Administración","",new ArrayList<Header>()));
		lista.get(0).getSons().add(new Header("users","Usuarios","users.do",null));
		lista.get(0).getSons().add(new Header("groups", "Grupos", "groups.do",null));
		lista.add(new Header("actions", "Acciones", "actions.do", null));
		return lista;
	}

}
