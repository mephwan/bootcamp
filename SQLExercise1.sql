create table regions (
	REGION_ID integer primary key,
    REGION_NAME varchar(25)
);

create table countries (
	COUNTRY_ID varchar(2) primary key,
    COUNTRY_NAME varchar(40),
    REGION_ID integer, 
    foreign key (REGION_ID) references regions (REGION_ID)
);

create table locations (
	LOCATION_ID Integer primary key,
    STREET_ADDRESS varchar(25),
    POSTAL_CODE varchar(12),
    CITY varchar(30),
    STATE_PROVINCE varchar(12),
    COUNTRY_ID varchar(2),
    foreign key (COUNTRY_ID) references countries (country_id)
);

create table departments (
	DEPARTMENT_ID integer primary key,
    DEPARTMENT_NAME varchar(30),
    MANAGER_ID integer,
    LOCATION_ID integer,
    foreign key (LOCATION_ID) references locations (LOCATION_ID)
);

create table jobs (
	JOB_ID varchar(10) primary key,
    JOB_TITLE varchar(35),
    MIN_SALARY integer,
    MAX_SALARY integer
);

create table employees (
	EMPLOYEE_ID integer primary key,
    FIRST_NAME varchar(20),
    LAST_NAME varchar(25),
    EMAIL varchar(25),
    PHONE_NUMBER varchar(20),
    HIRE_DATE date,
    JOB_ID varchar(10),
    SALARY integer,
    COMMISSION_PCT integer,
    MANAGER_ID integer,
    DEPARTMENT_ID integer,
    foreign key (DEPARTMENT_ID) references departments (DEPARTMENT_ID),
    foreign key (JOB_ID) references jobs (JOB_ID)
);

create table job_history (
	EMPLOYEE_ID integer,
    START_DATE date,
    END_DATE date,
    JOB_ID varchar(20),
    DEPARTMENT_ID integer,
    primary key (EMPLOYEE_ID, START_DATE),
    foreign key (JOB_ID) references jobs (JOB_ID),
    foreign key (DEPARTMENT_ID) references departments (DEPARTMENT_ID)
);

create table job_grades (
	GRADE_LEVEL varchar(2) primary key,
    LOWEST_SAL integer,
    HIGHEST_SAL integer
);

insert into regions (REGION_ID, REGION_NAME) values (1, 'North America'), (2, 'Europe'), (3, 'Asia'), (4, 'Australia');

insert into countries (COUNTRY_ID, COUNTRY_NAME, REGION_ID) 
values ('US', 'United States', 1), ('CA', 'Canada', 1), ('FR', 'France', 2), ('DE', 'Germany', 2), ('CN', 'China', 3), ('JP', 'Japan', 3), ('AU', 'Australia', 4);

insert into locations (LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID)
values (1, '123 Main St', '12345', 'New York', 'NY', 'US'), 
(2, '456 Maple Ave', '67890', 'Toronto', 'ON', 'CA'),
(3, '789 Elm St', '54321', 'Paris', 'Île', 'FR'),
(4, '101 Oak St', '98765', 'Berlin', 'Berlin', 'DE'),
(5, '202 Pine St', '11223', 'Beijing', 'Beijing', 'CN'),
(6, '303 Cedar St', '33445', 'Tokyo', 'Tokyo', 'JP'),
(7, '404 Birch St', '55667', 'Sydney', 'NSW', 'AU');

insert into departments (DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
values (1, 'HR', 101, 1),
(2, 'Finance', 102, 2),
(3, 'Engineering', 103, 3),
(4, 'Sales', 104, 4),
(5, 'Marketing', 105, 5),
(6, 'Finance', 102, 6);

insert into jobs (JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) 
values ('HR_MGR', 'HR Manager', 50000, 100000),
('FIN_MGR', 'Finance Manager', 60000, 120000),
('ENG_MGR', 'Engineering Manager', 70000, 140000),
('SALES_MGR', 'Sales Manager', 55000, 110000),
('MKT_MGR', 'Marketing Manager', 65000, 130000);

insert into employees (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
values (101, 'John', 'Doe', 'john.doe@example.com', '123-456-7890', '2023-01-01', 'HR_MGR', 75000, 0, NULL, 1),
(102, 'Jane', 'Smith', 'jane.smith@example.com', '234-567-8901', '2023-02-01', 'FIN_MGR', 85000, 0, NULL, 2),
(103, 'Alice', 'Johnson', 'alice.johnson@example.com', '345-678-9012', '2023-03-01', 'ENG_MGR', 95000, 0, NULL, 3),
(104, 'Bob', 'Brown', 'bob.brown@example.com', '456-789-0123', '2023-04-01', 'SALES_MGR', 80000, 0, NULL, 4),
(105, 'Carol', 'Davis', 'carol.davis@example.com', '567-890-1234', '2023-05-01', 'MKT_MGR', 90000, 0, NULL, 5),
(106, 'Steven', 'King', 'steven.king@example.com', '442-724-9775', '2023-11-04', 'FIN_MGR', 65000, 0, NULL, 6);

insert into job_history (EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID)
values (101, '2022-01-01', '2022-12-31', 'HR_MGR', 1),
(102, '2022-02-01', '2022-12-31', 'FIN_MGR', 2),
(103, '2022-03-01', '2022-12-31', 'ENG_MGR', 3),
(104, '2022-04-01', '2022-12-31', 'SALES_MGR', 4),
(105, '2022-05-01', '2022-12-31', 'MKT_MGR', 5);

insert into job_grades (GRADE_LEVEL, LOWEST_SAL, HIGHEST_SAL) 
values ('A', 30000, 50000),
('B', 50001, 70000),
('C', 70001, 90000),
('D', 90001, 110000),
('E', 110001, 130000);

select l.LOCATION_ID, l.STREET_ADDRESS, l.CITY, l.STATE_PROVINCE, c.COUNTRY_NAME
FROM locations l inner join countries c where c.COUNTRY_ID = l.COUNTRY_ID;

select FIRST_NAME, LAST_NAME, DEPARTMENT_ID from employees;

select e.FIRST_NAME, e.LAST_NAME, e.JOB_ID, e.DEPARTMENT_ID
from employees e 
inner join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID
inner join locations l on d.LOCATION_ID = l.LOCATION_ID
where l.COUNTRY_ID = 'JP';




