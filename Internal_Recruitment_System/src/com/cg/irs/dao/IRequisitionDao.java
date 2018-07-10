package com.cg.irs.dao;

import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IRequisitionDao {

	public String insertRequisition(RequisitionBean requisition) 
			throws RecruitmentSystemException;
	
}
