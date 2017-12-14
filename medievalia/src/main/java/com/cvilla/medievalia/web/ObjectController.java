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

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.ListaRelaciones;

@Controller
public class ObjectController {

	private int actionInt = Constants.P_OBJECT_LIST_BY_TYPE;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IObjectManager objectManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "objectController.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model;
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjetoDOM tipo = (TipoObjetoDOM) sesion.getAttribute("tipoObjeto");
		
		
		if(authManager.isAutorized(actionInt, user)){
			if((errorParam(request) && tipo == null) || groupA == null){
				return htmlManager.paramError(logManager, actionInt, user.getId());
			}
			else{
				int idTipoObjeto = (new Integer(request.getParameter("idTipo"))).intValue();
				TipoObjetoDOM tipoNuevo = objectManager.getTipoObjetoDOM(idTipoObjeto);
				if(tipoNuevo == null){
					return htmlManager.paramError(logManager, actionInt, user.getId());
				}
				else{
					if(tipo == null || tipo.getTipoDOM() != tipoNuevo.getTipoDOM()){
						sesion.setAttribute("tipoObjeto", tipoNuevo);
					}
					model = new ModelAndView("2-2.listaObjetos");
					model.addObject("tipo", tipoNuevo);
					List<TipoAtributoComplejoDOM> ac = objectManager.getTiposAtributosCompleos(tipoNuevo);
					model.addObject("tipoAtributosC", ac);
					List<ListaRelaciones> listaBiblio = objectManager.getRelaciones(ac);
					model.addObject("listasRelacion", listaBiblio);
					
					logManager.log(user.getId(), actionInt, "Pantalla de gestión de " + tipoNuevo.getNombreDOM(), Constants.P_OK);
					model.addObject("headers",htmlManager.getHeaders(user.getUser_role(),request));
					List<String> scripts = new ArrayList<String>();
					scripts.add("js/2-2.js");
					model.addObject("scripts",scripts);
					if(authManager.isAutorized(Constants.P_VALIDATE_OBJECT_INSTANCE, user)){
						model.addObject("validar", "ok");
					}
				}
			}
		}
		else{
			model = htmlManager.noPrivileges(user,logManager,actionInt,"mensaje",request);
		}	
		return model;
	}
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idTipo") == null || !htmlManager.isNumeric(request.getParameter("idTipo"));
	}
	
}
