package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Header;

@Component
public interface IHtmlManager extends Serializable {
	public  List<Header> getHeaders(int role, HttpServletRequest req);
	public ModelAndView processError(String message);
	public ModelAndView noPrivileges(User user, ILogManager logManager, int action, String message, HttpServletRequest req);
	public ModelAndView noPrivilegesA(User user, ILogManager logManager, int action, String message);
	public ModelAndView noPrivilegesJ(User user, ILogManager logManager, int action, String message);
	public ModelAndView paramError(ILogManager log,int idaction,int iduser);
	public String nullParameterString(HttpServletRequest request, String name, String defValue);
	public int nullParameterInt(HttpServletRequest request, String name, int defValue);
	public boolean isNumeric(String cadena);
}
