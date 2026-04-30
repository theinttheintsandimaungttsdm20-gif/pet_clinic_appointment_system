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

import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.service.serviceTypeservice;


@Controller
@Transactional
public class serviceTypeRegisterController {
	@Autowired
	private serviceTypeservice serviceTypeservice;
	@RequestMapping("/serviceTypeRegisterPath")
	public String serviceTypeRegisterDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{
		serviceTypeRegisterForm serviceTypeRegisterForm=new serviceTypeRegisterForm();
		modelMap.addAttribute("serviceTypeRegisterForm",serviceTypeRegisterForm);
		serviceTypeservice.preparserviceTypeRegister(serviceTypeRegisterForm);
		return "serviceTypeRegister";
	}
	
	@RequestMapping("/serviceTypeRegisterSubmitPath")
	public String serviceTypeRegisterSubmitDispatcher(@ModelAttribute("serviceTypeRegisterForm") serviceTypeRegisterForm serviceTypeRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	
		String finish=req.getParameter("Exit");
		String save=req.getParameter("save");
		if(save!=null){
		serviceTypeservice.preparserviceTypeRegister(serviceTypeRegisterForm);
		 modelMap.addAttribute("serviceTypeRegisterForm",serviceTypeRegisterForm);
	     return "serviceTypeRegister";
	     }else{
	     return "adminHome";
	     }
      }
	
	
}
