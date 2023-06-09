package com.Usa.ri.gov.ies.admin.model;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Plan {
	private Integer planId;
	
	private String activeSW;

	private String planDesc;

	private String planName;
	
	
	
	private String startDate;

	private String endDate;
	

	private String updatedBy;

    private String createdBy;
}
