package com.Usa.ri.gov.ies.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Usa.ri.gov.ies.admin.model.CaseWorker;
import com.Usa.ri.gov.ies.admin.model.Plan;
import com.Usa.ri.gov.ies.admin.service.AdminService;
import com.Usa.ri.gov.ies.constants.AppConstants;

@Controller
public class AdminController {
	
	Logger logger=LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	

	@GetMapping(value = "/accRegForm")
	public String loadCaseWorkerForm(Model model) {
		logger.info(AppConstants.METHOD_EXE_STARTED_STR);
		CaseWorker caseWorker=new CaseWorker();
		model.addAttribute("caseWorker", caseWorker);
		
		Map<String,String> roleMap= adminService.getAllRoles();
		model.addAttribute("roleMap", roleMap);
		 
		logger.info(AppConstants.METHOD_EXE_ENDED_STR);
		return  "caseWokerRegForm";
	}
	
	@PostMapping(value ="/saveCaseWorker" )
	public String registerCaseWorker(@ModelAttribute("caseWorker") CaseWorker cw,Model model) {
		logger.info(AppConstants.METHOD_EXE_STARTED_STR);
		 boolean isSaved=adminService.saveAccount(cw);
		 if(isSaved){
			 return "cwRegSuccses";
			 
		 }
		 logger.info(AppConstants.METHOD_EXE_ENDED_STR);
		return "caseWokerRegForm";
	}
	
	@GetMapping(value = "/getCaseWorkers")
	public String getAllCaseWorkers(HttpServletRequest req,Model model) {
		String role="CaseWorker";
		String selectedRole=req.getParameter("role");
		if(selectedRole!=null && !selectedRole.equals("")) {
			role=selectedRole;
		}
	List<CaseWorker> listCaseWorker=adminService.getAccountByRoleId(role);
	model.addAttribute("ListCW", listCaseWorker);
		return "viewAccounts";
	}
	
// for email verification during creating account
	@GetMapping(value = "/validateEmail")
	@ResponseBody
	public String emailValidation(@RequestParam("email")String email) {
		String emailStatus=adminService.getSWForEmailValidation(email);
		return  emailStatus;
	}
//*************************************For Plan**********************************	
	@GetMapping(value = "/addPlan")
	public String loadPlanForm(Model model) {
		    Plan plan=new Plan();
		    model.addAttribute("plan", plan);
		    
		return "regNewPlan";
	}
	
	@PostMapping(value = "/addPlan")
	public String  saveNewPlan(@ModelAttribute("plan")Plan plan,Model model) {
		     boolean isSaved= adminService.savePlan(plan);
		     if(isSaved) {
		    	 model.addAttribute("msg","New Plan created");
		    	 model.addAttribute("plan",new Plan());
		    	 return "regNewPlan";
		     }
		     else {
		    	 model.addAttribute("msg","Faild to create New Plan");
		    	 return "regNewPlan";
		     }
	}
	
	@GetMapping(value = "/viewPlans")
	public String getAllAvailablePlans(Model model) {
		         List<Plan> plans=adminService.getAllPlans();
		         if(plans!=null) {
		        	 model.addAttribute("plans",plans);
		        	 return "viewPlans";
		         }
		         return null;
	}
}
