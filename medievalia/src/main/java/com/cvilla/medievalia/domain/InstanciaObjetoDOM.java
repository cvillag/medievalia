package com.cvilla.medievalia.domain;

import java.util.List;

import com.cvilla.medievalia.utils.Constants;

public class InstanciaObjetoDOM {
	
	private int idInstancia;
	private TipoObjetoDOM tipo;
	private String nombre;
	private List<InstanciaAtributoSencilloDOM> atributosSencillos;
	private List<InstanciaAtributoComplejoDOM> atributosComplejos;
	private int validado;
	private User creador;
	private String textoValidacion;
	private int grupo;
	private int textoLeido;
	
	public InstanciaObjetoDOM() {
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

	public List<InstanciaAtributoSencilloDOM> getAtributosSencillos() {
		return atributosSencillos;
	}

	public void setAtributosSencillos(List<InstanciaAtributoSencilloDOM> atributosSencillos) {
		this.atributosSencillos = atributosSencillos;
	}

	public List<InstanciaAtributoComplejoDOM> getAtributosComplejos() {
		return atributosComplejos;
	}

	public void setAtributosComplejos(List<InstanciaAtributoComplejoDOM> atributosComplejos) {
		this.atributosComplejos = atributosComplejos;
	}

	public int getValidado() {
		return validado;
	}

	public void setValidado(int validado) {
		this.validado = validado;
	}

	public User getCreador() {
		return creador;
	}

	public void setCreador(User creador) {
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

	public boolean isTextoLeido() {
		return textoLeido == 1;
	}

	public void setTextoLeido(int textoLeido) {
		this.textoLeido = textoLeido;
	}
	
	public int getTextoLeido(){
		return this.textoLeido;
	}
}