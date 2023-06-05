package com.pky.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pky.constants.AppConstants;
import com.pky.entity.CaseWorkerEntity;
import com.pky.entity.RoleEntity;
import com.pky.model.CaseWorker;
import com.pky.repository.CaseWorkerRepository;
import com.pky.repository.WorkerRoleRepository;
import com.pky.utils.EmailUtils;
import com.pky.utils.PwdUtils;

@Service
public class CaseWorkerServiceImpl implements CaseWorkerService{
	
	@Autowired
	private CaseWorkerRepository caseWorkerRepo;
	
	@Autowired
	private WorkerRoleRepository roleRepo;
	
	@Autowired
	private EmailUtils emailUtils;
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
	public boolean saveCaseWorker(CaseWorker caseWorker) {
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
	public List<CaseWorker> getCaseWorkerByRoleId(String role) {
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
	public CaseWorker getCaseWorkerByEmailAndUserPwd(String email, String userPwd) {
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
	public boolean unlockCaseWorkerAccount(CaseWorker caseWorker) {
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

//get CaseWorker for Edit functionality
	@Override
	public CaseWorker getCaseWorkerAccountById(Integer id) {
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
	public boolean updateCaseWorkerAccount(CaseWorker caseWorker) {
		CaseWorkerEntity entity=new CaseWorkerEntity();
		BeanUtils.copyProperties(caseWorker, entity);
		CaseWorkerEntity cwEntity=caseWorkerRepo.save(entity);
		if(cwEntity!=null) {
			return true;
		}
		return false;
	}


	
	

	
	

}
