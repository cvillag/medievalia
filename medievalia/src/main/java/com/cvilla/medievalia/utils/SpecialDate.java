package com.cvilla.medievalia.utils;

public class SpecialDate {
	private Integer anio;
	private Integer mes;
	private Integer dia;
	
	public SpecialDate() {
		super();
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	
	@Override
	public String toString(){
		if(anio != null){
			if(mes != null){
				if(dia != null){
					return dia + " - " + mes + " - " + anio;
				}
				else{
					return mes + " - " + anio;
				}
			}
			else{
				return anio.toString();
			}
		}
		else{
			return " - ";
		}
	}

}
