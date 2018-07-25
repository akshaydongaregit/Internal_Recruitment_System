package com.cg.irs.pl;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

public class ResourceManagerExecutiveView implements View{

	BufferedReader in =new BufferedReader( new InputStreamReader(System.in));
	IRequisitionService requisitionService;
	public ResourceManagerExecutiveView() {
		requisitionService = new RequisitionServiceImpl();
	}

	/*
		 
	 	•	Login to the system using his/her credentials from Home Page
		•	Search employee on domain, skill, experience as per the requisitions
		•	Assign RMG project employee matching the profile in the requisitions
 		•	View all requisitions irrespective of the RM or for specific RM
		•	Generate various reports for pending as well as closed requisitions/ RMs 
			including List all raised requisitions for his/her concerned Project, a 
			specific period of time, pending or closed.

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
						viewAllRequisition();
						break;
					case 2:
						viewSpecificRequisition();
						break;
					case 3:
						return;
					default:
						out.print("\nInvalid choice");
				}
			} catch (IOException e) {
				//e.printStackTrace();
				out.print("\n"+e.getMessage());
			}catch(NumberFormatException e)
			{
				//e.printStackTrace();
				out.print("\nInvalid Choice");
			}
		}
	}
	
	private void viewAllRequisition() {
		
		List<RequisitionBean> requisitionList;
		try {
			requisitionList = requisitionService.getAllRequisition();
			printRequistionTable(requisitionList);
			
			//Sending List for Processing Requisitions by RMGE . 
			processRequisition(requisitionList);
			
		}
		catch (RecruitmentSystemException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}	
	}
	
	private void viewSpecificRequisition() {
		
		List<RequisitionBean> requisitionList;
		String rmId="";
		try {
			System.out.println("Enter RM Id to search Requisition : ");
			try {
				rmId = in.readLine();
			} catch (IOException e) {		
				e.printStackTrace();
			}
			
			requisitionList = requisitionService.getSpecificRequisition(rmId);
			printRequistionTable(requisitionList);
			
			//Sending List for Processing Requisitions by RMGE . 
			processRequisition(requisitionList);
		}
		catch (RecruitmentSystemException e) {
			//e.printStackTrace();
			System.out.println("\n"+e.getMessage());
		}
		
	}
	
	@Override
	public String getMenu() {
		String menu =""
				+ "\n1. View All Requisitions."
				+ "\n2. View Requisitions for Specific RM."
				+ "\n3. LogOut.";
		return menu;
	}

	public void printRequistionTable(List<RequisitionBean> requisitionList)
	{
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
	}
	
	/********************************************************************
	 	Module for Processing  Requisition.
	 
	 ********************************************************************/
	public void processRequisition(List<RequisitionBean> requisitionList)
	{
		IEmployeeService employeeService = new EmployeeServiceImpl();
		IAssignedRequisitionService assignedRequisitionService = new AssignedRequisitionServiceImpl();
		
		try {
			System.out.print("\nEnter Requistion Id To Process : ");
			String reqId = in.readLine();
			
			RequisitionBean requisition = null;
			
			for(RequisitionBean req : requisitionList)
			{
				if(req.getRequisitionId().equals(reqId))
				{
					requisition = req;
					break;
				}
			}
			
			if(requisition==null)
			{
				System.out.print("\nNo Requistion Found for Id "+reqId+"in List");
			}else
			{
				List<EmployeeBean> employeeList = employeeService.getMatchingEmployeeList(requisition);
				printEmployeeList(employeeList);
				
				/* Checking for required Employee number */
				if(requisition.getNumberRequired()>employeeList.size())
				{
					System.out.print("\nNot Enough Matching Employees to Complete the Requisition.\n\tNow Exiting...");
					return ;
				}
				
				/* Selecting Employees */
				List<EmployeeBean> selectedList = new ArrayList<EmployeeBean>();
				
				System.out.print("\nEnter \nEmployee Id to Select Employee."
						+ "\nS/s to submit. \nD/d to Discard.");
				int requiredCount = requisition.getNumberRequired();
				
				while(true)
				{
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
								//System.out.print("\nSubmitting...");
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
								
								requisitionService.updateStatus(requisition.getRequisitionId(),"ASSIGNED");
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
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecruitmentSystemException e) {
			//e.printStackTrace();
			System.out.print("\n"+e.getMessage());
		}
		
	}

	public EmployeeBean findEmployee(List<EmployeeBean> employeeList,String id)
	{
	
		for(EmployeeBean emp : employeeList)
			if(emp.getEmployeeId().equalsIgnoreCase(id))
				return emp;
			
		return null;
	}
	public void printEmployeeList(List<EmployeeBean> employeeList)
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
}
