package com.cg.irs.exception;

@SuppressWarnings("serial")
public class RecruitmentSystemException extends Exception{

	public RecruitmentSystemException()  {
	 super("Internal Recruitment System Exception");
	}
	
	public RecruitmentSystemException(String msg)  {
		super(msg);
	}
}
