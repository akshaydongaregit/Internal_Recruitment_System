package com.cg.irs.service;

import java.util.List;

import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IRequisitionService {

	public String insertRequisition(RequisitionBean requisition) 
			throws RecruitmentSystemException;
	public List<RequisitionBean> getAllRequisition() throws RecruitmentSystemException;
	public List<RequisitionBean> getSpecificRequisition(String rmId) throws RecruitmentSystemException;
	
}
