package com.cvilla.medievalia.domain;

import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.SpecialDate;

public class InstanciaAtributoComplejoDOM {
	
	private TipoObjetoDOM tipoPadre;
	private TipoObjetoDOM tipoHijo;
	private InstanciaObjetoDOM instanciaHijo;
	private String nombreAtributo;
	private int validado;
	private String textoValidacion;
	private int idGrupo;
	private int creador;
	private int textoLeido;
	private Integer idTipoObjetoRelacion;
	private InstanciaObjetoDOM instanciaObjetoRelacion;
	private int conFecha;
	private SpecialDate fechaInicio;
	private SpecialDate fechaFin;
	
	public InstanciaAtributoComplejoDOM() {
		super();
	}
	public InstanciaAtributoComplejoDOM(TipoObjetoDOM tipoPadre) {
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
	public InstanciaObjetoDOM getInstanciaHijo() {
		return instanciaHijo;
	}
	public void setInstanciaHijo(InstanciaObjetoDOM instanciaHijo) {
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
	public int getTextoLeido() {
		return textoLeido;
	}
	public void setTextoLeido(int textoLeido) {
		this.textoLeido = textoLeido;
	}
	public boolean isTextoLeido(){
		return this.textoLeido == 1 || this.textoValidacion.length() < 1;
	}
	public InstanciaObjetoDOM getInstanciaObjetoRelacion() {
		return instanciaObjetoRelacion;
	}
	public void setInstanciaObjetoRelacion(InstanciaObjetoDOM idInstanciaObjetoRelacion) {
		this.instanciaObjetoRelacion = idInstanciaObjetoRelacion;
	}
	public Integer getIdTipoObjetoRelacion() {
		return idTipoObjetoRelacion;
	}
	public void setIdTipoObjetoRelacion(Integer idTipoObjetoRelacion) {
		this.idTipoObjetoRelacion = idTipoObjetoRelacion;
	}
	public int getConFecha() {
		return conFecha;
	}
	public void setConFecha(int conFecha) {
		this.conFecha = conFecha;
	}
	public SpecialDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(SpecialDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public SpecialDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(SpecialDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public boolean hasDate(){
		return conFecha == 1;
	}
}
