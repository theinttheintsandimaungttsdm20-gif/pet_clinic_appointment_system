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
import com.coder.form.PetRegisterForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.form.SpeciesRegisterForm;
import com.coder.service.Doctorservice;
import com.coder.service.Ownerservice;
import com.coder.service.Petservice;
import com.coder.service.serviceservice;
import com.coder.service.serviceTypeservice;
import com.coder.service.Speciesservice;


@Controller
@Transactional
public class SpeciesRegisterController {
	@Autowired
	private Speciesservice speciesservice;
	
	@RequestMapping("/speciesRegisterPath")
	public String speciesRegisterDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{
		SpeciesRegisterForm speciesRegisterForm=new SpeciesRegisterForm();
		
		speciesservice.preparSpeciesRegister(speciesRegisterForm);
		modelMap.addAttribute("speciesRegisterForm",speciesRegisterForm);
		return "speciesRegister";
	}
	
	@RequestMapping("/speciesRegisterSubmitPath")
	public String ownerRegisterSubmitDispatcher(@ModelAttribute("speciesRegisterForm") SpeciesRegisterForm speciesRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	
		String finish=req.getParameter("Exit");
		String save=req.getParameter("save");
		
		if(save!=null){
		speciesservice.preparSpeciesRegister(speciesRegisterForm);
		
		modelMap.addAttribute("speciesRegisterForm",speciesRegisterForm);
	     return "speciesRegister";
	     }else{
	     return "adminHome";
	     }
      }
	
	
}
