package com.Usa.ri.gov.ies.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Usa.ri.gov.ies.admin.model.CaseWorker;
import com.Usa.ri.gov.ies.admin.model.Plan;
import com.Usa.ri.gov.ies.admin.service.AdminService;

@Controller
public class ViewPlanController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping(value = "/editSWPlan")
	public String editPlan(@RequestParam("pid")Integer planId,Model model) {
		  Plan plan= adminService.getPlanById(planId);
		
			  model.addAttribute("plan", plan);
			  return "regNewPlan";
		
	}

	@GetMapping(value = "/deletePlan")
	public String deletePlan(@RequestParam("pid")Integer id,Model model) {
		      Plan plan= adminService.getPlanById(id);
		boolean isDelete=adminService.deletePlan(plan);
		if(isDelete) {
		return "redirect:/viewPlans";
		}
		return null;
	}
	
	@GetMapping(value = "/activatePlan")
	public String activateCaseWorkerAccount(@RequestParam("pid")Integer id,Model model) {
		Plan plan= adminService.getPlanById(id);
			boolean isActivated=adminService.activatePlan(plan);
			if(isActivated) {
			return "redirect:/viewPlans";
			}
			return null;
	}
}
