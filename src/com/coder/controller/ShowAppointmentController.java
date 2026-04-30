  package com.coder.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coder.form.AppointmentForm;
import com.coder.form.AppointmentRegisterForm;
import com.coder.form.DoctorRegisterForm;
import com.coder.form.OwnerRegisterForm;
import com.coder.form.PetRegisterForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.form.ShowAppointmentForm;
import com.coder.model.Appointment;
import com.coder.model.PetLogin;
import com.coder.service.Appointmentservice;
import com.coder.service.Doctorservice;
import com.coder.service.Ownerservice;
import com.coder.service.Petservice;
import com.coder.service.serviceservice;
import com.coder.service.serviceTypeservice;
import com.coder.service.ShowAppointmentservice;


@Controller
@Transactional
public class ShowAppointmentController {
	@Autowired
	private ShowAppointmentservice showAppointmentservice;
	@RequestMapping("/showAppointmentPath1")
	public String showAppointmentDispatcher1(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{   
	   	ShowAppointmentForm showAppointmentForm=new ShowAppointmentForm();
		this.showAppointmentservice.preparShowAppointment(showAppointmentForm);
		modelMap.addAttribute("showAppointmentForm", showAppointmentForm);
		return "selectDoctorSchedule";
	}
	@RequestMapping("/showAppointmentPath2")
	public String showAppointmentDispatcher2(@ModelAttribute("showAppointmentForm") ShowAppointmentForm showAppointmentForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp) throws ParseException
	    {
		String frmScheduleId=req.getParameter("frmScheduleId");
		HttpSession session=req.getSession(true);
		if(frmScheduleId!=null){
		session.setAttribute("frmScheduleId",frmScheduleId );
		}
		 
		 modelMap.addAttribute("showAppointmentForm",showAppointmentForm);
		 return "showAppointment";
	     }  
			 
	    
	@RequestMapping("/showAppointmentPath3")
	public String showAppointmentDispatcher3(@ModelAttribute("showAppointmentForm") ShowAppointmentForm showAppointmentForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp) throws ParseException, FileNotFoundException, IOException
	    {	
		
		String exit=req.getParameter("exit");
		String refresh=req.getParameter("refresh");
		HttpSession session=req.getSession(true);
		String frmScheduleId=(String)session.getAttribute("frmScheduleId");
		showAppointmentForm.setDoctorScheduleId(frmScheduleId);
	    String idType=(String) session.getAttribute("idType");
	   // System.out.println(" refresh="+refresh);
		if(refresh!=null){
			//System.out.println("Arrive refresh");
		 showAppointmentForm=this.showAppointmentservice.preparShowAppoimentSubmit(showAppointmentForm);
		// System.out.println("Show App="+showAppointmentForm.getShortestJobFirstForms().size());
		 modelMap.addAttribute("showAppointmentForm",showAppointmentForm);
		 return "showAppointment";
	     }else if(exit!=null){
			 if(idType.equals("doctor")){
			      return "doctorHome";
			     }else  if(idType.equals("oid")){
				  return "userHome";
				 } else{
				  return "adminHome"; 
				 }
			 }else{
				 return "showAppointment";
			 }
      }
	@RequestMapping("/showAppointmentSubmitPath")
	public String showAppointmentSubmitDispatcher(@ModelAttribute("showAppointmentForm") ShowAppointmentForm showAppointmentForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp) throws ParseException, FileNotFoundException, IOException
	    {	
		
		String exit=req.getParameter("exit");
		String refresh=req.getParameter("refresh");
		HttpSession session=req.getSession(true);
		String frmScheduleId=(String)session.getAttribute("frmScheduleId");
		showAppointmentForm.setDoctorScheduleId(frmScheduleId);
	    String idType=(String) session.getAttribute("idType");
			if(refresh!=null){
			
		 showAppointmentForm=this.showAppointmentservice.preparShowAppoimentSubmit(showAppointmentForm);
		 System.out.println("Show App="+showAppointmentForm.getShortestJobFirstForms().size());
		 modelMap.addAttribute("showAppointmentForm",showAppointmentForm);
		 return "showAppointment";
	     }else if(exit!=null){
			 if(idType.equals("doctor")){
			      return "doctorHome";
			     }else  if(idType.equals("oid")){
				  return "userHome";
				 } else{
				  return "adminHome"; 
				 }
			 }else{
				 return "showAppointment";
			 }
      }
	
	@RequestMapping("/showAllAppointmentPath")
	public String showAllAppointmentDispatcher(@ModelAttribute("appointmentRegisterForm") AppointmentRegisterForm appointmentRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp) throws ParseException
	    {
		List<Appointment> appointments=this.showAppointmentservice.showAllAppoiment();
		 appointmentRegisterForm=new AppointmentRegisterForm();
		 appointmentRegisterForm.setAppointments(appointments);
		modelMap.addAttribute("appointmentRegisterForm",appointmentRegisterForm);
			return "showAllAppointment";	
	    }
	
}
