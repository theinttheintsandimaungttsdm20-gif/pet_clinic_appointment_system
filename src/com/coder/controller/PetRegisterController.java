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
import com.coder.form.OwnerRegisterForm;
import com.coder.form.PetLoginForm;
import com.coder.form.PetRegisterForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.model.PetLogin;
import com.coder.service.Doctorservice;
import com.coder.service.Ownerservice;
import com.coder.service.Petservice;
import com.coder.service.serviceservice;
import com.coder.service.serviceTypeservice;


@Controller
@Transactional
public class PetRegisterController {
	@Autowired
	private Petservice petservice;
	@RequestMapping("/petRegisterPath")
	public String serviceRegisterDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{
		PetRegisterForm petRegisterForm=new PetRegisterForm();
		HttpSession session=req.getSession(true);	
		PetLogin petLogin=(PetLogin) session.getAttribute("petLogin");
		//System.out.println("pet login="+petLogin.getLoginId());
		petRegisterForm.setOwnerId(petLogin.getLoginId());
		modelMap.addAttribute("petRegisterForm",petRegisterForm);
		petservice.preparPetRegister(petRegisterForm);
		return "petRegister";
	}
	
	@RequestMapping("/petRegisterSubmitPath")
	public String petRegisterSubmitDispatcher(@ModelAttribute("petRegisterForm") PetRegisterForm petRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	    {	
		String finish=req.getParameter("Exit");
		String save=req.getParameter("save");
		HttpSession session=req.getSession(true);	
		PetLoginForm petLogin=(PetLoginForm) session.getAttribute("petLogin");
		//System.out.println("petLogin="+petLogin);
		petRegisterForm.setOwnerId(petLogin.getLoginId());
		if(save!=null){
		petservice.preparPetRegister(petRegisterForm);
		modelMap.addAttribute("petRegisterForm",petRegisterForm);
	    return "petRegister";
	    }else{
	    return "userHome";
	    }
      }
	
	
}
