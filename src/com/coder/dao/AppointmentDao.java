package com.coder.dao;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coder.model.Appointment;
import com.coder.model.Doctor;
import com.coder.model.Owner;
import com.coder.model.serviceType;
@Repository("appointmentDao")
public class AppointmentDao extends AbstractDao<Integer,Appointment>{
	
	public Integer saveAppointment(Appointment appointment)
	{
	return (Integer)super.persist(appointment);
    }
	
	public Appointment getAppointmentById(int id){
		Appointment appointment=super.criteriaQuerryGetObjectById(id,"appointmentId");
	return appointment;}
	
	public List<Appointment> getAppointmentByNow(Date date){
		System.out.println("getAppointmentByNow:date="+date);
		//List<Appointment> appointments=(List<Appointment>) super.getObjectTwoParam(scheduleId,"doctorSchedule.scheduleId", date,"appointmentDate");
		List<Appointment> appointments=super.criteriaQuerryGetObjectsByDate(date,"appointmentDate");
		//System.out.println("Nowappointments="+appointments.size());
		return appointments;
	}
	public List<Appointment> getAppointmentByNowApptime(Date date){
		System.out.println("getAppointmentByNow:date="+date);
		//List<Appointment> appointments=(List<Appointment>) super.getObjectTwoParam(scheduleId,"doctorSchedule.scheduleId", date,"appointmentDate");
		List<Appointment> appointments=super.criteriaQuerryGetObjectsByDates(date,"appointmentDate","appointmentTime",null);
		System.out.println("Nowappointments="+appointments.size());
		return appointments;
	}
  
public void  updateAppointment(Appointment appointment)
{
super.update(appointment);

}
public void  deleteAppointment(Appointment appointment)
{
super.delete(appointment);

}

public List<Appointment> getAppointments() {
	List<Appointment> appointment=(List<Appointment>)super.getAllEntity();
		return appointment;
	}

}
