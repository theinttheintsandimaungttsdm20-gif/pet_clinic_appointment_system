package com.coder.dao;

import org.springframework.stereotype.Repository;

import com.coder.model.Scheduleservice;

@Repository("scheduleServiceDao")
public class ScheduleServiceDao extends AbstractDao<Integer,Scheduleservice>{
	
	public Object saveScheduleservice(Scheduleservice scheduleservice)
	  {
	  return (Object)super.persistMtoM(scheduleservice);
      }
	
	public Scheduleservice getScheduleserviceById(int id){
		Scheduleservice scheduleservice=super.criteriaQuerryGetObjectById(id,"doctorserviceId");
		return scheduleservice;
		}
	
	public void  updateScheduleservice(Scheduleservice scheduleservice)
       {
       super.update(scheduleservice);
       }

public Boolean deleteScheduleservice(Scheduleservice scheduleservice){
	return super.delete(scheduleservice);
}

}
