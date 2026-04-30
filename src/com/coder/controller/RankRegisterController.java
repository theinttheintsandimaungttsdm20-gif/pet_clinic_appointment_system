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

import com.coder.form.RankRegisterForm;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.service.Rankservice;
import com.coder.service.serviceTypeservice;


@Controller
@Transactional
public class RankRegisterController {
	@Autowired
	private Rankservice rankservice;
	@RequestMapping("/rankRegisterPath")
	public String serviceTypeRegisterDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{
		RankRegisterForm rankRegisterForm=new RankRegisterForm();
		modelMap.addAttribute("rankRegisterForm",rankRegisterForm);
		rankservice.preparRankRegister(rankRegisterForm);
		return "rankRegister";
	}
	
	@RequestMapping("/rankRegisterSubmitPath")
	public String serviceTypeRegisterSubmitDispatcher(@ModelAttribute("rankRegisterForm")RankRegisterForm rankRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	
		String finish=req.getParameter("Exit");
		String save=req.getParameter("save");
		if(save!=null){
           rankservice.preparRankRegister(rankRegisterForm);
		 modelMap.addAttribute("rankRegisterForm",rankRegisterForm);
	     return "rankRegister";
	     }else{
	     return "adminHome";
	     }
      }
	
	
}
