package com.coder.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.servicee;

import com.coder.dao.AppointmentserviceDao;
import com.coder.dao.AppointmentDao;
import com.coder.dao.DoctorDao;
import com.coder.dao.DoctorScheduleDao;
import com.coder.dao.DoctorserviceDao;
import com.coder.dao.OwnerDao;
import com.coder.dao.PetDao;
import com.coder.dao.RankDao;
import com.coder.dao.ScheduleserviceDao;
import com.coder.dao.serviceDao;
import com.coder.dao.serviceTypeDao;
import com.coder.dao.SpeciesDao;
import com.coder.form.AppointmentForm;
import com.coder.form.AppointmentRegisterForm;
import com.coder.form.DoctorForm;
import com.coder.form.DoctorRegisterForm;
import com.coder.form.DoctorScheduleForm;
import com.coder.form.PetForm;
import com.coder.form.PetRegisterForm;
import com.coder.form.serviceForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;

import com.coder.model.Appointment;
import com.coder.model.AppointmentserviceId;
import com.coder.model.Doctor;
import com.coder.model.DoctorSchedule;
import com.coder.model.DoctorserviceId;
import com.coder.model.Owner;
import com.coder.model.Pet;
import com.coder.model.Rank;
import com.coder.model.Scheduleservice;
import com.coder.model.service;
import com.coder.model.serviceType;
import com.coder.model.Species;
import com.coder.util.DateFormat;
import com.coder.util.PrjSubFunction;

import javafx.scene.input.DataFormat;

@servicee
@Repository("appointmentservice")
public class Appointmentservice {
	@Autowired
	private AppointmentDao appointmentDao;
	@Autowired
	private ScheduleserviceDao scheduleserviceDao;
	@Autowired
	private serviceTypeDao serviceTypeDao;
	@Autowired
	private DoctorScheduleDao doctorScheduleDao;
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private PetDao petDao;
	@Autowired
	private AppointmentserviceDao appointmentserviceDao;
	@Autowired
	private serviceDao serviceDao;
	public void preparAppointmentRegister1(AppointmentRegisterForm appointmentRegisterForm){
		 if(appointmentRegisterForm.getOwnerId()!=null){
			 appointmentRegisterForm.getMapserviceTypes().put("1","Gneral Consulation"); 
			 appointmentRegisterForm.getMapserviceTypes().put("4","Dental Treatment"); 
		 }else{
		 List<serviceType> serviceTypes=serviceTypeDao.getserviceTypes();
		
		 for(serviceType st:serviceTypes){
			 appointmentRegisterForm.getMapserviceTypes().put(st.getserviceTypeId()+"",st.getserviceTypeName());
		 }}
         }
	public void preparAppointmentRegister2(AppointmentRegisterForm appointmentRegisterForm) throws ParseException{
		 AppointmentForm appointmentForm=appointmentRegisterForm.getAppointmentForm();
		 serviceType serviceType=this.serviceTypeDao.getserviceTypeById(Integer.parseInt(appointmentForm.getserviceTypeId()));
		 String dayName=DateFormat.dateFormat_EEE(appointmentForm.getAppointmentDate());
		 List<DoctorSchedule> doctorSchedules=this. doctorScheduleDao.getDoctorScheduleByDay(dayName.toUpperCase());
		 
		 List<DoctorSchedule> doctorSchedulesSelect=new ArrayList<>();
		 if(doctorSchedules!=null){
	     for(DoctorSchedule ds:doctorSchedules){
			Set<Scheduleservice> scheduleservices= ds.getScheduleservices();
			Boolean flag=false;
			for(Scheduleservice sc:scheduleservices){
				String serviceTypeName=sc.getserviceType().getserviceTypeName();
				if(serviceTypeName.equals(serviceType.getserviceTypeName())){
					flag=true;
				}
			}
			if(flag){
			doctorSchedulesSelect.add(ds);
			}
		 }
		 }
		 ArrayList<DoctorScheduleForm> doctorScheduleForms=new ArrayList<>();
		 for(DoctorSchedule ds:doctorSchedulesSelect){
			 
			 DoctorScheduleForm doctorScheduleForm=new DoctorScheduleForm();
			 doctorScheduleForm.setDayName(ds.getDayName());
			 doctorScheduleForm.setStartTime(ds.getStartTime()+"");
			 doctorScheduleForm.setEndTime(ds.getEndTime()+"");
			 doctorScheduleForm.setScheduleId(ds.getScheduleId()+"");
			ArrayList<Integer> minutes=PrjSubFunction.getScheduleByAppserviceMinute(ds,DateFormat.stringToDateFormat_dd_mm_yyyy(appointmentForm.getAppointmentDate()));
			
			int timeSlot=DateFormat.timeDifference1(ds.getStartTime()+"",ds.getEndTime()+"");
			int temp=serviceType.getAverageTime();
			for(int min: minutes){
			timeSlot=timeSlot-min;
			}
			int count=-1;
			boolean flag=true;
			while(flag){
				System.out.println("timeSlot-temp="+timeSlot+"-"+temp);
				if((timeSlot-temp)<0){
					flag=false;
				}else{
				    timeSlot=timeSlot-temp;
			        System.out.println("timeSlot="+timeSlot);
				}
				count++;
			}
			 doctorScheduleForm.setTimeSlot(count);
			 doctorScheduleForms.add(doctorScheduleForm);
		 }
		
	     appointmentRegisterForm.setDoctorScheduleForms(doctorScheduleForms);
        }
	
