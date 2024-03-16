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


/*
21.
Write a query that displays the employee number and lastname of all employees whowork in a department with any employee whose lastname contains a "u".
*/
select   E1.employee_id,E1.last_name ,E1.department_id
from employees E1 
where E1.department_id  in (select DISTINCT E.department_id from  employees E  
				Where    E.last_name IS NOT NULL AND INSTR(E.last_name,"u") > 0 )
ORDER BY E1.department_id;




/*
22.
Display the lastname, department number, and job id of all employees whosedepartment location id is 1700.
*/

select   E.last_name ,E.department_id,E.job_id	
from employees E 
	JOIN departments D
	ON E.department_id = D.department_id 
where D.location_id = 1700;

/*
23.
Display the lastname and salary of every employee who reports to "King".
--self join with employee on manager id and last name = King 
*/

/*
24.
Display the department number, lastname, and job id for every employee in the"Executive" department.
--JOIN with departments and department_name = "Executive"
*/


/*
25.
Display the employee number, lastname, and salary of all employees who earn morethan the average salary and who work in a department with any employee with a "u"in their lastname.
*/
select   E1.employee_id,E1.last_name ,E1.salary	
from employees E1 
where E1.department_id  in (select DISTINCT E.department_id from  employees E  
				Where    E.last_name IS NOT NULL AND INSTR(E.last_name,"u") > 0 )
AND E1.salary	> ( SELECT AVG(salary) from employees)
ORDER BY E1.department_id;


/*
26.
Write a query to get unique department ID from employee table.
--DISTINCT department_id from employees 
*/

/*
27.
Write a query to get all employee details from the employee table order by firstname, descending.
--ORDER BY first_name DESC
*/

/*
28.
Write a query to get the names (first_name, last_name), salary, PF of all theemployees (PF is calculated as 15% of salary).
*/

select   E1.employee_id,E1.last_name ,E1.salary	, E1.salary*0.15 AS PF
from employees E1 ;

/*
29.
Write a query to get the employee ID, names (first_name, last_name), salary inascending order of salary.
--ORDER BY salary ASC
*/


/*
30.
Write a query to get the total salaries payable to employees.
*/

select SUM(salary) from employees;

/*
31.
Write a query to get the maximum and minimum salary from employees table.

*/
select MAX(salary),MIN(salary) from employees;

/*
32.
Write a query to get the average salary and number of employees in the employeestable.
*/
select AVG(salary),COUNT(*) from employees;

/*
33.
Write a query to get the number of employees working with the company.
*/
select COUNT(*) from employees;

/*
34.
Write a query to get the number of jobs available in the employees table
*/
select COUNT(DISTINCT job_id) from employees ;

/*
35.
Write a query get all first name from employees table in upper case.
*/
select UPPER(first_name) from employees ;

/*
36.
Write a query to get the first 3 characters of first name from employees table.
*/
select left(first_name,3) from employees ;


/*
37.
Write a query to get the names (for example Ellen Abel, Sundar Ande etc.) of all theemployees from employees table.
*/
select CONCAT_WS(' ',first_name,last_name) AS name from employees;

/*
38.
Write a query to get first name from employees table after removing white spacesfrom both side.
*/
select TRIM(first_name) from employees;

/*
39.
Write a query to get the length of the employee names (first_name, last_name) fromemployees table.
*/
select CONCAT_WS(' ',first_name,last_name), LENGTH(CONCAT_WS(' ',first_name,last_name)) AS Length from employees;
 

/*
40.
Write a query to check if the first_name fields of the employees table containsnumbers.
--NOT YET FOUND A SOLUTION
*/

/*
41.
Write a query to select first 10 records from a table.

*/

Select * from employees Limit 10;

/*
42.
Write a query to get monthly salary (round 2 decimal places) of each and everyemployee.
*/
Select round(salary/12,2) AS "Monthly Slry" from employees ;

/*43.
Write a query to display the name (first_name, last_name) and salary for allemployees whose salary is not in the range $10,000 through $15,000.
*/
select CONCAT_WS(' ',first_name, last_name) AS Name, salary from employees where salary NOT BETWEEN 10000 AND 15000;


/*
44.
Write a query to display the name (first_name, last_name) and department ID of allemployees in departments 30 or 100 in ascending order.
*/

select CONCAT_WS(' ',first_name, last_name) AS Name, department_id from employees where department_id  IN (30,100);





