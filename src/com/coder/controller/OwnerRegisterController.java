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
import com.coder.form.OwnerForm;
import com.coder.form.OwnerRegisterForm;
import com.coder.form.PetLoginForm;
import com.coder.form.PetRegisterForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.model.Owner;
import com.coder.model.PetLogin;
import com.coder.service.Doctorservice;
import com.coder.service.Ownerservice;
import com.coder.service.PetLoginservice;
import com.coder.service.Petservice;
import com.coder.service.serviceservice;
import com.coder.service.serviceTypeservice;


@Controller
@Transactional
public class OwnerRegisterController {
	@Autowired
	private PetLoginservice petLoginservice;
	@Autowired
	private Petservice petservice;
	@Autowired
	private Ownerservice ownerservice;
	@RequestMapping("/ownerRegisterPath")
	public String serviceRegisterDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{
		OwnerRegisterForm ownerRegisterForm=new OwnerRegisterForm();
		ownerservice.preparOwnerRegister(ownerRegisterForm);
		modelMap.addAttribute("ownerRegisterForm",ownerRegisterForm);
		return "ownerRegister";
	}
	
	@RequestMapping("/ownerRegisterSubmitPath")
	public String ownerRegisterSubmitDispatcher(@ModelAttribute("ownerRegisterForm") OwnerRegisterForm ownerRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	
		String finish=req.getParameter("Exit");
		String save=req.getParameter("save");
		HttpSession session=req.getSession(true);	
		if(save!=null){
			PetRegisterForm petRegisterForm=new PetRegisterForm();
			PetLoginForm petLoginForm=new PetLoginForm();
			OwnerForm ownerForm =ownerRegisterForm.getOwnerForm();
			petRegisterForm.setOwnerId(ownerForm.getOwnerId());
            petLoginForm.setLoginId(ownerForm.getOwnerId());
			petLoginForm.setPassword(ownerForm.getOwnerPassword());
			petLoginForm.setUserName(ownerForm.getOwnerName());
			session.setAttribute("petLogin",petLoginForm);
			session.setAttribute("idType","oid");
		ownerservice.preparOwnerRegister(ownerRegisterForm);
		petLoginservice.petLoginRegister(petLoginForm);
		petservice.preparPetRegister(petRegisterForm);
		
		modelMap.addAttribute("petRegisterForm",petRegisterForm);
	     return "petRegister";
	     }else{
	     return "userHome";
	     }
      }
	
	@RequestMapping("/ownerInfoPath")
	public String ownerInfoDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{   HttpSession session=req.getSession(true);	
	PetLogin petLogin=(PetLogin) session.getAttribute("petLogin");
		String exit=req.getParameter("exit");
		if(exit==null){
		Owner owner=this.ownerservice.getOwnerById(petLogin.getLoginId());
		modelMap.addAttribute("owner",owner);
		
		return "ownerInfo";
		}else{
		return "userHome";	
		}
	}
}
