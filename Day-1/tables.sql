create table regions(
    region_id integer ,
    region_name varchar(50) not null,
    constraint pk_regions primary key (region_id )
);

create table countries(
    country_id varchar(2) ,
    country_name varchar(50) not null,
    region_id integer,
    constraint pk_countries primary key (country_id ),
    constraint fk_countries FOREIGN KEY (region_id) REFERENCES regions(region_id )
);
create table locations(
    location_id integer ,
    street_address varchar(255),
    postal_code varchar(10),
    city varchar(50),
    state_province varchar(50),
    country_id varchar(2) ,
    constraint pk_locations primary key (location_id ),
    constraint fk_locations FOREIGN KEY (country_id) REFERENCES countries(country_id )
);
create table jobs(
   job_id	varchar(15),	
   job_title	varchar(50),	
   min_salary	int,	
   max_salary	int,
   constraint pk_jobs primary key (job_id )	
);

create table departments(
    department_id integer ,
    department_name varchar(50),
    location_id integer ,
    manager_id integer ,
    constraint pk_departments primary key (department_id ),
    constraint fkl_departments FOREIGN KEY (location_id ) REFERENCES locations(location_id  )
);
create table employees(
   employee_id	int	,
   first_name	varchar(50),	
   last_name	varchar(50),	
   email	varchar(100),	
   phone_number	varchar(50),	
   hire_date	date	,
   salary	float,	
   commission_pct	float	,
   job_id	varchar(15),
   manager_id	int	,
   department_id	int	,
   constraint pk_employees  primary key (employee_id),
   constraint fkj_employees  FOREIGN KEY (job_id ) REFERENCES jobs(job_id  ),
   constraint fkm_employees  FOREIGN KEY (manager_id ) REFERENCES employees(employee_id  ),
   constraint fkd_employees  FOREIGN KEY (department_id  ) REFERENCES departments(department_id)
    
);

alter table departments 
    add constraint fkm_departments  FOREIGN KEY (manager_id ) REFERENCES employees(employee_id  )  ;

create table job_history(
     employee_id	int,	
     start_date	        date,	
     end_date	        date,	
     job_id	varchar(15),	
     department_id	int,	
     constraint pk_job_history  primary key (employee_id,start_date),
     constraint fke_job_history FOREIGN KEY( employee_id) REFERENCES  employees(employee_id  ),
     constraint fkj_job_history FOREIGN KEY (job_id ) REFERENCES jobs(job_id  ),
     constraint fkd_job_history  FOREIGN KEY (department_id  ) REFERENCES departments(department_id)
   
);

