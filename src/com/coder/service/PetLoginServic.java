package com.coder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.servicee;

import com.coder.dao.DoctorDao;
import com.coder.dao.DoctorserviceDao;
import com.coder.dao.OwnerDao;
import com.coder.dao.PetLoginDao;
import com.coder.dao.RankDao;
import com.coder.dao.serviceDao;
import com.coder.dao.serviceTypeDao;
import com.coder.form.DoctorForm;
import com.coder.form.DoctorRegisterForm;
import com.coder.form.OwnerForm;
import com.coder.form.OwnerRegisterForm;
import com.coder.form.PetLoginForm;
import com.coder.form.PetLoginRegisterForm;
import com.coder.form.serviceForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.model.Doctor;
import com.coder.model.DoctorserviceId;
import com.coder.model.Owner;
import com.coder.model.PetLogin;
import com.coder.model.Rank;
import com.coder.model.service;
import com.coder.model.serviceType;

@servicee
@Repository("petLoginservice")
public class PetLoginservice {
	@Autowired
	private PetLoginDao petLoginDao;
	
	    public void petLoginRegister(PetLoginForm petLoginForm){
	    	
		PetLogin petLogin=new PetLogin();
		petLogin.setLoginId(petLoginForm.getLoginId());
		petLogin.setUserName(petLoginForm.getUserName());
		petLogin.setPassword(petLoginForm.getPassword());
		this.petLoginDao.savePetLogin(petLogin);
 	}
	    public PetLogin petLoginUser(PetLoginForm petLoginForm){
	    	
			PetLogin petLogin=this.petLoginDao.getPetLoginByUserNameAndPassword(petLoginForm.getLoginId(),petLoginForm.getPassword());
			return petLogin;
	    	
	    }
	    
	
}
