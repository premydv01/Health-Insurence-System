package com.pky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pky.entity.CaseWorkerEntity;
import com.pky.model.CaseWorker;
import com.pky.model.UnlockAccount;
import com.pky.service.CaseWorkerService;

@Controller
public class UnlockCaseWorkerController {
	@Autowired
	private CaseWorkerService service;
	
	@GetMapping(value = "/unlockAcc")
	public String loadUnlockCaseWorkerForm(@RequestParam("email") String email,Model model) {
		UnlockAccount unlockAccount=new UnlockAccount();
		unlockAccount.setEmail(email);
		model.addAttribute("unlock", unlockAccount);
		
		return "unlockCaseWokerForm";
				
	}
	
	@PostMapping(value = "/unlockAcc")
	public String unlockCaseWorkerAccount(@ModelAttribute("unlock")UnlockAccount account, Model model) {
		    CaseWorker cw=service.getCaseWorkerByEmailAndUserPwd(account.getEmail(),account.getTempPwd());
		  if(cw!=null) {
			  cw.setUserPwd(account.getConfirmPwd());
			 boolean isUpdate= service.updateCaseWorkerAccount(cw);
			  if(isUpdate) {
			  model.addAttribute("msg","Account Unlocked Successfully");
			 }
		  }else {
			  model.addAttribute("msg","Invalid Credential");
		  }
		    return "unlockAccStatus";
	}

}
