package com.cg.irs.dao;

import com.cg.irs.dto.AssignedRequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IAssignedRequisitionDao {

	public int insertAssignedRequisition(AssignedRequisitionBean requisition) throws RecruitmentSystemException;
}
