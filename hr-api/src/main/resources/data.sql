-- Organizational Units
-- Root
INSERT INTO organizational_units (id, description, parent_id) VALUES (1, 'Company', NULL);

-- Level 1 - Main departments
INSERT INTO organizational_units (id, description, parent_id) VALUES (2, 'Sales', 1);
INSERT INTO organizational_units (id, description, parent_id) VALUES (3, 'Engineering', 1);
INSERT INTO organizational_units (id, description, parent_id) VALUES (4, 'Human Resources', 1);

-- Level 2 - Sub-departments
INSERT INTO organizational_units (id, description, parent_id) VALUES (5, 'Inside Sales', 2);
INSERT INTO organizational_units (id, description, parent_id) VALUES (6, 'Field Sales', 2);
INSERT INTO organizational_units (id, description, parent_id) VALUES (7, 'Backend', 3);
INSERT INTO organizational_units (id, description, parent_id) VALUES (8, 'Frontend', 3);
INSERT INTO organizational_units (id, description, parent_id) VALUES (9, 'Recruiting', 4);
INSERT INTO organizational_units (id, description, parent_id) VALUES (10, 'Payroll', 4);

-- Employees
-- Sales
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Alice', 'Smith', 'asmith', 'Sales Director', '1980-03-15', 2);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Bob', 'Johnson', 'bjohnson', 'Inside Sales Rep', '1992-07-22', 5);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Carol', 'Williams', 'cwilliams', 'Inside Sales Rep', '1995-11-08', 5);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('David', 'Brown', 'dbrown', 'Field Sales Rep', '1988-05-30', 6);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Emma', 'Jones', 'ejones', 'Field Sales Rep', '1990-09-12', 6);

-- Engineering
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Frank', 'Garcia', 'fgarcia', 'Engineering Manager', '1982-01-25', 3);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Grace', 'Miller', 'gmiller', 'Backend Developer', '1993-04-18', 7);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Henry', 'Davis', 'hdavis', 'Backend Developer', '1991-08-07', 7);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Ivy', 'Martinez', 'imartinez', 'Senior Backend Developer', '1987-12-03', 7);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Jack', 'Wilson', 'jwilson', 'Frontend Developer', '1994-06-20', 8);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Karen', 'Taylor', 'ktaylor', 'Frontend Developer', '1996-02-14', 8);

-- Human Resources
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Leo', 'Anderson', 'landerson', 'HR Director', '1979-10-28', 4);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Mia', 'Thomas', 'mthomas', 'Recruiter', '1993-03-09', 9);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Noah', 'Jackson', 'njackson', 'Senior Recruiter', '1989-07-16', 9);
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Olivia', 'White', 'owhite', 'Payroll Specialist', '1991-11-22', 10);

-- Executive at root level
INSERT INTO employees (first_name, last_name, user_id, position, date_of_birth, organizational_unit_id) VALUES ('Peter', 'Harris', 'pharris', 'CEO', '1975-05-01', 1);