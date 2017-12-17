package com.cvilla.medievalia.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final String PASS_KEY = "28165A0B371ED2D9441B830D21A30887";
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
	public static final int P_MODIFY_OBJECT_INSTANCE_OWN = 48;
	
	public static final int P_UNVALIDATED_OBJECT_LIST_BY_GROUP_USER = 49;
	public static final int P_SET_OBJECT_TEXT_READED = 50;
	public static final int P_SET_COMPLEX_ATTRIBUTE_TEXT_READED = 51;
	
	public static final int P_VIEW_AUTHORS_STATISTICS_PER_TYPE = 52;
	public static final int P_VIEW_OWN_AUTHORS_STATISTICS_PER_TYPE = 53;
	
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
	public static final int TIPO_ATRIBUTO_OBJECT = 6;
	
	public static final String TEXTO_VALIDACION_PROFESOR = "Creado ya validado";
	public static final String TEXTO_SIN_VALIDAR = "";
	public static final int TEXTO_LEIDO = 1;
	public static final int TEXTO_NO_LEIDO = 0;
		
	public static String getKey(){
		return PASS_KEY;
	}
	
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public static boolean isAcceptedRoleInGroup(int role){
		return Constants.ROLES_ACCEPTED_IN_GROUP.contains(role);
	}
}
