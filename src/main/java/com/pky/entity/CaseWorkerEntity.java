package com.pky.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "CASE_WORKER")
@Data
public class CaseWorkerEntity {
	@Id
	@SequenceGenerator(name = "cwid_seq_gen",sequenceName = "CASE_WORKER_ID_SEQ",allocationSize = 1)
	@GeneratedValue(generator ="cwid_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "CASE_WORKER_ID")
    private Integer caseWorkerId;
	
	@Column(name = "FIRST_NAME")
	private String fname;
	@Column(name = "lAST_NAME")
	private String lname;
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "DOB" )
	private String dob;
	
	@Column(name = "GENDER")
	private String  gender;
	
	@Column(name = "SSN_NO")
	private Long   ssnNo;
	
	@Column(name = "ACC_STATUS")
	private String accStatus;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "CONTACT_NO")
	private Long contatNo;
	
	@Column(name = "USER_PWD")
	private String userPwd;
	
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DT",updatable = false)
	private Date createdDate;
	
	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DT" ,insertable = false)
	private Date updatesDate;
	
	@Column(name = "ACTIVE_SW ")
	private String activeSW;

}
