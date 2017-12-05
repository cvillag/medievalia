package com.cvilla.medievalia.web.ajax;

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
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.Fechas;
import com.cvilla.medievalia.utils.SpecialDate;

@Controller
public class UpdateComplexAttributeAjaxController {
	
	private int actionInt = Constants.P_MODIFY_OBJECT_INSTANCE;
	private int actionInt2 = Constants.P_VALIDATE_COMPLEX_ATTRIBUTE;
	
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
	
	@RequestMapping(value = "updateRelation.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/empty");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		TipoObjetoDOM tipo = (TipoObjetoDOM) sesion.getAttribute("tipoObjeto");
		JSONObject j = new JSONObject();
		
		if(authManager.isAutorized(actionInt, user)){
			if((errorParam(request) && tipo == null) || groupA == null){
				j.put("message","noType");
				logManager.log(user.getId(), actionInt, "Fallo al actualizar atributo complejo a instancia de objeto. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				int idInstPadre = (new Integer(request.getParameter("instanciaP"))).intValue();
				int idTipoAttr = (new Integer(request.getParameter("tipoH"))).intValue();
				int idInstHijo = (new Integer(request.getParameter("instanciaH"))).intValue();
				int selRel = (new Integer(request.getParameter("select"))).intValue();
				int confecha = (new Integer(request.getParameter("confecha"))).intValue();
				SpecialDate inicio = null;
				SpecialDate fin = null;
				int paginaDoc = 0;
				if(confecha == 1){
					inicio = Fechas.getDate(request,"i");
					fin = Fechas.getDate(request,"f");
				}
				if(objectManager.isConPag(tipo.getTipoDOM(), idTipoAttr)){
					paginaDoc = (new Integer(request.getParameter("paginaDoc"))).intValue();
				}
				int val;
				if(authManager.isAutorized(actionInt2, user)){
					val = Constants.OBJETO_VALIDADO;
				}
				else{
					val = Constants.OBJETO_NO_VALIDADO;
				}
				String message = objectManager.updateObjetoDOMAttributeByType(idInstPadre, idInstHijo, tipo, idTipoAttr, val, user, groupA,selRel,inicio,fin,paginaDoc);
				logManager.log(user.getId(), actionInt, "Actualización de atributo complejo de instancia padre" + idInstPadre + " idObjeto " + tipo.getNombreDOM(), Constants.P_OK);
				j.put("message", message);
				j.put("pag", idTipoAttr);
			}
			model.addObject("json", j);
		}
		else{
			model = Constants.noPrivilegesJ(user,logManager,actionInt,"Actualización de atributo complejo no permitida ");
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("select") == null || !Constants.isNumeric(request.getParameter("select")) ||
				request.getParameter("confecha") == null || !Constants.isNumeric(request.getParameter("confecha")) ||
				request.getParameter("instanciaH") == null || !Constants.isNumeric(request.getParameter("instanciaH")) ||
				request.getParameter("tipoH") == null || !Constants.isNumeric(request.getParameter("tipoH")) ||
				request.getParameter("instanciaP") == null || !Constants.isNumeric(request.getParameter("instanciaP"));
	}
	

}
