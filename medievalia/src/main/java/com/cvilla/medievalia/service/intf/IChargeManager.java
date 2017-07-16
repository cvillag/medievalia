package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Charge;

@Component
public interface IChargeManager extends Serializable{
	
	public List<Charge> getChargeList();
}
