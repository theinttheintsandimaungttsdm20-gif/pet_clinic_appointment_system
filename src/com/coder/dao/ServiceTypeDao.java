package com.coder.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.coder.model.serviceType;
@Repository("serviceTypeDao")
public class ServiceTypeDao extends AbstractDao<Integer,serviceType>{
	
	public Integer saveserviceType(serviceType serviceType)
	{
	return (Integer)super.persist(serviceType);

		}
	public serviceType getserviceTypeById(int id){
		serviceType serviceType=super.criteriaQuerryGetObjectById(id,"serviceTypeId");
		return serviceType;
		}
	public serviceType getserviceTypeByName(String name){
		serviceType serviceType=super.criteriaQuerryGetObjectByName(name,"serviceTypeName");
		return serviceType;
		}
  
public void  updateserviceType(serviceType serviceType)
{
super.update(serviceType);

}
public void  deleteserviceType(serviceType serviceType)
{
super.delete(serviceType);

}

public List<serviceType> getserviceTypes() {
	List<serviceType> serviceType=(List<serviceType>)super.getAllEntity();
		return serviceType;
	}

}
