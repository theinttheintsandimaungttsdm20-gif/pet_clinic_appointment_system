package com.coder.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.coder.model.Doctorservice;

@Repository("doctorServiceDoa")
public class DoctorServiceDao extends AbstractDao<Integer,Doctorservice>{
	
	public Object saveDoctorservice(Doctorservice doctorservice)
	  {
	  return (Object)super.persistMtoM(doctorservice);
      }
	
	public Doctorservice getDoctorserviceById(int id){
		Doctorservice doctorservice=super.criteriaQuerryGetObjectById(id,"doctorserviceId");
		return doctorservice;
		}
	public List<Doctorservice>  getDoctorserviceByDoctorId(int id){
		List<Doctorservice> doctorservices=super.criteriaQuerryGetObjectsById(id,"doctorId");
		return doctorservices;
		}
	
	public void  updateDoctorservice(Doctorservice doctorservice)
       {
       super.update(doctorservice);
       }

public Boolean deleteDoctorservice(Doctorservice doctorservice){
	return super.delete(doctorservice);
}

}
