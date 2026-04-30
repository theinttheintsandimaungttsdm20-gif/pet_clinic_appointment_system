package com.coder.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coder.model.Doctor;
import com.coder.model.DoctorSchedule;
import com.coder.model.serviceType;
@Repository("doctorScheduleDao")
public class DoctorScheduleDao extends AbstractDao<Integer,DoctorSchedule>{
	
	public Integer saveDoctorSchedule(DoctorSchedule doctorSchedule)
	{
	return (Integer)super.persist(doctorSchedule);

		}
	public DoctorSchedule getDoctorScheduleById(int id){
		DoctorSchedule doctorSchedule=super.criteriaQuerryGetObjectById(id,"scheduleId");
		return doctorSchedule;
		}
	public List<DoctorSchedule> getDoctorScheduleByDay(String day){
		List<DoctorSchedule> doctorSchedules=super.criteriaQuerryGetObjectsByName(day,"dayName");
		return doctorSchedules;
	}
  
public void  updateDoctorSchedule(DoctorSchedule doctorSchedule)
{
super.update(doctorSchedule);

}
public void  deleteDoctorSchedule(DoctorSchedule doctorSchedule)
{
super.delete(doctorSchedule);

}

public List<DoctorSchedule> getDoctorSchedules() {
	List<DoctorSchedule> doctorSchedule=(List<DoctorSchedule>)super.getAllEntity();
		return doctorSchedule;
	}

}
