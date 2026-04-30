package com.coder.dao;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.coder.model.Appointment;
import com.coder.model.Appointmentservice;
import com.coder.model.Doctor;
import com.coder.model.Owner;
import com.coder.model.serviceType;

@Repository("appointmentServiceDao")
public class AppointmentServiceDao extends AbstractDao<Integer,Appointmentservice>{
	
	public void saveAppointmentservice(Appointmentservice appointmentservice)
	{
	 super.persistVoid(appointmentservice);
    }
	
	public Appointmentservice getAppoimentserviceById(int id){
		Appointmentservice appoimentservice=super.criteriaQuerryGetObjectById(id,"appointmentId");
	return appoimentservice;}
  
public void  updateAppointmentservice(Appointmentservice appointmentservice)
{
super.update(appointmentservice);

}
public void  deleteAppointmentservice(Appointmentservice appointmentservice)
{
super.delete(appointmentservice);

}

public List<Appointmentservice> getAppointmentservices() {
	List<Appointmentservice> appointmentservices=(List<Appointmentservice>)super.getAllEntity();
		return appointmentservices;
	}

}
