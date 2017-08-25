package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IPersonageDAO;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Personage;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IPersonageManager;
import com.cvilla.medievalia.utils.Constants;

public class PersonageManager implements IPersonageManager {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IPersonageDAO persdao;

	public List<Personage> getPersonageList() {
		return persdao.getPersonageList();
	}

	public Personage getPersonage(int idPers) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addPersonage(Personage p, Group groupA, User user) {
		if(persdao.nameRepeat(p.getNombre())){
			return "nombreRepetido";
		}
		else{
			if(p.getDnacimiento() != null && (p.getDnacimiento() < 1 ||  p.getDnacimiento() > 31 )){
				return "fechaIncorrecta";
			}
			else{
				if(p.getDfallecimiento() != null && (p.getDfallecimiento() < 1 || p.getDfallecimiento() > 31)){
					return "fechaIncorrecta";
				}
				else{
					if(p.getMnacimiento() != null && (p.getMnacimiento() < 1 ||  p.getMnacimiento() > 12 )){
						return "fechaIncorrecta";
					}
					else{
						if(p.getMfallecimiento() != null && (p.getMfallecimiento() < 1 || p.getMfallecimiento() > 12)){
							return "fechaIncorrecta";
						}
						else{
							if(p.getAfallecimiento() != null && p.getAfallecimiento() == 0){
								return "fechaIncorrecta";
							}
							else{
								if(p.getAnacimiento() != null && p.getAnacimiento() == 0){
									return "fechaIncorrecta";
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
			}
		}
	}

}
