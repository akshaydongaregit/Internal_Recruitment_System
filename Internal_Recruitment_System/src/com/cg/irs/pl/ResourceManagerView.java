package com.cg.irs.pl;

import static java.lang.System.out;











import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tbf.formatter.TTable;

import com.cg.irs.dto.AssignedRequisitionBean;
import com.cg.irs.dto.EmployeeBean;
import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;
import com.cg.irs.service.AssignedRequisitionServiceImpl;
import com.cg.irs.service.EmployeeServiceImpl;
import com.cg.irs.service.IAssignedRequisitionService;
import com.cg.irs.service.IEmployeeService;
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
			List<RequisitionBean> reqList = viewRequisitions();
			
			System.out.println("Choose Requesition Id : ");
			String requesitionId = in.readLine();
			
			for(RequisitionBean req:reqList)
				if(requesitionId.equals(req.getRequisitionId()))
					processRequisition(req);
			
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
	
	private void processRequisition(RequisitionBean requisition)
	{
		/*System.out.println("printing list for "+requisition.getRequisitionId());
		*/
		IAssignedRequisitionService assignedService = new AssignedRequisitionServiceImpl();
		IEmployeeService employeeService = new EmployeeServiceImpl();
		try {
			
			List<String> empIdList = assignedService.getEmployeeIdsByRequisitionId(requisition.getRequisitionId());
			List<EmployeeBean> empList = employeeService.getEmployeeListByIdList(empIdList);
			/* Selecting Employees */
			List<EmployeeBean> selectedList = new ArrayList<EmployeeBean>();
			
			printEmployeeList(empList);
			
			/51
			
			/*Actual Selecting process of employees*/
			System.out.print("\nEnter \nEmployee Id to Select Employee."
					+ "\nS/s to submit. \nD/d to Discard.");
			int requiredCount = requisition.getNumberRequired();
			
			while(true)
			{
				if(requiredCount==0)
					
				System.out.println("\n["+requiredCount+"]\nEnter Response : ");
				String response = in.readLine();
				
					switch (response) {
					case "s":
					case "S":
						if(requiredCount>0)
						{
							System.out.print(requiredCount+" more Employees Requiered!");
						}else
						{
							
							System.out.print("\n** Requisition Processed SuccessFully **");
							return ;
						}
						break;
					case "d":
					case "D":
						System.out.print("\nAll Changes Discarded \n \t Now Exiting...");
						return ;

					default:
							EmployeeBean emp = findEmployee(employeeList, response);
							if(emp!=null)
							{
								//swap
								selectedList.add(emp);
								employeeList.remove(emp);
								requiredCount--;
								
								System.out.print(" selected list : "+selectedList.size()+" employee list : "+employeeList.size());
								
								System.out.print("\nEmployee "+response+" is Added to Selected.");
							}
							else if(findEmployee(employeeList, response)!=null)
							{
								System.out.print("\nEmployee "+response+" is Allready Selected.");
							}
							else
							{
								System.out.print("\nEmployee With Id "+response+" is Not Available to Select.");
							}
								
						break;
					}
				
			}
			
		} catch (RecruitmentSystemException e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean saveList(List<EmployeeBean> empList,)
	{
		System.out.print("\nSubmitting...");
		for(EmployeeBean emp : selectedList)
		{
			
			AssignedRequisitionBean assigned = new AssignedRequisitionBean();
			assigned.setEmployeeId(emp.getEmployeeId());
			assigned.setRequisitionId(requisition.getRequisitionId());
			assigned.setRmgeId(Main.getCurrent().getUserId());
			
			assignedRequisitionService.insertAssignedRequisition(assigned);
			employeeService.changeStatus(emp.getEmployeeId(),"ASSIGNED");
			
			System.out.print("\n "+emp.getEmployeeId()+" Added. ");
		}
		
		return true;
	}
	private void printEmployeeList(List<EmployeeBean> employeeList)
	{
		//employee_id,employee_name,project_id,skill,domain,experience_yrs
		Header.printLine(); 
		TTable<EmployeeBean> table = new TTable<EmployeeBean>();
		table.addColumn("Employee_Id","employeeId",15);
		table.addColumn("Employee Name","employeeName",15);
		table.addColumn("Project Id", "projectId", 15);
		table.addColumn("Skill", "skill", 15);
		table.addColumn("Domain","domain",15);
		table.addColumn("Experience Year", "experienceYears", 15);
		table.printHeader();
		table.printBeans(employeeList);
		System.out.print("\n");
		
	}
	
	private List<RequisitionBean> viewRequisitions() throws RecruitmentSystemException {
		
		IRequisitionService requisitionService = new RequisitionServiceImpl();
		
		List<RequisitionBean> requisitionList;
		String rmId=Main.getCurrent().getUserId();		
			requisitionList = requisitionService.getAssignedRequisitionList(rmId);
			Header.printLine(); 
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

		return requisitionList;
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
