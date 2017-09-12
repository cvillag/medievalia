package com.cvilla.medievalia.domain;

import java.util.List;

import com.cvilla.medievalia.utils.Constants;

public class ObjetoDOM {
	
	private int idInstancia;
	private TipoObjetoDOM tipo;
	private String nombre;
	private List<AtributoSencilloDOM> atributosSencillos;
	private List<ObjetoDOM> atributosComplejos;
	private int validado;
	private int creador;
	private String textoValidacion;
	private int grupo;
	
	public ObjetoDOM() {
		super();
	}

		public int getIdInstancia() {
		return idInstancia;
	}
		
	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public int getCreador() {
			return creador;
	}

	public void setCreador(int creador) {
		this.creador = creador;
	}

	public String getTextoValidacion() {
		return textoValidacion;
	}

	public void setTextoValidacion(String textoValidacion) {
		this.textoValidacion = textoValidacion;
	}

	public void setIdInstancia(int idInstancia) {
		this.idInstancia = idInstancia;
	}

	public int getIdObjetoDOM() {
		return idInstancia;
	}

	public void setIdObjetoDOM(int idObjetoDOM) {
		this.idInstancia = idObjetoDOM;
	}

	public List<AtributoSencilloDOM> getAtributosSencillos() {
		return atributosSencillos;
	}

	public void setAtributosSencillos(List<AtributoSencilloDOM> atributosSencillos) {
		this.atributosSencillos = atributosSencillos;
	}

	public List<ObjetoDOM> getAtributosComplejos() {
		return atributosComplejos;
	}

	public void setAtributosComplejos(List<ObjetoDOM> atributosComplejos) {
		this.atributosComplejos = atributosComplejos;
	}

	public TipoObjetoDOM getTipo() {
		return tipo;
	}

	public void setTipo(TipoObjetoDOM tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValidado() {
		return validado;
	}

	public void setValidado(int validado) {
		this.validado = validado;
	}
	
	public boolean isValidado(){
		return this.validado == Constants.OBJETO_VALIDADO;
	}
}
