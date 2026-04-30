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
public class serviceTypeUpdateDeleteController {
	@Autowired
	private serviceTypeservice serviceTypeservice;
	
	@RequestMapping("/serviceTypeUpdatePath")
	public String serviceTypeUpdateDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{ 
		serviceTypeRegisterForm serviceTypeRegisterForm=new serviceTypeRegisterForm();
		serviceTypeForm serviceTypeForm=new serviceTypeForm();
		String frmserviceTypeId=req.getParameter("frmserviceTypeId");
		serviceTypeForm.setserviceTypeId(frmserviceTypeId);
		serviceTypeRegisterForm.setserviceTypeForm(serviceTypeForm);
        modelMap.addAttribute("serviceTypeRegisterForm",serviceTypeRegisterForm);
		serviceTypeservice.preparserviceTypeUpdate(serviceTypeRegisterForm);
		return "serviceTypeUpdate";
	}
	
	@RequestMapping("/serviceTypeUpdateSubmitPath")
	public String serviceTypeUpdateSubmitDispatcher(@ModelAttribute("serviceTypeRegisterForm") serviceTypeRegisterForm serviceTypeRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	String cancle=req.getParameter("cancle");
		String update=req.getParameter("update");
		if(update!=null){
		serviceTypeservice.serviceTypeUpdate(serviceTypeRegisterForm);
		
     }else if(cancle!=null){
    	 serviceTypeRegisterForm.setserviceTypeForm(null);
    	 serviceTypeservice.serviceTypeUpdate(serviceTypeRegisterForm); 
     }
		this.serviceTypeservice.preparserviceTypeRegister(serviceTypeRegisterForm);
		 modelMap.addAttribute("serviceTypeRegisterForm",serviceTypeRegisterForm);
		 return "serviceTypeRegister";
	  }
	@RequestMapping("/serviceTypeDeletePath")
	public String serviceTypeDeleteDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{ 
		serviceTypeRegisterForm serviceTypeRegisterForm=new serviceTypeRegisterForm();
		serviceTypeForm serviceTypeForm=new serviceTypeForm();
		String frmserviceTypeId=req.getParameter("frmserviceId");
		serviceTypeForm.setserviceTypeId(frmserviceTypeId);
		serviceTypeRegisterForm.setserviceTypeForm(serviceTypeForm);
       
		this.serviceTypeservice.serviceTypeDelete(serviceTypeRegisterForm);
		this.serviceTypeservice.preparserviceTypeRegister(serviceTypeRegisterForm);
		 modelMap.addAttribute("serviceTypeRegisterForm",serviceTypeRegisterForm);
		return "serviceTypeRegister";
	}
	
}
