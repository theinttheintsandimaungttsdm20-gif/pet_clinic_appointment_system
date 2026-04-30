package com.coder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.servicee;


import com.coder.dao.serviceTypeDao;

import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;

import com.coder.model.serviceType;

@servicee
@Repository("serviceTypeservice")
public class serviceTypeservice {
	@Autowired
	private serviceTypeDao serviceTypeDao;
	
	
	public void preparserviceTypeRegister(serviceTypeRegisterForm serviceTypeRegisterForm){
		serviceTypeForm serviceTypeForm=serviceTypeRegisterForm.getserviceTypeForm();
		if(serviceTypeForm!=null){
			this.serviceTypeRegister(serviceTypeForm);
			serviceTypeForm=null;
		  }
		serviceTypeRegisterForm.setserviceTypeForm(serviceTypeForm);
		List<serviceType> serviceTypes=this.serviceTypeDao.getserviceTypes();
		serviceTypeRegisterForm.setserviceTypes(serviceTypes);
         }
	
	private int serviceTypeRegister(serviceTypeForm serviceTypeForm){
		serviceType serviceType=new serviceType();
		serviceType.setserviceTypeName(serviceTypeForm.getserviceTypeName());
		serviceType.setserviceTypeDesc(serviceTypeForm.getserviceTypeDesc());
		serviceType.setAverageTime(Integer.parseInt(serviceTypeForm.getAverageTime()));
		int id=this.serviceTypeDao.saveserviceType(serviceType);
		return id; 
	}
	
	public void preparserviceTypeUpdate(serviceTypeRegisterForm serviceTypeRegisterForm){
		serviceTypeForm serviceTypeForm=serviceTypeRegisterForm.getserviceTypeForm();
	int serviceTypeId=Integer.parseInt(serviceTypeForm.getserviceTypeId());
	serviceType serviceType=this.serviceTypeDao.getserviceTypeById(serviceTypeId);
	serviceTypeForm.setserviceTypeId(serviceType.getserviceTypeId()+"");
	serviceTypeForm.setserviceTypeName(serviceType.getserviceTypeName());
	serviceTypeForm.setserviceTypeDesc(serviceType.getserviceTypeDesc());
	serviceTypeForm.setAverageTime(serviceType.getAverageTime()+"");
	serviceTypeRegisterForm.setserviceTypeForm(serviceTypeForm);
	}
	
	public void serviceTypeUpdate(serviceTypeRegisterForm serviceTypeRegisterForm){
		serviceTypeForm serviceTypeForm=serviceTypeRegisterForm.getserviceTypeForm();
		if(serviceTypeForm!=null){
		serviceType serviceType=new serviceType();
		serviceType.setserviceTypeId(Integer.parseInt(serviceTypeForm.getserviceTypeId()));
		serviceType.setserviceTypeName(serviceTypeForm.getserviceTypeName());
		serviceType.setserviceTypeDesc(serviceTypeForm.getserviceTypeDesc());
		serviceType.setAverageTime(Integer.parseInt(serviceTypeForm.getAverageTime()));
		this.serviceTypeDao.updateserviceType(serviceType);
		}
		serviceTypeForm=null;
		serviceTypeRegisterForm.setserviceTypeForm(serviceTypeForm);
	}
	public void serviceTypeDelete(serviceTypeRegisterForm serviceTypeRegisterForm){
		serviceTypeForm serviceTypeForm=serviceTypeRegisterForm.getserviceTypeForm();
		int id=Integer.parseInt(serviceTypeForm.getserviceTypeId());
		serviceType serviceType=this.serviceTypeDao.getserviceTypeById(id);
		
		
		this.serviceTypeDao.deleteserviceType(serviceType);
		serviceTypeForm=null;
		serviceTypeRegisterForm.setserviceTypeForm(serviceTypeForm);
		
	}

}
