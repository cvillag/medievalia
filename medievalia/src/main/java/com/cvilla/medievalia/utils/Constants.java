package com.cvilla.medievalia.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.ILogManager;

public class Constants {
	
	private static final String PASS_KEY = "28165A0B371ED2D9441B830D21A30887";
	public static final int P_LOGIN = 1;
	public static final int P_EDIT_PROFILE = 2;
	public static final int P_USER_LIST = 3;
	public static final int P_CREATE_USER = 4;
	public static final int P_DELETE_USER = 5;
	public static final int P_MODIFY_USER = 6;
	public static final int P_ACCESO_PORTAL = 7;
	public static final int P_LOGOUT = 8;
	public static final int P_GROUP_LIST = 9;
	public static final int P_GROUP_OWN = 10;
	public static final int P_DETAIL_OTHER_USER = 11;
	public static final int P_DETAIL_DIRECTOR_GROUPS_OTHER = 12;
	public static final int P_DETAIL_DIRECTOR_GROUPS_OWN = 13;
	public static final int P_DETAIL_TEACHER_GROUPS_OTHER = 14;
	public static final int P_DETAIL_TEACHER_GROUPS_OWN = 15;
	public static final int P_DETAIL_STUDENT_GROUPS_OWN = 16;
	public static final int P_DETAIL_STUDENT_GROUPS_OTHER = 17;
	public static final int P_GENERAL_LOG = 18;
	public static final int P_SELECT_ACTIVE_GROUP = 19;
	public static final int P_CREATE_GROUP  = 20;
	
	public static final int P_MODIFY_USER_OWN = 28;
	public static final int P_MODIFY_USER_PASS_OWN = 29;
	public static final int P_ADD_STUDENT_TO_GROUP = 30;
	public static final int P_ADD_TEACHER_TO_GROUP = 31;
	public static final int P_LIST_USERS_TO_ADD_GROUP = 32;
	public static final int P_PARTICIPANT_LIST = 33;
	public static final int P_REMOVE_STUDENT_TO_GROUP = 34;
	public static final int P_REMOVE_TEACHER_TO_GROUP = 35;
	
	public static final int P_OBJECT_TYPE_LIST = 36;
	public static final int P_VALIDATE_OBJECT_INSTANCE = 37;
	public static final int P_OBJECT_LIST_BY_TYPE = 38;
	public static final int P_VIEW_OBJECT_INSTANCE_DETAIL = 39;
	public static final int P_CREATE_OBJECT_INSTANCE = 40;
	public static final int P_DELETE_OBJECT_INSTANCE = 41;
	public static final int P_RENAME_OBJECT_INSTANCE = 42;
	public static final int P_MODIFY_OBJECT_INSTANCE = 43;
	public static final int P_VALIDATE_COMPLEX_ATTRIBUTE = 44;
	public static final int P_RENAME_OBJECT_INSTANCE_OWN = 45;
	public static final int P_DELETE_OBJECT_INSTANCE_OWN = 46;
	public static final int P_UNVALIDATED_OBJECT_LIST_BY_GROUP = 47;
	
