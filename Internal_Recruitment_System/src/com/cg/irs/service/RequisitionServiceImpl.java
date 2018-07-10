package com.cg.irs.service;

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

}