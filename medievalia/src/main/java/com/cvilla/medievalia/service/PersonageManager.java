package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IPersonageDAO;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Personage;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.IPersonageManager;
import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.Fechas;

public class PersonageManager implements IPersonageManager {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IPersonageDAO persdao;
	
	@Autowired
	private IGroupManager groupManager;

	public List<Personage> getPersonageList() {
		return persdao.getPersonageList();
	}

	public Personage getPersonage(int idPers) {
		return persdao.getPersonage(idPers);
	}
	
	public Personage getPersonageByName(String name) {
		return persdao.getPersonage(name);
	}

	public String addPersonage(Personage p, Group groupA, User user) {
		if(persdao.nameRepeat(p.getNombre())){
			return "nombreRepetido";
		}
		else{
			if(Fechas.fechaIncorrecta(p.getDnacimiento(),p.getMnacimiento(), p.getAnacimiento())){
				return "fechaIncorrecta";
			}
			else{
				if(Fechas.fechaIncorrecta(p.getDfallecimiento() ,p.getMfallecimiento(), p.getAfallecimiento())){
					return "fechaIncorrecta";
				}
				else{
					if(p.getNombre().length() < Constants.MIN_PERSONAGE_NAME){
						return "nombreCorto";
					}
					else{
						if(user.getUser_role() == Constants.ROLE_PROFESOR){
							p.setValidado(Constants.OBJETO_VALIDADO);
						}
						else{
							p.setValidado(Constants.OBJETO_NO_VALIDADO);
						}
						return persdao.addPersonage(p);
					}
				}
			}
		}
	}

	public String deleteCharacter(Personage c, User user, Group groupA) {
		if(c.getIdGrupo() != groupA.getIdGrupo() || !groupManager.isTeacherOrDirector(user, c.getIdGrupo())){
			return "noGroup";
		}
		else{
			return persdao.deleteCharacter(c);
		}
	}

	public String deleteOwnCharacter(Personage c, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String renameCharacter(String nombre, int idPers, User user,
			Group groupA) {
		Personage p = persdao.getPersonage(idPers);
		if(p == null){
			return "noExist";
		}
		else{
			if(p.getIdGrupo() != groupA.getIdGrupo()){
				return "noGroup";
			}
			else{
				if(nombre.length() < Constants.MIN_PERSONAGE_NAME){
					return "nombreCorto";
				}
				else{
					if(persdao.nameRepeat(nombre)){
						return "nameExist";
					}
					else{
						return persdao.renamePersonage(idPers, nombre);
					}
				}
			}
		}
	}

	public String renameCharacterOwn(String nombre, int idPers, User user,
			Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}
}
