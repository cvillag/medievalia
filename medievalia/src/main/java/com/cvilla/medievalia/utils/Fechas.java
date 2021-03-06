package com.cvilla.medievalia.utils;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

public class Fechas {
	
	public static SpecialDate getDate(HttpServletRequest request, String x){
		SpecialDate date = new SpecialDate();
		String d = request.getParameter("dia" + x);
		if(d != null && Constants.isNumeric(d)){
			date.setDia(new Integer(d));
		}
		String m = request.getParameter("mes" + x);
		if(m != null && Constants.isNumeric(m)){
			date.setMes(new Integer(m));
		}
		String a = request.getParameter("anio" + x);
		if(a != null && Constants.isNumeric(a)){
			date.setAnio(new Integer(a));
		}
		return date;
	}
	
	public static boolean fechaIncorrecta(SpecialDate d){
		return fechaIncorrecta(d.getDia(),d.getMes(),d.getAnio());
	}

	public static boolean fechaIncorrecta(Integer dia, Integer mes, Integer año){
		if(dia == null || mes == null || año == null){
			if(año != null || (año != null && mes != null && mes <= 12 && mes > 0)){
				return false;
			}
			else if (dia == null && mes == null && año == null){
				return false;
			}
				
			else{
				return true;
			}
		}
		else{
			int m = mes.intValue();
			int d = dia.intValue();
			int a = año.intValue();
			if(a == 0){
				return true;
			}
			else{
				if(m > 12 || m < 1){
					return true;
				}
				else{
					if(d > 31 || d < 1){
						return true;
					}
					else{
						GregorianCalendar c = new GregorianCalendar();
						if(c.isLeapYear(a)){
							if(d > 29 && m == 2){
								return true;
							}
							else{
								return (m == 4 || m == 6 || m == 9 || m == 11) && d > 30;
							}
						}
						else{
							if(d > 28 && m == 2){
								return true;
							}
							else{
								return (m == 4 || m == 6 || m == 9 || m == 11) && d > 30;
							}
						}
					}
				}
			}
		}
	}
}
