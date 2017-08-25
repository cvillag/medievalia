package com.cvilla.medievalia.domain;

public class Personage {

	private int idPersonaje;
	private int idGrupo;
	private int creador;
	private String nombreCreador;
	private String nombre;
	private String otros;
	private int validado;
	private int lugarNacimiento;
	private int lugarFallecimiento;
	private Integer anacimiento;
	private Integer mnacimiento;
	private Integer dnacimiento;
	private Integer afallecimiento;
	private Integer mfallecimiento;
	private Integer dfallecimiento;
	
	public Personage() {
		super();
	}
	public int getIdPersonaje() {
		return idPersonaje;
	}
	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
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
	public String getNombreCreador() {
		return nombreCreador;
	}
	public void setNombreCreador(String nombreCreador) {
		this.nombreCreador = nombreCreador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
	public int getValidado() {
		return validado;
	}
	public void setValidado(int validado) {
		this.validado = validado;
	}
	public int getLugarNacimiento() {
		return lugarNacimiento;
	}
	public void setLugarNacimiento(int lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}
	public int getLugarFallecimiento() {
		return lugarFallecimiento;
	}
	public void setLugarFallecimiento(int lugarFallecimiento) {
		this.lugarFallecimiento = lugarFallecimiento;
	}
	public Integer getAnacimiento() {
		return anacimiento;
	}
	public void setAnacimiento(Integer anacimiento) {
		this.anacimiento = anacimiento;
	}
	public Integer getMnacimiento() {
		return mnacimiento;
	}
	public void setMnacimiento(Integer mnacimiento) {
		this.mnacimiento = mnacimiento;
	}
	public Integer getDnacimiento() {
		return dnacimiento;
	}
	public void setDnacimiento(Integer dnacimiento) {
		this.dnacimiento = dnacimiento;
	}
	public Integer getAfallecimiento() {
		return afallecimiento;
	}
	public void setAfallecimiento(Integer afallecimiento) {
		this.afallecimiento = afallecimiento;
	}
	public Integer getMfallecimiento() {
		return mfallecimiento;
	}
	public void setMfallecimiento(Integer mfallecimiento) {
		this.mfallecimiento = mfallecimiento;
	}
	public Integer getDfallecimiento() {
		return dfallecimiento;
	}
	public void setDfallecimiento(Integer dfallecimiento) {
		this.dfallecimiento = dfallecimiento;
	}
	public void setFallecimiento(int a, int m, int d){
		this.afallecimiento = a;
		this.mfallecimiento = m;
		this.dfallecimiento = d;
	}
	public void setNacimiento(int a, int m, int d){
		this.anacimiento = a;
		this.mnacimiento = m;
		this.dnacimiento = d;
	}
}
