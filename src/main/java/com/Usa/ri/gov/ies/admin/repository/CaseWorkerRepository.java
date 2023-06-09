package com.Usa.ri.gov.ies.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Usa.ri.gov.ies.admin.entity.CaseWorkerEntity;

public interface CaseWorkerRepository extends JpaRepository<CaseWorkerEntity, Integer>{
	
	public  List<CaseWorkerEntity>  findByRole(String role);
	
	public CaseWorkerEntity findByEmailAndUserPwd(String email,String userPwd);
	
	public CaseWorkerEntity findByEmail(String email);

}
