package com.cg.irs.dao;

import java.util.List;

import com.cg.irs.dto.AssignedRequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IAssignedRequisitionDao {

	public int insertAssignedRequisition(AssignedRequisitionBean requisition) throws RecruitmentSystemException;
	public List<String> getEmployeeIdsByRequisitionId(String requisitionId) throws RecruitmentSystemException;
	public int deleteAssignedRequisition(String requisitionId,String employeeId) throws RecruitmentSystemException;
}
