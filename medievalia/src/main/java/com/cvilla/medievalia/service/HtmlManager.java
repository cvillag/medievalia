package com.cvilla.medievalia.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.Header;

public class HtmlManager implements IHtmlManager {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IGroupManager groupManager;
		
	public String getKey(){
		return Constants.PASS_KEY;
	}
	
	public   List<Header> getHeaders(int role, HttpServletRequest req){
		HttpSession ses = req.getSession();
		ArrayList<Header> lista = new ArrayList<Header>();
		int index=-1;
		if(role == 1){
			lista.add(new Header("admin","Administración","",new ArrayList<Header>()));
			index++;
			lista.get(index).getSons().add(new Header("users","Usuarios","users.do",null));
			lista.get(index).getSons().add(new Header("groups", "Grupos", "groups.do",null));
			lista.get(index).getSons().add(new Header("logs","Logs","generalLog.do",null));
			lista.add(new Header("actions", "Acciones", "actions.do", null));
			index++;
		}
		else{
			if(role == 2 || role == 3){
				lista.add(new Header("groups","Grupos","",new ArrayList<Header>()));
				index++;
				Group g = (Group) ses.getAttribute("grupoActual");
				User user = (User) ses.getAttribute("user");
				if(g !=  null){
					lista.get(index).getSons().add(new Header("actual","Actual","contentManager.do?idGroup=" + g.getIdGrupo(),null));
				}
				List<Group> gd = groupManager.getListByDirector(user, user);
				if(gd != null && gd.size() > 0){
					
					List<Header> dir = new ArrayList<Header>();
					for(Group g1 : gd){
						dir.add(new Header(g1.getName(),g1.getName(),"contentManager.do?idGroup=" + g1.getIdGrupo(),null));
					}
					lista.get(index).getSons().add(new Header("dirigidos","Dirigidos", "", dir));
				}
				if(role == 2){
					List<Teachers> gt = groupManager.getListByTeacher(user,user);
					if(gt != null && gt.size() > 0){
						
						List<Header> t = new ArrayList<Header>();
						for(Teachers g1 : gt){
							t.add(new Header(g1.getName(),g1.getName(),"contentManager.do?idGroup=" + g1.getIdGroup(),null));
						}
						lista.get(index).getSons().add(new Header("profesor","Como profesor", "", t));
					}
				}
				if(role == 3){
					List<Students> gs = groupManager.getListByStudent(user, user);
					if(gs != null && gs.size() > 0){
						
						List<Header> s = new ArrayList<Header>();
						for(Students g1 : gs){
							s.add(new Header(g1.getGroupName(),g1.getGroupName(),"contentManager.do?idGroup=" + g1.getIdGroup(),null));
						}
						lista.get(index).getSons().add(new Header("alumno","Como alumno", "", s));
					}
				}
			}
		}
		lista.add(new Header("profile","Perfil","profile.do",null));
		index++;
		
		return lista;
	}
	
	public  ModelAndView processError(String message){
		ModelAndView model = new ModelAndView("5-2-error");
		model.addObject("mensaje2", message);
		return model;
	}
	
	public  ModelAndView noPrivileges(User user, ILogManager logManager, int action, String message, HttpServletRequest req){
		if(user != null){
			logManager.log(user.getId(), action, message, Constants.P_NOK);
			ModelAndView model = new ModelAndView("5-2-error");
			String mensaje2 = "test.noPermiso";
			model.addObject("mensaje2", mensaje2);
			model.addObject("headers",getHeaders(user.getUser_role(),req));
			return model;
		}
		else{
			logManager.log(Constants.P_NOUSER, action, "Sesión posiblemente expirada (usuario nulo)", Constants.P_NOK);
			ModelAndView model = new ModelAndView("0-bienvenida");
			model.addObject("mensaje2", "test.noSesion");
			return model;
		}
	}
	
	public  ModelAndView noPrivilegesA(User user, ILogManager logManager, int action, String message){
		if(user != null){
			logManager.log(user.getId(), action, message, Constants.P_NOK);
			ModelAndView model = new ModelAndView("5-2-errorAjax");
			String mensaje2 = "test.noPermiso";
			model.addObject("mensaje2", mensaje2);
			return model;
		}
		else{
			logManager.log(Constants.P_NOUSER, action, "Sesión posiblemente expirada (usuario nulo)", Constants.P_NOK);
			ModelAndView model = new ModelAndView("5-2-errorAjax");
			model.addObject("mensaje2", "test.noSesion");
			return model;
		}
	}
	
	public  ModelAndView noPrivilegesJ(User user, ILogManager logManager, int action, String message){
		ModelAndView model = new ModelAndView("ajax/empty");
		JSONObject j = new JSONObject();
		String m;
		if(user != null){
			logManager.log(user.getId(), action, message, Constants.P_NOK);
			m = "sinPrivilegios";
		}
		else{
			logManager.log(Constants.P_NOUSER, action, "Sesión posiblemente expirada (usuario nulo)", Constants.P_NOK);
			m = "sinSesion";
		}
		j.put("message", m);
		model.addObject("json", j);
		return model;
	}
	
	public  ModelAndView paramError(ILogManager log,int idaction,int iduser){
		log.log(iduser, idaction, "Error de falta de parámetros", Constants.P_NOK);
		ModelAndView model = new ModelAndView("5-2-error");
		String mensaje2 = "test.noParam";
		model.addObject("mensaje2", mensaje2);
		return model;
	}
	
	public  String nullParameterString(HttpServletRequest request, String name, String defValue){
		String par = request.getParameter(name);
		if(par == null || par.equals("")){
			return defValue;
		}
		else{
			return par;
		}
	}
	
	public  int nullParameterInt(HttpServletRequest request, String name, int defValue){
		String par = request.getParameter(name);
		if(par != null && ! par.equals("")){
			return (new Integer(par)).intValue();
		}
		else{
			return defValue;
		}
	}
	
	public  boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
