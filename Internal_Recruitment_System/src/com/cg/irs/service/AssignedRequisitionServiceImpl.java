package com.cg.irs.service;

import com.cg.irs.dao.AssignedRequisitionDaoImpl;
import com.cg.irs.dao.IAssignedRequisitionDao;
import com.cg.irs.dto.AssignedRequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public class AssignedRequisitionServiceImpl implements IAssignedRequisitionService{

	IAssignedRequisitionDao dao;
	
	public AssignedRequisitionServiceImpl() {
		dao = new AssignedRequisitionDaoImpl();
	}
	@Override
	public int insertAssignedRequisition(AssignedRequisitionBean requisition)
			throws RecruitmentSystemException {
		//System.out.println("inserting in assigned");
		return dao.insertAssignedRequisition(requisition);
	}

}
