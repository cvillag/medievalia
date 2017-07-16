package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IChargeDAO;
import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.service.intf.IChargeManager;

public class ChargeManager implements IChargeManager{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IChargeDAO chargedao;
	
	@Autowired
	public IChargeDAO  getAuthdao() {
		return chargedao;
	}
	
	@Autowired
	public void setAuthdao(IChargeDAO authdao) {
		this.chargedao = authdao;
	}
	
	public List<Charge> getChargeList() {
		return chargedao.getChargeList();
	}
}
