package com.cg.irs.pl;
import static java.lang.System.out;




import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.cg.irs.dao.IProjectDao;
import com.cg.irs.dao.ProjectDaoImpl;
import com.cg.irs.dto.UserBean;
import com.cg.irs.service.IUserService;
import com.cg.irs.service.UserServiceImpl;

public class Main {

	private static UserBean current;
	
	public Main() {
	}

	public static void main(String[] args) 
	{
		
		//Print Application Header.
		
		while(true)
		switch(loginUser())
		{
			case "ADMIN":
					new AdminView().present();
				break;
			case "RMGE":
					new ResourceManagerExecutiveView().present();
				break;
			case "RM":
					new ResourceManagerView().present();
				break;
			case "EMPLOYEE":
					new EmployeeView().present();
				break;
		}
	}
	
	public static String loginUser()
	{
		
		BufferedReader in =new BufferedReader( new InputStreamReader(System.in));
		String userName;
		String password;
		String role = "";
		
		Header.printHeader();
		try {
			out.print("\n			User Id   :   ");
			userName = in.readLine();	
			out.print("\n");
			out.print("\n			Password  :   ");
			password = in.readLine();
			out.print("\n");
			
			UserBean credinals = new UserBean();
			credinals.setUserId(userName);
			credinals.setPassword(password);
			IUserService service = new UserServiceImpl();
			credinals = service.authenticateCredinal(credinals);
			
			//setting current login user.
			if(credinals.getRole()!=null)
			{
				setCurrent(credinals);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			out.print("Error : "+e.getMessage()+"\n");
		}
		
		
		return current!=null?current.getRole():"";			
		
	}

	public static UserBean getCurrent() {
		return current;
	}

	public static void setCurrent(UserBean current) {
		Main.current = current;
	}
	
}
