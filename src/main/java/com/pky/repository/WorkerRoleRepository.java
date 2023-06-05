package com.pky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pky.entity.RoleEntity;

public interface WorkerRoleRepository extends JpaRepository<RoleEntity,Integer>{

}
