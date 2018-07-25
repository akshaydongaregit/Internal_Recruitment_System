package com.cg.irs.service;

import java.util.List;

import com.cg.irs.dao.IUserDao;
import com.cg.irs.dao.UserDaoImpl;
import com.cg.irs.dto.UserBean;
import com.cg.irs.exception.RecruitmentSystemException;

public class UserServiceImpl implements IUserService {

	IUserDao dao;
	
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}

	@Override
	public UserBean authenticateCredinal(UserBean credinals)
			throws RecruitmentSystemException {
		return dao.authenticateCredinal(credinals);
	}

	@Override
	public UserBean createUser(UserBean user) throws RecruitmentSystemException {
		if(validateUser(user))
			return dao.createUser(user);
		else 
			throw new RecruitmentSystemException("Invalid Role");
	}
	
	private boolean validateUser(UserBean user) throws RecruitmentSystemException
	{
		String role = user.getRole();
		
		if(role.equals("ADMIN")||role.equals("RM")||role.equals("RMGE"))
			return true;
		
		return false;
	}
	@Override
	public boolean updateRole(UserBean user) {
		return false;
	}

	@Override
	public boolean deleteUser(String id) throws RecruitmentSystemException {
		return dao.deleteUser(id);
	}

	@Override
	public List<UserBean> getUsers() throws RecruitmentSystemException {
		return dao.getUsers();
	}

}
