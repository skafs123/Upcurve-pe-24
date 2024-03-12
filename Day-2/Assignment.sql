use test1_db;


create table job_grades(
   grade_level   varchar(2),	
   min_salary   int,	
   max_salary   int,
   constraint pk_job_grades primary key (grade_level )	
);

INSERT INTO job_grades(grade_level,min_salary,max_salary )
VALUES ('1',2000,5000),
       ('2',5001,10000),
	('3',10001,15000),
	('4',15001,20000),
	('5',20001,30000),
	('6',30001,40000);


/* 1. Write a query to display the lastname, department number, and department name
for all employees.*/

Select E.first_name, E.last_name , D.department_id ,D.department_name 
from employees E
	LEFT JOIN departments D
 	on E.department_id = D.department_id;


/* 2. Create a unique listing of all jobs that are in department 30. Include the location id in
the output.*/

select DISTINCT J.job_id ,D.location_id 	        	
from departments D   
	JOIN  employees E 
	ON D.department_id = E.department_id 	
 	JOIN jobs J
	ON E.job_id = J.job_id	
where D.department_id =30;

/* 3. Write a query to display the employee lastname, department name, location id, and
city of all employees who earn a commission.*/

select  E.last_name ,D.department_name, D.location_id ,L.city 	 
from employees E ,departments D , locations L  
where  (E.department_id = D.department_id ) AND (D.location_id = L.location_id ) 
AND commission_pct is not null; 


/*4. Display the employee lastname, and department name for all employees who have
an "a" in their lastname.*/
select E.last_name ,D.department_name 
from employees E ,departments D    
where  (E.department_id = D.department_id ) AND 
E.last_name IS NOT NULL AND INSTR(E.last_name,"a") > 0 ; 


/*
5. Write a query to display the lastname, job, department number, and department
name for all employees who work in Toronto.*/
select  E.last_name ,D.department_id,D.department_name 	 
from employees E ,departments D , locations L  
where  (E.department_id = D.department_id ) AND (D.location_id = L.location_id ) 
AND L.city = "Toronto";  



/*
6. Display the employee lastname and employee number along with their manager's
lastname and manager number. Label the columns "Employee", "Emp#", "Manager"
and "Manager#" respectively.*/
select   E.employee_id AS   "Emp#"  , E.last_name AS  "Employee",   M.employee_id   AS "Manager#", M.last_name AS "Manager"
from employees E ,employees M
where  (E.manager_id = M.employee_id );
 


/*
7. Modify the above to display the same columns for all employees (including "King",
who has no manager). Order the result by the employee number.*/
select   E.employee_id AS   "Emp#"  , E.last_name AS  "Employee",   M.employee_id   AS "Manager#", M.last_name AS "Manager"
from employees E 
	LEFT JOIN employees M
	ON E.manager_id = M.employee_id 
	ORDER BY E.employee_id ;



/*
8. Create query that displays employee lastnames, department numbers, and all the
employees who work in the same department as a given employee. Give each 
column an appropriate label.*/
select last_name  AS "Employee" ,department_id  "Department" 
	from employees
	ORDER BY department_id;

/*
9.
Create a query that displays the name, job, department name, salary and grade forall employees.*/
select CONCAT( E.first_name, ' ', E.last_name) AS  "Employee", J.job_title	,
 D.department_name,E.salary, JG.grade_level   	  
from employees E, departments D , jobs J, job_grades JG 
where (E.department_id= D.department_id	) AND
      (E.job_id	=J.job_id)  AND
	(select JG.grade_level where 
		( E.salary >= JG.min_salary) AND ( E.salary <= JG.max_salary));

select CONCAT( E.first_name, ' ', E.last_name) AS  "Employee", J.job_title	,
 D.department_name,E.salary, JG.grade_level   	  
from employees E, departments D , jobs J, job_grades JG 
where (E.department_id= D.department_id	) AND
      (E.job_id	=J.job_id)  AND
	(select JG.grade_level where 
		 E.salary  BETWEEN JG.min_salary AND JG.max_salary);

