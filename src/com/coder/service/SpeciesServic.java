package com.coder.service;

import java.util.List;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.servicee;

import com.coder.dao.DoctorDao;
import com.coder.dao.DoctorserviceDao;
import com.coder.dao.OwnerDao;
import com.coder.dao.PetDao;
import com.coder.dao.RankDao;
import com.coder.dao.serviceDao;
import com.coder.dao.serviceTypeDao;
import com.coder.dao.SpeciesDao;
import com.coder.form.DoctorForm;
import com.coder.form.DoctorRegisterForm;
import com.coder.form.PetForm;
import com.coder.form.PetRegisterForm;
import com.coder.form.serviceForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.form.SpeciesForm;
import com.coder.form.SpeciesRegisterForm;
import com.coder.model.Doctor;
import com.coder.model.DoctorserviceId;
import com.coder.model.Owner;
import com.coder.model.Pet;
import com.coder.model.Rank;
import com.coder.model.service;
import com.coder.model.serviceType;
import com.coder.model.Species;
import com.coder.util.DateFormat;

@servicee
@Repository("speciesservice")
public class Speciesservice {
	
	@Autowired
	private SpeciesDao speciesDao;
	
	public void preparSpeciesRegister(SpeciesRegisterForm speciesRegisterForm){
		SpeciesForm speciesForm=speciesRegisterForm.getSpeciesForm();
		if(speciesForm!=null){
			this.speciesRegister(speciesRegisterForm);
			speciesForm=null;
		  }
		speciesRegisterForm.setSpeciesForm(speciesForm);
		List<Species> species=this.speciesDao.getSpecies();
		speciesRegisterForm.setSpecies(species);
         }
	
	private void speciesRegister(SpeciesRegisterForm speciesRegisterForm){
		Species species=new Species();
		SpeciesForm speciesForm=speciesRegisterForm.getSpeciesForm();
		species.setSpeciesName(speciesForm.getSpeciesName());
		this.speciesDao.saveSpecies(species);
		
		
        }
	    
	}

