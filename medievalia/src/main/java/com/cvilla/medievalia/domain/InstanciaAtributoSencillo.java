package com.cvilla.medievalia.domain;

import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.SpecialDate;

public class InstanciaAtributoSencillo {
	private int tipoAtributo;
	private Object valor;
	private String nombreTipoAtributo;
	private int idAtributo;
	private int subtipo;
	
	public InstanciaAtributoSencillo() {
		super();
	}

	public int getTipoAtributo() {
		return tipoAtributo;
	}

	public void setTipoAtributo(int tipo) {
		this.tipoAtributo = tipo;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public String getNombreTipoAtributo() {
		return nombreTipoAtributo;
	}

	public void setNombreTipoAtributo(String nombreTipoAtributo) {
		this.nombreTipoAtributo = nombreTipoAtributo;
	}

	public int getIdAtributo() {
		return idAtributo;
	}

	public void setIdAtributo(int idAtributo) {
		this.idAtributo = idAtributo;
	}

	public int getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(int subtipo) {
		this.subtipo = subtipo;
	}
	
	@Override
	public String toString(){
		if(this.valor != null){
			if(this.tipoAtributo == Constants.TIPO_ATRIBUTO_DOUBLE){
				return ((Double)this.valor).toString();
			}
			else if(this.tipoAtributo == Constants.TIPO_ATRIBUTO_FECHA){
				SpecialDate sd = ((SpecialDate)this.valor);
				return sd.toString();
			}
			else if(this.tipoAtributo == Constants.TIPO_ATRIBUTO_INT){
				return ((Integer)this.valor).toString();
			}
			else if(this.tipoAtributo == Constants.TIPO_ATRIBUTO_STRING || this.tipoAtributo == Constants.TIPO_ATRIBUTO_TEXT){
				return (String)this.valor;
			}
			else if(this.tipoAtributo == Constants.TIPO_ATRIBUTO_OBJECT){
				return ((InstanciaObjeto)this.valor).getNombre();
			}
			else{
				return "";
			}
		}
		else{
			return "";
		}
	}
	
}
