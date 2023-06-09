package com.Usa.ri.gov.ies.admin.service;

import java.util.List;
import java.util.Map;

import com.Usa.ri.gov.ies.admin.entity.CaseWorkerEntity;
import com.Usa.ri.gov.ies.admin.model.CaseWorker;
import com.Usa.ri.gov.ies.admin.model.Plan;

public interface AdminService {
	
	public boolean saveAccount(CaseWorker caseWorker);
	
	public String getSWForEmailValidation(String email);
	
	public Map<String,String> getAllRoles();
	
	public List<CaseWorker> getAccountByRoleId(String role);
	
	public CaseWorker getAccountByEmailAndUserPwd(String email,String userPwd);
	
	public boolean unlockAccount(CaseWorker caseWorker);
	
	public CaseWorker getAccountById(Integer id);
	
	public boolean updateAccount(CaseWorker caseWorker);
	
	public boolean deleteAccount(CaseWorker caseWorker);
	
	public boolean activateAccount(CaseWorker caseWorker);
	
	//for plan 
	public boolean savePlan(Plan plan);
	
	public List<Plan> getAllPlans();
	public Plan getPlanById(Integer id);
	public boolean deletePlan(Plan plan);
	public boolean activatePlan(Plan plan);
}
