package com.coder.form;

public class ServiceTypeForm {
	private String serviceTypeId;
	private String serviceTypeName;
	private String serviceTypeDesc;
	private String averageTime;
	public String getserviceTypeName() {
		return serviceTypeName;
	}
	public void setserviceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	public String getserviceTypeDesc() {
		return serviceTypeDesc;
	}
	public void setserviceTypeDesc(String serviceTypeDesc) {
		this.serviceTypeDesc = serviceTypeDesc;
	}
	public String getserviceTypeId() {
		return serviceTypeId;
	}
	public void setserviceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public String getAverageTime() {
		return averageTime;
	}
	public void setAverageTime(String averageTime) {
		this.averageTime = averageTime;
	}
}
