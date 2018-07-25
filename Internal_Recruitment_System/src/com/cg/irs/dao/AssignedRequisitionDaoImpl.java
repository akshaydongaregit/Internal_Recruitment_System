package com.cg.irs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.irs.dto.AssignedRequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;
import com.cg.irs.util.DatabaseConnection;

public class AssignedRequisitionDaoImpl implements IAssignedRequisitionDao {

	@Override
	public int insertAssignedRequisition(AssignedRequisitionBean requisition)
			throws RecruitmentSystemException {
		
		Connection con = DatabaseConnection.getConnection();
		
		String sql = "insert into assigned_requisition(rmge_id,employee_id,requisition_id) values(?,?,?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,requisition.getRmgeId());
			st.setString(2,requisition.getEmployeeId());
			st.setString(3,requisition.getRequisitionId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new RecruitmentSystemException(" Unable to Assign Requisition.");
		}
		
		return 1;
	}

	@Override
	public List<String> getEmployeeIdsByRequisitionId(String requisitionId)
			throws RecruitmentSystemException {
		 
		Connection con = DatabaseConnection.getConnection();
		String sql = "select employee_id from assigned_requisition where requisition_id=?";
		
		List<String> list = new ArrayList<String>();
		
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,requisitionId);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				list.add(rs.getString("employee_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RecruitmentSystemException(" Unable Select Assign Requisition.");
		}
		
		return list;
	}

	@Override
	public int deleteAssignedRequisition(String requisitionId,String empId)
			throws RecruitmentSystemException {
		
		Connection con = DatabaseConnection.getConnection();
		
		String sql = "delete from assigned_requisition where requisition_id=? and employee_id=?";
		
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,requisitionId);
			st.setString(2, empId);
			
			int rs = st.executeUpdate();
			
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RecruitmentSystemException(" Unable Delete Assigned Requisition : "+e.getMessage());
		}
		
	}

}
