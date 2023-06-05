package com.pky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pky.model.CaseWorker;
import com.pky.service.CaseWorkerService;

@Controller
public class ViewCaseWorkerAccountController {
	
	@Autowired
	private CaseWorkerService cwService;
	
	@GetMapping(value = "/editSWAccount")
	public String  editCWAccount(@RequestParam("cid")Integer id,Model model) {
		
		  CaseWorker caseWorker=cwService.getCaseWorkerAccountById(id);
		  model.addAttribute("caseWorker", caseWorker);
		  
		  return "caseWokerRegForm";
	}
	
	
	@PostMapping(value = "/editSWAccount")
	public String updateSWAccount(@ModelAttribute("caseWorker")CaseWorker cw,Model model) {
		     boolean isUpdated=cwService.updateCaseWorkerAccount(cw);
		     if(isUpdated) {
		    	 model.addAttribute("msg","Your Account is Updated");
		    	 return "updateSucc";
		     }else {
		    	 model.addAttribute("msg","Faild to update");
		    	 return "updateSucc";
		     }
	}

}
