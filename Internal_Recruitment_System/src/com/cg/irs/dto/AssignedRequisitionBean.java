package com.cg.irs.dto;

public class AssignedRequisitionBean {

	 private String rmgeId;
	 private String employeeId ;
	 private String requisitionId;
	 
	 public AssignedRequisitionBean() {
		 
	}
	 
	public String getRmgeId() {
		return rmgeId;
	}
	public void setRmgeId(String rmgeId) {
		this.rmgeId = rmgeId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getRequisitionId() {
		return requisitionId;
	}
	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}
	 
}
