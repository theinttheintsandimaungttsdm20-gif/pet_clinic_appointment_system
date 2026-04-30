package com.coder.form;

import java.util.Date;

import com.coder.model.Doctor;
import com.coder.model.DoctorSchedule;
import com.coder.model.Owner;
import com.coder.model.service;

public class AppointmentForm {
	private String ownerId;
	private String appointmentId;
	private String petId;
	private String[] selectedservicees;
	private String serviceTypeId;
	private String appointmentDate;
	private String appointmentTime;
	private String appointmentInfo;
	public String getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getAppointmentInfo() {
		return appointmentInfo;
	}
	public void setAppointmentInfo(String appointmentInfo) {
		this.appointmentInfo = appointmentInfo;
	}
	public String getserviceTypeId() {
		return serviceTypeId;
	}
	public void setserviceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public String[] getSelectedservicees() {
		return selectedservicees;
	}
	public void setSelectedservicees(String[] selectedservicees) {
		this.selectedservicees = selectedservicees;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getPetId() {
		return petId;
	}
	public void setPetId(String petId) {
		this.petId = petId;
	}
}
