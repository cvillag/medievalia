package com.cvilla.medievalia.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.service.ILogManager;

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
	
	public static final int P_OK = 1;
	public static final int P_NOK = 0;
	public static final int P_NOUSER = 0;
	
	public static final String M_DUPLICATED_USER = "p1-2.error.duplicateUser";
	public static final String M_CREATE_USER_OK = "p1-2.createok";
	public static final String M_NO_ROLE = "p1-2.error.norole";
	public static final String M_NO_PASS = "p1-2.error.nopass";
	public static final String M_NO_LNAME = "p1-2.error.nolname";
	public static final String M_NO_NAME = "p1-2.error.noname";
	
	
	public static String getKey(){
		return PASS_KEY;
	}
	
	public static  List<Header> getHeaders(int role){
		ArrayList<Header> lista = new ArrayList<Header>();
		int index=-1;
		if(role == 1){
			lista.add(new Header("admin","Administración","",new ArrayList<Header>()));
			index++;
			lista.get(index).getSons().add(new Header("users","Usuarios","users.do",null));
			lista.get(index).getSons().add(new Header("groups", "Grupos", "groups.do",null));
			lista.get(index).getSons().add(new Header("profile","Perfil","profile.do",null));
			lista.add(new Header("actions", "Acciones", "actions.do", null));
			index++;
		}
		else{
			if(role == 2){
				lista.add(new Header("docencia","Docencia","",new ArrayList<Header>()));
				index++;
				lista.get(index).getSons().add(new Header("revisiones","Revisiones","review.do",null));
				lista.get(index).getSons().add(new Header("matriculacion","Alumnos", "student.do", null));
			}
			lista.add(new Header("investigacion","Investigación","",new ArrayList<Header>()));
			index++;
			lista.get(index).getSons().add(new Header("gestionContenido","Gestión de contenido", "contentManager",null));
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
	
	public static ModelAndView noPrivileges(){
		//FIXME Mensaje temporal. La página ha de ser otra
		ModelAndView model = new ModelAndView("0-bienvenida");
		String mensaje2 = "test.noSesion";
		model.addObject("mensaje2", mensaje2);
		return model;
	}
	
	public static ModelAndView paramError(ILogManager log,int idaction,int iduser){
		//FIXME Mensaje temporal. La página ha de ser otra
		log.log(iduser, idaction, "Error de falta de parámetros", Constants.P_NOK);
		ModelAndView model = new ModelAndView("0-bienvenida");
		String mensaje2 = "test.noParam";
		model.addObject("mensaje2", mensaje2);
		return model;
	}

}
