package com.coder.controller;

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
import com.coder.model.PetLogin;
import com.coder.service.Appointmentservice;
import com.coder.service.Doctorservice;
import com.coder.service.Ownerservice;
import com.coder.service.Petservice;
import com.coder.service.serviceservice;
import com.coder.service.serviceTypeservice;


@Controller
@Transactional
public class AppointmentRegisterController {
	@Autowired
	private Appointmentservice appointmentservice;
	@RequestMapping("/appointmentRegisterPath1")
	public String appointmentRegisterDispatcher1(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{   String ownerId=req.getParameter("frmOwnerId");
	    System.out.println("ownerId="+ownerId);
	 	AppointmentRegisterForm appointmentRegisterForm=new AppointmentRegisterForm();
		appointmentRegisterForm.setOwnerId(ownerId);
		appointmentservice.preparAppointmentRegister1(appointmentRegisterForm);
   		modelMap.addAttribute("appointmentRegisterForm",appointmentRegisterForm);
	 
		return "appointmentRegister1";
	}
	
	@RequestMapping("/appointmentRegisterPath2")
	public String appointmentRegisterDispatcher2(@ModelAttribute("appointmentRegisterForm") AppointmentRegisterForm appointmentRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp) throws ParseException
	     {	
		String exit=req.getParameter("exit");
		String next=req.getParameter("next");
		HttpSession session=req.getSession(true);	
		AppointmentForm appointmentForm=appointmentRegisterForm.getAppointmentForm();
		String serviceTypeId=appointmentForm.getserviceTypeId();
		String appointmentDate=appointmentForm.getAppointmentDate();
		session.setAttribute("serviceTypeId",serviceTypeId);
		String idType=(String) session.getAttribute("idType");
		session.setAttribute("appointmentDate",appointmentDate);
 	 	 if(next!=null){
		 this.appointmentservice.preparAppointmentRegister2(appointmentRegisterForm);
		 modelMap.addAttribute("appointmentRegisterForm",appointmentRegisterForm);
	     return "appointmentRegister2";
	     }else if(idType.equals("doctor")){
	     return "doctorHome";
	     }else {
		  return "userHome";
		 }
      }
	
	@RequestMapping("/appointmentRegisterPath3")
	public String appointmentRegisterDispatcher3(@ModelAttribute("appointmentRegisterForm") AppointmentRegisterForm appointmentRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	     {	
		HttpSession session=req.getSession(true);	
		PetLogin petLogin=(PetLogin) session.getAttribute("petLogin");
		String idType=(String) session.getAttribute("idType");
		String serviceTypeId=(String) session.getAttribute("serviceTypeId");
		
			if(serviceTypeId!=null){
				String doctorScheduleId=req.getParameter("frmScheduleId");
				System.out.println("frmScheduleId="+doctorScheduleId);
				if(doctorScheduleId!=null){
				session.setAttribute("doctorScheduleId", doctorScheduleId);
				appointmentRegisterForm.setDoctorScheduleId(doctorScheduleId);
				}
			appointmentRegisterForm.setserviceTypeId(serviceTypeId);
		    AppointmentForm appointmentForm=appointmentRegisterForm.getAppointmentForm();
			     if(appointmentForm==null&&idType.equals("doctor")){
			    	    
				 modelMap.addAttribute("appointmentRegisterForm",appointmentRegisterForm);
			     return "getOwnerId";
			 }else if(appointmentForm!=null&&appointmentForm.getOwnerId()!=null&&idType.equals("doctor")){
				 String controllerOid=appointmentForm.getOwnerId();
				 System.out.println("controllerOid="+controllerOid);
				 session.setAttribute("ownerId",appointmentForm.getOwnerId());	
				 System.out.println("appointmentForm.getOwnerId()="+appointmentForm.getOwnerId());
				 appointmentRegisterForm.setOwnerId(appointmentForm.getOwnerId());	
				 appointmentservice.preparAppointmentRegister3(appointmentRegisterForm);
				 modelMap.addAttribute("appointmentRegisterForm",appointmentRegisterForm);
				 return "appointmentRegister3";
				 
			 }else if(idType.equals("owner")){
		         appointmentRegisterForm.setOwnerId(petLogin.getLoginId());	
		         session.setAttribute("ownerId",petLogin.getLoginId());	
		         appointmentservice.preparAppointmentRegister3(appointmentRegisterForm);
		         modelMap.addAttribute("appointmentRegisterForm",appointmentRegisterForm);
	             return "appointmentRegister3";
			 }
	     }else if(idType.equals("doctor")){
		     return "doctorHome";
		     }else {
			  return "userHome";
			 }
			return "";
      }
	
	@RequestMapping("/appointmentRegisterSubmitPath")
	public String appointmentRegisterSubmitDispatcher(@ModelAttribute("appointmentRegisterForm") AppointmentRegisterForm appointmentRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	     {
		HttpSession session=req.getSession(true);	
		String confirm=req.getParameter("confirm");
	
		String idType=(String) session.getAttribute("idType");
	
		if(confirm!=null){
			AppointmentForm appointmentForm=appointmentRegisterForm.getAppointmentForm();
			if(idType.equals("doctor")){
				appointmentForm=appointmentRegisterForm.getAppointmentForm();
				//String ownerId=appointmentForm.getOwnerId();
				//System.out.println("IdDoctor::ownerId="+ownerId);
				//session.setAttribute("ownerId",ownerId);
			}
		String appoimentDate=(String) session.getAttribute("appointmentDate");
		String doctorScheduleId=(String)session.getAttribute("doctorScheduleId");
		System.out.println("doctorScheduleId="+doctorScheduleId);
		PetLogin petLogin=(PetLogin) session.getAttribute("petLogin");
		String ownerId=(String) session.getAttribute("ownerId");
		System.out.println("SessionOwnerId="+ownerId);
		appointmentRegisterForm.setOwnerId(ownerId);
	    appointmentForm=appointmentRegisterForm.getAppointmentForm();
	    appointmentRegisterForm.setDoctorScheduleId(doctorScheduleId);
	    appointmentForm.setAppointmentDate(appoimentDate);
	    appointmentRegisterForm.setAppointmentForm(appointmentForm);
		 this.appointmentservice.appointmentRegister(appointmentRegisterForm);
		 modelMap.addAttribute("appointmentRegisterForm",appointmentRegisterForm);
	     return "appointmentResult";
	     }else if(idType.equals("doctor")){
		     return "doctorHome";
		     }else {
			  return "userHome";
			 }
      }
	
	
}
