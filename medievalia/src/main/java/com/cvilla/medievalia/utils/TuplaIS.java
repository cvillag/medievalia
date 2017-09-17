package com.cvilla.medievalia.utils;

public class TuplaIS {
	
	private String s;
	private Integer i;
	public TuplaIS() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public Integer getI() {
		return i;
	}
	public void setI(Integer i) {
		this.i = i;
	}
	
	public boolean equals(Object o){
		if(o instanceof TuplaIS){
			TuplaIS o2 = (TuplaIS)o;
			return this.i.equals(o2.getI()) && this.s.equals(o2.getS());
		}
		else
			return false;
	}
}