	//TODO: Permisos a eliminar tras la refactorización
//	public static final int P_TOPIC_LIST = 21;
//	public static final int P_TOPIC_MANAGER = 22;
//	public static final int P_CREATE_TOPIC = 23;
//	public static final int P_TOPIC_DETAIL = 24;
//	public static final int P_CREATE_SUB_TOPIC = 25;
//	public static final int P_SUBTOPIC_LIST = 26;
//	public static final int P_RENAME_TOPIC = 27;
//	
//	public static final int P_DELETE_TOPIC = 36;
//	public static final int P_RENAME_SUBTOPIC = 37;
//	public static final int P_DELETE_STOPIC = 38;
//	public static final int P_VIEW_CHARGES = 39;
//	public static final int P_CREATE_CHARGE = 40;
//	public static final int P_RENAME_CHARGE = 41;
//	public static final int P_RENAME_CHARGE_OWN = 42;
//	public static final int P_DELETE_CHARGE = 43;
//	public static final int P_VALIDAR_CARGO = 44;
//	public static final int P_VIEW_OWN_CHARGES = 45;
//	public static final int P_DELETE_OWN_CHARGES = 46;
//	public static final int P_VIEW_CHARGE_STATISTICS = 47;
//	public static final int P_VIEW_OWN_CHARGE_STATISTICS = 48;
//	
//	public static final int P_VIEW_STUDIES = 49;
//	public static final int P_VALIDATE_STUDY = 50;
//	public static final int P_CREATE_STUDY = 51;
//	public static final int P_RENAME_STUDY = 52;
//	public static final int P_RENAME_STUDY_OWN = 53;
//	public static final int P_DELETE_STUDY = 54;
//	public static final int P_VIEW_OWN_STUDY = 55;
//	public static final int P_DELETE_OWN_STUDIES = 56;
//	public static final int P_VIEW_STUDIES_STATISTICS = 47;
//	public static final int P_VIEW_OWN_STUDIES_STATISTICS = 48;
//	
//	public static final int P_VIEW_PLACES = 57;
//	public static final int P_VALIDATE_PLACE = 58;
//	public static final int P_CREATE_PLACE = 59;
//	public static final int P_RENAME_PLACE = 60;
//	public static final int P_RENAME_PLACE_OWN = 61;
//	public static final int P_DELETE_PLACE = 62;
//	public static final int P_VIEW_OWN_PLACE = 63;
//	public static final int P_DELETE_OWN_PLACES = 64;
//	public static final int P_VIEW_PLACES_STATISTICS = 47;
//	public static final int P_VIEW_OWN_PLACES_STATISTICS = 48;
//	
//	public static final int P_VIEW_AUTHORS = 65;
//	public static final int P_VALIDATE_AUTHOR = 66;
//	public static final int P_CREATE_AUTHOR = 67;
//	public static final int P_RENAME_AUTHOR = 68;
//	public static final int P_RENAME_AUTHOR_OWN = 69;
//	public static final int P_DELETE_AUTHOR = 70;
//	public static final int P_VIEW_OWN_AUTHOR = 71;
//	public static final int P_DELETE_OWN_AUTHORS = 72;
//	public static final int P_VIEW_AUTHORS_STATISTICS = 47;
//	public static final int P_VIEW_OWN_AUTHORS_STATISTICS = 48;
//	
//	public static final int P_VIEW_CHARACTER_LIST = 73;
//	public static final int P_VALIDATE_CHARACTER = 74;
//	public static final int P_DELETE_CHARACTER = 75;
//	public static final int P_RENAME_CHARACTER = 76;
//	public static final int P_CREATE_CHARACTER = 77;
//	public static final int P_MODIFY_CHARACTER = 78;
//	
//	public static final int P_DELETE_OWN_CHARACTER = 79;
//	public static final int P_RENAME_CHARACTER_OWN = 80;
//	public static final int P_MODIFY_DATA_CHARACTER_OWN = 81;
//	public static final int P_VIEW_CHARGES_BY_CHARACTER = 82;
//	public static final int P_VALIDATE_CHARGE_CHARACTER = 83;
	
	//FIN PERMISOS A BORRAR
	
	public static final int P_OK = 1;
	public static final int P_NOK = 0;
	public static final int P_NOUSER = 0;
	
	public static final String M_DUPLICATED_USER = "p1-2.error.duplicateUser";
	public static final String M_CREATE_USER_OK = "p1-2.createok";
	public static final String M_NO_ROLE = "p1-2.error.norole";
	public static final String M_NO_PASS = "p1-2.error.nopass";
	public static final String M_NO_LNAME = "p1-2.error.nolname";
	public static final String M_NO_NAME = "p1-2.error.noname";
	public static final String M_PASS_NO_MATCH = "p1-2.error.passnomatch";
	
	public static final int ROLE_ADMIN = 1;
	public static final int ROLE_PROFESOR = 2;
	public static final int ROLE_ALUMNO = 3;
	public static final int ROLE_INACTIVO = 4;
	
	public static final boolean ORDER_ASC = true;
	public static final boolean ORDER_DESC = false;
	
