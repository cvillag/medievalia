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
import com.cvilla.medievalia.domain.InstanciaAtributoSencillo;
import com.cvilla.medievalia.domain.InstanciaObjeto;
import com.cvilla.medievalia.domain.TipoAtributoComplejo;
import com.cvilla.medievalia.domain.TipoObjeto;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class StudentObjectListAjaxController {
	
	private int actionInt = Constants.P_UNVALIDATED_OBJECT_LIST_BY_GROUP_USER;
	
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
	
	@RequestMapping(value = "studentObjectListA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/2-2-listaAlumno");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjeto tipo = (TipoObjeto) sesion.getAttribute("tipoObjeto");
		JSONObject j = new JSONObject();
		
		if((errorParam(request) && tipo == null) || groupA == null){
			return htmlManager.paramError(logManager, actionInt, user.getId());
		}
		else{
			if(authManager.isAutorized(actionInt, user)){
				if(errorParam(request)){
					j.put("message","noType");
					logManager.log(user.getId(), actionInt, "Fallo en listado de objetos modificados por el usuario (alumno). Parámetros incorrectos.", Constants.P_NOK);
				}
				else{
					List<InstanciaObjeto> listag = objectManager.getStudentObjetoDOMList(tipo, user, groupA);
					model.addObject("listaObjetos", listag);
					model.addObject("type",request.getParameter("type"));
					
					List<TipoAtributoComplejo> lac = objectManager.getTiposAtributosCompleos(tipo);
					boolean btnModify = objectManager.hasSimpleAttributes(tipo) || lac.size()>0;
					if(btnModify && authManager.isAutorized(Constants.P_MODIFY_OBJECT_INSTANCE_OWN, user)){
						model.addObject("permisomodificar", "ok");
					}
					if(authManager.isAutorized(Constants.P_RENAME_OBJECT_INSTANCE_OWN, user)){
						model.addObject("permisorenombrar","ok");
					}
					if(authManager.isAutorized(Constants.P_DELETE_OBJECT_INSTANCE_OWN, user)){
						model.addObject("permisoborrado", "ok");
					}
					
					logManager.log(user.getId(), actionInt, "Listado de objetos modificados por el usuario (alumno) " + tipo.getNombreDOM() + " del grupo " + groupA.getName(), Constants.P_OK);
				}
			}
			else{
				model = htmlManager.noPrivilegesA(user,logManager,actionInt,"Visualización de listado de objetos modificados por el usuario (alumno) no permitida (grupo: " + groupA.getName() + ")");
			}
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("type") == null;
	}
}
