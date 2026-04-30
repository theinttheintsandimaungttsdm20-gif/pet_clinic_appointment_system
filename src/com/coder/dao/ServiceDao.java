package com.coder.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coder.model.service;
@Repository("serviceDao")
public class ServiceDao extends AbstractDao<Integer,service>{
	
	public Integer saveservice(service service)
	{
	return (Integer)super.persist(service);

		}
	public service getserviceById(int id){
		service service=super.criteriaQuerryGetObjectById(id,"serviceId");
		return service;
		}
  
public void  updateservice(service service)
{
super.update(service);

}
public void  deleteservice(service service)
{
super.delete(service);

}

public List<service> getservices() {
	List<service> service=(List<service>)super.getAllEntity();
		return service;
	}

}