	public void preparAppointmentRegister3(AppointmentRegisterForm appointmentRegisterForm){
		String serviceTypeId=appointmentRegisterForm.getserviceTypeId();
		serviceType serviceType=this.serviceTypeDao.getserviceTypeById(Integer.parseInt(serviceTypeId));
        Owner owner=this.ownerDao.getOwnerById(PrjSubFunction.convertId(appointmentRegisterForm.getOwnerId(),"oid"));
		Set<service> serviceSets=serviceType.getservices();
		Set<Pet> petSets=owner.getPets();
		System.out.println("petSets="+petSets.size());
		for(Pet pet:petSets){
		//	System.out.println("pet.getPetId()="+pet.getPetId()+" pet.getPetName()="+pet.getPetName()+"("+pet.getSpecies().getSpeciesName()+")");
			appointmentRegisterForm.getMapPets().put(pet.getPetId()+"",pet.getPetName()+"("+pet.getSpecies().getSpeciesName()+")");
		}
		for(service s:serviceSets){
			appointmentRegisterForm.getMapservicees().put(s.getserviceId()+"",s.getserviceName());
		}		
	}
	public void appointmentRegister(AppointmentRegisterForm appointmentRegisterForm){
		AppointmentForm appointmentForm=appointmentRegisterForm.getAppointmentForm();
		Appointment appointment=new Appointment();
		Date date=DateFormat.stringToDateFormat_dd_mm_yyyy(appointmentForm.getAppointmentDate());
		appointment.setAppointmentDate(date);
		appointment.setAppointmentTime(null);
		appointment.setAppointmentInfo(appointmentForm.getAppointmentInfo());
		DoctorSchedule doctorSchedule=this.doctorScheduleDao.getDoctorScheduleById(Integer.parseInt(appointmentRegisterForm.getDoctorScheduleId()));
		Doctor doctor=doctorSchedule.getDoctor();
		String strId=appointmentForm.getOwnerId();
		if(strId==null){
			strId=appointmentRegisterForm.getOwnerId();
		}
	    int id=PrjSubFunction.convertId(strId,"oid");
	    System.out.println("oid="+id);
		Owner owner=ownerDao.getOwnerById(id);
		Pet pet=petDao.getPetById(Integer.parseInt(appointmentForm.getPetId()));
		appointment.setPet(pet);
		appointment.setOwner(owner);
		appointment.setDoctor(doctor);
		appointment.setDoctorSchedule(doctorSchedule);
		int Id=this.appointmentDao.saveAppointment(appointment);
		String[] selectservice=appointmentForm.getSelectedservicees();
		ArrayList<String> services=new ArrayList<>();
		for(String s:selectservice){
		service service=this.serviceDao.getserviceById(Integer.parseInt(s));
		services.add(service.getserviceName());		}
		List<Appointment> appointments=this.appointmentDao.getAppointmentByNow(date);
		List<Appointment> appointments1=this.appointmentDao.getAppointmentByNow(date);
		int count=0;
		System.out.println("appointments.size()="+appointments.size());
		for(Appointment app:appointments){
			if(appointments1.size()>count&&app.getDoctorSchedule().getScheduleId()!=doctorSchedule.getScheduleId()){
				appointments1.remove(count);
			}
			count++;
		}
		appointmentRegisterForm.setTokenNo((appointments1.size())+"");
		appointmentRegisterForm.setservicees(services);
		if(selectservice!=null){
			for(String serviceId:selectservice){
			AppointmentserviceId appointmentserviceId=new AppointmentserviceId();
			appointmentserviceId.setAppointmentId(Id);
			appointmentserviceId.setserviceId(Integer.parseInt(serviceId));
			com.coder.model.Appointmentservice appointmentservice=new com.coder.model.Appointmentservice();
			appointmentservice.setId(appointmentserviceId);
			appointmentserviceDao.saveAppointmentservice(appointmentservice);
			appointmentRegisterForm.setDoctorSchedule(doctorSchedule);
			}
		}
	}
	
}

