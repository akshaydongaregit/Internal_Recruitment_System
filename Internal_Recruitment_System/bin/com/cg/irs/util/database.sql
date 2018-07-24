--Delete Commands
Delete from assigned_Requisition;
Delete from Requisition;
Delete from employee;
Delete from project;
Delete from users;

--Drop Commands
clear screen;
DROP TABLE assigned_Requisition;
DROP TABLE Requisition;
DROP TABLE employee;
DROP TABLE project;
DROP TABLE users;
DROP sequence user_id_seq;
DROP sequence requisition_id_seq;




CREATE TABLE users(
	users_id VARCHAR2(10) Primary Key,
	password VARCHAR2(20),
	role VARCHAR2(10)
);


CREATE TABLE project(
	project_id VARCHAR2(10) Primary Key,
	project_name VARCHAR2(10),
	description VARCHAR2(20),
	start_date TIMESTAMP,
	end_date TIMESTAMP,
	RM_id  VARCHAR2(15) references users(users_id)
 );
	
		
CREATE TABLE employee
(
	employee_id VARCHAR2(10) Primary Key,
	employee_name VARCHAR2(50),
	project_id VARCHAR2(10) references project(project_id),
	skill VARCHAR2(15),
	domain VARCHAR2(15),
	experience_yrs NUMBER
 );
 

 CREATE TABLE Requisition(
 	requisition_id VARCHAR2(10) Primary Key,
 	RM_id VARCHAR2(10) references users(users_id),
 	project_id VARCHAR2(10) references project(project_id),
 	date_created TIMESTAMP,
 	date_closed TIMESTAMP,
 	current_status  VARCHAR2(10),
 	vacancy_name VARCHAR2(10),
 	skill VARCHAR2(15),
 	domain VARCHAR2(15),
 	number_required NUMBER
 );
 
 
 CREATE TABLE assigned_Requisition(
 RMGE_id VARCHAR2(10) references users(users_id),
 employee_id VARCHAR2(10) references employee(employee_id),
 requisition_id VARCHAR2(10) references Requisition(requisition_id)
 );

 /* squence for generating ID	*/
 create sequence user_id_seq start with 101 increment by 1;  
 create sequence requisition_id_seq start with 101 increment by 1;
 
 