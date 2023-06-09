package com.Usa.ri.gov.ies.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Usa.ri.gov.ies.admin.model.CaseWorker;
import com.Usa.ri.gov.ies.admin.service.AdminService;

@Controller
public class ViewAccountsController {
	
	@Autowired
	private AdminService cwService;
	
	@GetMapping(value = "/editSWAccount")
	public String  editCWAccount(@RequestParam("cid")Integer id,Model model) {
		
		  CaseWorker caseWorker=cwService.getAccountById(id);
		  model.addAttribute("caseWorker", caseWorker);
		  
		  return "caseWokerRegForm";
	}
	
	
	@PostMapping(value = "/editSWAccount")
	public String updateSWAccount(@ModelAttribute("caseWorker")CaseWorker cw,Model model) {
		     boolean isUpdated=cwService.updateAccount(cw);
		     if(isUpdated) {
		    	 model.addAttribute("msg","Your Account is Updated");
		    	 return "updateSucc";
		     }else {
		    	 model.addAttribute("msg","Faild to update");
		    	 return "updateSucc";
		     }
	}
   
	@GetMapping(value = "/deleteAccount")
	public String deleteCaseWorkerAccount(@RequestParam("cwid")Integer id,Model model) {
		      CaseWorker cw= cwService.getAccountById(id);
		boolean isDelete=cwService.deleteAccount(cw);
		if(isDelete) {
		return "redirect:/getCaseWorkers";
		}
		return null;
	}
	
	@GetMapping(value = "/activateAccount")
	public String activateCaseWorkerAccount(@RequestParam("cwid")Integer id,Model model) {
		  CaseWorker cw= cwService.getAccountById(id);
			boolean isActivated=cwService.activateAccount(cw);
			if(isActivated) {
			return "redirect:/getCaseWorkers";
			}
			return null;
	}
}
