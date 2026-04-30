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

import com.coder.form.serviceForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.service.serviceservice;
import com.coder.service.serviceTypeservice;


@Controller
@Transactional
public class serviceUpdateDeleteController {
	@Autowired
	private serviceservice serviceservice;
	
	@RequestMapping("/serviceUpdatePath")
	public String serviceTypeUpdateDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{ 
		serviceRegisterForm serviceRegisterForm=new serviceRegisterForm();
		serviceForm serviceForm=new serviceForm();
		String frmserviceId=req.getParameter("frmserviceId");
		serviceForm.setserviceId(frmserviceId);
		serviceRegisterForm.setserviceForm(serviceForm);
        modelMap.addAttribute("serviceRegisterForm",serviceRegisterForm);
		serviceservice.preparserviceUpdate(serviceRegisterForm);
		return "serviceUpdate";
	}
	
	@RequestMapping("/serviceUpdateSubmitPath")
	public String serviceTypeUpdateSubmitDispatcher(@ModelAttribute("serviceRegisterForm") serviceRegisterForm serviceRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	String cancle=req.getParameter("cancle");
		String update=req.getParameter("update");
		if(update!=null){
		serviceservice.serviceUpdate(serviceRegisterForm);
		
     }
		this.serviceservice.preparserviceRegister(serviceRegisterForm);
		 modelMap.addAttribute("serviceRegisterForm",serviceRegisterForm);
		 return "serviceRegister";
	  }
	@RequestMapping("/serviceDeletePath")
	public String serviceTypeDeleteDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{ 
		serviceRegisterForm serviceRegisterForm=new serviceRegisterForm();
		serviceForm serviceForm=new serviceForm();
		String frmserviceId=req.getParameter("frmserviceId");
		serviceForm.setserviceId(frmserviceId);
		serviceRegisterForm.setserviceForm(serviceForm);
        this.serviceservice.serviceDelete(serviceRegisterForm);
		this.serviceservice.preparserviceRegister(serviceRegisterForm);
		 modelMap.addAttribute("serviceRegisterForm",serviceRegisterForm);
		return "serviceRegister";
	}
	
}
