package com.cg.irs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;
import com.cg.irs.util.DatabaseConnection;

public class RequisitionDaoImpl implements IRequisitionDao{

	public RequisitionDaoImpl() {
	}

	@Override
	public String insertRequisition(RequisitionBean requisition)
			throws RecruitmentSystemException {

		String sql = "insert into Requisition(requisition_id,RM_id,project_id,date_created,current_status,vacancy_name,skill,domain,number_required) "
				+ " values(?,?,?,?,?,?,?,?,?)";
		
		Connection con = DatabaseConnection.getConnection();
		
		
		try
		{
			requisition.setRequisitionId(this.generateRequisitionId());
			
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1,requisition.getRequisitionId());
			pst.setString(2,requisition.getRmId());
			pst.setString(3, requisition.getProjectId());
			pst.setTimestamp(4,requisition.getDateCreated());
			pst.setString(5, requisition.getCurrentStatus());
			pst.setString(6, requisition.getVacancyName());
			pst.setString(7, requisition.getSkill());
			pst.setString(8, requisition.getDomain());
			pst.setInt(9,requisition.getNumberRequired());
			
			int res = pst.executeUpdate();
			if(res>0)
				return requisition.getRequisitionId();
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			throw new RecruitmentSystemException("Error while Inserting "+e);
		}
		
		return null;
	}

	private String generateRequisitionId() throws RecruitmentSystemException
	{
		String sql = "select requisition_id_seq.NEXTVAL from dual";
		Connection con = DatabaseConnection.getConnection();
		
		try
		{
			Statement st=con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				String id = ""+rs.getInt(1);
				return id;
			}else
				throw new RecruitmentSystemException("Cant Generate Requisition Id");
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			throw new RecruitmentSystemException("Cant Generate Requisition Id");
		}
		
	}
	
}
