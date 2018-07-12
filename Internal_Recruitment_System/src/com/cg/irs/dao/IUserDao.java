package com.cg.irs.dao;

import java.util.List;

import com.cg.irs.dto.UserBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IUserDao {
	
	public UserBean authenticateCredinal(UserBean credinals) throws RecruitmentSystemException;
	public UserBean createUser(UserBean user)  throws RecruitmentSystemException;
	public boolean updateRole(UserBean user)  throws RecruitmentSystemException;
	public boolean deleteUser(String id)  throws RecruitmentSystemException;
	public List<UserBean> getUsers() throws RecruitmentSystemException;
}
