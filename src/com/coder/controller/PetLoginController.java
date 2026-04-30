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
import com.coder.form.PetLoginRegisterForm;
import com.coder.form.PetRegisterForm;
import com.coder.form.serviceRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.form.SpeciesRegisterForm;
import com.coder.model.PetLogin;
import com.coder.service.Doctorservice;
import com.coder.service.Ownerservice;
import com.coder.service.PetLoginservice;
import com.coder.service.Petservice;
import com.coder.service.serviceservice;
import com.coder.service.serviceTypeservice;
import com.coder.service.Speciesservice;


@Controller
@Transactional
public class PetLoginController {
	@Autowired
	private PetLoginservice petLoginservice;
	@Autowired
	private Ownerservice ownerservice;
	@RequestMapping("/petLoginPath")
	public String speciesRegisterDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{
		PetLoginRegisterForm petLoginRegisterForm=new PetLoginRegisterForm();
		modelMap.addAttribute("petLoginRegisterForm",petLoginRegisterForm);
		return "petLogin";
	}
	
	@RequestMapping("/petLoginSubmitPath")
	public String ownerRegisterSubmitDispatcher(@ModelAttribute("petLoginRegisterForm") PetLoginRegisterForm petLoginRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	
		String newacount=req.getParameter("newacount");
		String login=req.getParameter("login");
	
		//exalToBar.main(null);
		if(login!=null){
		PetLoginForm petLoginForm=petLoginRegisterForm.getPetLoginForm();
		PetLogin petLogin=petLoginservice.petLoginUser(petLoginForm);
	
		if(petLogin!=null){
		String id=petLogin.getLoginId();
		HttpSession session=req.getSession(true);	
		session.setAttribute("idType","oid");
		
		if(id.startsWith("oid")){
			session.setAttribute("petLogin",petLogin);
			session.setAttribute("idType","oid");
			return "userHome";
		}else if(id.startsWith("did")){
			
			session.setAttribute("petLogin",petLogin);
			session.setAttribute("idType","doctor");
			return "doctorHome";
		}else if(id.startsWith("id")){
			
			session.setAttribute("idType","id");
			return "adminHome";
		}
		
		}else{
			req.setAttribute("error","Incorrect User Name And Password!");
		}
	    
	     }else{
	    	 OwnerRegisterForm ownerRegisterForm=new OwnerRegisterForm();
	 		 ownerservice.preparOwnerRegister(ownerRegisterForm);
	 		 modelMap.addAttribute("ownerRegisterForm",ownerRegisterForm);
	 		
	 		return "ownerRegister";
	    
	     }
		
		return "petLogin";
      }
	
	
}
