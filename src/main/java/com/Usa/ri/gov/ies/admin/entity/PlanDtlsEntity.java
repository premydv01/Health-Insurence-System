package com.Usa.ri.gov.ies.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "PLAN_DTLS")
@Data
public class PlanDtlsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAN_ID")
	private Integer planId;
	@Column(name = "ACTIVE_SW")
	private String activeSW;
	@Column(name = "PLAN_DESC")
	private String planDesc;
	@Column(name = "PLAN_NAME")
	private String planName;
	
	@Column(name = "START_DT")
	private String startDate;
	@Column(name = "END_DT")
	private String endDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "CREATED_BY")
    private String createdBy;
    
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DT",updatable =false )
    private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DT", insertable = false)
	private Date updatedDate;


}
