package com.cg.irs.dao;

import java.util.List;

import com.cg.irs.dto.ProjectBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IProjectDao {
	
	public List<ProjectBean> getProjectDetails() throws RecruitmentSystemException;
	
}
