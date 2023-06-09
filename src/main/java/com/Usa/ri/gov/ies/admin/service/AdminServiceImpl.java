package com.Usa.ri.gov.ies.admin.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Usa.ri.gov.ies.admin.entity.CaseWorkerEntity;
import com.Usa.ri.gov.ies.admin.entity.PlanDtlsEntity;
import com.Usa.ri.gov.ies.admin.entity.RoleEntity;
import com.Usa.ri.gov.ies.admin.model.CaseWorker;
import com.Usa.ri.gov.ies.admin.model.Plan;
import com.Usa.ri.gov.ies.admin.repository.CaseWorkerRepository;
import com.Usa.ri.gov.ies.admin.repository.PlanDtlsRepository;
import com.Usa.ri.gov.ies.admin.repository.WorkerRoleRepository;
import com.Usa.ri.gov.ies.constants.AppConstants;
import com.Usa.ri.gov.ies.utils.EmailUtils;
import com.Usa.ri.gov.ies.utils.PwdUtils;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private CaseWorkerRepository caseWorkerRepo;
	
	@Autowired
	private WorkerRoleRepository roleRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private PlanDtlsRepository planRepo;
	
	@Override
	public Map<String, String> getAllRoles() {
		Map<String,String> roleMaps=new LinkedHashMap<String, String>();
		 List<RoleEntity>  roleList=roleRepo.findAll();
		  
		 roleList.forEach(role->{
			 roleMaps.put(role.getRole(),role.getRole());
		 });
		 
		return roleMaps;
	}
	

	@Override
	public boolean saveAccount(CaseWorker caseWorker) {
		caseWorker.setUserPwd(PwdUtils.generateTempPwd(AppConstants.TEMP_PWD_LENGTH));
		caseWorker.setAccStatus(AppConstants.LOCKED_STR);
		
		CaseWorkerEntity caseWorkerEntity=new CaseWorkerEntity();
		
		BeanUtils.copyProperties(caseWorker, caseWorkerEntity);
		
		CaseWorkerEntity worker=caseWorkerRepo.save(caseWorkerEntity);
		if(worker!=null) {
			return  emailUtils.sendUserUnlockEmail(worker);
			//return true;
		}
		return false;
	}


	@Override
	public List<CaseWorker> getAccountByRoleId(String role) {
		List<CaseWorker> caseWroker=new ArrayList<CaseWorker>();
		List<CaseWorkerEntity>  caseWorkerList=caseWorkerRepo.findByRole(role);
		     
		caseWorkerList.forEach(cW->{
			CaseWorker cWorker=new CaseWorker();
			BeanUtils.copyProperties(cW, cWorker);
			caseWroker.add(cWorker);
		});
		
		return caseWroker;
	}

//this method is For Getting CaseWorker For Unlocking the Account
	@Override
	public CaseWorker getAccountByEmailAndUserPwd(String email, String userPwd) {
		  CaseWorkerEntity cWEntity=caseWorkerRepo.findByEmailAndUserPwd(email, userPwd);
		  if(cWEntity!=null) {
			  CaseWorker caseWorker=new CaseWorker();
			  BeanUtils.copyProperties(cWEntity, caseWorker);
			  return caseWorker;
		  }
		return null;
	}

//this method is for updating Caseworker Account
	@Override
	public boolean unlockAccount(CaseWorker caseWorker) {
		caseWorker.setAccStatus(AppConstants.UNLOCKED_STR);
		caseWorker.setActiveSW(AppConstants.ACTIVE_SW_YES);
		CaseWorkerEntity entity=new CaseWorkerEntity();
				BeanUtils.copyProperties(caseWorker, entity);
		         CaseWorkerEntity cwEntity=caseWorkerRepo.save(entity);
		         if(cwEntity!=null) {
		        	 return true;
		         }
		return false;
	}
	
//For Email validation
	@Override
	public String getSWForEmailValidation(String email) {
		CaseWorkerEntity cWEntity=caseWorkerRepo.findByEmail(email);
		System.out.println(cWEntity);
		 if(cWEntity!=null) {
			 return "Duplicate";
		 }else {
		return "Uniqe";
		 }
	}

