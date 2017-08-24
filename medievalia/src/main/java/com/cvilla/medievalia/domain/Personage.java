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
	private int anacimiento;
	private int mnacimiento;
	private int dnacimiento;
	private int afallecimiento;
	private int mfallecimiento;
	private int dfallecimiento;
	
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
	public int getAnacimiento() {
		return anacimiento;
	}
	public void setAnacimiento(int anacimiento) {
		this.anacimiento = anacimiento;
	}
	public int getMnacimiento() {
		return mnacimiento;
	}
	public void setMnacimiento(int mnacimiento) {
		this.mnacimiento = mnacimiento;
	}
	public int getDnacimiento() {
		return dnacimiento;
	}
	public void setDnacimiento(int dnacimiento) {
		this.dnacimiento = dnacimiento;
	}
	public int getAfallecimiento() {
		return afallecimiento;
	}
	public void setAfallecimiento(int afallecimiento) {
		this.afallecimiento = afallecimiento;
	}
	public int getMfallecimiento() {
		return mfallecimiento;
	}
	public void setMfallecimiento(int mfallecimiento) {
		this.mfallecimiento = mfallecimiento;
	}
	public int getDfallecimiento() {
		return dfallecimiento;
	}
	public void setDfallecimiento(int dfallecimiento) {
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
