--Drop Commands
DROP TABLE Assigned;
DROP TABLE users;
DROP TABLE project;
DROP TABLE employee;
DROP TABLE Requisition;

CREATE TABLE users(
	users_id VARCHAR2(3) Primary Key,
	password VARCHAR2(20),
	role VARCHAR2(10)
);


CREATE TABLE project(
	project_id VARCHAR2(3) Primary Key,
	project_name VARCHAR2(10),
	description VARCHAR2(20),
	start_date TIMESTAMP,
	end_date TIMESTAMP,
	RM_id  VARCHAR2(3) references users(users_id)
 );
	
		
CREATE TABLE employee(
	employee_id VARCHAR2(3) Primary Key,
	employee_name VARCHAR2(50),
	project_id VARCHAR2(3) references project(project_id),
	skill VARCHAR2(15),
	domain VARCHAR2(15),
	experience_yrs NUMBER
 );
 

 CREATE TABLE Requisition(
 	requisition_id VARCHAR2(3) Primary Key,
 	RM_id VARCHAR2(3) references users(users_id),
 	project_id VARCHAR2(3) references project(project_id),
 	date_created TIMESTAMP,
 	date_closed TIMESTAMP,
 	current_status  VARCHAR2(10),
 	vacancy_name VARCHAR2(10),
 	skill VARCHAR2(5),
 	domain VARCHAR2(5),
 	number_required NUMBER
 );
 
 
 CREATE TABLE Assigned(
 RMG_id VARCHAR2(3) references users(users_id),
 employee_id VARCHAR2(3) references employee(employee_id),
 requisition_id VARCHAR2(3) references Requisition(requisition_id)
 );
 
 /* squence for generating ID	*/
 create sequence user_id_seq start with 101 increment by 1;  
 create sequence requisition_id_seq start with 101 increment by 1;
 
 