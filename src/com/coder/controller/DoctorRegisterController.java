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

import com.coder.form.DoctorForm;
import com.coder.form.DoctorRegisterForm;
import com.coder.form.PetLoginForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.model.PetLogin;
import com.coder.service.Doctorservice;
import com.coder.service.PetLoginservice;
import com.coder.service.serviceservice;
import com.coder.service.serviceTypeservice;


@Controller
@Transactional
public class DoctorRegisterController {
	@Autowired
	private PetLoginservice petLoginservice;
	@Autowired
	private Doctorservice doctorservice;
	@RequestMapping("/doctorRegisterPath")
	public String serviceRegisterDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{
		DoctorRegisterForm doctorRegisterForm=new DoctorRegisterForm();
		modelMap.addAttribute("doctorRegisterForm",doctorRegisterForm);
		doctorservice.preparDoctorRegister(doctorRegisterForm);
		return "doctorRegister";
	}
	
	@RequestMapping("/doctorRegisterSubmitPath")
	public String serviceTypeRegisterSubmitDispatcher(@ModelAttribute("doctorRegisterForm") DoctorRegisterForm doctorRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	
		String finish=req.getParameter("Exit");
		String save=req.getParameter("save");
		if(save!=null){
	     DoctorForm doctorForm=doctorRegisterForm.getDoctorForm();
	    PetLoginForm petLoginForm=new PetLoginForm();
	    String id=doctorForm.getDoctorId();
	    System.out.println("doctorId="+id);
	    petLoginForm.setLoginId(id);
	    petLoginForm.setUserName(doctorForm.getDoctorName());
	    petLoginForm.setPassword(doctorForm.getPassword());
	    this.petLoginservice.petLoginRegister(petLoginForm);
	    HttpSession session=req.getSession(true);	
	    session.setAttribute("doctorId",id);
		doctorservice.preparDoctorRegister(doctorRegisterForm);
		
		 modelMap.addAttribute("doctorRegisterForm",doctorRegisterForm);
	     return "doctorRegister";
	     }else{
	     return "adminHome";
	     }
      }
	
	
}
