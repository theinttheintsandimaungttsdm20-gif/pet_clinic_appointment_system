package com.coder.form;

import java.util.List;

import com.coder.model.serviceType;

public class ServiceTypeRegisterForm {
private ServiceTypeForm serviceTypeForm;
private List<serviceType> serviceTypes;

public ServiceTypeForm getserviceTypeForm() {
	return serviceTypeForm;
}

public void setserviceTypeForm(ServiceTypeForm serviceTypeForm) {
	this.serviceTypeForm = serviceTypeForm;
}

public List<serviceType> getserviceTypes() {
	return serviceTypes;
}

public void setserviceTypes(List<serviceType> serviceTypes) {
	this.serviceTypes = serviceTypes;
}


}
