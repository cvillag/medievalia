package com.cvilla.medievalia.utils;

import java.util.List;

public class Header {

	private String serviceTag;
	private String serviceName;
	private String serviceUrl;
	private List<Header> sons;
	
	public Header(String serviceT, String serviceName, String serviceUrl, List<Header> sons) {
		super();
		this.serviceTag = serviceT;
		this.serviceName = serviceName;
		this.serviceUrl = serviceUrl;
		this.sons = sons;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public List<Header> getSons() {
		return sons;
	}
	public void setSons(List<Header> sons) {
		this.sons = sons;
	}
	public String getServiceTag() {
		return serviceTag;
	}
	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
	}
	
}
