package com.cg.irs.service;

import java.util.List;

import com.cg.irs.dao.EmployeeDaoImpl;
import com.cg.irs.dao.IEmployeeDao;
import com.cg.irs.dto.EmployeeBean;
import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public class EmployeeServiceImpl implements IEmployeeService{

	IEmployeeDao dao ;
	
	public EmployeeServiceImpl() {
		dao = new EmployeeDaoImpl();
	}

	@Override
	public List<EmployeeBean> getMatchingEmployeeList(
			RequisitionBean requisition) throws RecruitmentSystemException {
		return dao.getMatchingEmployeeList(requisition);
	}

	@Override
	public int updateProjectId(String empId, String projectId)
			throws RecruitmentSystemException {
		return dao.updateProjectId(empId, projectId);
	}

	@Override
	public int changeStatus(String empId, String status)
			throws RecruitmentSystemException {
		return dao.updateProjectId(empId, status);
	}

}
