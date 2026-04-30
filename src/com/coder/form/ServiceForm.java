package com.coder.form;

public class ServiceForm {
	private String serviceId;
	private String serviceTypeId;
	private String serviceName;
	private String servicePoint;
	private String serviceMinute;
	private String serviceAbout;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceAbout() {
		return serviceAbout;
	}
	public void setServiceAbout(String serviceAbout) {
		this.serviceAbout = serviceAbout;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServicePoint() {
		return servicePoint;
	}
	public void setServicePoint(String servicePoint) {
		this.servicePoint = servicePoint;
	}
	public String getServiceMinute() {
		return serviceMinute;
	}
	public void setServiceMinute(String serviceMinute) {
		this.serviceMinute = serviceMinute;
	}
	public String getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
}
