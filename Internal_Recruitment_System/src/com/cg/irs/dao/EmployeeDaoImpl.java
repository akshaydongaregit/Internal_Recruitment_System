package com.cg.irs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cg.irs.dto.EmployeeBean;
import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;
import com.cg.irs.util.DatabaseConnection;

public class EmployeeDaoImpl implements IEmployeeDao{

	@Override
	public List<EmployeeBean> getMatchingEmployeeList(
			RequisitionBean requisition) throws RecruitmentSystemException {
		Connection con = DatabaseConnection.getConnection();
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();
		String sql = "select * from employee where skill like ? and domain like ? and project_id='RMG' and project_id NOT LIKE 'ASSIGNED'";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,requisition.getSkill());
			st.setString(2, requisition.getDomain());
			
			ResultSet res = st.executeQuery();
			
			while(res.next())
			{
				EmployeeBean emp = new EmployeeBean();
				emp.setEmployeeId(res.getString("employee_id"));
				emp.setEmployeeName(res.getString("employee_name"));
				emp.setProjectId(res.getString("project_id"));
				emp.setDomain(res.getString("domain"));
				emp.setSkill(res.getString("skill"));
				emp.setExperienceYears(res.getInt("experience_yrs"));
				list.add(emp);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RecruitmentSystemException("No Employee Found "+e);
		}
		
		return list;
	}

	@Override
	public int updateProjectId(String empId, String projectId)
			throws RecruitmentSystemException {
		
		String sql = "update employee set project_id = ? where employee_id = ?";
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,projectId);
			st.setString(2,empId);
			int rs = st.executeUpdate();
			
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RecruitmentSystemException("unable to update project Id");
		}
	}

	@Override
	public List<EmployeeBean> getEmployeeListByIdList(List<String> idList)
			throws RecruitmentSystemException {
		
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();
		
		try
		{
			
			
			for(String id:idList)
			{
				EmployeeBean emp = getEmployeeById(id);
				list.add(emp);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RecruitmentSystemException("No Employee Found "+e);
		}
		
		return list;
	}

	public EmployeeBean getEmployeeById(String id) throws RecruitmentSystemException
	{
		Connection con = DatabaseConnection.getConnection();
		
		String sql = "select * from employee where employee_id=?";
		
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,id);
			
			ResultSet res = st.executeQuery();
			
			if(res.next())
			{
				EmployeeBean emp = new EmployeeBean();
				emp.setEmployeeId(res.getString("employee_id"));
				emp.setEmployeeName(res.getString("employee_name"));
				emp.setProjectId(res.getString("project_id"));
				emp.setDomain(res.getString("domain"));
				emp.setSkill(res.getString("skill"));
				emp.setExperienceYears(res.getInt("experience_yrs"));
				
				return emp;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RecruitmentSystemException("No Employee Found "+e);
		}
		
		return null;
	}
}
