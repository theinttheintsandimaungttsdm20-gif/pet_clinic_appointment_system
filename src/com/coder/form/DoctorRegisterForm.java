package com.coder.form;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.coder.model.Doctor;
import com.coder.model.serviceType;

public class DoctorRegisterForm {
private DoctorForm doctorForm;
private List<Doctor> doctors;
private Map<String,String> mapRanks=new HashedMap<String,String>();
private Map<String,String> mapserviceTypes=new HashedMap<String,String>();
public DoctorForm getDoctorForm() {
	return doctorForm;
}
public void setDoctorForm(DoctorForm doctorForm) {
	this.doctorForm = doctorForm;
}

public Map<String, String> getMapRanks() {
	return mapRanks;
}
public void setMapRanks(Map<String, String> mapRanks) {
	this.mapRanks = mapRanks;
}
public Map<String, String> getMapserviceTypes() {
	return mapserviceTypes;
}
public void setMapserviceTypes(Map<String, String> mapserviceTypes) {
	this.mapserviceTypes = mapserviceTypes;
}
public List<Doctor> getDoctors() {
	return doctors;
}
public void setDoctors(List<Doctor> doctors) {
	this.doctors = doctors;
}

}
