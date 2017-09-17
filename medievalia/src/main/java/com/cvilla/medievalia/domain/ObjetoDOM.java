package com.cvilla.medievalia.domain;

import java.util.List;

import com.cvilla.medievalia.utils.Constants;

public class ObjetoDOM {
	
	private int idInstancia;
	private TipoObjetoDOM tipo;
	private String nombre;
	private List<AtributoSencilloDOM> atributosSencillos;
	private List<AtributoComplejoDOM> atributosComplejos;
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

	public void setIdInstancia(int idInstancia) {
		this.idInstancia = idInstancia;
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

	public List<AtributoSencilloDOM> getAtributosSencillos() {
		return atributosSencillos;
	}

	public void setAtributosSencillos(List<AtributoSencilloDOM> atributosSencillos) {
		this.atributosSencillos = atributosSencillos;
	}

	public List<AtributoComplejoDOM> getAtributosComplejos() {
		return atributosComplejos;
	}

	public void setAtributosComplejos(List<AtributoComplejoDOM> atributosComplejos) {
		this.atributosComplejos = atributosComplejos;
	}

	public int getValidado() {
		return validado;
	}

	public void setValidado(int validado) {
		this.validado = validado;
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

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public boolean isValidado(){
		return this.validado == Constants.OBJETO_VALIDADO;
	}
}