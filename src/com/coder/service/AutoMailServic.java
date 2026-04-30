package com.coder.service;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.servicee;

import com.coder.dao.AppointmentDao;
import com.coder.dao.DoctorScheduleDao;
import com.coder.dao.OwnerDao;
import com.coder.form.ExalForm;
import com.coder.form.MailForm;
import com.coder.form.ShortestJobFirstForm;
import com.coder.form.ShowAppointmentForm;
import com.coder.model.Appointment;
import com.coder.model.Appointmentservice;
import com.coder.model.DoctorSchedule;
import com.coder.model.Owner;
import com.coder.util.DateFormat;
import com.coder.util.PrjSubFunction;

@servicee
@Repository("autoMailservice")
public class AutoMailservice {


@Autowired
private DoctorScheduleDao  doctorScheduleDao;
@Autowired
private ShowAppointmentservice showAppoimentservice;
@Autowired
private OwnerDao ownerDao;
@Autowired
private Generalservicee generalservicee;
@Autowired
private AppointmentDao appointmentDao;
@Scheduled(fixedDelay=50000)
public void autoMail() throws IOException, ParseException{
	//System.out.println("@Scheduled process");
	Date date=DateFormat.subDays(+1);
	List<DoctorSchedule> doctorSchedules=this.doctorScheduleDao.getDoctorSchedules();
	for(DoctorSchedule ds:doctorSchedules){
		int timeSlot=DateFormat.timeDifference1(ds.getStartTime()+"",ds.getEndTime()+"");
		Set<Appointment> appointmentSets=ds.getAppointments();
	    List<Appointment> appointments=new ArrayList<Appointment>();
	for(Appointment app:appointmentSets){
		
		String date2=app.getAppointmentDate()+"";
		String dateStr=DateFormat.dateToString_DB_Format(date);
		//System.out.println("date2="+date2+"::"+"dateStr="+dateStr);
		if(date2.equals(dateStr)){
			appointments.add(app);
		}
	}
	if(appointments.size()!=0){
	Appointment apptemp=appointments.get(appointments.size()-1);
	timeSlot-=PrjSubFunction.serviceTimeCollect(apptemp.getAppointmentservices());
	}
	for(Appointment app:appointments){
		System.out.println("app.getAppointmentDate="+app.getAppointmentDate());
		if(timeSlot>=0){
		Set<Appointmentservice> appointmentservicees=app.getAppointmentservices();
		int stime=PrjSubFunction.serviceTimeCollect(appointmentservicees);
		timeSlot-=stime;
		}
	}
	System.out.println("timeSlot="+timeSlot);
	if(timeSlot<=0){
	
	if(appointments.size()!=0){
	List<Appointment> appSort=PrjSubFunction.sortJob(appointments);
	ShowAppointmentForm showAppointmentForm=new ShowAppointmentForm();
	ShowAppointmentForm appointmentForm=PrjSubFunction.shortestJobFirstCal(appSort,"sjf", showAppointmentForm);
	List<ShortestJobFirstForm> firstForms=appointmentForm.getShortestJobFirstForms();
	int tokenNo=1;
	for(ShortestJobFirstForm fs:firstForms){
	Appointment appointment=this.appointmentDao.getAppointmentById(Integer.parseInt(fs.getAppintmentId())); 
	if(appointment.getAppointmentTime()==null){
	MailForm mailForm=new MailForm();
	Owner owner=this.ownerDao.getOwnerById(Integer.parseInt(fs.getOwnerId()));
	mailForm.setContent("Dear "+owner.getOwnerName());
	Appointment app=this.appointmentDao.getAppointmentById(Integer.parseInt(fs.getAppintmentId()));
	mailForm.setToMail(owner.getOwnerEmail().trim());
	Set<Appointmentservice> appointmentservices=app.getAppointmentservices();
	String servicee = "";
	String subject="";
	Date arrTime=DateFormat.addDateInMinute(Integer.parseInt(fs.getWaitingTime()),app.getDoctorSchedule().getStartTime());
	String arrTimeStr=DateFormat.dateToDayAM_PM(arrTime);
	subject += "Appointment Time in Sitt Nadi Pet Clinic for Your Pet " +app.getPet().getPetName()+" is Confirmed";
	servicee+="Your Appointment in Sitt Nadi Pet Clinic is confirmed. Date and time, you should arrive has been described below. Please arrive on time.";
	servicee+="\nPet Owner Name:"+owner.getOwnerName();
	servicee+="\nToken No:"+tokenNo;
	servicee+="\nPet Name:"+app.getPet().getPetName();
	servicee+="\nAppointment Time:"+arrTimeStr;
	servicee+="\nAppointment Date="+date;
	servicee+="\n";
	for(Appointmentservice appointmentservice:appointmentservices){
		servicee+="treatment type:"+appointmentservice.getservice().getserviceName()+"\n";
	}
	tokenNo++;
	mailForm.setSubject(subject);
	mailForm.setContent(servicee);
	appointment.setAppointmentTime(arrTime);
	this.appointmentDao.update(appointment);
	System.out.println("Send Mail");
    generalservicee.processSendMail(mailForm);
	
	}
	
}
}
}
	}
}
}
