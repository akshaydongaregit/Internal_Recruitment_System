package com.cg.irs.service;

import java.util.List;

import com.cg.irs.dto.ProjectBean;
import com.cg.irs.exception.RecruitmentSystemException;

public interface IProjectService {
	public List<ProjectBean> getProjectDetails() throws RecruitmentSystemException;

}