select CONCAT( E.first_name, ' ', E.last_name) AS  "Employee", J.job_title	,
 D.department_name,E.salary, JG.grade_level   	  
from employees E
	JOIN departments D 
	ON E.department_id= D.department_id
	JOIN  jobs J
	ON E.job_id=J.job_id 
	JOIN  job_grades JG 
	ON E.salary  BETWEEN JG.min_salary AND JG.max_salary;

/*
10.
Create a query to display the name and hiredate of any employee hired afteremployee "Davies"
*/
select CONCAT( E.first_name, ' ', E.last_name) AS  "Employee", E.hire_date
from employees E 
where (E.hire_date >=  
	(select EX.hire_date from employees EX where (EX.last_name =  "Davies" )))
ORDER BY E.hire_date;

/*
11.
Display the names and hire dates for all employees who were hired before theirmanagers, along with their manager's names and hiredates. Label the columns"Employee", "Emp hired", "Manager", and "Manager hired" respectively.
*/

select   E.last_name AS  "Employee",E.hire_date   AS   "Emp hired",  M.last_name AS "Manager" , M.hire_date   AS   "Manager hired"
from employees E 
	JOIN employees M
	ON E.manager_id = M.employee_id 
where E.hire_date < M.hire_date ;


/*
12.
Display the highest, lowest, sum and average salary of all employees. Label thecolumns "Maximum", "Minimum", "Sum", and "Average" respectively.*/

select Max(salary) AS "Maximum" ,Min(salary) AS "Minimum" , Sum(salary) AS "Sum" , AVG(salary) AS "Average" from employees; 
   


/*
13.
Modify the above query to display the same data for each job type.
*/

select employees.job_id, Max(salary) AS "Maximum" ,Min(salary) AS "Minimum" , Sum(salary) AS "Sum" , AVG(salary) AS "Average" from employees
GROUP BY employees.job_id ; 


/*
14.
Write a query to display the number of people with the same job.
*/
select employees.job_id, Count(employees.job_id) AS "Num of Employees"  
from employees
GROUP BY employees.job_id ; 


/*
15.
Determine the number of managers without listing them. Label the column "Numberof Managers". [Hint: use the MANAGER_ID column to determine the number ofmanagers]
*/
select Count(DISTINCT manager_id ) from employees;

/*
16.
Write a query that displays the difference between the highest and the lowes salaries.Label the column as "Difference".
*/
select MAX(salary)-MIN(salary)  AS  "Difference" from employees;


/*
17.
Display the manager number and the salary of the lowest paid employee for thatmanager. Exclude anyone whose manager is not known. Exclude any group wherethe minimum salary is less than $6000. Sort the output in descending order of salary*/

select manager_id ,MIN(salary)  AS MinSalary
 from employees
where ( manager_id IS NOT NULL) 
GROUP BY manager_id 
HAVING MinSalary >6000 ;


/*
18.
Write a query to display each department's name, location, number of employees,and the average salary for all employees in that department. Label the columns"Name", "Location", "No.of people", and "SAlary" respectively. Round the averagesalary to two decimal places.*/
select  D.department_name AS "Name", D.location_id AS  "Location",  COUNT( *) AS  "Number of people",TRUNCATE( AVG(E.salary),2) AS "Salary" 
from employees E , departments D 
Where  E.department_id= D.department_id
GROUP BY E.department_id;


/*
19.
Write a query to display the lastname, and hiredate of any employee in thedepartment as the employee "Zlotkey". Exclude "Zlotkey".*/

select E.last_name AS  "Employee", E.hire_date
from employees E 
where (E.hire_date =  
	(select EX.hire_date from employees EX where (EX.last_name =  "Zlotkey" )))
	AND E.last_name !=  "Zlotkey";

/*
20.
Create a query to display the employee numbers and lastnames of all employeeswho earn more than the average salary. Sort the result in ascending order of salary.*/

select E.employee_id , E.last_name AS  "Employee" , E.salary
from employees E 
where E.salary > (select AVG(salary) from employees)
ORDER BY E.salary ASC ;


