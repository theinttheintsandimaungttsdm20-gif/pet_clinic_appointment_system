package com.coder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.servicee;

import com.coder.dao.DoctorDao;
import com.coder.dao.DoctorserviceDao;
import com.coder.dao.RankDao;
import com.coder.dao.serviceDao;
import com.coder.dao.serviceTypeDao;
import com.coder.form.DoctorForm;
import com.coder.form.DoctorRegisterForm;
import com.coder.form.serviceForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.model.Doctor;
import com.coder.model.DoctorserviceId;
import com.coder.model.Rank;
import com.coder.model.service;
import com.coder.model.serviceType;

@servicee
@Repository("doctorservice")
public class Doctorservice {
	@Autowired
	private DoctorserviceDao doctorserviceDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private serviceTypeDao serviceTypeDao;
	@Autowired
	private RankDao rankDao;
	public void preparDoctorRegister(DoctorRegisterForm doctorRegisterForm){
		DoctorForm doctorForm=doctorRegisterForm.getDoctorForm();
		if(doctorForm!=null){
			this.doctorRegister(doctorForm);
			
		  }
		doctorForm=new DoctorForm();
		doctorRegisterForm.setDoctorForm(doctorForm);
		List<serviceType> serviceTypes=this.serviceTypeDao.getserviceTypes();
		for(serviceType st:serviceTypes){
			doctorRegisterForm.getMapserviceTypes().put(""+st.getserviceTypeId(),""+st.getserviceTypeName());
		}
	     List<Rank> ranks=this.rankDao.getRanks();
		for( Rank ra:ranks){
			doctorRegisterForm.getMapRanks().put(""+ra.getRankId(),""+ra.getRankName());
		}
		List<Doctor> doctors=this.doctorDao.getDoctors();
		if(doctors.size()==0){
		
			doctorForm.setDoctorId("did-1");
		}else{
			Doctor doctor=doctors.get(doctors.size()-1);
			doctorForm.setDoctorId("did-"+(doctor.getDoctorId()+1));
		}
	
		doctorRegisterForm.setDoctors(doctors);
         }
	
	private void doctorRegister(DoctorForm doctorForm){
		Doctor doctor=new Doctor();
		doctor.setDoctorName(doctorForm.getDoctorName());
		doctor.setDoctorGender(doctorForm.getDoctorGender());
		doctor.setDoctorGmail(doctorForm.getDoctorGmail());
		doctor.setDoctorAddress(doctorForm.getDoctorAddress());
		System.out.println("doctorForm.getDoctorAbout()="+doctorForm.getDoctorAbout());
		doctor.setDoctorAbout(doctorForm.getDoctorAbout());
		doctor.setDoctorPhone(doctorForm.getDoctorPhone());
		Rank rank=rankDao.getRankById(Integer.parseInt(doctorForm.getRankId()));
        doctor.setRank(rank);
       int doctorId=this.doctorDao.saveDoctor(doctor);
        for(String id:doctorForm.getSelectserviceTypes()){
        	
        	serviceType serviceType=this.serviceTypeDao.getserviceTypeById(Integer.parseInt(id));
        	DoctorserviceId doctorserviceId=new DoctorserviceId();
        	doctorserviceId.setDoctorId(doctorId);
        	doctorserviceId.setserviceTypeId(serviceType.getserviceTypeId());
        	com.coder.model.Doctorservice doctorservice=new com.coder.model.Doctorservice();
        	doctorservice.setId(doctorserviceId);
        	this.doctorserviceDao.saveDoctorservice(doctorservice);
        }
	    
	}
}
