package com.cvilla.medievalia.domain;

import com.cvilla.medievalia.utils.Constants;

public class AtributoComplejoDOM {
	
	private TipoObjetoDOM tipoPadre;
	private TipoObjetoDOM tipoHijo;
	private ObjetoDOM instanciaHijo;
	private String nombreAtributo;
	private int validado;
	private String textoValidacion;
	private int idGrupo;
	private int creador;
	
	public AtributoComplejoDOM() {
		super();
	}
	public AtributoComplejoDOM(TipoObjetoDOM tipoPadre) {
		super();
		this.tipoPadre = tipoPadre;
	}
	public TipoObjetoDOM getTipoPadre() {
		return tipoPadre;
	}
	public void setTipoPadre(TipoObjetoDOM tipoPadre) {
		this.tipoPadre = tipoPadre;
	}
	public TipoObjetoDOM getTipoHijo() {
		return tipoHijo;
	}
	public void setTipoHijo(TipoObjetoDOM tipoHijo) {
		this.tipoHijo = tipoHijo;
	}
	public ObjetoDOM getInstanciaHijo() {
		return instanciaHijo;
	}
	public void setInstanciaHijo(ObjetoDOM instanciaHijo) {
		this.instanciaHijo = instanciaHijo;
	}
	public String getNombreAtributo() {
		return nombreAtributo;
	}
	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}
	public int getValidado() {
		return validado;
	}
	public void setValidado(int validado) {
		this.validado = validado;
	}
	public String getTextoValidacion() {
		return textoValidacion;
	}
	public void setTextoValidacion(String textoValidacion) {
		this.textoValidacion = textoValidacion;
	}
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public int getCreador() {
		return creador;
	}
	public void setCreador(int creador) {
		this.creador = creador;
	}
	public boolean isValidado(){
		return this.validado == Constants.OBJETO_VALIDADO;
	}

}
