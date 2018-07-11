package com.cg.irs.service;

import java.util.List;

import com.cg.irs.dao.IProjectDao;
import com.cg.irs.dao.ProjectDaoImpl;
import com.cg.irs.dto.ProjectBean;
import com.cg.irs.exception.RecruitmentSystemException;

public class ProjectServiceImpl implements IProjectService{
	IProjectDao dao;
	public ProjectServiceImpl() {
		dao = new ProjectDaoImpl();
	}
	public List<ProjectBean> getProjectDetails() throws RecruitmentSystemException {
		return dao.getProjectDetails();	
	}

}
