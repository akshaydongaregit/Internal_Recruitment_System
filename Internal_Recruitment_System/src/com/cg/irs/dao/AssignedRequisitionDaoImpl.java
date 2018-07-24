package com.cg.irs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cg.irs.dto.AssignedRequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;
import com.cg.irs.util.DatabaseConnection;

public class AssignedRequisitionDaoImpl implements IAssignedRequisitionDao {

	@Override
	public int insertAssignedRequisition(AssignedRequisitionBean requisition)
			throws RecruitmentSystemException {
		System.out.println("inserting in assigned");
		
		Connection con = DatabaseConnection.getConnection();
		
		String sql = "insert into assigned_requisition(rmge_id,employee_id,requisition_id) values(?,?,?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,requisition.getRmgeId());
			st.setString(2,requisition.getEmployeeId());
			st.setString(3,requisition.getRequisitionId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RecruitmentSystemException(" Unable to Assign Requisition.");
		}
		
		return 1;
	}

}
