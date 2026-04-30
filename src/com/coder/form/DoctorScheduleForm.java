package com.coder.form;

import java.util.Date;

import com.coder.model.Doctor;

public class DoctorScheduleForm {
	public int getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	private String scheduleId;
	private String dayName;
	private String startTime;
	private String endTime;
	private int timeSlot;
	private String[] selectserviceTypes;
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String[] getSelectserviceTypes() {
		return selectserviceTypes;
	}
	public void setSelectserviceTypes(String[] selectserviceTypes) {
		this.selectserviceTypes = selectserviceTypes;
	}
	
	
}