	public static final int MIN_TEMA_NAME = 4;
	public static final int MIN_USER_NAME = 4;
	public static final int MIN_PERSONAGE_NAME = 3;
	public static final int MIN_PASS = 6;
	
	public static final int NUM_ACCEPTED = 2;
	public static final List<Integer> ROLES_ACCEPTED_IN_GROUP = Arrays.asList(2,3);
	public static final int OBJETO_NO_VALIDADO = 0;
	public static final int OBJETO_VALIDADO = 1;
	public static final int OBJETO_DENEGADO = 2;
	
	public static final int TIPO_ATRIBUTO_FECHA = 1;
	public static final int TIPO_ATRIBUTO_DOUBLE = 2;
	public static final int TIPO_ATRIBUTO_INT = 3;
	public static final int TIPO_ATRIBUTO_STRING = 4;
	public static final int TIPO_ATRIBUTO_TEXT = 5;
	
	public static final String TEXTO_VALIDACION_PROFESOR = "Creado ya validado";
	public static final String TEXTO_SIN_VALIDAR = "";
		
	public static String getKey(){
		return PASS_KEY;
	}
	
	public static boolean isAcceptedRoleInGroup(int role){
		return ROLES_ACCEPTED_IN_GROUP.contains(role);
	}
	
	public static  List<Header> getHeaders(int role, HttpServletRequest req){
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
			if(role == 2){
				lista.add(new Header("docencia","Docencia","",new ArrayList<Header>()));
				index++;
				if(ses.getAttribute("grupoActual") !=  null){
					lista.get(index).getSons().add(new Header("temas","Temas","topicManager.do",null));
				}
				lista.get(index).getSons().add(new Header("matriculacion","Alumnos", "student.do", null));
			}
			lista.add(new Header("investigacion","Investigación","",new ArrayList<Header>()));
			index++;
			lista.get(index).getSons().add(new Header("gestionContenido","Gestión de contenido", "contentManager.do",null));
			lista.get(index).getSons().add(new Header("temas","Temas", "topics.do",null));
		}
		lista.add(new Header("profile","Perfil","profile.do",null));
		index++;
		
		return lista;
	}
	
	public static ModelAndView processError(String message){
		ModelAndView model = new ModelAndView("5-2-error");
		model.addObject("mensaje2", message);
		return model;
	}
	
	public static ModelAndView noPrivileges(User user, ILogManager logManager, int action, String message, HttpServletRequest req){
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
	
	public static ModelAndView noPrivilegesA(User user, ILogManager logManager, int action, String message){
		if(user != null){
			logManager.log(user.getId(), action, message, Constants.P_NOK);
			ModelAndView model = new ModelAndView("5-2-errorAjax");
			String mensaje2 = "test.noPermiso";
			model.addObject("mensaje2", mensaje2);
			return model;
		}
		else{
			logManager.log(Constants.P_NOUSER, action, "Sesión posiblemente expirada (usuario nulo)", Constants.P_NOK);
			ModelAndView model = new ModelAndView("0-bienvenida");
			model.addObject("mensaje2", "test.noSesion");
			return model;
		}
	}
	
	public static ModelAndView noPrivilegesJ(User user, ILogManager logManager, int action, String message){
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
	
	public static ModelAndView paramError(ILogManager log,int idaction,int iduser){
		log.log(iduser, idaction, "Error de falta de parámetros", Constants.P_NOK);
		ModelAndView model = new ModelAndView("5-2-error");
		String mensaje2 = "test.noParam";
		model.addObject("mensaje2", mensaje2);
		return model;
	}
	
	public static String nullParameterString(HttpServletRequest request, String name, String defValue){
		String par = request.getParameter(name);
		if(par == null || par.equals("")){
			return defValue;
		}
		else{
			return par;
		}
	}
	
	public static int nullParameterInt(HttpServletRequest request, String name, int defValue){
		String par = request.getParameter(name);
		if(par != null && ! par.equals("")){
			return (new Integer(par)).intValue();
		}
		else{
			return defValue;
		}
	}
}
