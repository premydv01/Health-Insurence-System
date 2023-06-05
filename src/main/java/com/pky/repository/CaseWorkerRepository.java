package com.pky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pky.entity.CaseWorkerEntity;

public interface CaseWorkerRepository extends JpaRepository<CaseWorkerEntity, Integer>{
	
	public  List<CaseWorkerEntity>  findByRole(String role);
	
	public CaseWorkerEntity findByEmailAndUserPwd(String email,String userPwd);
	
	public CaseWorkerEntity findByEmail(String email);

}
