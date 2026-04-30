package com.coder.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.servicee;

import com.coder.dao.DoctorDao;
import com.coder.dao.DoctorScheduleDao;
import com.coder.dao.DoctorserviceDao;
import com.coder.dao.RankDao;
import com.coder.dao.ScheduleserviceDao;
import com.coder.dao.serviceDao;
import com.coder.dao.serviceTypeDao;
import com.coder.form.DoctorForm;
import com.coder.form.DoctorRegisterForm;
import com.coder.form.DoctorScheduleForm;
import com.coder.form.DoctorScheduleRegisterForm;
import com.coder.form.serviceForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.model.Doctor;
import com.coder.model.DoctorSchedule;
import com.coder.model.Doctorservice;
import com.coder.model.DoctorserviceId;
import com.coder.model.Rank;
import com.coder.model.Scheduleservice;
import com.coder.model.ScheduleserviceId;
import com.coder.model.service;
import com.coder.model.serviceType;
import com.coder.util.DateFormat;
import com.coder.util.PrjSubFunction;

@servicee
@Repository("doctorScheduleservice")
public class DoctorScheduleservice {
	@Autowired
	private ScheduleserviceDao scheduleserviceDao;
	@Autowired
	private DoctorScheduleDao doctorScheduleDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private DoctorserviceDao doctorserviceDao;
	
	
	public void preparDoctorScheduleRegister(DoctorScheduleRegisterForm doctorScheduleRegisterForm){
		DoctorScheduleForm doctorScheduleForm=doctorScheduleRegisterForm.getDoctorScheduleForm();
		if(doctorScheduleForm!=null){
			this.doctorScheduleRegister(doctorScheduleRegisterForm);
			doctorScheduleForm=null;
			doctorScheduleRegisterForm.setDoctorScheduleForm(doctorScheduleForm);
		  }
	String docId=	doctorScheduleRegisterForm.getDoctorId();
		if(docId!=null){
			 Doctor doctor=this.doctorDao.getDoctorById(Integer.parseInt(docId));
		     doctorScheduleRegisterForm.setDoctor(doctor);
		Set<Doctorservice> doctorservices=doctor.getDoctorservices();
		for(Doctorservice ds:doctorservices){
			serviceType st=ds.getserviceType();
			doctorScheduleRegisterForm.getMapserviceTypes().put(""+st.getserviceTypeId(),""+st.getserviceTypeName());
		}
         
	 	 doctorScheduleRegisterForm.setDoctorSchedules(this.doctorScheduleDao.getDoctorSchedules());
		} 
		List<Doctor> doctors=this.doctorDao.getDoctors();
		doctorScheduleRegisterForm.setDoctors(doctors);     
		
		doctorScheduleRegisterForm.setTimes(PrjSubFunction.getDataTimeArr());
		String[]  arr=PrjSubFunction.getDataDayNamesArr();
		doctorScheduleRegisterForm.setDayNames(arr);
         }
	
	private void doctorScheduleRegister(DoctorScheduleRegisterForm doctorScheduleRegisterForm){
		DoctorScheduleForm doctorScheduleForm=doctorScheduleRegisterForm.getDoctorScheduleForm();
		DoctorSchedule doctorSchedule=new DoctorSchedule();
		doctorSchedule.setDayName(doctorScheduleForm.getDayName());
		doctorSchedule.setEndTime(DateFormat.stringToTime_aa(doctorScheduleForm.getEndTime()));
		doctorSchedule.setStartTime(DateFormat.stringToTime_aa(doctorScheduleForm.getStartTime()));
		Doctor doctor=this.doctorDao.getDoctorById(Integer.parseInt(doctorScheduleRegisterForm.getDoctorId()));
		doctorSchedule.setDoctor(doctor);
		
       int doctorSchduleId=this.doctorScheduleDao.saveDoctorSchedule(doctorSchedule);
       String[] serviceTypeIds=doctorScheduleForm.getSelectserviceTypes();
        	for(String id:serviceTypeIds){
        	
        	Scheduleservice scheduleservice=new Scheduleservice();
        	ScheduleserviceId scheduleserviceId=new ScheduleserviceId();
         	scheduleserviceId.setScheduleId(doctorSchduleId);
        	scheduleserviceId.setserviceTypeId(Integer.parseInt(id));
        	scheduleservice.setId(scheduleserviceId);
        	this.scheduleserviceDao.saveScheduleservice(scheduleservice);
        }
	}
}
