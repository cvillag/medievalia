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

import com.cvilla.medievalia.domain.InstanciaAtributoComplejo;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.InstanciaAtributoSencillo;
import com.cvilla.medievalia.domain.InstanciaObjeto;
import com.cvilla.medievalia.domain.TipoObjeto;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

@Controller 
public class ViewObjectInstanceComplexAttributesAjaxController {
	
	final static int actionId = Constants.P_VIEW_OBJECT_INSTANCE_DETAIL; 

	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IObjectManager objectManager;
	
	@Autowired
	private IHtmlManager htmlManager;
	
	@RequestMapping(value = "objectDetailComplexAttributes.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjeto tipo = (TipoObjeto) sesion.getAttribute("tipoObjeto");
		ModelAndView model;
		JSONObject j = new JSONObject();
		if(errorParam(request) || groupA == null || tipo == null){
			return htmlManager.paramError(logManager, actionId, user.getId());
		}
		else{
			int idInstancia = (new Integer(request.getParameter("idInstancia"))).intValue();
			int modo = (new Integer(request.getParameter("modo"))).intValue();
			int pag = (new Integer(request.getParameter("pag"))).intValue();
			int recarga = (new Integer(request.getParameter("recarga"))).intValue();
			j.put("recarga", recarga);
			if(authManager.isAutorized(actionId, user)){
				InstanciaObjeto obj = objectManager.getObjetoDOMUnvalidated(tipo, idInstancia, groupA, user);
				fillNames(obj, tipo);
				if(obj != null){
					model = new ModelAndView("ajax/empty");
					logManager.log(user.getId(), actionId, "Visualización de detalle de objeto a modificar ", Constants.P_OK);
					List<InstanciaAtributoComplejo> ac2 = objectManager.getAtributosCDisponiblesObjetoDOM(tipo,obj,pag);
					for(InstanciaAtributoComplejo ia : ac2){
						fillNames(ia);
					}
					j.put("disponibles", ac2);
				}
				else{
					return htmlManager.noPrivilegesJ(user, logManager, actionId, "El objeto seleccionado no existe");
				}
				List<InstanciaAtributoComplejo> actual = objectManager.getAtributosCPorTipo(obj,pag);
				int idRelacion = objectManager.getTypeRelacionForComplexAttribute(obj,pag);
				
				
				j.put("actual", actual);
				j.put("modo",modo);
				j.put("pag",pag);
				j.put("relacion", idRelacion);
				j.put("idUser", user.getId());
			}
			else{
				return htmlManager.noPrivilegesJ(user, logManager, actionId, "Visualización de detalle de objeto");
			}
		}
		model.addObject("json", j);
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idInstancia") == null || !htmlManager.isNumeric(request.getParameter("idInstancia")) ||
				request.getParameter("modo") == null || !htmlManager.isNumeric(request.getParameter("modo")) ||
				request.getParameter("pag") == null || !htmlManager.isNumeric(request.getParameter("pag")) ||
				request.getParameter("recarga") == null || !htmlManager.isNumeric(request.getParameter("recarga"));
	}
	
	private void fillNames(InstanciaObjeto o,TipoObjeto tipo){
		for(InstanciaAtributoComplejo iao : o.getAtributosComplejos()){
			fillNames(iao);
		}
	}
	
	private void fillNames(InstanciaAtributoComplejo iao){
		if(iao.getInstanciaHijo().getTipo().getTipoDOM() == Constants.OBJETO_CNC){
			InstanciaObjeto n2 = objectManager.getObjetoDOM(iao.getInstanciaHijo().getTipo(), iao.getInstanciaHijo().getNombre());
			String sub = "";
			if(n2 != null){
				for(InstanciaAtributoSencillo ias : n2.getAtributosSencillos()){
					if(ias.getIdAtributo() == Constants.OBJETO_ATT_CNC && ias.getValor() != null){
						sub = " ," + ((InstanciaObjeto)ias.getValor()).getNombre();
					}
				}
				iao.getInstanciaHijo().setNombre(iao.getInstanciaHijo().getNombre() + sub);
			}
		}
	}
}
