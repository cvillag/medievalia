package com.cvilla.medievalia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IObjetoDAO;
import com.cvilla.medievalia.domain.InstanciaAtributoComplejo;
import com.cvilla.medievalia.domain.InstanciaAtributoSencillo;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.InstanciaObjeto;
import com.cvilla.medievalia.domain.TipoAtributoComplejo;
import com.cvilla.medievalia.domain.TipoObjeto;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IHtmlManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.Fechas;
import com.cvilla.medievalia.utils.ListaAtributoSimple;
import com.cvilla.medievalia.utils.ListaRelaciones;
import com.cvilla.medievalia.utils.SpecialDate;

public class ObjectManager implements IObjectManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IHtmlManager htmlManager;
	
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

	public List<TipoObjeto> getTiposObjetosDOM() {
		return objetoDAO.getObjectTypeList();
	}
	
	public TipoObjeto getTipoObjetoDOM(int idType) {
		return objetoDAO.getObjectType(idType);
	}
	
	public List<TipoObjeto> getAtributosCObjetoDOM(TipoObjeto tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<InstanciaAtributoSencillo> getAtributosSObjetoDOM(TipoObjeto tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public InstanciaObjeto getObjetoDOM(TipoObjeto tipoObj, int idInstancia) {
		InstanciaObjeto o = objetoDAO.getObjectInstance(tipoObj,idInstancia);
		if(o != null){
			o.setAtributosSencillos(objetoDAO.getAtributosSencillos(tipoObj,idInstancia));
			o.setAtributosComplejos(objetoDAO.getAtributosComplejos(tipoObj,idInstancia));
		}
		return o;
	}
	public InstanciaObjeto getObjetoDOM(TipoObjeto tipo, String nombre) {
		InstanciaObjeto o = objetoDAO.getObjectByName(tipo, nombre);
		if(o != null){
			o.setAtributosSencillos(objetoDAO.getAtributosSencillos(tipo,o.getIdInstancia()));
			o.setAtributosComplejos(objetoDAO.getAtributosComplejos(tipo,o.getIdInstancia()));
		}
		return o;
	}

	public List<InstanciaObjeto> getObjetoDOMListByType(TipoObjeto tipo) {
		return objetoDAO.getObjectListByTipe(tipo);
	}
	
	public List<InstanciaObjeto> getObjetoDOMListByTypeFilter(TipoObjeto tipo, HttpServletRequest req) {
		boolean nulo = false;
		int index = 0;
		List<InstanciaAtributoComplejo> filtros = new ArrayList<InstanciaAtributoComplejo>();
		while(!nulo){
			String idoh = req.getParameter("idoh"+index);
			String idih = req.getParameter("idih"+index);
			String prep = req.getParameter("prep"+index);
			nulo = idoh == null || idoh.length() < 1 || !htmlManager.isNumeric(idoh) || idih == null || idih.length() < 1 || !htmlManager.isNumeric(idih);
			if(!nulo){
				InstanciaAtributoComplejo ia = new InstanciaAtributoComplejo();
				ia.setTipoPadre(tipo);
				InstanciaObjeto ih = new InstanciaObjeto();
				ih.setIdInstancia(new Integer(idih));
				ia.setInstanciaHijo(ih);
				TipoObjeto t = new TipoObjeto();
				t.setTipoDOM(new Integer(idoh));
				ia.setTipoHijo(t);
				if(htmlManager.isNumeric(prep) && prep.equals("1")){
					ia.setConFecha(1);
				}
				else{
					ia.setConFecha(0);
				}
				filtros.add(ia);
			}
			index++;
		}
		return objetoDAO.getObjectListByTipeFilter(tipo, filtros);
	}

	public String addObjetoDOM(TipoObjeto tipo, InstanciaObjeto o, Group groupA, User user) {
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

	public String renameObjetoDOM(TipoObjeto tipo, String nombre, int id, User user, Group groupA) {
		InstanciaObjeto obj = objetoDAO.getObjectInstance(tipo, id);
		if(obj == null){
			obj = objetoDAO.getObjectInstanceNotVal(tipo, id);
			if(obj == null){
				return "noObject";
			}
		}
		if(nombre.length() >= Constants.MIN_PERSONAGE_NAME){
			InstanciaObjeto o2 = objetoDAO.getObjectByName(tipo,nombre);
			if(o2 == null || o2.getIdInstancia() == id){
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

	public String renameObjetoDOMOwn(TipoObjeto tipo, String nombre, int id,	User user, Group groupA) {
		InstanciaObjeto obj = objetoDAO.getObjectInstance(tipo, id);
		if(obj == null){
			obj = objetoDAO.getObjectInstanceNotVal(tipo, id);
			if(obj == null){
				return "noObject";
			}
		}
		if(obj.getCreador().getId() == user.getId()){
			if(nombre.length() >= Constants.MIN_PERSONAGE_NAME){
				InstanciaObjeto o = objetoDAO.getObjectByName(tipo,nombre);
				if(o == null || o.getIdInstancia() == id){
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

	public String deleteObjetoDOM(InstanciaObjeto obj, User user, Group groupA){
		return objetoDAO.deleteObjetoDOM(obj);
	}

	public List<InstanciaObjeto> getStudentObjetoDOMList(TipoObjeto tipo, User user, Group groupA) {
		List<InstanciaObjeto> lista = objetoDAO.getStudentObjetoDOMList(tipo,groupA,user);
		for(InstanciaObjeto o : lista){
			if(o.getCreador().getId() != user.getId()){
				o.setTextoLeido(Constants.TEXTO_LEIDO);
				o.setTextoValidacion("");
			}
		}
		lista = fillUsers(lista);
		return lista;
	}

	public String deleteObjetoDOMOwn(InstanciaObjeto obj, User user,	Group groupA) {
		if(obj.isValidado()){
			return "alreadyValidated";
		}
		else{
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
	}

	public List<InstanciaObjeto> getTeachersObjetoDOMList(TipoObjeto tipo, User user, Group groupA) {
		if(groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			List<InstanciaObjeto> lista = objetoDAO.getTeachersObjetoDOMList(tipo,groupA);
			lista = fillUsers(lista);
			return lista;
		}
		else{
			return null;
		}
	}
	

	public String validateObjetoDOM(TipoObjeto tipo, int id, User user, Group group, int val, String text) {
		InstanciaObjeto obj = objetoDAO.getObjectInstanceNotVal(tipo, id);
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

	public List<User> getUsersToValidateObjectDOMByGroup(User teacher, Group group, TipoObjeto tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumUsersToValidateByGroup(User teacher, Group group, TipoObjeto tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumObjetoDOMToValidateByUser(User teacher, Group group, TipoObjeto tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<InstanciaObjeto> getObjetoDOMAtributeByType(TipoObjeto tipoPadre, TipoObjeto tipoHijo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addObjetoDOMAttributeByType(int padre, int hijo, TipoObjeto tipoP, int tipoH, int val, User user, Group groupA, int selRel, SpecialDate inicio, SpecialDate fin, String paginaDoc) {
		String message = "";
		InstanciaObjeto op = objetoDAO.getObjectInstance(tipoP, padre);
		TipoAtributoComplejo tac  = null;
		if(op == null){
			op = objetoDAO.getObjectInstanceNotVal(tipoP, padre);
			if(op == null){
				return "noType";
			}
		}
		List<TipoAtributoComplejo> tacl = objetoDAO.getTiposAtributosCompleos(tipoP);
		boolean enc = false;
		int i = 0;
		while(!enc && i < tacl.size()){
			enc = tacl.get(i).getIdTipoHijo() == tipoH;
			if(enc)
				tac = tacl.get(i);
			i++;
		}
		InstanciaObjeto docrel = null;
		if(objetoDAO.hasRelationObject(tipoP.getTipoDOM(),tipoH)){
			docrel = objetoDAO.getObjectInstance(objetoDAO.getObjectType(tac.getIdTipoRelacion()), selRel);
			if(docrel == null){
				docrel = objetoDAO.getObjectInstanceNotVal(objetoDAO.getObjectType(tac.getIdTipoRelacion()), selRel);
			}
			if(docrel == null){
				return "noType";
			}
		}
		if(!enc){
			message = "noType";
		}
		else{
			TipoObjeto tipoHijo = new TipoObjeto();
			tipoHijo.setTipoDOM(tac.getIdTipoHijo());
			InstanciaObjeto oh = objetoDAO.getObjectInstance(tipoHijo, hijo);
			if(oh == null){
				message = "noType";
			}
			else{
				InstanciaAtributoComplejo ao = new InstanciaAtributoComplejo();
				ao.setCreador(user.getId());
				ao.setIdGrupo(groupA.getIdGrupo());
				ao.setInstanciaHijo(oh);
				ao.setNombreAtributo(tac.getNombreAtributo());
				ao.setTipoHijo(tipoHijo);
				ao.setTipoPadre(tipoP);
				ao.setValidado(val);
				ao.setIdTipoObjetoRelacion(tac.getIdTipoRelacion());
				ao.setInstanciaObjetoRelacion(docrel);
				ao.setConPaginaDoc(tac.getConPaginaDoc());
				if(ao.isConPagina() && paginaDoc != null){
					ao.setPaginaDoc(paginaDoc);
				}
				if(objetoDAO.isConFecha(tipoP.getTipoDOM(),tipoH)){
					if(!Fechas.fechaIncorrecta(inicio) && !Fechas.fechaIncorrecta(fin)){
						ao.setConFecha(1);
						ao.setFechaInicio(inicio);
						ao.setFechaFin(fin);
					}
				}
				if(val == Constants.OBJETO_VALIDADO){
					ao.setTextoValidacion(Constants.TEXTO_VALIDACION_PROFESOR);
				}
				ao.setTextoLeido(Constants.TEXTO_LEIDO);
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

	public String deleteObjetoDOMAttributeByType(int padre, int hijo, TipoObjeto tipoP, int tipoH, int val, User user, Group groupA) {
		String message = "";
		InstanciaObjeto op = objetoDAO.getObjectInstance(tipoP, padre);
		TipoAtributoComplejo tac  = null;
		if(op == null){
			op = objetoDAO.getObjectInstanceNotVal(tipoP, padre);
			if( op == null){
			return "noType";
			}
		}
		List<TipoAtributoComplejo> tacl = objetoDAO.getTiposAtributosCompleos(tipoP);
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
			TipoObjeto tipoHijo = new TipoObjeto();
			tipoHijo.setTipoDOM(tac.getIdTipoHijo());
			InstanciaObjeto oh = objetoDAO.getObjectInstance(tipoHijo, hijo);
			if(oh == null){
				message = "noType";
			}
			else{
				InstanciaAtributoComplejo acd = null;
				if(authManager.isAutorized(Constants.P_VALIDATE_COMPLEX_ATTRIBUTE, user)){
					acd = objetoDAO.getAtributoComplejo(tipoP.getTipoDOM(), padre, tipoHijo.getTipoDOM(), hijo);
					if(acd == null){
						acd = objetoDAO.getAtributoComplejoNotVal(tipoP.getTipoDOM(), padre, tipoHijo.getTipoDOM(), hijo);
					}
				}
				else{
					acd = objetoDAO.getAtributoComplejoNotVal(tipoP.getTipoDOM(), padre, tipoHijo.getTipoDOM(), hijo);
					if(acd == null){
						return "noType";
					}
					if(acd.getCreador() != user.getId()){
						return "noOwner";
					}
				}
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

	public List<InstanciaObjeto> getStudentObjetoDOMAtributeByType(TipoObjeto tipoPadre, TipoObjeto tipoHijo, User user,
			Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addStudentObjetoDOMAttributeByType(InstanciaObjeto padre, InstanciaObjeto hijo, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteStudentObjetoDOMAttributeByType(InstanciaObjeto padre, InstanciaObjeto hijo, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TipoAtributoComplejo> getTiposAtributosCompleos(TipoObjeto tipo) {
		return objetoDAO.getTiposAtributosCompleos(tipo);
	}

	public List<InstanciaAtributoComplejo> getAtributosCDisponiblesObjetoDOM(TipoObjeto tipo, InstanciaObjeto obj, int pag) {
		List<InstanciaAtributoComplejo> listaret = new ArrayList<InstanciaAtributoComplejo>();
		
		TipoObjeto d = new TipoObjeto();
		d.setTipoDOM(pag);
		List<InstanciaObjeto> lista = getObjetoDOMListByType(d);
		for(InstanciaObjeto o : lista){
			boolean enc = false;
			int i = 0;
			while(!enc && i < obj.getAtributosComplejos().size()){
				enc=o.getIdInstancia() == obj.getAtributosComplejos().get(i).getInstanciaHijo().getIdInstancia() && o.getTipo().getTipoDOM() == obj.getAtributosComplejos().get(i).getTipoHijo().getTipoDOM();
				i++;
			}
			if(!enc){
				InstanciaAtributoComplejo atr = new InstanciaAtributoComplejo();
				atr.setInstanciaHijo(o);
				atr.setTipoHijo(o.getTipo());
				atr.setTipoPadre(tipo);
				listaret.add(atr);
			}
		}
		return listaret;
	}

	public List<InstanciaAtributoComplejo> getAtributosCPorTipo(InstanciaObjeto obj, int pag) {
		List<InstanciaAtributoComplejo> retl = new ArrayList<InstanciaAtributoComplejo>();
		if(obj == null)
			return null;
		else{
			for(InstanciaAtributoComplejo ac : obj.getAtributosComplejos()){
				if(ac.getTipoHijo().getTipoDOM() == pag){
					retl.add(ac);
				}
			}
			return retl;
		}
	}

	public String modifySimpleAttribute(InstanciaObjeto obj,Group g, User u) {
		for(InstanciaAtributoSencillo as : obj.getAtributosSencillos()){
			if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_OBJECT && as.getValor() != null){
				int tipo = as.getSubtipo();
				int id = ((InstanciaObjeto) as.getValor()).getIdInstancia();
				if(id != 0){
					InstanciaObjeto o = getObjetoDOM(new TipoObjeto(tipo,""), id);
					if(o == null){
						o = getObjetoDOMUnvalidated(new TipoObjeto(tipo,""), id, g, u);
					}
					if(o == null){
						return "errorParam";
					}
				}
				if(!objetoDAO.atributoSimpleObjetoExists(as.getIdAtributo(),obj.getTipo().getTipoDOM(),as.getSubtipo())){
					return "errorParam";
				}
			}
		}
//		boolean enc = false;
//		int i = 0;
//		List<InstanciaAtributoSencilloDOM> ltas = getAtributosSObjetoDOM(obj.getTipo());
//		while (!enc && i < ltas.size()){
//			boolean enc2 = false;
//			int j = 0;
//			while(!enc2 && j < obj.getAtributosSencillos().size()){
//				enc2 = obj.getAtributosSencillos().get(j).getSubtipo() == ltas.get(i).getSubtipo();
//				j++;
//			}
//			enc = !enc2;
//			i++;
//		}
//		if(!enc){
			return objetoDAO.updateSimpleAttributes(obj);
//		}
//		else{
//			return "errorParam";
//		}
	}

	public List<InstanciaObjeto> fillUsers(List<InstanciaObjeto> l) {
		for(InstanciaObjeto o : l){
			User u = loginManager.getUser(o.getCreador().getId());
			o.setCreador(u);
		}
		return l;
	}

	public InstanciaObjeto getObjetoDOMUnvalidated(TipoObjeto tipo, int id, Group g, User u) {
		InstanciaObjeto obj = objetoDAO.getObjectInstanceNotVal(tipo, id);
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

	public Map<Integer, Integer> getBadgesFromObject(InstanciaObjeto obj) {
		Map<Integer, Integer> badges = new HashMap<Integer, Integer>();
		if(obj == null){
			return null;
		}
		for(InstanciaAtributoComplejo ac : obj.getAtributosComplejos()){
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

	public String validateAtributoC(int idHijo, int tipoHijo, int idPadre, TipoObjeto tipo, User user, Group groupA, String textV, int val){
		if(objetoDAO.getObjectInstanceNotVal(tipo, idPadre) != null || objetoDAO.getObjectInstance(tipo, idPadre) != null){
			if(groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
				InstanciaAtributoComplejo ac = objetoDAO.getAtributoComplejoNotVal(tipo.getTipoDOM(), idPadre, tipoHijo, idHijo);
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
					InstanciaAtributoComplejo ac2 = objetoDAO.getAtributoComplejo(tipo.getTipoDOM(), idPadre, tipoHijo, idHijo);
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

	public List<ListaAtributoSimple> getListaDisponibleAtributoSimpleObjeto(InstanciaObjeto ac) {
		List<ListaAtributoSimple> lista = new ArrayList<ListaAtributoSimple>();
		for(InstanciaAtributoSencillo as : ac.getAtributosSencillos()){
			if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_OBJECT){
				ListaAtributoSimple li = new ListaAtributoSimple();
				li.setAtributo(as);
				TipoObjeto t = new TipoObjeto();
				t.setTipoDOM(as.getSubtipo());
				li.setDisponibles(getObjetoDOMListByType(t));
				lista.add(li);
			}
		}
		return lista;
	}

	public List<ListaRelaciones> getRelaciones(List<TipoAtributoComplejo> ac) {
		List<ListaRelaciones> lista = new ArrayList<ListaRelaciones>();
		if(ac != null){
			for(TipoAtributoComplejo ta : ac){
				ListaRelaciones lr = new ListaRelaciones();
				lr.setAc(ta);
				TipoObjeto t = getTipoObjetoDOM(ta.getIdTipoRelacion());
				lr.setLi(getObjetoDOMListByType(t));
				lista.add(lr);
			}
			return lista;
		}
		else{
			return null;
		}
	}

	public String setObjectTextReaded(int idInstancia, User user, TipoObjeto tipo, Group groupA) {
		InstanciaObjeto obj = getObjetoDOM(tipo, idInstancia);
		if(obj == null){
			obj = getObjetoDOMUnvalidated(tipo, idInstancia, groupA, user);
		}
		if(obj == null || obj.getCreador().getId() != user.getId()){
			return "noType";
		}
		else{
			return objetoDAO.setObjectTextReaded(obj);
		}
	}

	public int getTypeRelacionForComplexAttribute(InstanciaObjeto obj,int pag) {
		return objetoDAO.getRelacionForComplexAttribute(obj,pag);
	}

	public InstanciaAtributoComplejo getComplexAttribute(TipoObjeto tipo,	int idTipoHijo, int idInstPadre, int idInstHijo, Group g, User u) {
		InstanciaObjeto objP = getObjetoDOM(tipo, idInstPadre);
		if(objP == null){
			objP = getObjetoDOMUnvalidated(tipo, idInstPadre, g, u);
		}
		if(objP != null){
			TipoObjeto tipoH = getTipoObjetoDOM(idTipoHijo);
			if(tipoH != null){
				InstanciaObjeto objH = getObjetoDOM(tipoH, idInstHijo);
				if(objH != null){
					return objetoDAO.getAtributoComplejo(tipo.getTipoDOM(), idInstPadre, idTipoHijo, idInstHijo);
				}
			}
		}
		return null;
	}
	
	public InstanciaAtributoComplejo getComplexAttributeNotVal(TipoObjeto tipo,	int idTipoHijo, int idInstPadre, int idInstHijo, Group g, User u) {
		InstanciaObjeto objP = getObjetoDOM(tipo, idInstPadre);
		if(objP == null){
			objP = getObjetoDOMUnvalidated(tipo, idInstPadre, g, u);
		}
		if(objP != null){
			TipoObjeto tipoH = getTipoObjetoDOM(idTipoHijo);
			if(tipoH != null){
				InstanciaObjeto objH = getObjetoDOM(tipoH, idInstHijo);
				if(objH != null){
					return objetoDAO.getAtributoComplejoNotVal(tipo.getTipoDOM(), idInstPadre, idTipoHijo, idInstHijo);
				}
			}
		}
		return null;
	}

	public String updateObjetoDOMAttributeByType(int idInstPadre, int idInstHijo, TipoObjeto tipo, int idTipoAttr, int val, User user, Group groupA, int selRel, SpecialDate inicio,	SpecialDate fin, String paginaDoc) {
		String message = "";
		InstanciaObjeto op = objetoDAO.getObjectInstance(tipo, idInstPadre);
		TipoAtributoComplejo tac  = null;
		if(op == null){
			op = objetoDAO.getObjectInstanceNotVal(tipo, idInstPadre);
			if(op == null){
				return "noType";
			}
		}
		List<TipoAtributoComplejo> tacl = objetoDAO.getTiposAtributosCompleos(tipo);
		boolean enc = false;
		int i = 0;
		while(!enc && i < tacl.size()){
			enc = tacl.get(i).getIdTipoHijo() == idTipoAttr;
			if(enc)
				tac = tacl.get(i);
			i++;
		}
		InstanciaObjeto docrel = objetoDAO.getObjectInstance(objetoDAO.getObjectType(tac.getIdTipoRelacion()), selRel);
		if(docrel == null){
			docrel = objetoDAO.getObjectInstanceNotVal(objetoDAO.getObjectType(tac.getIdTipoRelacion()), selRel);
		}
		if(docrel == null){
			return "noType";
		}
		if(!enc){
			message = "noType";
		}
		else{
			TipoObjeto tipoHijo = new TipoObjeto();
			tipoHijo.setTipoDOM(tac.getIdTipoHijo());
			InstanciaObjeto oh = objetoDAO.getObjectInstance(tipoHijo, idInstHijo);
			InstanciaAtributoComplejo ioc = objetoDAO.getAtributoComplejo(tipo.getTipoDOM(), idInstPadre, idTipoAttr, idInstHijo);
			if(ioc == null){
				ioc = objetoDAO.getAtributoComplejoNotVal(tipo.getTipoDOM(), idInstPadre, idTipoAttr, idInstHijo);
			}
			if(oh == null || ioc == null){
				message = "noType";
			}
			else{
				InstanciaAtributoComplejo ao = new InstanciaAtributoComplejo();
				ao.setCreador(user.getId());
				ao.setIdGrupo(groupA.getIdGrupo());
				ao.setInstanciaHijo(oh);
				ao.setNombreAtributo(tac.getNombreAtributo());
				ao.setTipoHijo(tipoHijo);
				ao.setTipoPadre(tipo);
				ao.setValidado(val);
				ao.setIdTipoObjetoRelacion(tac.getIdTipoRelacion());
				ao.setInstanciaObjetoRelacion(docrel);
				ao.setConPaginaDoc(tac.getConPaginaDoc());
				if(ao.isConPagina()){
					ao.setPaginaDoc(paginaDoc);
				}
				if(objetoDAO.isConFecha(tipo.getTipoDOM(),idTipoAttr)){
					if(!Fechas.fechaIncorrecta(inicio) && !Fechas.fechaIncorrecta(fin)){
						ao.setConFecha(1);
						ao.setFechaInicio(inicio);
						ao.setFechaFin(fin);
					}
				}
				if(val == Constants.OBJETO_VALIDADO){
					ao.setTextoValidacion(Constants.TEXTO_VALIDACION_PROFESOR);
				}
				String ret = objetoDAO.updateComplexAttribute(ao,idInstPadre);
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

	public boolean isConFecha(int tipoDOM, int idTipoAttr) {
		return objetoDAO.isConFecha(tipoDOM, idTipoAttr);
	}

	public String setComplexAttributeTextReaded(int idPadre, int idHijo,int tipoHijo, User user, TipoObjeto tipo, Group groupA) {
		TipoObjeto th = getTipoObjetoDOM(tipoHijo);
		if(th != null){
			InstanciaObjeto ip = getObjetoDOM(tipo, idPadre);
			if(ip == null){
				ip = getObjetoDOMUnvalidated(tipo, idPadre, groupA, user);
			}
			if(ip != null){
				InstanciaObjeto ih = getObjetoDOM(th, idHijo);
				if(ih != null){
					InstanciaAtributoComplejo ac = getComplexAttribute(tipo, tipoHijo, idPadre, idHijo, groupA, user);
					if(ac == null){
						ac = getComplexAttributeNotVal(tipo, tipoHijo, idPadre, idHijo, groupA, user);
					}
					if(ac != null){
						if(ac.getCreador() == user.getId()){
							return objetoDAO.setComplexAttributeTextReaded(tipo.getTipoDOM(),tipoHijo,idPadre,idHijo);
						}
						else{
							return "noOwner";
						}
					}
					else{
						return "noType";
					}
				}
				else{
					return "noType";
				}
			}
			else{
				return "noType";
			}
		}
		else{
			return "noType";
		}
	}

	public boolean isConPag(int tipoDOM, int idTipoAttr) {
		TipoObjeto to = objetoDAO.getObjectType(tipoDOM);
		if(to != null){
			List<TipoAtributoComplejo> ta = objetoDAO.getTiposAtributosCompleos(to);
			boolean enc = false;
			int i = 0;
			TipoAtributoComplejo tac = null;
			while(!enc &&  i < ta.size()){
				enc = ta.get(i).getIdTipoHijo() == idTipoAttr;
				tac = ta.get(i);
				i++;
			}
			if(enc){
				return tac.isConPaginaDoc();
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public Map<Integer,Integer> getStatisticsToValidate(Group g){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<TipoObjeto> tipos = getTiposObjetosDOM();
		for(TipoObjeto t : tipos){
			int a = objetoDAO.getNumUnvalidatedInstances(t.getTipoDOM(),g.getIdGrupo());
			a += objetoDAO.getNumValidatedInstancesWithUnvalidatedAC(t.getTipoDOM(), g.getIdGrupo());
			map.put(t.getTipoDOM(), a);
		}
		return map;
	}
	
	public Map<Integer,Integer> getStatisticsUsersToValidate(Group g){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<TipoObjeto> tipos = getTiposObjetosDOM();
		for(TipoObjeto t : tipos){
			int a = objetoDAO.getNumStudentsUnvalidatedInstances(t.getTipoDOM(), g.getIdGrupo());
			map.put(t.getTipoDOM(), a);
		}
		return map;
	}
	
	public Map<Integer,Integer> getUserStatisticsObjetsToVal(User u, Group g){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<TipoObjeto> tipos = getTiposObjetosDOM();
		for(TipoObjeto t : tipos){
			List<InstanciaObjeto> l = getStudentObjetoDOMList(t,u,g);
			int total = 0;
			for (InstanciaObjeto i : l){
				if(!i.isValidado()){
					total++;
				}
			}
			map.put(t.getTipoDOM(), total);
		}
		return map;
	}
	
	public Map<Integer,Integer> getUserStatisticsObjetsTotal(User u, Group g){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<TipoObjeto> tipos = getTiposObjetosDOM();
		for(TipoObjeto t : tipos){
			List<InstanciaObjeto> l = getStudentObjetoDOMList(t,u,g);
			int total = 0;
			for (InstanciaObjeto i : l){
				if(i.getCreador().getId() == u.getId()){
					total++;
				}
			}
			map.put(t.getTipoDOM(), total);
		}
		return map;
	}
	
	public Map<Integer,Integer> getUserStatisticsObjetsToValAC(User u, Group g){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<TipoObjeto> tipos = getTiposObjetosDOM();
		for(TipoObjeto t : tipos){
			List<InstanciaObjeto> l = getStudentObjetoDOMList(t,u,g);
			int total = 0;
			for (InstanciaObjeto i : l){
				i.setAtributosComplejos(objetoDAO.getAtributosComplejos(t, i.getIdInstancia()));
				if(i.getAtributosComplejos() != null){
					for(InstanciaAtributoComplejo iac : i.getAtributosComplejos()){
						if(iac.getCreador() == u.getId() && !iac.isValidado()){
							total++;
						}
					}
				}
			}
			map.put(t.getTipoDOM(), total);
		}
		return map;
	}
	
	public Map<Integer,Integer> getUserStatisticsObjetsTotalAC(User u, Group g){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<TipoObjeto> tipos = getTiposObjetosDOM();
		for(TipoObjeto t : tipos){
			List<InstanciaObjeto> l = getStudentObjetoDOMList(t,u,g);
			int total = 0;
			for (InstanciaObjeto i : l){
				i.setAtributosComplejos(objetoDAO.getAtributosComplejos(t, i.getIdInstancia()));
				if(i.getAtributosComplejos() != null){
					for(InstanciaAtributoComplejo iac : i.getAtributosComplejos()){
						if(iac.getCreador() == u.getId()){
							total++;
						}
					}
				}
			}
			map.put(t.getTipoDOM(), total);
		}
		return map;
	}

	public Object getStatisticsTotalInstancesPerType() {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<TipoObjeto> tipos = getTiposObjetosDOM();
		for(TipoObjeto t : tipos){
			List<InstanciaObjeto> lis = objetoDAO.getObjectListByTipe(t);
			map.put(t.getTipoDOM(), lis.size());
		}
		return map;
	}

	public boolean hasSimpleAttributes(TipoObjeto t) {
		TipoObjeto o2 = objetoDAO.getObjectType(t.getTipoDOM());
		if(o2 == null){
			return false;
		}
		else{
			return objetoDAO.numAtributosSencillos(t.getTipoDOM()) > 0;
		}
	}
}
