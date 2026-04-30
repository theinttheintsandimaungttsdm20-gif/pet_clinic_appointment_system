package com.coder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.servicee;

import com.coder.dao.ServiceDao;
import com.coder.dao.ServiceTypeDao;
import com.coder.dao.serviceDao;
import com.coder.dao.serviceTypeDao;
import com.coder.form.ServiceForm;
import com.coder.form.serviceForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.model.service;
import com.coder.model.serviceType;

@Service
@Repository("serviceService")
public class ServiceService {
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceTypeDao serviceTypeDao;
	
	public void preparserviceRegister(ServiceRegisterForm serviceRegisterForm){
		ServiceForm serviceForm=serviceRegisterForm.getserviceForm();
		if(serviceForm!=null){
			this.serviceRegister(serviceForm);
			serviceForm=null;
		  }
		serviceRegisterForm.setserviceForm(serviceForm);
		List<serviceType> serviceTypes=this.serviceTypeDao.getserviceTypes();
		List<service> services=this.serviceDao.getservices();
		serviceRegisterForm.setservices(services);
		for( serviceType be:serviceTypes){
		serviceRegisterForm.getMapserviceTypes().put(""+be.getserviceTypeId(),""+be.getserviceTypeName());
		}
         }
	
	private int serviceRegister(serviceForm serviceForm){
		service service=new service();
		service.setserviceName(serviceForm.getserviceName());
		service.setserviceMinute(Integer.parseInt(serviceForm.getserviceMinute()));
		service.setservicePoint(Integer.parseInt(serviceForm.getservicePoint()));
		service.setserviceAbout(serviceForm.getserviceAbout());
	    serviceType serviceType=new serviceType();
	    serviceType.setserviceTypeId(Integer.parseInt(serviceForm.getserviceTypeId()));
	    service.setserviceType(serviceType);
		int id=this.serviceDao.saveservice(service);
		return id; 
	}
	
	public void preparserviceUpdate(serviceRegisterForm serviceRegisterForm){
	serviceForm serviceForm=serviceRegisterForm.getserviceForm();
	int serviceId=Integer.parseInt(serviceForm.getserviceId());
	service service=this.serviceDao.getserviceById(serviceId);
	serviceForm.setserviceId(service.getserviceId()+"");
	serviceForm.setserviceName(service.getserviceName());
	serviceForm.setserviceMinute(service.getserviceMinute()+"");
	serviceForm.setservicePoint(service.getservicePoint()+"");
	serviceForm.setserviceAbout(service.getserviceAbout());
	serviceType serviceType=service.getserviceType();
	serviceRegisterForm.setSelectserviceType(serviceType);
	serviceRegisterForm.setserviceForm(serviceForm);
	List<serviceType> serviceTypes=this.serviceTypeDao.getserviceTypes();
	for( serviceType be:serviceTypes){
		if(be.getserviceTypeId()!=serviceType.getserviceTypeId())
		serviceRegisterForm.getMapserviceTypes().put(""+be.getserviceTypeId(),""+be.getserviceTypeName());
		}
	}
	
	public void serviceUpdate(serviceRegisterForm serviceRegisterForm){
		serviceForm serviceForm=serviceRegisterForm.getserviceForm();
		service service=new service();
		service.setserviceId(Integer.parseInt(serviceForm.getserviceId()));
		service.setserviceName(serviceForm.getserviceName());
		service.setserviceAbout(serviceForm.getserviceAbout());
		service.setserviceMinute(Integer.parseInt(serviceForm.getserviceMinute()));
		service.setservicePoint(Integer.parseInt(serviceForm.getservicePoint()));
		serviceType serviceType=new serviceType();
		serviceType.setserviceTypeId(Integer.parseInt(serviceForm.getserviceTypeId()));
		service.setserviceType(serviceType);
		this.serviceDao.updateservice(service);
		serviceForm=null;
		serviceRegisterForm.setserviceForm(serviceForm);
	}
	public void serviceDelete(serviceRegisterForm serviceRegisterForm){
		serviceForm serviceForm=serviceRegisterForm.getserviceForm();
		int id=Integer.parseInt(serviceForm.getserviceId());
		service service=this.serviceDao.getserviceById(id);
        this.serviceDao.deleteservice(service);
		serviceForm=null;
		serviceRegisterForm.setserviceForm(serviceForm);
		
	}

}
