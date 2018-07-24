package com.cg.irs.service;

import java.util.List;

import com.cg.irs.dto.AssignedRequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IAssignedRequisitionService {

	public int insertAssignedRequisition(AssignedRequisitionBean requisition) throws RecruitmentSystemException;
	public List<String> getEmployeeIdsByRequisitionId(String requisitionId) throws RecruitmentSystemException;
}
