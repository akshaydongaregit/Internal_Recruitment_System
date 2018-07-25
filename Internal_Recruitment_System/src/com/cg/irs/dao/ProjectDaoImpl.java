package com.cg.irs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cg.irs.dto.ProjectBean;
import com.cg.irs.exception.RecruitmentSystemException;
import com.cg.irs.util.DatabaseConnection;

public class ProjectDaoImpl implements IProjectDao{

		public ProjectDaoImpl() {
	}

		public List<ProjectBean> getProjectDetails() throws RecruitmentSystemException {
			String sql ="Select * from project where project_id not in ('RMG','ASSIGNED')";
			try {
				List<ProjectBean> list = new ArrayList<ProjectBean>();
				Connection conn = DatabaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					ProjectBean project = new ProjectBean();
					project.setProjectId(rs.getString("project_id"));
					project.setProjectName(rs.getString("project_name"));
					project.setRmId(rs.getString("RM_id"));
					project.setStartDate(rs.getTimestamp("start_date"));
					project.setEndDate(rs.getTimestamp("end_date"));
					project.setDescription(rs.getString("description"));
				    list.add(project);	
				}
				return list;
			} catch (SQLException e) {
				throw new RecruitmentSystemException("No Project Found");
			}	
			
		}


	
}
