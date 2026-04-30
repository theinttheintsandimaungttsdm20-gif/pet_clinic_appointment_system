package com.coder.form;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.coder.model.service;
import com.coder.model.serviceType;

public class ServiceRegisterForm {
private serviceForm serviceForm;
private List<service> services;
private serviceType selectserviceType;
private Map<String,String> mapserviceTypes=new HashedMap<String,String>();
public serviceForm getserviceForm() {
	return serviceForm;
}
public void setserviceForm(serviceForm serviceForm) {
	this.serviceForm = serviceForm;
}

public List<service> getservices() {
	return services;
}
public void setservices(List<service> services) {
	this.services = services;
}
public serviceType getSelectserviceType() {
	return selectserviceType;
}
public void setSelectserviceType(serviceType selectserviceType) {
	this.selectserviceType = selectserviceType;
}
public Map<String, String> getMapserviceTypes() {
	return mapserviceTypes;
}
public void setMapserviceTypes(Map<String, String> mapserviceTypes) {
	this.mapserviceTypes = mapserviceTypes;
}
}
