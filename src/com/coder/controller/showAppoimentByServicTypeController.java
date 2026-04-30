package com.coder.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coder.exal.ExalToBarByScheduleNormal;
import com.coder.exal.ExalToBarByScheduleSJF;
import com.coder.exal.ExalToBarByserviceTypeNormal;
import com.coder.exal.ExalToBarByserviceTypeSJF;
import com.coder.form.serviceTypeForm;
import com.coder.form.serviceTypeRegisterForm;
import com.coder.form.ShowAppointmentForm;
import com.coder.service.serviceTypeservice;
import com.coder.service.ShowAppointmentservice;


@Controller
@Transactional
public class showAppoimentByserviceTypeController {
	@Autowired
	private ShowAppointmentservice showAppointmentservice;
	@Autowired
	private serviceTypeservice serviceTypeservice;
	@RequestMapping("/showAppoinmentByserviceTypePath")
	public String serviceTypeRegisterDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{
		serviceTypeRegisterForm serviceTypeRegisterForm=new serviceTypeRegisterForm();
		modelMap.addAttribute("serviceTypeRegisterForm",serviceTypeRegisterForm);
		serviceTypeservice.preparserviceTypeRegister(serviceTypeRegisterForm);
		return "showAppByType0";
	}
	@RequestMapping("/exalByDoctorSchedule")
	public String exalByDoctorScheduleDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp) throws FileNotFoundException, IOException
	{   ExalToBarByScheduleNormal barByScheduleNormal=new ExalToBarByScheduleNormal();
	    ExalToBarByScheduleSJF barByScheduleSJF=new ExalToBarByScheduleSJF();
	    List<ShowAppointmentForm> appointmentForms=showAppointmentservice.ShowAppointmentByDoctorSchedule();
	    XSSFWorkbook wb=new XSSFWorkbook();
	    barByScheduleNormal.barColumnChart(appointmentForms, wb);
	    barByScheduleSJF.barColumnChart(appointmentForms, wb);
		serviceTypeRegisterForm serviceTypeRegisterForm=new serviceTypeRegisterForm();
		modelMap.addAttribute("serviceTypeRegisterForm",serviceTypeRegisterForm);
		serviceTypeservice.preparserviceTypeRegister(serviceTypeRegisterForm);
		return "showAppByType0";
	}
	@RequestMapping("/exalByserviceType")
	public String exalByserviceTypeDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp) throws FileNotFoundException, IOException
	{   ExalToBarByserviceTypeNormal exalToBarByserviceTypeNormal=new ExalToBarByserviceTypeNormal();
	    ExalToBarByserviceTypeSJF exalToBarByserviceTypeSJF=new ExalToBarByserviceTypeSJF();
	    List<ShowAppointmentForm> appointmentForms=showAppointmentservice.sjfFormByserviceType();
	    XSSFWorkbook wb=new XSSFWorkbook();
	    exalToBarByserviceTypeNormal.barColumnChart(appointmentForms, wb);
	    exalToBarByserviceTypeSJF.barColumnChart(appointmentForms, wb);
		serviceTypeRegisterForm serviceTypeRegisterForm=new serviceTypeRegisterForm();
		modelMap.addAttribute("serviceTypeRegisterForm",serviceTypeRegisterForm);
		serviceTypeservice.preparserviceTypeRegister(serviceTypeRegisterForm);
		return "showAppByType0";
	}
	
	@RequestMapping("/showAppoinmentByserviceTypeSubmitPath")
	public String serviceTypeRegisterSubmitDispatcher(@ModelAttribute("serviceTypeRegisterForm") serviceTypeRegisterForm serviceTypeRegisterForm,ModelMap modelMap,
			HttpServletRequest req,HttpServletResponse resp)
	{	
		String frmserviceTypeId=req.getParameter("frmserviceTypeId");
		ShowAppointmentForm showAppointmentForm=this.showAppointmentservice.showAppoimentByserviceType(frmserviceTypeId);
		modelMap.addAttribute("showAppointmentForm",showAppointmentForm);
	     return "showAppByType1";
	   
      }
	
	
}
