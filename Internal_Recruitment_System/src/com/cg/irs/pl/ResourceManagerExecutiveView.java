package com.cg.irs.pl;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.cg.irs.dto.RequisitionBean;
import com.cg.irs.exception.RecruitmentSystemException;
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
	
	private void viewAllRequisition() {
		
		List<RequisitionBean> requisitions;
		try {
				requisitions = requisitionService.getAllRequisition();
			 
			System.out.println("requisitionId  rmId  projectId  currentStatus  skilldomain  numberRequired");
			for(RequisitionBean r : requisitions)
			{
				System.out.println(requisitions);
			}
		}
		catch (RecruitmentSystemException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
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

}