//get CaseWorker for Edit functionality or delete functionality
	@Override
	public CaseWorker getAccountById(Integer id) {
	        Optional<CaseWorkerEntity> entity=caseWorkerRepo.findById(id);
	      if(entity.isPresent()) {
	    	  CaseWorkerEntity cwEntity=entity.get();
	    	  CaseWorker cw=new CaseWorker();
	          BeanUtils.copyProperties(cwEntity, cw);
	          return cw;
	      }
	      
		return null;
	}

// this method is for updating Edited account
	@Override
	public boolean updateAccount(CaseWorker caseWorker) {
		CaseWorkerEntity entity=new CaseWorkerEntity();
		BeanUtils.copyProperties(caseWorker, entity);
		CaseWorkerEntity cwEntity=caseWorkerRepo.save(entity);
		if(cwEntity!=null) {
			return true;
		}
		return false;
	}

// this method is used for making Account deactiveted
	@Override
	public boolean deleteAccount(CaseWorker caseWorker) {
		caseWorker.setActiveSW(AppConstants.ACTIVE_SW_NO);
		CaseWorkerEntity entity=new CaseWorkerEntity();
				BeanUtils.copyProperties(caseWorker, entity);
		         CaseWorkerEntity cwEntity=caseWorkerRepo.save(entity);
		         if(cwEntity!=null) {
		        	 return true;
		         }
		return false;
	}

// this methosd is for activate the cw account
	@Override
	public boolean activateAccount(CaseWorker caseWorker) {
		caseWorker.setActiveSW(AppConstants.ACTIVE_SW_YES);
		CaseWorkerEntity entity=new CaseWorkerEntity();
				BeanUtils.copyProperties(caseWorker, entity);
		         CaseWorkerEntity cwEntity=caseWorkerRepo.save(entity);
		         if(cwEntity!=null) {
		        	 return true;
		         }
		return false;
	}


//********************************************For plan**********************************
//forg saving new plan
	@Override
	public boolean savePlan(Plan plan) {
		PlanDtlsEntity entity=new PlanDtlsEntity();
		BeanUtils.copyProperties(plan, entity);
		  PlanDtlsEntity planEntity= planRepo.save(entity);
		  if(planEntity!=null) {
			  return true;
		  }
		return false;
	}


	@Override
	public List<Plan> getAllPlans() {
	
		List<PlanDtlsEntity> listPlansEntity=planRepo.findAll();
		if(listPlansEntity!=null) {
			List<Plan> listPlan=listPlansEntity.stream().map(entity->{
				Plan plan =new Plan();
				BeanUtils.copyProperties(entity,plan);
				return plan;
				
			}).collect(Collectors.toList());
			 return listPlan;
		}
		return null;
	}


	@Override
	public Plan getPlanById(Integer id) {
	             Optional<PlanDtlsEntity> planEntity=planRepo.findById(id);
	             
	   	      if(planEntity.isPresent()) {
	   	    	  PlanDtlsEntity entity=planEntity.get();
	   	    	Plan plan=new Plan();
	   	          BeanUtils.copyProperties(entity, plan);
	   	          return plan;
	   	      }
		return null;
	}
	
	
	// this method is used for making Account deactiveted
		@Override
		public boolean deletePlan(Plan plan) {
			plan.setActiveSW(AppConstants.ACTIVE_SW_NO);
			PlanDtlsEntity entity=new PlanDtlsEntity();
					BeanUtils.copyProperties(plan, entity);
			         PlanDtlsEntity planEntity=planRepo.save(entity);
			         if(planEntity!=null) {
			        	 return true;
			         }
			return false;
		}


		@Override
		public boolean activatePlan(Plan plan) {
			plan.setActiveSW(AppConstants.ACTIVE_SW_YES);
			PlanDtlsEntity entity=new PlanDtlsEntity();
					BeanUtils.copyProperties(plan, entity);
			         PlanDtlsEntity planEntity=planRepo.save(entity);
			         if(planEntity!=null) {
			        	 return true;
			         }
			return false;
		}
		}
	
	


