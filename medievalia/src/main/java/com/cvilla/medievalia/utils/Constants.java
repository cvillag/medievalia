package com.cvilla.medievalia.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	private static final String PASS_KEY = "28165A0B371ED2D9441B830D21A30887";
	
	public static String getKey(){
		return PASS_KEY;
	}
	
	public static  List<Header> getHeaders(int role){
		//FIXME: dejar un solo header, y lo de más dentro
		ArrayList<Header> lista = new ArrayList<Header>();
		int index=-1;
		//lista.add(new Header("index","inicio","inicio.do",null));
		//index++;
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

}
