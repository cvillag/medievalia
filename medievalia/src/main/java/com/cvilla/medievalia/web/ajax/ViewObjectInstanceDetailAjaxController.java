package com.cvilla.medievalia.web.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.InstanciaAtributoComplejoDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.LoginManager;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class ViewObjectInstanceDetailAjaxController {
	
	final static int actionId = Constants.P_VIEW_OBJECT_INSTANCE_DETAIL;
	final static int actionId2 = Constants.P_MODIFY_OBJECT_INSTANCE;
	final static int actionId3 = Constants.P_VALIDATE_OBJECT_INSTANCE;

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IObjectManager objectManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@RequestMapping(value = "objectDetail.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjetoDOM tipo = (TipoObjetoDOM) sesion.getAttribute("tipoObjeto");
		ModelAndView model;
//		JSONObject j = new JSONObject();
		String message;
		if(authManager.isAutorized(actionId, user) || authManager.isAutorized(actionId3, user)){
			if(errorParam(request) || groupA == null || tipo == null){
				return Constants.paramError(logManager, actionId, user.getId());
			}
			else{
				int idInstancia = (new Integer(request.getParameter("idInstancia"))).intValue();
				int modo = (new Integer(request.getParameter("modo"))).intValue();
				int validado = (new Integer(request.getParameter("val")).intValue());
				message = "ok";
				InstanciaObjetoDOM obj;
				if(validado == 1){
					obj= objectManager.getObjetoDOM(tipo, idInstancia);
				}
				else{
					obj= objectManager.getObjetoDOMUnvalidated(tipo, idInstancia,groupA,user);
				}
				//Lista de tipoAtributoComplejo para crear las pestañas del modal
				List<TipoAtributoComplejoDOM> ac = objectManager.getTiposAtributosCompleos(tipo);
				Map<Integer, Integer> badges = objectManager.getBadgesFromObject(obj);
				if(modo == 1){
					if(validado == 1){
						model = new ModelAndView("ajax/2-2-detalleObjeto");
					}
					else{
						if(authManager.isAutorized(actionId3, user)){
							model = new ModelAndView("ajax/2-2-validaObjetoProfe");
						}
						else{
							return Constants.noPrivilegesA(user, logManager, actionId, "Visualización de detalle de objeto no validado");
						}
					}
					logManager.log(user.getId(), actionId, "Visualización de detalle de objeto ", Constants.P_OK);
				}
				else if(modo == 2){
					model = new ModelAndView("ajax/2-2-modificaObjeto");
					logManager.log(user.getId(), actionId2, "Visualización de detalle de objeto a modificar ", Constants.P_OK);
				}
				else if(modo == 3){
					model = new ModelAndView("ajax/2-2-validaObjetoProfe");
					logManager.log(user.getId(), actionId3, "Visualización de detalle de objeto a validar ", Constants.P_OK);
				}
				else{
					return Constants.paramError(logManager, actionId, user.getId());
				}
				model.addObject("users",loginManager.listar());
				model.addObject("badges",badges);
				model.addObject("modo",modo);
				model.addObject("object", obj);
				model.addObject("tatributoc",ac);
				
			}
		model.addObject("message", message);
		}
		else{
			return Constants.noPrivilegesA(user, logManager, actionId, "Visualización de detalle de objeto");
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idInstancia") == null &&
				request.getParameter("modo") == null &&
				request.getParameter("val") == null;
	}
}
