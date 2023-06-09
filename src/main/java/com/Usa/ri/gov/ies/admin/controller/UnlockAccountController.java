package com.Usa.ri.gov.ies.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Usa.ri.gov.ies.admin.entity.CaseWorkerEntity;
import com.Usa.ri.gov.ies.admin.model.CaseWorker;
import com.Usa.ri.gov.ies.admin.model.UnlockAccount;
import com.Usa.ri.gov.ies.admin.service.AdminService;

@Controller
public class UnlockAccountController {
	@Autowired
	private AdminService service;
	
	@GetMapping(value = "/unlockAcc")
	public String loadUnlockCaseWorkerForm(@RequestParam("email") String email,Model model) {
		UnlockAccount unlockAccount=new UnlockAccount();
		unlockAccount.setEmail(email);
		model.addAttribute("unlock", unlockAccount);
		
		return "unlockCaseWokerForm";
				
	}
	
	@PostMapping(value = "/unlockAcc")
	public String unlockCaseWorkerAccount(@ModelAttribute("unlock")UnlockAccount account, Model model) {
		    CaseWorker cw=service.getAccountByEmailAndUserPwd(account.getEmail(),account.getTempPwd());
		  if(cw!=null) {
			  cw.setUserPwd(account.getConfirmPwd());
			 boolean isUpdate= service.updateAccount(cw);
			  if(isUpdate) {
			  model.addAttribute("msg","Account Unlocked Successfully");
			 }
		  }else {
			  model.addAttribute("msg","Invalid Credential");
		  }
		    return "unlockAccStatus";
	}

}
