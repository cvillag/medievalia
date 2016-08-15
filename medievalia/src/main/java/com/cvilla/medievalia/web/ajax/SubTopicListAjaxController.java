package com.cvilla.medievalia.web.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.SubTema;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

@Controller
public class SubTopicListAjaxController {
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private ILogManager logManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	private ITemaManager temaManager;
	
	@RequestMapping(value = "subtopicListA.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("ajax/2-6-listaSTemas");
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		Group groupA = (Group) sesion.getAttribute("grupoActual");
		
		if(authManager.isAutorized(Constants.P_SUBTOPIC_LIST, user)){
			if(errorParam(request)){
				logManager.log(user.getId(), Constants.P_SUBTOPIC_LIST, "Visualización de subtemas (grupo: " + groupA.getName() + "). Error de parámetros", Constants.P_NOK);
				model.addObject("message", "noParam");
			}
			else{
				int idTema = (new Integer(request.getParameter("idTema"))).intValue();
				List<SubTema> listag = temaManager.getSubTemaGrupoByTema(groupA,idTema);
				model.addObject("listaSubTemas", listag);
				logManager.log(user.getId(), Constants.P_SUBTOPIC_LIST, "Visualización de temas (grupo: " + groupA.getName() + ")", Constants.P_OK);
			}
		}
		else{
			model = Constants.noPrivilegesA(user,logManager,Constants.P_TOPIC_LIST,"Visualización de temas no permitida (grupo: " + groupA.getName() + ")");
		}
		
		return model;
	}
	
	private boolean errorParam(HttpServletRequest request){
			return request.getParameter("idTema") == null;
	}
}
