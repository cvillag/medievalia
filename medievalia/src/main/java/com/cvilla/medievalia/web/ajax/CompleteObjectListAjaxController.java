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

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class CompleteObjectListAjaxController {
	
	private int actionInt = Constants.P_OBJECT_LIST_BY_TYPE;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private IObjectManager objectManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "completeObjectList.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/2-2-listaCompleta");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjetoDOM tipo = (TipoObjetoDOM) sesion.getAttribute("tipoObjeto");
		JSONObject j = new JSONObject();
		
		if((errorParam(request) && tipo == null) || groupA == null){
			return htmlManager.paramError(logManager, actionInt, user.getId());
		}
		else{
			if(authManager.isAutorized(actionInt, user)){
				if(errorParam(request)){
					j.put("message","noType");
					logManager.log(user.getId(), actionInt, "Fallo en listado de objetos. Parámetros incorrectos.", Constants.P_NOK);
				}
				else{
					List<InstanciaObjetoDOM> listag;
					String t2 = request.getParameter("tipo");
					if(t2 != null && t2.length()>0 && htmlManager.isNumeric(t2)){
						TipoObjetoDOM to = new TipoObjetoDOM();
						to.setTipoDOM(new Integer(t2));
						listag = objectManager.getObjetoDOMListByType(to);
					}
					else{
						listag = objectManager.getObjetoDOMListByType(tipo);
					}
					model.addObject("listaObjetos", listag);
					model.addObject("type",request.getParameter("type"));
					logManager.log(user.getId(), actionInt, "Visualización lista de instancias de objeto", Constants.P_OK);
					if(authManager.isAutorized(Constants.P_DELETE_OBJECT_INSTANCE, user)){
						model.addObject("permisoborrado","ok");
					}
					if(authManager.isAutorized(Constants.P_RENAME_OBJECT_INSTANCE, user)){
						model.addObject("permisoRenombrado", "ok");
					}
					if(authManager.isAutorized(Constants.P_MODIFY_OBJECT_INSTANCE, user)){
						model.addObject("permisoModificar","ok");
					}
				}
				logManager.log(user.getId(), actionInt, "Listado completo de objeto " + tipo.getNombreDOM() + " del grupo " + groupA.getName(), Constants.P_OK);
			}
			else{
				model = htmlManager.noPrivilegesA(user,logManager,actionInt,"Visualización de objetos no permitida (grupo: " + groupA.getName() + ")");
			}
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("type") == null;
	}
}
