package com.cg.irs.service;

import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IRequisitionService {

	public String insertRequisition(RequisitionBean requisition) 
			throws RecruitmentSystemException;
}
