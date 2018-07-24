package com.cg.irs.dao;

import java.util.List;

import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IRequisitionDao {

	public String insertRequisition(RequisitionBean requisition) 
			throws RecruitmentSystemException;
	public List<RequisitionBean> getAllRequisition() throws RecruitmentSystemException;
	public List<RequisitionBean> getSpecificRequisition(String rmId) throws RecruitmentSystemException;
	public List<RequisitionBean> getAssignedRequisitionList(String rmId) throws RecruitmentSystemException;
	public void updateStatus(String id,String status) throws RecruitmentSystemException;
	
}
