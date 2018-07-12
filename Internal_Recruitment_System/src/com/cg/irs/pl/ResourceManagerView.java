package com.cg.irs.pl;

import static java.lang.System.out;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import tbf.formatter.TTable;

import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;
import com.cg.irs.service.IRequisitionService;
import com.cg.irs.service.RequisitionServiceImpl;

public class ResourceManagerView implements View{

	BufferedReader in =new BufferedReader( new InputStreamReader(System.in));

	public ResourceManagerView() {
	}

	
/*
  	•	Login to the system from Home Page
	•	Raise a requisition for internal recruitment for his/her concerned  project/s
	•	Accept/Reject the suggested resources against the requisition (if accepted following status updates are done automatically : Requisition status changed to ‘closed’ and Project id of the employee is changed to the concerned project) 
	•	Manually change the ‘Project Name/Code’ of employee on the RM’s concerned project/s to ‘RMG’ once the project is completed.  
	•	Generate various reports for pending as well as closed requisitions for his/her concerned Project, requisitions for a specific period of time, requisitions that are pending or closed.
*/
	@Override
	public void present() {
		while(true)
		{
			try {
				Header.printLine();
				out.print("\n 									"+Main.getCurrent().getRole()+"["+Main.getCurrent().getUserId()+"]");
				out.print(getMenu());
				out.print("\nEnter Choice : ");
				int ch = Integer.parseInt(in.readLine());
				
				switch(ch)
				{
					case 1:
						raiseRequisition();
						break;
					case 2:
						viewSuggestedRequesition();
						break;
					case 3:
						return;
					default:
						out.print("\nInvalid choice");
				}
			} catch (IOException e) {
				e.printStackTrace();
				out.print(e.getMessage());
			}catch(NumberFormatException e)
			{
				out.print("\nInvalid Choice");
			}
		}
	}

	private void raiseRequisition()
	{
		String projectId;
		String vacancyName;
		String skill;
		String domain;
		int required;
		
		try
		{
			out.print("\nEnter Project Id	:");
			projectId = in.readLine();
			out.print("\nEnter Vacancy Name :");
			vacancyName = in.readLine();
			out.print("\nEnter Skills    	:");
			skill = in.readLine();
			out.print("\nEnter Domain    	:");
			domain = in.readLine();
			out.print("\nEnter required  	:");
			required = Integer.parseInt(in.readLine());
			
			RequisitionBean bean = new RequisitionBean();
			bean.setProjectId(projectId);
			bean.setVacancyName(vacancyName);
			bean.setSkill(skill);
			bean.setDomain(domain);
			bean.setNumberRequired(required);
			bean.setDateCreated(new Timestamp(new Date().getTime()));
			bean.setCurrentStatus("OPEN");
			bean.setRmId(Main.getCurrent().getUserId());
			
			IRequisitionService service = new RequisitionServiceImpl();
			String id = service.insertRequisition(bean);
			
			out.print("Requisition is Successfully raised with Id : "+id);
			
		}catch(IOException e)
		{
			e.printStackTrace();
		} catch (RecruitmentSystemException e) {
			e.printStackTrace();
		}
		
	}

	private void viewSuggestedRequesition()
	{
		try {
			//Show Requesition Raised
		viewRequisitions();
		System.out.println("Choose Requesition Id : ");
			String requesitionId = in.readLine();
		
			
		}
		catch(RecruitmentSystemException e)
		{
			e.printStackTrace();
			System.out.print("\n"+e.getMessage());	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void viewRequisitions() throws RecruitmentSystemException {
		IRequisitionService requisitionService = new RequisitionServiceImpl();
		List<RequisitionBean> requisitionList;
		String rmId=Main.getCurrent().getUserId();		
			requisitionList = requisitionService.getSpecificRequisition(rmId);
			Header.printLine(); 
			/*System.out.print("\nrequisitionId  rmId  projectId  dateCreated  dateClosed  currentStatus  vacancyName  skilldomain  numberRequired");
			*/
			TTable<RequisitionBean> table = new TTable<RequisitionBean>();
			table.addColumn("RequisitionId","requisitionId",15);
			table.addColumn("RM Id","rmId",5);
			table.addColumn("ProjectId","projectId",10);
			table.addColumn("Date Created", "dateCreated", 20);
			table.addColumn("Status", "currentStatus", 8);
			table.addColumn("Vacancy Name", "vacancyName",15);
			table.addColumn("Skill","skill",20);
			table.addColumn("Domain","domain",15);
			table.addColumn("Required","numberRequired",10);
			
			table.printHeader();
			table.printBeans(requisitionList);
			System.out.print("\n");
			
			/*for(RequisitionBean requisition : requisitionList)
			{
				System.out.print("\n"+requisition);
			}*/
		
	}
	
	@Override
	public String getMenu() {
		String menu = ""
				+ "\n1. Raise a Requisition."
				+ "\n2. View Suggested Requistions Resources."
				+ "\n3. LogOut";
		return menu;
	}

}
