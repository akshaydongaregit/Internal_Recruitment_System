package com.cg.irs.service;

import com.cg.irs.dto.UserBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IUserService {
	
	public UserBean authenticateCredinal(UserBean credinals) throws RecruitmentSystemException;
	public UserBean createUser(UserBean user) throws RecruitmentSystemException;
	public boolean updateRole(UserBean user) throws RecruitmentSystemException;
	public boolean deleteUser(String id) throws RecruitmentSystemException;
}