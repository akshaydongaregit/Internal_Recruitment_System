package com.cg.irs.service;

import java.util.List;

import com.cg.irs.dao.IRequisitionDao;
import com.cg.irs.dao.RequisitionDaoImpl;
import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public class RequisitionServiceImpl implements IRequisitionService{

	IRequisitionDao dao;
	public RequisitionServiceImpl() {
		dao = new RequisitionDaoImpl();
	}

	@Override
	public String insertRequisition(RequisitionBean requisition)
			throws RecruitmentSystemException {
		
		String id = dao.insertRequisition(requisition);
		return id;
	}

	@Override
	public List<RequisitionBean> getAllRequisition()
			throws RecruitmentSystemException {
		 return dao.getAllRequisition();	
	}

	@Override
	public List<RequisitionBean> getSpecificRequisition(String rmId)
			throws RecruitmentSystemException {
		return dao.getSpecificRequisition(rmId);
	}

	@Override
	public List<RequisitionBean> getAssignedRequisitionList(String rmId)
			throws RecruitmentSystemException {
		return dao.getAssignedRequisitionList(rmId);
	}

	@Override
	public void updateStatus(String id, String status)
			throws RecruitmentSystemException {
		
		dao.updateStatus(id, status);
	}
	
	

}
