package com.coder.controller;

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

import com.coder.form.DoctorRegisterForm;
import com.coder.form.DoctorScheduleRegisterForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.model.Doctor;
import com.coder.service.DoctorScheduleservice;
import com.coder.service.Doctorservice;
import com.coder.service.serviceservice;
import com.coder.service.serviceTypeservice;


@Controller
@Transactional
public class DoctorScheduleController {
	@Autowired
	private DoctorScheduleservice doctorSchduleservice; 
	@RequestMapping("/selectDoctorPath")
	public String selectDoctorDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{
		DoctorScheduleRegisterForm doctorScheduleRegisterForm=new DoctorScheduleRegisterForm();
		doctorSchduleservice.preparDoctorScheduleRegister(doctorScheduleRegisterForm);
		modelMap.addAttribute("doctorScheduleRegisterForm",doctorScheduleRegisterForm);
		return "selectDoctor";
	}
	@RequestMapping("/doctorScheduleRegisterPath")
	public String serviceRegisterDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{   String frmDoctorId=req.getParameter("frmDoctorId");
	HttpSession session=req.getSession(true);	
	
	DoctorScheduleRegisterForm doctorScheduleRegisterForm=new DoctorScheduleRegisterForm();
	doctorScheduleRegisterForm.setDoctorId(frmDoctorId);
		modelMap.addAttribute("doctorScheduleRegisterForm",doctorScheduleRegisterForm);
		doctorSchduleservice.preparDoctorScheduleRegister(doctorScheduleRegisterForm);
		session.setAttribute("doctor",doctorScheduleRegisterForm.getDoctor());
		return "doctorScheduleRegister";
	}
	
	@RequestMapping("/doctorScheduleRegisterSubmitPath")
	public String serviceTypeRegisterSubmitDispatcher(@ModelAttribute("doctorScheduleRegisterForm") DoctorScheduleRegisterForm doctorScheduleRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	HttpSession session=req.getSession(true);
		String finish=req.getParameter("Exit");
		String save=req.getParameter("save");
		if(save!=null){
			Doctor doctor=(Doctor) session.getAttribute("doctor");
			doctorScheduleRegisterForm.setDoctorId(doctor.getDoctorId()+"");
		    doctorSchduleservice.preparDoctorScheduleRegister(doctorScheduleRegisterForm);
		    modelMap.addAttribute("doctorScheduleRegisterForm",doctorScheduleRegisterForm);
	     return "doctorScheduleRegister";
	     }else{
	     return "adminHome";
	     }
      }
	
	
}
