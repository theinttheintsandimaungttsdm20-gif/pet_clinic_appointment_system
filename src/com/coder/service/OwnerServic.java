package com.coder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.servicee;

import com.coder.dao.DoctorDao;
import com.coder.dao.DoctorserviceDao;
import com.coder.dao.OwnerDao;
import com.coder.dao.RankDao;
import com.coder.dao.serviceDao;
import com.coder.dao.serviceTypeDao;
import com.coder.form.DoctorForm;
import com.coder.form.DoctorRegisterForm;
import com.coder.form.OwnerForm;
import com.coder.form.OwnerRegisterForm;
import com.coder.form.serviceForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.model.Doctor;
import com.coder.model.DoctorserviceId;
import com.coder.model.Owner;
import com.coder.model.Rank;
import com.coder.model.service;
import com.coder.model.serviceType;
import com.coder.util.DateFormat;
import com.coder.util.PrjSubFunction;

@servicee
@Repository("ownerservice")
public class Ownerservice {
	@Autowired
	private OwnerDao ownerDao;
	
	public void preparOwnerRegister(OwnerRegisterForm ownerRegisterForm){
		OwnerForm ownerForm=ownerRegisterForm.getOwnerForm();
		if(ownerForm!=null){
			this.ownerRegister(ownerForm);
			
		  }
		List<Owner> owners=this.ownerDao.getOwners();
		int nextId=1;
		if(owners.size()!=0){
		Owner owner=owners.get(owners.size()-1);
		 nextId=owner.getOwnerId()+1;
		}
		ownerForm=new OwnerForm();
		ownerForm.setOwnerId("oid-"+nextId);
		ownerRegisterForm.setOwnerForm(ownerForm);
      }
	
	private void ownerRegister(OwnerForm ownerForm){
		Owner owner=new Owner();
		owner.setOwnerName(ownerForm.getOwnerName());
		owner.setOwnerGender(ownerForm.getOwnerGender());
		owner.setOwnerEmail(ownerForm.getOwnerEmail());
		owner.setOwnerAddress(ownerForm.getOwnerAddress());
		owner.setOwnerAdd("");
		owner.setOwnerPh(ownerForm.getOwnerPh());
		
       int ownerId=this.ownerDao.saveOwner(owner);
        
	    
	}
	public Owner getOwnerById(String Id){
		int id=PrjSubFunction.convertId(Id,"oid");
		return this.ownerDao.getOwnerById(id);
	}
}
