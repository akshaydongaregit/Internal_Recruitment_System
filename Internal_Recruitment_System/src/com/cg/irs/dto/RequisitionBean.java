package com.cg.irs.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RequisitionBean {

	public RequisitionBean() {
	}
	
	private String requisitionId;
	private String rmId;
	private String projectId;
	private Timestamp dateCreated ;
	private Timestamp dateClosed;
	private String currentStatus;
	private String vacancyName;
	private String skill;
	private String domain;
	private int numberRequired;
	
	
	public String getRequisitionId() {
		return requisitionId;
	}
	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Timestamp getDateClosed() {
		return dateClosed;
	}
	public void setDateClosed(Timestamp timestamp) {
		this.dateClosed = timestamp;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getVacancyName() {
		return vacancyName;
	}
	public void setVacancyName(String vacancyName) {
		this.vacancyName = vacancyName;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getNumberRequired() {
		return numberRequired;
	}
	public void setNumberRequired(int numberRequired) {
		this.numberRequired = numberRequired;
	}
	public String getRmId() {
		return rmId;
	}
	public void setRmId(String rmId) {
		this.rmId = rmId;
	}
	@Override
	public String toString() {
		return requisitionId+"  "+rmId+"  "+projectId+"  "+currentStatus+"  "+skill+"  "+domain+"  "+numberRequired;
	}
	


}
