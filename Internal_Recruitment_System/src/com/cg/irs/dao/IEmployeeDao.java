package com.cg.irs.dao;

import java.util.List;

import com.cg.irs.dto.EmployeeBean;
import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IEmployeeDao {

	public List<EmployeeBean> getMatchingEmployeeList(RequisitionBean requisition) throws RecruitmentSystemException;
	public int updateProjectId(String empId,String projectId) throws RecruitmentSystemException;

}
