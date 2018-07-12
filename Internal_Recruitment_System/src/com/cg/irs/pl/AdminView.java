package com.cg.irs.pl;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import tbf.formatter.TTable;

import com.cg.irs.dto.UserBean;
import com.cg.irs.exception.RecruitmentSystemException;
import com.cg.irs.service.IUserService;
import com.cg.irs.service.UserServiceImpl;

public class AdminView implements View{

	BufferedReader in =new BufferedReader( new InputStreamReader(System.in));
	
	public AdminView() {
		
	}
/*
	•	Login to the system using his/her credentials from Home Page
	•	Add new users & assign roles, Delete existing users 
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
						addNewUser();
						break;
					case 2:
						deleteUser();
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
	
	public void addNewUser()
	{
		String password;
		String role;
		
		
		try {
			
			out.print("\nEnter Password 	: ");
			password = in.readLine();
			out.print("\nAssign User Role 	: ");
			role = in.readLine();
			
			UserBean user = new UserBean();
			user.setPassword(password);
			user.setRole(role);
			
			IUserService service = new UserServiceImpl();
			user = service.createUser(user);
			
			out.print("User Successfully generated with Id : "+user.getUserId()+""
					+ "\nAssigned Role : "+user.getRole());
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecruitmentSystemException e) {
			e.printStackTrace();
		}
	}
	
	private void displayUser() throws RecruitmentSystemException
	{
		IUserService service = new UserServiceImpl();
		List<UserBean> users = service.getUsers();
		TTable<UserBean> table = new TTable<UserBean>();
		table.addColumn("User Name","userId",10);
		table.addColumn("Role","role",10);
		table.printHeader();
		table.printBeans(users);
	}
	private void deleteUser() {
		String id ;
		try
		{
			displayUser();
			out.print("\nEnter User Id to Delete : ");
			id =  in.readLine();
			
			IUserService service = new UserServiceImpl();
			if(service.deleteUser(id))
			{
				out.print("\nSuccessfully Deleted User for Id : "+id);
			}else
			{
				out.print("\ncant delete User.");
			}
			
		}catch(IOException e)
		{
			e.printStackTrace();
		} catch (RecruitmentSystemException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String getMenu() {
		String menu = 
				  "\n1. Add New User."
				//+ "\n2. Assign Roles."
				+ "\n2. Delete Existing Users."
				+ "\n3. LogOut.";
		return menu;
	}
	
}
