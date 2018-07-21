insert into users(users_id,password,role) values(user_id_seq.NEXTVAL,'1234','ADMIN');

insert into users(users_id,password,role) values(user_id_seq.NEXTVAL,'1234','RM');
insert into users(users_id,password,role) values(user_id_seq.NEXTVAL,'1234','RM');
insert into users(users_id,password,role) values(user_id_seq.NEXTVAL,'1234','RMGE');
insert into users(users_id,password,role) values(user_id_seq.NEXTVAL,'1234','RMGE');

insert into project(project_id,project_name,description,start_date,end_date,rm_id)
	values ('101','Space X','space mission','','','102');
insert into project(project_id,project_name,description,start_date,end_date,rm_id)
	values ('102','AeroSpace','Air Bus 777','','','102');
insert into project(project_id,project_name,description,start_date,end_date,rm_id)
	values ('103','open AI','OpenSource AI','','','103');
insert into project(project_id,project_name,description,start_date,end_date,rm_id)
	values ('RMG','','','','','103');
insert into project(project_id,project_name,description,start_date,end_date,rm_id)
	values ('ASSIGNED','','','','','103');
	
 insert into employee(employee_id,employee_name,project_id,skill,domain,experience_yrs) 
 		values('101','Pradeep Rai','RMG','Level3','Java EE',3);
 insert into employee(employee_id,employee_name,project_id,skill,domain,experience_yrs) 
 		values('102','Sarita Sakhare','RMG','Level2','UI/UX',2);
 insert into employee(employee_id,employee_name,project_id,skill,domain,experience_yrs)
 		values('103','Akshay Dongare','103','Level13','Java EE',13);
 insert into employee(employee_id,employee_name,project_id,skill,domain,experience_yrs)
 		values('104','Akash Moralwar','RMG','Level3','Python',3);
 insert into employee(employee_id,employee_name,project_id,skill,domain,experience_yrs) 
 		values('105','Omkar Khare','RMG','Level3','Java EE',3); 		
 
/*	Select Queries	*/
select project_id,project_name,description,rm_id from project;
select requisition_id,RM_id,project_id,current_status,vacancy_name,skill,domain,number_required
from requisition;
select * from assigned_requisition;

Select distinct r.* from Requisition r,assigned_Requisition ar where r.requisition_id=ar.requisition_id and
r.rm_id='103' ; 