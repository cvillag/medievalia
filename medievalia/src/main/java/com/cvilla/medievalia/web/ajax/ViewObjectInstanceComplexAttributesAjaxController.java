package com.cvilla.medievalia.web.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.AtributoComplejoDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class ViewObjectInstanceComplexAttributesAjaxController {
	
	final static int actionId = Constants.P_VIEW_OBJECT_INSTANCE_DETAIL; 
	final static int actionId2 = Constants.P_MODIFY_OBJECT_INSTANCE;

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IObjectManager objectManager;
	
	@RequestMapping(value = "objectDetailComplexAttributes.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjetoDOM tipo = (TipoObjetoDOM) sesion.getAttribute("tipoObjeto");
		ModelAndView model;
		JSONObject j = new JSONObject();
		if(errorParam(request) || groupA == null || tipo == null){
			return Constants.paramError(logManager, actionId, user.getId());
		}
		else{
			int idInstancia = (new Integer(request.getParameter("idInstancia"))).intValue();
			int modo = (new Integer(request.getParameter("modo"))).intValue();
			int pag = (new Integer(request.getParameter("pag"))).intValue();
			if(authManager.isAutorized(actionId, user)){
				ObjetoDOM obj = objectManager.getObjetoDOM(tipo, idInstancia);
				if(obj != null){
					model = new ModelAndView("ajax/empty");
					logManager.log(user.getId(), actionId2, "Visualización de detalle de objeto a modificar ", Constants.P_OK);
					List<AtributoComplejoDOM> ac2 = objectManager.getAtributosCDisponiblesObjetoDOM(tipo,obj,pag);
					j.put("disponibles", ac2);
				}
				else{
					return Constants.paramError(logManager, actionId, user.getId());
				}
				List<AtributoComplejoDOM> actual = objectManager.getAtributosCPorTipo(obj,pag);
				j.put("actual", actual);
				j.put("modo",modo);
				j.put("pag",pag);
			}
			else{
				return Constants.noPrivilegesJ(user, logManager, actionId, "Visualización de detalle de objeto");
			}
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idInstancia") == null &&
				request.getParameter("modo") == null &&
				request.getParameter("pag") == null;
	}
}
