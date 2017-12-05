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
import com.cvilla.medievalia.utils.SpecialDate;

@Controller
public class GetComplexAttributeAjaxController {
	
	private int actionInt = Constants.P_VIEW_OBJECT_INSTANCE_DETAIL;
	
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
	
	@RequestMapping(value = "getComplexAttribute.do")
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
				logManager.log(user.getId(), actionInt, "Fallo al consultar detalle de atributo complejo. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				int idInstPadre = (new Integer(request.getParameter("instp"))).intValue();
				int idTipoHijo = (new Integer(request.getParameter("tipoh"))).intValue();
				int idInstHijo = (new Integer(request.getParameter("insth"))).intValue();
				InstanciaAtributoComplejoDOM iac = objectManager.getComplexAttribute(tipo,idTipoHijo,idInstPadre,idInstHijo, groupA,  user);
				if(iac == null){
					iac = objectManager.getComplexAttributeNotVal(tipo,idTipoHijo,idInstPadre,idInstHijo, groupA,  user);
				}
				if(iac != null){
					logManager.log(user.getId(), actionInt, "Consulta de atributo complejo de instancia padre" + idInstPadre + " idObjeto " + tipo.getNombreDOM(), Constants.P_OK);
					j.put("message", "ok");
					j.put("idInstRel", iac.getInstanciaObjetoRelacion().getIdInstancia());
					j.put("conFecha", iac.getConFecha());
					j.put("instH", iac.getInstanciaHijo().getIdInstancia());
					j.put("tipoH", iac.getTipoHijo().getTipoDOM());
					j.put("instP", iac.getIdInstanciaPadre());
					if(iac.getFechaInicio() != null){
						j.put("diaIM", iac.getFechaInicio().getDia());
						j.put("mesIM", iac.getFechaInicio().getMes());
						j.put("anioIM", iac.getFechaInicio().getAnio());
						j.put("fechaINull", "0");
					}
					else{
						j.put("fechaINull", "1");
					}
					if(iac.getFechaFin() != null){
						j.put("diaFM", iac.getFechaFin().getDia());
						j.put("mesFM", iac.getFechaFin().getMes());
						j.put("anioFM", iac.getFechaFin().getAnio());
						j.put("fechaFNull", "0");
					}
					else{
						j.put("fechaFNull", "1");
					}
					j.put("conPaginaDoc", iac.getConPaginaDoc());
					j.put("paginaDoc", iac.getPaginaDoc());
				}
				else{
					logManager.log(user.getId(), actionInt, "Consulta de atributo complejo de instancia padre" + idInstPadre + " idObjeto " + tipo.getNombreDOM(), Constants.P_NOK);
					j.put("message", "nok");
				}
			}
			model.addObject("json", j);
		}
		else{
			model = Constants.noPrivilegesJ(user,logManager,actionInt,"Consulta de detalle de atributo complejo no permitida.");
		}
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("instp") == null &&
				request.getParameter("insth") == null &&
				request.getParameter("tipoh") == null;
	}
}
