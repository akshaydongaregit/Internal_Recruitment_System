package com.cg.irs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.cg.irs.dto.UserBean;
import com.cg.irs.exception.RecruitmentSystemException;
import com.cg.irs.util.DatabaseConnection;

public class UserDaoImpl implements IUserDao {

	public UserDaoImpl() {

	}

	@Override
	public UserBean authenticateCredinal(UserBean credinals)
			throws RecruitmentSystemException {
		
		String sql = "select ROLE from users where users_id=? and password=?";
		Connection con = DatabaseConnection.getConnection();
		
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, credinals.getUserId());
			pst.setString(2, credinals.getPassword());
			
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				credinals.setRole(rs.getString(1));
				return credinals;
			}else
				throw new RecruitmentSystemException("Invalid Username or Password");
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			throw new RecruitmentSystemException("Invalid Username or Password");
		}
	}

	@Override
	public UserBean createUser(UserBean user) throws RecruitmentSystemException {
		
		String sql = "insert into users(users_id,password,role) values(?,?,?)";
		Connection con = DatabaseConnection.getConnection();
		
		
		try
		{
			user.setUserId(this.generateUserId());
			
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, user.getUserId());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getRole());
			
			int res = pst.executeUpdate();
			if(res>0)
				return user;
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			throw new RecruitmentSystemException("Error while Inserting "+e);
		}
		
		return null;
	}
	
	private String generateUserId() throws RecruitmentSystemException
	{
		String sql = "select user_id_seq.NEXTVAL from dual";
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
				throw new RecruitmentSystemException("Cant Generate user Id");
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			throw new RecruitmentSystemException("Cant Generate user Id");
		}
		
	}
	
	@Override
	public boolean updateRole(UserBean user) throws RecruitmentSystemException {
		return false;
	}

	@Override
	public boolean deleteUser(String id) throws RecruitmentSystemException {
		
		String sql = "delete from users where users_id=?";
		Connection con = DatabaseConnection.getConnection();
		
		
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, id);

			
			int res = pst.executeUpdate();
			if(res>0)
				return true;
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			throw new RecruitmentSystemException("Error while Deleting "+e);
		}
		
		return false;
	}

}
