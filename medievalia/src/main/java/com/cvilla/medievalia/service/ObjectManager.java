package com.cvilla.medievalia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IObjetoDAO;
import com.cvilla.medievalia.dao.intfc.IUserDAO;
import com.cvilla.medievalia.domain.InstanciaAtributoComplejoDOM;
import com.cvilla.medievalia.domain.InstanciaAtributoSencilloDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;

public class ObjectManager implements IObjectManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IAutorizationManager authManager;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private IObjetoDAO objetoDAO;

	@Autowired
	private ILoginManager loginManager;
	
	@Autowired
	public IObjetoDAO getObjetoDAO() {
		return objetoDAO;
	}
	
	@Autowired
	public void setObjetoDAO(IObjetoDAO objetoDAO) {
		this.objetoDAO = objetoDAO;
	}

	public List<TipoObjetoDOM> getTiposObjetosDOM() {
		return objetoDAO.getObjectTypeList();
	}
	
	public TipoObjetoDOM getTipoObjetoDOM(int idType) {
		return objetoDAO.getObjectType(idType);
	}
	
	public List<TipoObjetoDOM> getAtributosCObjetoDOM(TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<InstanciaAtributoSencilloDOM> getAtributosSObjetoDOM(TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public InstanciaObjetoDOM getObjetoDOM(TipoObjetoDOM tipoObj, int idInstancia) {
		InstanciaObjetoDOM o = objetoDAO.getObjectInstance(tipoObj,idInstancia);
		if(o != null){
			o.setAtributosSencillos(objetoDAO.getAtributosSencillos(tipoObj,idInstancia));
			o.setAtributosComplejos(objetoDAO.getAtributosComplejos(tipoObj,idInstancia));
		}
		return o;
	}

	public List<InstanciaObjetoDOM> getObjetoDOMListByType(TipoObjetoDOM tipo) {
		return objetoDAO.getObjectListByTipe(tipo);
	}

	public String addObjetoDOM(TipoObjetoDOM tipo, InstanciaObjetoDOM o, Group groupA, User user) {
		o.setCreador(new User(user.getId()));
		o.setGrupo(groupA.getIdGrupo());
		o.setTipo(tipo);
		if(authManager.isAutorized(Constants.P_VALIDATE_OBJECT_INSTANCE, user)){
			o.setValidado(Constants.OBJETO_VALIDADO);
			o.setTextoValidacion(Constants.TEXTO_VALIDACION_PROFESOR);
			o.setTextoLeido(Constants.TEXTO_LEIDO);
			
		}
		else{
			o.setValidado(Constants.OBJETO_NO_VALIDADO);
			o.setTextoValidacion(Constants.TEXTO_SIN_VALIDAR);
			o.setTextoLeido(Constants.TEXTO_LEIDO);
		}
		return objetoDAO.createObjectInstance(o);
	}

	public String renameObjetoDOM(TipoObjetoDOM tipo, String nombre, int id, User user, Group groupA) {
		InstanciaObjetoDOM obj = objetoDAO.getObjectInstance(tipo, id);
		if(obj == null){
			obj = objetoDAO.getObjectInstanceNotVal(tipo, id);
			if(obj == null){
				return "noObject";
			}
		}
		if(nombre.length() >= Constants.MIN_PERSONAGE_NAME){
			InstanciaObjetoDOM o2 = objetoDAO.getObjectByName(tipo,nombre);
			if(o2 == null){
				return objetoDAO.renameObject(tipo,id,nombre);
			}
			else{
				return "nameRepeated";
			}
		}
		else{
			return "noLength";
		}
	}

	public String renameObjetoDOMOwn(TipoObjetoDOM tipo, String nombre, int id,	User user, Group groupA) {
		InstanciaObjetoDOM obj = objetoDAO.getObjectInstance(tipo, id);
		if(obj == null){
			obj = objetoDAO.getObjectInstanceNotVal(tipo, id);
			if(obj == null){
				return "noObject";
			}
		}
		if(obj.getCreador().getId() == user.getId()){
			if(nombre.length() >= Constants.MIN_PERSONAGE_NAME){
				InstanciaObjetoDOM o = objetoDAO.getObjectByName(tipo,nombre);
				if(o == null){
					return objetoDAO.renameObject(tipo,id,nombre);
				}
				else{
					return "nameRepeated";
				}
			}
			else{
				return "noLength";
			}
		}
		else{
			return "noOwner";
		}
	}

	public String deleteObjetoDOM(InstanciaObjetoDOM obj, User user, Group groupA){
		return objetoDAO.deleteObjetoDOM(obj);
	}

	public List<InstanciaObjetoDOM> getStudentObjetoDOMList(TipoObjetoDOM tipo, User user, Group groupA) {
		List<InstanciaObjetoDOM> lista = objetoDAO.getStudentObjetoDOMList(tipo,groupA,user);
		lista = fillUsers(lista);
		return lista;
	}

	public String deleteObjetoDOMOwn(InstanciaObjetoDOM obj, User user,	Group groupA) {
		if(obj.getCreador().getId() == user.getId()){
			if(obj.getGrupo() == groupA.getIdGrupo()){
				return objetoDAO.deleteObjetoDOM(obj);
			}
			else{
				return "noGroup";
			}
		}
		else{
			return "noOwner";
		}
	}

	public List<InstanciaObjetoDOM> getTeachersObjetoDOMList(TipoObjetoDOM tipo, User user, Group groupA) {
		if(groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			List<InstanciaObjetoDOM> lista = objetoDAO.getTeachersObjetoDOMList(tipo,groupA);
			lista = fillUsers(lista);
			return lista;
		}
		else{
			return null;
		}
	}
	

	public String validateObjetoDOM(TipoObjetoDOM tipo, int id, User user, Group group, int val, String text) {
		InstanciaObjetoDOM obj = objetoDAO.getObjectInstanceNotVal(tipo, id);
		if(obj == null){
			return "noObject";
		}
		else{
			if(!groupManager.isTeacherOrDirector(user, obj.getGrupo())){
				return "noGroup";
			}
			else{
				if(val == Constants.OBJETO_NO_VALIDADO){
					return objetoDAO.commentObjectInstance(obj,text);
				}
				else if(val == Constants.OBJETO_VALIDADO){
					return objetoDAO.validateObjectInstance(obj,text);
				}
				else{
					return "errorParam";
				}
			}
		}
	}

	public List<User> getUsersToValidateObjectDOMByGroup(User teacher, Group group, TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumUsersToValidateByGroup(User teacher, Group group, TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumObjetoDOMToValidateByUser(User teacher, Group group, TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<InstanciaObjetoDOM> getObjetoDOMAtributeByType(TipoObjetoDOM tipoPadre, TipoObjetoDOM tipoHijo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addObjetoDOMAttributeByType(int padre, int hijo, TipoObjetoDOM tipoP, int tipoH, int val, User user, Group groupA) {
		String message = "";
		InstanciaObjetoDOM op = objetoDAO.getObjectInstance(tipoP, padre);
		TipoAtributoComplejoDOM tac  = null;
		if(op == null){
			op = objetoDAO.getObjectInstanceNotVal(tipoP, padre);
			if(op == null){
				return "noType";
			}
		}
		List<TipoAtributoComplejoDOM> tacl = objetoDAO.getTiposAtributosCompleos(tipoP);
		boolean enc = false;
		int i = 0;
		while(!enc && i < tacl.size()){
			enc = tacl.get(i).getIdTipoHijo() == tipoH;
			if(enc)
				tac = tacl.get(i);
			i++;
		}
		if(!enc){
			message = "noType";
		}
		else{
			TipoObjetoDOM tipoHijo = new TipoObjetoDOM();
			tipoHijo.setTipoDOM(tac.getIdTipoHijo());
			InstanciaObjetoDOM oh = objetoDAO.getObjectInstance(tipoHijo, hijo);
			if(oh == null){
				message = "noType";
			}
			else{
				InstanciaAtributoComplejoDOM ao = new InstanciaAtributoComplejoDOM();
				ao.setCreador(user.getId());
				ao.setIdGrupo(groupA.getIdGrupo());
				ao.setInstanciaHijo(oh);
				ao.setNombreAtributo(tac.getNombreAtributo());
				ao.setTipoHijo(tipoHijo);
				ao.setTipoPadre(tipoP);
				ao.setValidado(val);
				if(val == Constants.OBJETO_VALIDADO){
					ao.setTextoValidacion(Constants.TEXTO_VALIDACION_PROFESOR);
				}
				String ret = objetoDAO.addComplexAttribute(ao,padre);
				if(ret.equals("añadido") && !ao.isValidado()){
					return "añadidoS";
				}
				else{
					return ret;
				}
			}
		}
		return message;
	}

	public String deleteObjetoDOMAttributeByType(int padre, int hijo, TipoObjetoDOM tipoP, int tipoH, int val, User user, Group groupA) {
		String message = "";
		InstanciaObjetoDOM op = objetoDAO.getObjectInstance(tipoP, padre);
		TipoAtributoComplejoDOM tac  = null;
		if(op == null){
			op = objetoDAO.getObjectInstanceNotVal(tipoP, padre);
			if( op == null){
			return "noType";
			}
		}
		List<TipoAtributoComplejoDOM> tacl = objetoDAO.getTiposAtributosCompleos(tipoP);
		boolean enc = false;
		int i = 0;
		while(!enc && i < tacl.size()){
			enc = tacl.get(i).getIdTipoHijo() == tipoH;
			if(enc)
				tac = tacl.get(i);
			i++;
		}
		if(!enc){
			message = "noType";
		}
		else{
			TipoObjetoDOM tipoHijo = new TipoObjetoDOM();
			tipoHijo.setTipoDOM(tac.getIdTipoHijo());
			InstanciaObjetoDOM oh = objetoDAO.getObjectInstance(tipoHijo, hijo);
			if(oh == null){
				message = "noType";
			}
			else{
				InstanciaAtributoComplejoDOM acd = objetoDAO.getAtributoComplejo(tipoP.getTipoDOM(), padre, tipoHijo.getTipoDOM(), hijo);
				if(acd == null){
					return "noType";
				}
				else{
					return objetoDAO.remAtributoComplejo(acd,padre);
				}
			}
		}
		
		return message;
	}

	public List<InstanciaObjetoDOM> getStudentObjetoDOMAtributeByType(TipoObjetoDOM tipoPadre, TipoObjetoDOM tipoHijo, User user,
			Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addStudentObjetoDOMAttributeByType(InstanciaObjetoDOM padre, InstanciaObjetoDOM hijo, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteStudentObjetoDOMAttributeByType(InstanciaObjetoDOM padre, InstanciaObjetoDOM hijo, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TipoAtributoComplejoDOM> getTiposAtributosCompleos(TipoObjetoDOM tipo) {
		return objetoDAO.getTiposAtributosCompleos(tipo);
	}

	public List<InstanciaAtributoComplejoDOM> getAtributosCDisponiblesObjetoDOM(TipoObjetoDOM tipo, InstanciaObjetoDOM obj, int pag) {
		List<InstanciaAtributoComplejoDOM> listaret = new ArrayList<InstanciaAtributoComplejoDOM>();
		
		TipoObjetoDOM d = new TipoObjetoDOM();
		d.setTipoDOM(pag);
		List<InstanciaObjetoDOM> lista = getObjetoDOMListByType(d);
		for(InstanciaObjetoDOM o : lista){
			boolean enc = false;
			int i = 0;
			while(!enc && i < obj.getAtributosComplejos().size()){
				enc=o.getIdInstancia() == obj.getAtributosComplejos().get(i).getInstanciaHijo().getIdInstancia() && o.getTipo().getTipoDOM() == obj.getAtributosComplejos().get(i).getTipoHijo().getTipoDOM();
				i++;
			}
			if(!enc){
				InstanciaAtributoComplejoDOM atr = new InstanciaAtributoComplejoDOM();
				atr.setInstanciaHijo(o);
				atr.setTipoHijo(o.getTipo());
				atr.setTipoPadre(tipo);
				listaret.add(atr);
			}
		}
		return listaret;
	}

	public List<InstanciaAtributoComplejoDOM> getAtributosCPorTipo(InstanciaObjetoDOM obj, int pag) {
		List<InstanciaAtributoComplejoDOM> retl = new ArrayList<InstanciaAtributoComplejoDOM>();
		if(obj == null)
			return null;
		else{
			for(InstanciaAtributoComplejoDOM ac : obj.getAtributosComplejos()){
				if(ac.getTipoHijo().getTipoDOM() == pag){
					retl.add(ac);
				}
			}
			return retl;
		}
	}

	public String modifySimpleAttribute(InstanciaObjetoDOM obj) {
		return objetoDAO.updateSimpleAttributes(obj);
	}

	public List<InstanciaObjetoDOM> fillUsers(List<InstanciaObjetoDOM> l) {
		for(InstanciaObjetoDOM o : l){
			User u = loginManager.getUser(o.getCreador().getId());
			o.setCreador(u);
		}
		return l;
	}

	public InstanciaObjetoDOM getObjetoDOMUnvalidated(TipoObjetoDOM tipo, int id, Group g, User u) {
		if(groupManager.isTeacherOrDirector(u, g.getIdGrupo())){
			InstanciaObjetoDOM obj = objetoDAO.getObjectInstanceNotVal(tipo, id);
			if(obj == null){
				obj = objetoDAO.getObjectInstance(tipo, id);
			}
			if(obj != null){
				obj.setAtributosSencillos(objetoDAO.getAtributosSencillos(tipo,id));
				obj.setAtributosComplejos(objetoDAO.getAtributosComplejosNoVal(tipo,id));
				return obj;
			}
			else{
				return null;
			}
		}
		else{
			InstanciaObjetoDOM obj = objetoDAO.getObjectInstanceNotVal(tipo, id);
			if(obj.getCreador().getId() == u.getId()){
				return obj;
			}
			else{
				return null;
			}
		}
	}

	public Map<Integer, Integer> getBadgesFromObject(InstanciaObjetoDOM obj) {
		Map<Integer, Integer> badges = new HashMap<Integer, Integer>();
		if(obj == null){
			return null;
		}
		for(InstanciaAtributoComplejoDOM ac : obj.getAtributosComplejos()){
			if(!ac.isValidado()){
				Integer i = badges.get(ac.getTipoHijo().getTipoDOM());
				if(i != null){
					badges.put(ac.getTipoHijo().getTipoDOM(),++i);
				}
				else{
					badges.put(ac.getTipoHijo().getTipoDOM(), 1);
				}
			}
		}
		return badges;
	}

	public String validateAtributoC(int idHijo, int tipoHijo, int idPadre, TipoObjetoDOM tipo, User user, Group groupA, String textV, int val){
		if(objetoDAO.getObjectInstanceNotVal(tipo, idPadre) != null){
			if(groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
				InstanciaAtributoComplejoDOM ac = objetoDAO.getAtributoComplejoNotVal(tipo.getTipoDOM(), idPadre, tipoHijo, idHijo);
				if(ac != null){
					if(val == Constants.OBJETO_VALIDADO){
						return objetoDAO.validateAtributoComplejo(tipo.getTipoDOM(), idPadre, tipoHijo, idHijo,textV);
					}
					else{
						if(val == Constants.OBJETO_NO_VALIDADO){
							return objetoDAO.commentAtributoComplejoNoVal(tipo.getTipoDOM(), idPadre, tipoHijo, idHijo,textV);
						}
						else{
							return "errVal";
						}
					}
				}
				else{
					InstanciaAtributoComplejoDOM ac2 = objetoDAO.getAtributoComplejo(tipo.getTipoDOM(), idPadre, tipoHijo, idHijo);
					if(ac2 != null){
						return "alreadyValidated";
					}
					else{
						return "noAtributoC";
					}
				}
			}
			else{
				return "noPrivileges";
			}
		}
		else{
			return "noObject";
		}
	}
}
