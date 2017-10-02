package com.cvilla.medievalia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IObjetoDAO;
import com.cvilla.medievalia.domain.AtributoComplejoDOM;
import com.cvilla.medievalia.domain.AtributoSencilloDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
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
	private IObjetoDAO objetoDAO;
	
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

	public List<AtributoSencilloDOM> getAtributosSObjetoDOM(TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjetoDOM getObjetoDOM(TipoObjetoDOM tipoObj, int idInstancia) {
		ObjetoDOM o = objetoDAO.getObjectInstance(tipoObj,idInstancia);
		if(o != null){
			o.setAtributosSencillos(objetoDAO.getAtributosSencillos(tipoObj,idInstancia));
			o.setAtributosComplejos(objetoDAO.getAtributosComplejos(tipoObj,idInstancia));
		}
		return o;
	}

	public List<ObjetoDOM> getObjetoDOMListByType(TipoObjetoDOM tipo) {
		return objetoDAO.getObjectListByTipe(tipo);
	}

	public String addObjetoDOM(TipoObjetoDOM tipo, ObjetoDOM o, Group groupA, User user) {
		o.setCreador(user.getId());
		o.setGrupo(groupA.getIdGrupo());
		o.setTipo(tipo);
		if(authManager.isAutorized(Constants.P_VALIDATE_OBJECT_INSTANCE, user)){
			o.setValidado(Constants.OBJETO_VALIDADO);
			o.setTextoValidacion(Constants.TEXTO_VALIDACION_PROFESOR);
			
		}
		else{
			o.setValidado(Constants.OBJETO_NO_VALIDADO);
			o.setTextoValidacion(Constants.TEXTO_SIN_VALIDAR);
		}
		return objetoDAO.createObjectInstance(o);
	}

	public String renameObjetoDOM(TipoObjetoDOM tipo, String nombre, int id, User user, Group groupA) {
		
		if(nombre.length() >= Constants.MIN_PERSONAGE_NAME){
			ObjetoDOM o = objetoDAO.getObjectByName(tipo,nombre);
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

	public String renameObjetoDOMOwn(TipoObjetoDOM tipo, String nombre, int id,
			User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteObjetoDOM(TipoObjetoDOM tipo, int id, User user,
			Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ObjetoDOM> getStudentObjetoDOMList(TipoObjetoDOM tipo, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteObjetoDOMOwn(TipoObjetoDOM tipo, int id, User user,
			Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ObjetoDOM> getTeachersObjetoDOMList(TipoObjetoDOM tipo,
			User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String validateObjetoDOM(TipoObjetoDOM tipo, int id, User user,
			Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersToValidateObjectDOMByGroup(User teacher,
			Group group, TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumUsersToValidateByGroup(User teacher, Group group,
			TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumObjetoDOMToValidateByUser(User teacher, Group group,
			TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ObjetoDOM> getObjetoDOMAtributeByType(TipoObjetoDOM tipoPadre,
			TipoObjetoDOM tipoHijo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addObjetoDOMAttributeByType(int padre, int hijo, TipoObjetoDOM tipoP, int tipoH, int val, User user, Group groupA) {
		String message = "";
		ObjetoDOM op = objetoDAO.getObjectInstance(tipoP, padre);
		TipoAtributoComplejoDOM tac  = null;
		if(op == null){
			message = "noType";
		}
		else{
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
				ObjetoDOM oh = objetoDAO.getObjectInstance(tipoHijo, hijo);
				if(oh == null){
					message = "noType";
				}
				else{
					AtributoComplejoDOM ao = new AtributoComplejoDOM();
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
			
		}
		return message;
	}

	public String deleteObjetoDOMAttributeByType(int padre, int hijo, TipoObjetoDOM tipoP, int tipoH, int val, User user, Group groupA) {
		String message = "";
		ObjetoDOM op = objetoDAO.getObjectInstance(tipoP, padre);
		TipoAtributoComplejoDOM tac  = null;
		if(op == null){
			message = "noType";
		}
		else{
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
				ObjetoDOM oh = objetoDAO.getObjectInstance(tipoHijo, hijo);
				if(oh == null){
					message = "noType";
				}
				else{
					AtributoComplejoDOM acd = objetoDAO.getAtributoComplejo(tipoP.getTipoDOM(), padre, tipoHijo.getTipoDOM(), hijo);
					if(acd == null){
						return "noType";
					}
					else{
						return objetoDAO.remAtributoComplejo(acd,padre);
					}
				}
			}
			
		}
		return message;
	}

	public List<ObjetoDOM> getStudentObjetoDOMAtributeByType(
			TipoObjetoDOM tipoPadre, TipoObjetoDOM tipoHijo, User user,
			Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addStudentObjetoDOMAttributeByType(ObjetoDOM padre,
			ObjetoDOM hijo, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteStudentObjetoDOMAttributeByType(ObjetoDOM padre,
			ObjetoDOM hijo, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TipoAtributoComplejoDOM> getTiposAtributosCompleos(TipoObjetoDOM tipo) {
		return objetoDAO.getTiposAtributosCompleos(tipo);
	}

	public List<AtributoComplejoDOM> getAtributosCDisponiblesObjetoDOM(TipoObjetoDOM tipo, ObjetoDOM obj, int pag) {
		List<AtributoComplejoDOM> listaret = new ArrayList<AtributoComplejoDOM>();
		
		TipoObjetoDOM d = new TipoObjetoDOM();
		d.setTipoDOM(pag);
		List<ObjetoDOM> lista = getObjetoDOMListByType(d);
		for(ObjetoDOM o : lista){
			boolean enc = false;
			int i = 0;
			while(!enc && i < obj.getAtributosComplejos().size()){
				enc=o.getIdInstancia() == obj.getAtributosComplejos().get(i).getInstanciaHijo().getIdInstancia() && o.getTipo().getTipoDOM() == obj.getAtributosComplejos().get(i).getTipoHijo().getTipoDOM();
				i++;
			}
			if(!enc){
				AtributoComplejoDOM atr = new AtributoComplejoDOM();
				atr.setInstanciaHijo(o);
				atr.setTipoHijo(o.getTipo());
				atr.setTipoPadre(tipo);
				listaret.add(atr);
			}
		}
		return listaret;
	}

	public List<AtributoComplejoDOM> getAtributosCPorTipo(ObjetoDOM obj, int pag) {
		List<AtributoComplejoDOM> retl = new ArrayList<AtributoComplejoDOM>();
		if(obj == null)
			return null;
		else{
			for(AtributoComplejoDOM ac : obj.getAtributosComplejos()){
				if(ac.getTipoHijo().getTipoDOM() == pag){
					retl.add(ac);
				}
			}
			return retl;
		}
	}

	public String modifySimpleAttribute(ObjetoDOM obj) {
		return objetoDAO.updateSimpleAttributes(obj);
	}
}
