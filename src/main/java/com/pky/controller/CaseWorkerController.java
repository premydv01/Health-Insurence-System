package com.pky.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pky.model.CaseWorker;
import com.pky.service.CaseWorkerService;

@Controller
public class CaseWorkerController {
	
	@Autowired
	private CaseWorkerService caseWorkerService;
	

	@GetMapping(value = "/accRegForm")
	public String loadCaseWorkerForm(Model model) {
		CaseWorker caseWorker=new CaseWorker();
		model.addAttribute("caseWorker", caseWorker);
		
		Map<String,String> roleMap= caseWorkerService.getAllRoles();
		model.addAttribute("roleMap", roleMap);
		
		return  "caseWokerRegForm";
	}
	
	@PostMapping(value ="/saveCaseWorker" )
	public String registerCaseWorker(@ModelAttribute("caseWorker") CaseWorker cw,Model model) {
		
		 boolean isSaved=caseWorkerService.saveCaseWorker(cw);
		 if(isSaved){
			 return "cwRegSuccses";
		 }
		return "caseWokerRegForm";
	}
	
	@GetMapping(value = "/getCaseWorkers")
	public String getAllCaseWorkers(Model model) {
	List<CaseWorker> listCaseWorker=caseWorkerService.getCaseWorkerByRoleId("CaseWorker");
	model.addAttribute("ListCW", listCaseWorker);
		return "viewAccounts";
	}
	
// for email verification
	@GetMapping(value = "/validateEmail")
	@ResponseBody
	public String emailValidation(@RequestParam("email")String email) {
		String emailStatus=caseWorkerService.getSWForEmailValidation(email);
		return  emailStatus;
	}
}
