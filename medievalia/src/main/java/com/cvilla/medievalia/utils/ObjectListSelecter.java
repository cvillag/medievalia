package com.cvilla.medievalia.utils;

import java.util.ArrayList;
import java.util.List;

import com.cvilla.medievalia.domain.AtributoComplejoDOM;

public class ObjectListSelecter {
	
	public static List<TuplaIS> getDifferentTypesI(List<AtributoComplejoDOM> l){
		List<TuplaIS> ret = new ArrayList<TuplaIS>();
		for(AtributoComplejoDOM ac : l){
			TuplaIS t = new TuplaIS();
			t.setI(ac.getTipoHijo().getTipoDOM());
			t.setS(ac.getNombreAtributo());
			if(!ret.contains(t)){
				ret.add(t);
			}
		}
		return ret;
	}
}
