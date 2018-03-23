DROP TABLE IF EXISTS department;
CREATE TABLE department (
 departmentId INT NOT NULL AUTO_INCREMENT,
 departmentName VARCHAR(255) NOT NULL,
 departmentDescription VARCHAR(255) NULL,
 PRIMARY KEY (departmentId)
);
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
 employeeId INT NOT NULL AUTO_INCREMENT,
 employeeName VARCHAR(255) NOT NULL,
 employeeEmail VARCHAR(255) NOT NULL,
 employeeSalary DECIMAL(10,2) NOT NULL DEFAULT 0,
 departmentId INT NOT NULL,
 PRIMARY KEY (employeeId)
);
ALTER TABLE employee ADD CONSTRAINT fk_dep_emp FOREIGN KEY (departmentId) REFERENCES department (departmentId);