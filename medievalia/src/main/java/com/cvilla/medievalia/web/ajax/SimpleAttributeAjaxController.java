package com.cvilla.medievalia.web.ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.InstanciaAtributoComplejoDOM;
import com.cvilla.medievalia.domain.InstanciaAtributoSencilloDOM;
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
public class SimpleAttributeAjaxController {
	
	private int actionInt = Constants.P_MODIFY_OBJECT_INSTANCE;
	
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
	
	@RequestMapping(value = "simpleAttributes.do")
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
				logManager.log(user.getId(), actionInt, "Fallo en modificación de instancia de objeto. Parámetros o sesión incorrectos.", Constants.P_NOK);
			}
			else{
				int id = (new Integer(request.getParameter("idInstanciaObjeto"))).intValue();
				InstanciaObjetoDOM obj = objectManager.getObjetoDOM(tipo, id);
				if(obj != null){
					String m = cambiaAtributosS(obj,request); 
					if(m.equals("ok")){
						String message = objectManager.modifySimpleAttribute(obj);
						j.put("message", message);
					}
					else{
						j.put("message", m);
					}
				}
				else{
					j.put("message", "noObject");
				}
				
			}
			model.addObject("json", j);
		}
		else{
			model = Constants.noPrivilegesJ(user,logManager,actionInt,"Modificación de objeto no permitida ");
		}
		return model;
	}
	
	private String cambiaAtributosS(InstanciaObjetoDOM obj, HttpServletRequest request) {
		for(InstanciaAtributoSencilloDOM as : obj.getAtributosSencillos()){
			int idAt = as.getIdAtributo();
			int tipoA = as.getTipoAtributo();
			int pos = numAtributo(obj, idAt);
			if(pos < 0){
				return "errorAtributos";
			}
			if(tipoA == Constants.TIPO_ATRIBUTO_FECHA){
				SpecialDate dat = new SpecialDate();
				dat.setAnio(new Integer(request.getParameter("anio"+idAt)));
				dat.setMes(new Integer(request.getParameter("mes"+idAt)));
				dat.setDia(new Integer(request.getParameter("dia"+idAt)));
				if(Fechas.fechaIncorrecta(dat.getDia(), dat.getMes(), dat.getAnio())){
					return "errorAtributos";
				}
				else{
					obj.getAtributosSencillos().get(pos).setValor(dat);
				}
			}
			else if(tipoA == Constants.TIPO_ATRIBUTO_DOUBLE){
				Double dat = (new Double(request.getParameter("double"+idAt)));
				obj.getAtributosSencillos().get(pos).setValor(dat);
			}
			else if(tipoA == Constants.TIPO_ATRIBUTO_INT){
				Integer dat = (new Integer(request.getParameter("int"+idAt)));
				obj.getAtributosSencillos().get(pos).setValor(dat);
			}
			else if(tipoA == Constants.TIPO_ATRIBUTO_STRING){
				String dat = request.getParameter("string"+idAt);
				obj.getAtributosSencillos().get(pos).setValor(dat);
			}
			else if(tipoA == Constants.TIPO_ATRIBUTO_TEXT){
				String dat = request.getParameter("text"+idAt);
				obj.getAtributosSencillos().get(pos).setValor(dat);
			}
		}
		return "ok";
	}
	
	private int numAtributo(InstanciaObjetoDOM o, int idAtributo){
		int i = 0;
		while(i < o.getAtributosSencillos().size()){
			if(idAtributo == o.getAtributosSencillos().get(i).getIdAtributo()){
				return i;
			}
			else{
				i++;
			}
		}
		return -1;
	}

	private boolean errorParam(HttpServletRequest request){
		return request.getParameter("idInstanciaObjeto") == null;
	}
}