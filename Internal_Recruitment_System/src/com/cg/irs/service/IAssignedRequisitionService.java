package com.cg.irs.service;

import com.cg.irs.dto.AssignedRequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IAssignedRequisitionService {

	public int insertAssignedRequisition(AssignedRequisitionBean requisition) throws RecruitmentSystemException;
}
