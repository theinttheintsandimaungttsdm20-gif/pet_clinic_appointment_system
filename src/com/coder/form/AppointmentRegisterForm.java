package com.coder.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.coder.model.Appointment;
import com.coder.model.DoctorSchedule;

public class AppointmentRegisterForm {
private String OwnerId;
private String serviceTypeId;
private String doctorScheduleId;
private String tokenNo;
private DoctorSchedule doctorSchedule;
private AppointmentForm appointmentForm;
private List<Appointment> appointments;
private ArrayList<String> servicees=new ArrayList<>();
private ArrayList<Integer> timeSlots=new ArrayList<>();
private Map<String,String> mapPets=new HashedMap<String,String>();
private Map<String,String> mapserviceTypes=new HashedMap<String,String>();
private Map<String,String> mapservicees=new HashedMap<String,String>();
private List<DoctorSchedule> doctorSchedules;
private ArrayList<DoctorScheduleForm> doctorScheduleForms=new ArrayList<>();
public Map<String, String> getMapservicees() {
	return mapservicees;
}
public void setMapservicees(Map<String, String> mapservicees) {
	this.mapservicees = mapservicees;
}
public Map<String, String> getMapserviceTypes() {
	return mapserviceTypes;
}
public void setMapserviceTypes(Map<String, String> mapserviceTypes) {
	this.mapserviceTypes = mapserviceTypes;
}
public List<DoctorSchedule> getDoctorSchedules() {
	return doctorSchedules;
}
public void setDoctorSchedules(List<DoctorSchedule> doctorSchedules) {
	this.doctorSchedules = doctorSchedules;
}

public String getDoctorScheduleId() {
	return doctorScheduleId;
}
public void setDoctorScheduleId(String doctorScheduleId) {
	this.doctorScheduleId = doctorScheduleId;
}
public String getserviceTypeId() {
	return serviceTypeId;
}
public void setserviceTypeId(String serviceTypeId) {
	this.serviceTypeId = serviceTypeId;
}
public AppointmentForm getAppointmentForm() {
	return appointmentForm;
}
public void setAppointmentForm(AppointmentForm appointmentForm) {
	this.appointmentForm = appointmentForm;
}
public ArrayList<Integer> getTimeSlots() {
	return timeSlots;
}
public void setTimeSlots(ArrayList<Integer> timeSlots) {
	this.timeSlots = timeSlots;
}
public List<Appointment> getAppointments() {
	return appointments;
}
public void setAppointments(List<Appointment> appointments) {
	this.appointments = appointments;
}
public ArrayList<DoctorScheduleForm> getDoctorScheduleForms() {
	return doctorScheduleForms;
}
public Map<String, String> getMapPets() {
	return mapPets;
}
public void setMapPets(Map<String, String> mapPets) {
	this.mapPets = mapPets;
}
public void setDoctorScheduleForms(ArrayList<DoctorScheduleForm> doctorScheduleForms) {
	this.doctorScheduleForms = doctorScheduleForms;
}
public String getOwnerId() {
	return OwnerId;
}
public void setOwnerId(String ownerId) {
	OwnerId = ownerId;
}
public String getTokenNo() {
	return tokenNo;
}

public ArrayList<String> getservicees() {
	return servicees;
}
public void setservicees(ArrayList<String> servicees) {
	this.servicees = servicees;
}
public DoctorSchedule getDoctorSchedule() {
	return doctorSchedule;
}
public void setTokenNo(String tokenNo) {
	this.tokenNo = tokenNo;
}
public void setDoctorSchedule(DoctorSchedule doctorSchedule) {
	this.doctorSchedule = doctorSchedule;
}

}
