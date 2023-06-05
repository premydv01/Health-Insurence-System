package com.pky.service;

import java.util.List;
import java.util.Map;

import com.pky.entity.CaseWorkerEntity;
import com.pky.model.CaseWorker;

public interface CaseWorkerService {
	
	public boolean saveCaseWorker(CaseWorker caseWorker);
	
	public String getSWForEmailValidation(String email);
	
	public Map<String,String> getAllRoles();
	
	public List<CaseWorker> getCaseWorkerByRoleId(String role);
	
	public CaseWorker getCaseWorkerByEmailAndUserPwd(String email,String userPwd);
	
	public boolean unlockCaseWorkerAccount(CaseWorker caseWorker);
	
	public CaseWorker getCaseWorkerAccountById(Integer id);
	
	public boolean updateCaseWorkerAccount(CaseWorker caseWorker);

}
